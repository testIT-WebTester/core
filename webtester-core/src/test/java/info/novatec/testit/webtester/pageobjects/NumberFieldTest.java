package info.novatec.testit.webtester.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;

import info.novatec.testit.webtester.AbstractPageObjectTest;
import info.novatec.testit.webtester.eventsystem.events.pageobject.TextSetEvent;


public class NumberFieldTest extends AbstractPageObjectTest {

    @InjectMocks
    NumberField cut;

    /* get value */

    @Test
    public void testThatNumericValueCanBeReturendAsInteger() {
        stubNumberField("42");
        assertThat(cut.getValue(), is(42L));
    }

    @Test(expected = NumberFormatException.class)
    public void testThatAlphaNumericValueCantBeReturendAsInteger() {
        stubNumberField("a42");
        cut.getValue();
    }

    @Test
    public void testThatEmptyValueCantBeReturendAsInteger() {
        stubNumberField("");
        assertThat(cut.getValue(), is(nullValue()));
    }

    @Test
    public void testThatBlankValueCantBeReturendAsInteger() {
        stubNumberField(" ");
        assertThat(cut.getValue(), is(nullValue()));
    }

    @Test
    public void testThatNullValueCantBeReturendAsInteger() {
        stubNumberField(null);
        assertThat(cut.getValue(), is(nullValue()));
    }

    /* set value */

    @Test
    public void testThatSettingValueExecutesSamePrcedureAsSettingText() {

        cut.setValue(42L);

        InOrder inOrder = inOrder(webElement, listener);
        inOrder.verify(webElement).clear();
        inOrder.verify(webElement).sendKeys("42");
        inOrder.verify(listener).eventOccurred(any(TextSetEvent.class));

    }

    /* correctness of class */

    @Test
    public void testCorrectnessOfClassForWebElement_inputTag_numberType() {
        stubWebElementTagAndType("input", "number");
        assertThatCorrectnessOfClassIs(true);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_nonInputTag() {
        stubWebElementTagAndType("other", null);
        assertThatCorrectnessOfClassIs(false);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_inputTag_nonNumberFieldType() {
        stubWebElementTagAndType("input", "other");
        assertThatCorrectnessOfClassIs(false);
    }

    /* utilities */

    private void stubNumberField(String value) {
        doReturn(value).when(webElement).getAttribute("value");
    }

    private void assertThatCorrectnessOfClassIs(boolean expected) {
        assertThat(cut.isCorrectClassForWebElement(webElement), is(expected));
    }

}
