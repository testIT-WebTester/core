package info.novatec.testit.webtester.utils.conditions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static utils.TestObjectFactory.select;

import org.junit.Test;

import info.novatec.testit.webtester.pageobjects.Select;


public class SelectedValueTest {

    static final String VAL1 = "value1";
    static final String VAL2 = "value2";
    static final String VAL3 = "value3";

    SelectedValue cut = new SelectedValue(VAL1);

    @Test
    public void testThatMatchingSelectedValueEvaluatesToTrue_SingleSelection() {
        Select select = select().withSelectedValues(VAL1).build();
        assertThat(cut.apply(select), is(true));
    }

    @Test
    public void testThatMatchingSelectedValueEvaluatesToTrue_MultiSelection() {
        Select select = select().withSelectedValues(VAL2, VAL1).build();
        assertThat(cut.apply(select), is(true));
    }

    @Test
    public void testThatNotMatchingSelectedValueEvaluatesToFalse() {
        Select select = select().withSelectedValues(VAL2, VAL3).build();
        assertThat(cut.apply(select), is(false));
    }

    @Test
    public void testThatNoSelectedValueEvaluatesToFalse() {
        Select select = select().withoutSelectedValues().build();
        assertThat(cut.apply(select), is(false));
    }

}
