package info.novatec.testit.webtester.pageobjects.html5;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import info.novatec.testit.webtester.AbstractPageObjectTest;
import info.novatec.testit.webtester.eventsystem.events.pageobject.UrlSetEvent;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;


public class UrlFieldTest extends AbstractPageObjectTest {

    @InjectMocks
    UrlField cut;

    /* set value */

    @Test
    public void testThatSettingValueExecutesSameProcedureAsSetUrl() {

        cut.setUrl("http://www.google.de");

        InOrder inOrder = inOrder(browser, listener);
        inOrder.verify(browser).executeJavaScript("arguments[0].value=''", webElement);
        inOrder.verify(browser).executeJavaScript("arguments[0].value='http://www.google.de'", webElement);
        inOrder.verify(listener).eventOccurred(any(UrlSetEvent.class));

    }

    /* correctness of class */

    @Test
    public void testCorrectnessOfClassForWebElement_inputTag_urlType() {
        stubWebElementTagAndType("input", "url");
        assertThatCorrectnessOfClassIs(true);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_nonInputTag() {
        stubWebElementTagAndType("other", null);
        assertThatCorrectnessOfClassIs(false);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_inputTag_nonUrlFieldType() {
        stubWebElementTagAndType("input", "other");
        assertThatCorrectnessOfClassIs(false);
    }

    /* utilities */
    private void assertThatCorrectnessOfClassIs(boolean expected) {
        assertThat(cut.isCorrectClassForWebElement(webElement), is(expected));
    }
}
