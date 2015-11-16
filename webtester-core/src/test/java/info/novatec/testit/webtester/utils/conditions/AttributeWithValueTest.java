package info.novatec.testit.webtester.utils.conditions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static utils.TestObjectFactory.pageObject;

import org.junit.Test;

import info.novatec.testit.webtester.pageobjects.PageObject;


public class AttributeWithValueTest {

    AttributeWithValue cut = new AttributeWithValue("attribute", "value");

    @Test
    public void testThatMatchingAttributeEvaluatesToTrue() {
        PageObject pageObject = pageObject().withAttribute("attribute", "value").build();
        assertThat(cut.apply(pageObject), is(true));
    }

    @Test
    public void testThatNonMatchingExistingAttributeEvaluatesToFalse() {
        PageObject pageObject = pageObject().withAttribute("attribute", "otherValue").build();
        assertThat(cut.apply(pageObject), is(false));
    }

    @Test
    public void testThatNonExistingAttributeEvaluatesToFalse() {
        PageObject pageObject = pageObject().withoutAttribute("attribute").build();
        assertThat(cut.apply(pageObject), is(false));
    }

}
