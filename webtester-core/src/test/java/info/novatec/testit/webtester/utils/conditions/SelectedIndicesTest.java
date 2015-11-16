package info.novatec.testit.webtester.utils.conditions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static utils.TestObjectFactory.select;

import org.junit.Test;

import info.novatec.testit.webtester.pageobjects.Select;


public class SelectedIndicesTest {

    static final int IDX1 = 0;
    static final int IDX2 = 1;
    static final int IDX3 = 2;

    SelectedIndices cut = new SelectedIndices(IDX1, IDX2);

    @Test
    public void testThatExactlyMatchingSelectedIndicesEvaluatesToTrue() {
        Select select = select().withSelectedIndices(IDX1, IDX2).build();
        assertThat(cut.apply(select), is(true));
    }

    @Test
    public void testThatMoreSelectedIndicesThenCheckedEvaluatesToTrue() {
        Select select = select().withSelectedIndices(IDX1, IDX2, IDX3).build();
        assertThat(cut.apply(select), is(true));
    }

    @Test
    public void testThatLessSelectedIndicesThenCheckedEvaluatesToFalse() {
        Select select = select().withSelectedIndices(IDX1).build();
        assertThat(cut.apply(select), is(false));
    }

    @Test
    public void testThatDifferentSelectedIndicesThenCheckedEvaluatesToFalse() {
        Select select = select().withSelectedIndices(IDX1, IDX3).build();
        assertThat(cut.apply(select), is(false));
    }

    @Test
    public void testThatNoSelectedIndicesEvaluatesToFalse() {
        Select select = select().withoutSelectedIndices().build();
        assertThat(cut.apply(select), is(false));
    }

}
