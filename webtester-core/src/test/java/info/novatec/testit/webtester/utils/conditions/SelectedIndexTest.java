package info.novatec.testit.webtester.utils.conditions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static utils.TestObjectFactory.select;

import org.junit.Test;

import info.novatec.testit.webtester.pageobjects.Select;


public class SelectedIndexTest {

    static final int IDX1 = 0;
    static final int IDX2 = 1;
    static final int IDX3 = 2;

    SelectedIndex cut = new SelectedIndex(IDX1);

    @Test
    public void testThatMatchingSelectedIndexEvaluatesToTrue_SingleSelection() {
        Select select = select().withSelectedIndices(IDX1).build();
        assertThat(cut.apply(select), is(true));
    }

    @Test
    public void testThatMatchingSelectedIndexEvaluatesToTrue_MultiSelection() {
        Select select = select().withSelectedIndices(IDX2, IDX1).build();
        assertThat(cut.apply(select), is(true));
    }

    @Test
    public void testThatNotMatchingSelectedIndexEvaluatesToFalse() {
        Select select = select().withSelectedIndices(IDX2, IDX3).build();
        assertThat(cut.apply(select), is(false));
    }

    @Test
    public void testThatNoSelectedIndexEvaluatesToFalse() {
        Select select = select().withoutSelectedIndices().build();
        assertThat(cut.apply(select), is(false));
    }

}
