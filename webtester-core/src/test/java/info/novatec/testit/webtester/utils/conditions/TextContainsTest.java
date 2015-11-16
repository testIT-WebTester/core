package info.novatec.testit.webtester.utils.conditions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static utils.TestObjectFactory.hasText;

import org.junit.Test;

import info.novatec.testit.webtester.api.pageobjects.traits.HasText;


public class TextContainsTest {

    TextContains cut = new TextContains("foo");

    @Test
    public void testThatMatchingPartialTextEvaluatesToTrue() {
        HasText hasText = hasText("foo bar");
        assertThat(cut.apply(hasText), is(true));
    }

    @Test
    public void testThatNotMatchingPartialTextEvaluatesToFalse() {
        HasText hasText = hasText("barf");
        assertThat(cut.apply(hasText), is(false));
    }

}
