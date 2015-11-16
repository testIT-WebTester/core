package info.novatec.testit.webtester.utils.conditions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static utils.TestObjectFactory.selectable;

import org.junit.Test;

import info.novatec.testit.webtester.api.pageobjects.traits.Selectable;


public class SelectedTest {

    Selected cut = new Selected();

    @Test
    public void testThatSelectedElementEvaluatesToTrue() {
        Selectable selectable = selectable(true);
        assertThat(cut.apply(selectable), is(true));
    }

    @Test
    public void testThatNotSelectedElementEvaluatesToFalse() {
        Selectable selectable = selectable(false);
        assertThat(cut.apply(selectable), is(false));
    }

}
