package info.novatec.testit.webtester.utils.conditions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static utils.TestObjectFactory.pageObject;

import org.junit.Test;

import info.novatec.testit.webtester.pageobjects.PageObject;


public class EnabledTest {

    Enabled cut = new Enabled();

    @Test
    public void testThatEnabledPageObjectEvaluatesToTrue() {
        PageObject pageObject = pageObject().enabled().build();
        assertThat(cut.apply(pageObject), is(true));
    }

    @Test
    public void testThatDisabledPageObjectEvaluatesToFalse() {
        PageObject pageObject = pageObject().disabled().build();
        assertThat(cut.apply(pageObject), is(false));
    }

}
