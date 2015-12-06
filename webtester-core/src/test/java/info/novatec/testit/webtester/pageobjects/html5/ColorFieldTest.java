package info.novatec.testit.webtester.pageobjects.html5;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import info.novatec.testit.webtester.AbstractPageObjectTest;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsDisabledException;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsInvisibleException;
import info.novatec.testit.webtester.eventsystem.events.pageobject.ColorClearedEvent;
import info.novatec.testit.webtester.eventsystem.events.pageobject.ColorSetEvent;
import info.novatec.testit.webtester.eventsystem.events.pageobject.TextSetEvent;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.openqa.selenium.support.Color;


public class ColorFieldTest extends AbstractPageObjectTest {
    @Captor
    ArgumentCaptor<ColorClearedEvent> colorClearedCaptor;
    @Captor
    ArgumentCaptor<ColorSetEvent> colorSetCaptor;

    @InjectMocks
    ColorField cut;

    /* getting of text value */

    @Test
    public void testThatColorIsRetrievedFromTheCorrectAttribute() {
        doReturn("#efefef").when(webElement).getAttribute("value");
        String color = cut.getColorAsString();
        assertThat(color, is(equalTo("#efefef")));
    }

    @Test
    public void testThatEmptyStringIsReturnedIfNoTextValueIsAvailable() {
        doReturn(null).when(webElement).getAttribute("value");
        String color = cut.getColorAsString();
        assertThat(color, is(""));
    }

    /* clearing color */

    @Test
    public void testThatClearingColorDelegatesToCorrectWebElementMethods() {
        cut.reset();
        verify(browser).executeJavaScript("arguments[0].value=''", webElement);
    }

    @Test
    public void testThatClearingColorFiresCorrectEvent() {
        stubWebElementBeforeAndAfterTexts("#efefef", "#000000");

        cut.reset();
        verify(listener).eventOccurred(colorClearedCaptor.capture());
        ColorClearedEvent event = colorClearedCaptor.getValue();
        assertThat(event.getBefore().asHex(), is("#efefef"));
        assertThat(event.getAfter().asHex(), is("#000000"));

    }

    @Test
    public void testThatClearingColorStepsAreExecutedInOrder() {

        cut.reset();
        InOrder inOrder = inOrder(browser, listener);
        inOrder.verify(browser).executeJavaScript("arguments[0].value=''", webElement);
        inOrder.verify(listener).eventOccurred(any(ColorClearedEvent.class));
    }

    @Test(expected = PageObjectIsInvisibleException.class)
    public void testThatClearingTextOnInvisibleFieldThrowsException() {
        elementIsInvisible();
        cut.reset();
    }

    @Test(expected = PageObjectIsDisabledException.class)
    public void testThatClearingColorOnDisabledFieldThrowsException() {
        elementIsDisabled();
        cut.reset();
    }

    /* setting text */

    @Test
    public void testThatSettingColorDelegatesToCorrectWebElementMethods() {

        cut.setColor(Color.fromString("#efefef"));

        verify(browser).executeJavaScript("arguments[0].value='#efefef'", webElement);
    }

    @Test
    public void testThatSettingColorFiresCorrectEvent() {

        stubWebElementBeforeAndAfterTexts("#efefef", "#ee0000");

        cut.setColor(Color.fromString("#ee0000"));

        verify(listener).eventOccurred(colorSetCaptor.capture());

        ColorSetEvent event = colorSetCaptor.getValue();
        assertThat(event.getBefore().asHex(), is("#efefef"));
        assertThat(event.getAfter().asHex(), is("#ee0000"));
        assertThat(event.getTextToSet().asHex(), is("#ee0000"));

    }

    @Test
    public void testThatSettingColorStepsAreExecutedInOrder() {

        cut.setColor(Color.fromString("#efefef"));

        InOrder inOrder = inOrder(browser, listener);
        inOrder.verify(browser).executeJavaScript("arguments[0].value='#efefef'", webElement);
        inOrder.verify(listener).eventOccurred(any(TextSetEvent.class));

    }

    @Test(expected = PageObjectIsInvisibleException.class)
    public void testThatSettingColorOnInvisibleFieldThrowsException() {
        elementIsInvisible();
        cut.setColor(Color.fromString("#efefef"));
    }

    @Test(expected = PageObjectIsDisabledException.class)
    public void testThatSettingColorOnDisabledFieldThrowsException() {
        elementIsDisabled();
        cut.setColor(Color.fromString("#efefef"));
    }

    /* correctness of class */

    @Test
    public void testCorrectnessOfClassForWebElement_inputTag_ColorType() {
        stubWebElementTagAndType("input", "color");
        assertThatCorrectnessOfClassIs(true);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_nonInputTag() {
        stubWebElementTagAndType("other", null);
        assertThatCorrectnessOfClassIs(false);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_inputTag_nonColorFieldType() {
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
