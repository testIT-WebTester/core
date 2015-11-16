package info.novatec.testit.webtester.utils.conditions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static utils.TestObjectFactory.pageObject;

import org.junit.Test;

import info.novatec.testit.webtester.pageobjects.PageObject;


public class InteractableTest {

    Interactable cut = new Interactable();

    @Test
    public void testThatEnabledVisiblePageObjectEvaluatesToTrue() {
        PageObject pageObject = pageObject().enabled().visible().build();
        assertThat(cut.apply(pageObject), is(true));
    }

    @Test
    public void testThatEnabledInvisiblePageObjectEvaluatesToFalse() {
        PageObject pageObject = pageObject().enabled().invisible().build();
        assertThat(cut.apply(pageObject), is(false));
    }

    @Test
    public void testThatDisabledVisiblePageObjectEvaluatesToFalse() {
        PageObject pageObject = pageObject().disabled().visible().build();
        assertThat(cut.apply(pageObject), is(false));
    }

    @Test
    public void testThatDisabledInvisiblePageObjectEvaluatesToFalse() {
        PageObject pageObject = pageObject().disabled().invisible().build();
        assertThat(cut.apply(pageObject), is(false));
    }

}
