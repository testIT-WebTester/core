package info.novatec.testit.webtester.utils.conditions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static utils.TestObjectFactory.select;

import org.junit.Test;

import info.novatec.testit.webtester.pageobjects.Select;


public class SelectedValuesTest {

    static final String VAL1 = "value1";
    static final String VAL2 = "value2";
    static final String VAL3 = "value3";

    SelectedValues cut = new SelectedValues(VAL1, VAL2);

    @Test
    public void testThatExactlyMatchingSelectedValuesEvaluatesToTrue() {
        Select select = select().withSelectedValues(VAL1, VAL2).build();
        assertThat(cut.apply(select), is(true));
    }

    @Test
    public void testThatMoreSelectedValuesThenCheckedEvaluatesToTrue() {
        Select select = select().withSelectedValues(VAL1, VAL2, VAL3).build();
        assertThat(cut.apply(select), is(true));
    }

    @Test
    public void testThatLessSelectedValuesThenCheckedEvaluatesToFalse() {
        Select select = select().withSelectedValues(VAL1).build();
        assertThat(cut.apply(select), is(false));
    }

    @Test
    public void testThatDifferentSelectedValuesThenCheckedEvaluatesToFalse() {
        Select select = select().withSelectedValues(VAL1, VAL3).build();
        assertThat(cut.apply(select), is(false));
    }

    @Test
    public void testThatNoSelectedValuesEvaluatesToFalse() {
        Select select = select().withoutSelectedValues().build();
        assertThat(cut.apply(select), is(false));
    }

}
