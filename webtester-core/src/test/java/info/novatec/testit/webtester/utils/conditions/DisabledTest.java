package info.novatec.testit.webtester.utils.conditions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static utils.TestObjectFactory.pageObject;

import org.junit.Test;

import info.novatec.testit.webtester.pageobjects.PageObject;


public class DisabledTest {

    Disabled cut = new Disabled();

    @Test
    public void testThatDisabledPageObjectEvaluatesToTrue() {
        PageObject pageObject = pageObject().disabled().build();
        assertThat(cut.apply(pageObject), is(true));
    }

    @Test
    public void testThatEnabledPageObjectEvaluatesToFalse() {
        PageObject pageObject = pageObject().enabled().build();
        assertThat(cut.apply(pageObject), is(false));
    }

}
