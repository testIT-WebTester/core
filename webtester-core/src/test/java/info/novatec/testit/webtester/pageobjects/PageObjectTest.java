package info.novatec.testit.webtester.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;

import info.novatec.testit.webtester.AbstractPageObjectTest;
import info.novatec.testit.webtester.eventsystem.events.pageobject.ClickedEvent;


public class PageObjectTest extends AbstractPageObjectTest {

    @InjectMocks
    PageObject cut;

    @Test
    public void testThatClickDelegatesToWebElementAndFiresEvent() {

        cut.click();

        InOrder inOrder = inOrder(webElement, listener);
        inOrder.verify(webElement).click();
        inOrder.verify(listener).eventOccurred(any(ClickedEvent.class));
        inOrder.verifyNoMoreInteractions();

    }

    @Test
    public void testThatGetVisibleTextDelegatesToWebElementAndFiresEvent() {
        doReturn("foo").when(webElement).getText();
        String visibleText = cut.getVisibleText();
        assertThat(visibleText, is("foo"));
    }

}
