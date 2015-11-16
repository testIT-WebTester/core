package info.novatec.testit.webtester.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;

import info.novatec.testit.webtester.AbstractPageObjectTest;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsDisabledException;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsInvisibleException;
import info.novatec.testit.webtester.eventsystem.events.pageobject.TextAppendedEvent;
import info.novatec.testit.webtester.eventsystem.events.pageobject.TextClearedEvent;
import info.novatec.testit.webtester.eventsystem.events.pageobject.TextSetEvent;


public class TextFieldTest extends AbstractPageObjectTest {

    @Captor
    ArgumentCaptor<TextClearedEvent> textClearedCaptor;
    @Captor
    ArgumentCaptor<TextSetEvent> textSetCaptor;
    @Captor
    ArgumentCaptor<TextAppendedEvent> textAppendedCaptor;

    @InjectMocks
    TextField cut;

    /* getting of text value */

    @Test
    public void testThatTextIsRetrievedFromTheCorrectAttribute() {
        doReturn("foo").when(webElement).getAttribute("value");
        String text = cut.getText();
        assertThat(text, is(equalTo("foo")));
    }

    @Test
    public void testThatEmptyStringIsReturnedIfNoTextValueIsAvailable() {
        doReturn(null).when(webElement).getAttribute("value");
        String text = cut.getText();
        assertThat(text, is(StringUtils.EMPTY));
    }

    /* clearing text */

    @Test
    public void testThatClearingTextDelegatesToCorrectWebElementMethods() {
        cut.clearText();
        verify(webElement).clear();
    }

    @Test
    public void testThatClearingTextFiresCorrectEvent() {

        stubWebElementBeforeAndAfterTexts("some value", "");

        cut.clearText();

        verify(listener).eventOccurred(textClearedCaptor.capture());

        TextClearedEvent event = textClearedCaptor.getValue();
        assertThat(event.getBefore(), is("some value"));
        assertThat(event.getAfter(), is(""));

    }

    @Test
    public void testThatClearingTextStepsAreExecutedInOrder() {

        cut.clearText();

        InOrder inOrder = inOrder(webElement, listener);
        inOrder.verify(webElement).clear();
        inOrder.verify(listener).eventOccurred(any(TextClearedEvent.class));

    }

    @Test(expected = PageObjectIsInvisibleException.class)
    public void testThatClearingTextOnInvisibleFieldThrowsException() {
        elementIsInvisible();
        cut.clearText();
    }

    @Test(expected = PageObjectIsDisabledException.class)
    public void testThatClearingTextOnDisabledFieldThrowsException() {
        elementIsDisabled();
        cut.clearText();
    }

    /* setting text */

    @Test
    public void testThatSettingTextDelegatesToCorrectWebElementMethods() {

        cut.setText("foo");

        InOrder inOrder = inOrder(webElement);
        inOrder.verify(webElement).clear();
        inOrder.verify(webElement).sendKeys("foo");

    }

    @Test
    public void testThatSettingTextFiresCorrectEvent() {

        stubWebElementBeforeAndAfterTexts("some value", "value is too");

        cut.setText("value is too long");

        verify(listener).eventOccurred(textSetCaptor.capture());

        TextSetEvent event = textSetCaptor.getValue();
        assertThat(event.getBefore(), is("some value"));
        assertThat(event.getAfter(), is("value is too"));
        assertThat(event.getTextToSet(), is("value is too long"));

    }

    @Test
    public void testThatSettingTextStepsAreExecutedInOrder() {

        cut.setText("foo");

        InOrder inOrder = inOrder(webElement, listener);
        inOrder.verify(webElement).clear();
        inOrder.verify(webElement).sendKeys("foo");
        inOrder.verify(listener).eventOccurred(any(TextSetEvent.class));

    }

    @Test(expected = PageObjectIsInvisibleException.class)
    public void testThatSettingTextOnInvisibleFieldThrowsException() {
        elementIsInvisible();
        cut.setText("foo");
    }

    @Test(expected = PageObjectIsDisabledException.class)
    public void testThatSettingTextOnDisabledFieldThrowsException() {
        elementIsDisabled();
        cut.setText("foo");
    }

    /* appending text */

    @Test
    public void testThatAppendingTextDelegatesToCorrectWebElementMethods() {

        cut.appendText("foo");

        verify(webElement, never()).clear();
        verify(webElement).sendKeys("foo");

    }

    @Test
    public void testThatAppendingTextFiresCorrectEvent() {

        stubWebElementBeforeAndAfterTexts("some value", "some values are too");

        cut.appendText("s are too long");

        verify(listener).eventOccurred(textAppendedCaptor.capture());

        TextAppendedEvent event = textAppendedCaptor.getValue();
        assertThat(event.getBefore(), is("some value"));
        assertThat(event.getAfter(), is("some values are too"));
        assertThat(event.getTextToAppend(), is("s are too long"));

    }

    @Test
    public void testThatAppendingTextStepsAreExecutedInOrder() {

        cut.appendText("foo");

        InOrder inOrder = inOrder(webElement, listener);
        inOrder.verify(webElement).sendKeys("foo");
        inOrder.verify(listener).eventOccurred(any(TextAppendedEvent.class));

    }

    @Test(expected = PageObjectIsInvisibleException.class)
    public void testThatAppendingTextOnInvisibleFieldThrowsException() {
        elementIsInvisible();
        cut.appendText("foo");
    }

    @Test(expected = PageObjectIsDisabledException.class)
    public void testThatAppendingTextOnDisabledFieldThrowsException() {
        elementIsDisabled();
        cut.appendText("foo");
    }

    /* correctness of class */

    @Test
    public void testCorrectnessOfClassForWebElement_inputTag() {
        stubWebElementTagAndType("input", null);
        assertThatCorrectnessOfClassIs(true);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_inputTag_emptyType() {
        stubWebElementTagAndType("input", "");
        assertThatCorrectnessOfClassIs(true);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_inputTag_textType() {
        stubWebElementTagAndType("input", "text");
        assertThatCorrectnessOfClassIs(true);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_nonInputTag() {
        stubWebElementTagAndType("other", null);
        assertThatCorrectnessOfClassIs(false);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_inputTag_nonTextFieldType() {
        stubWebElementTagAndType("input", "other");
        assertThatCorrectnessOfClassIs(false);
    }

    /* utilities */

    private void stubWebElementBeforeAndAfterTexts(String before, String after) {
        when(webElement.getAttribute("value")).thenReturn(before, after);
    }

    private void assertThatCorrectnessOfClassIs(boolean expected) {
        assertThat(cut.isCorrectClassForWebElement(webElement), is(expected));
    }

}
