package info.novatec.testit.webtester.pageobjects.html5;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;
import info.novatec.testit.webtester.AbstractPageObjectTest;
import info.novatec.testit.webtester.eventsystem.events.pageobject.UrlSetEvent;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;


public class RangeFieldTest extends AbstractPageObjectTest {

    @InjectMocks
    RangeField cut;

    /* get value */

    @Test
    public void testThatNumericValueCanBeReturendAsInteger() {
        stubRangeField("42");
        assertThat(cut.getRange(), is(42));
    }

    @Test(expected = NumberFormatException.class)
    public void testThatAlphaNumericValueCantBeReturendAsInteger() {
        stubRangeField("a42");
        cut.getRange();
    }

    /* set value */

    @Test
    public void testThatSettingValueExecutesSameProcedureAsSetRange() {

        cut.setRange(42);

        InOrder inOrder = inOrder(browser, listener);
        inOrder.verify(browser).executeJavaScript("arguments[0].value='0'", webElement);
        inOrder.verify(browser).executeJavaScript("arguments[0].value='42'", webElement);
        inOrder.verify(listener).eventOccurred(any(UrlSetEvent.class));

    }

    /* correctness of class */

    @Test
    public void testCorrectnessOfClassForWebElement_inputTag_rangeType() {
        stubWebElementTagAndType("input", "range");
        assertThatCorrectnessOfClassIs(true);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_nonInputTag() {
        stubWebElementTagAndType("other", null);
        assertThatCorrectnessOfClassIs(false);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_inputTag_nonRangeFieldType() {
        stubWebElementTagAndType("input", "other");
        assertThatCorrectnessOfClassIs(false);
    }

    /* utilities */

    private void stubRangeField(String value) {
        doReturn(value).when(webElement).getAttribute("value");
    }

    private void assertThatCorrectnessOfClassIs(boolean expected) {
        assertThat(cut.isCorrectClassForWebElement(webElement), is(expected));
    }
}
