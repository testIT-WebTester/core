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
import info.novatec.testit.webtester.eventsystem.events.pageobject.EmailClearedEvent;
import info.novatec.testit.webtester.eventsystem.events.pageobject.EmailSetEvent;
import info.novatec.testit.webtester.eventsystem.events.pageobject.TextClearedEvent;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;


public class EmailFieldTest extends AbstractPageObjectTest {
    @Captor
    ArgumentCaptor<EmailClearedEvent> emailClearedCaptor;
    @Captor
    ArgumentCaptor<EmailSetEvent> textSetCaptor;

    @InjectMocks
    EmailField cut;

    /* getting of text value */

    @Test
    public void testThatColorIsRetrievedFromTheCorrectAttribute() {
        doReturn("test@testIt.de").when(webElement).getAttribute("value");
        String emailAdress = cut.getEmail();
        assertThat(emailAdress, is(equalTo("test@testIt.de")));
    }

    @Test
    public void testThatEmptyStringIsReturnedIfNoTextValueIsAvailable() {
        doReturn(null).when(webElement).getAttribute("value");
        String emailAdress = cut.getEmail();
        assertThat(emailAdress, is(""));
    }

    /* clearing emailField */

    @Test
    public void testThatClearingColorDelegatesToCorrectWebElementMethods() {
        cut.clear();
    }

    @Test
    public void testThatClearingColorFiresCorrectEvent() {

        stubWebElementBeforeAndAfterTexts("some value", "");
        cut.clear();
        verify(listener).eventOccurred(emailClearedCaptor.capture());
        EmailClearedEvent event = emailClearedCaptor.getValue();
        assertThat(event.getBefore(), is("some value"));
        assertThat(event.getAfter(), is(""));

    }

    @Test
    public void testThatClearingColorStepsAreExecutedInOrder() {

        cut.clear();
        InOrder inOrder = inOrder(webElement, listener);
        inOrder.verify(listener).eventOccurred(any(TextClearedEvent.class));

    }

    @Test(expected = PageObjectIsInvisibleException.class)
    public void testThatClearingTextOnInvisibleFieldThrowsException() {
        elementIsInvisible();
        cut.clear();
    }

    @Test(expected = PageObjectIsDisabledException.class)
    public void testThatClearingColorOnDisabledFieldThrowsException() {
        elementIsDisabled();
        cut.clear();
    }

    /* correctness of class */

    @Test
    public void testCorrectnessOfClassForWebElement_inputTag_EmailType() {
        stubWebElementTagAndType("input", "email");
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
