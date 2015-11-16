package info.novatec.testit.webtester.utils.conditions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static utils.TestObjectFactory.pageObject;

import org.junit.Test;

import info.novatec.testit.webtester.pageobjects.PageObject;


public class PresentTest {

    Present cut = new Present();

    @Test
    public void testThatExistingWebElementEvaluatesToTrue() {
        PageObject pageObject = pageObject().build();
        assertThat(cut.apply(pageObject), is(true));
    }

    @Test
    public void testThatNonExistingWebElementEvaluatesToFalse() {
        PageObject pageObject = pageObject().throwsNoSuchElementException().build();
        assertThat(cut.apply(pageObject), is(false));
    }

}
