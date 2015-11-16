package info.novatec.testit.webtester.utils.conditions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static utils.TestObjectFactory.hasLabel;

import org.junit.Test;

import info.novatec.testit.webtester.api.pageobjects.traits.HasLabel;


public class LabelTest {

    Label cut = new Label("foo");

    @Test
    public void testThatMatchingLabelEvaluatesToTrue() {
        HasLabel hasLabel = hasLabel("foo");
        assertThat(cut.apply(hasLabel), is(true));
    }

    @Test
    public void testThatNotMatchingLabelEvaluatesToFalse() {
        HasLabel hasLabel = hasLabel("bar");
        assertThat(cut.apply(hasLabel), is(false));
    }

}
