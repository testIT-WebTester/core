package info.novatec.testit.webtester.utils.conditions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static utils.TestObjectFactory.hasValue;

import org.junit.Test;

import info.novatec.testit.webtester.api.pageobjects.traits.HasValue;


public class ValueTest {

    Value<String> cut = new Value<String>("foo");

    @Test
    public void testThatMatchingValueEvaluatesToTrue() {
        HasValue<String> hasValue = hasValue("foo");
        assertThat(cut.apply(hasValue), is(true));
    }

    @Test
    public void testThatNotMatchingValueEvaluatesToFalse() {
        HasValue<String> hasValue = hasValue("bar");
        assertThat(cut.apply(hasValue), is(false));
    }

}
