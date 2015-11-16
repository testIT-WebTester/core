package info.novatec.testit.webtester.utils.conditions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static utils.TestObjectFactory.hasText;

import org.junit.Test;

import info.novatec.testit.webtester.api.pageobjects.traits.HasText;


public class TextEqualsTest {

    TextEquals cut = new TextEquals("foo");

    @Test
    public void testThatMatchingTextEvaluatesToTrue() {
        HasText hasText = hasText("foo");
        assertThat(cut.apply(hasText), is(true));
    }

    @Test
    public void testThatNotMatchingTextEvaluatesToFalse() {
        HasText hasText = hasText("bar");
        assertThat(cut.apply(hasText), is(false));
    }

}
