package info.novatec.testit.webtester.utils.conditions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static utils.TestObjectFactory.pageObject;

import org.junit.Test;

import info.novatec.testit.webtester.pageobjects.PageObject;


public class InvisibleTest {

    Invisible cut = new Invisible();

    @Test
    public void testThatInvisiblePageObjectEvaluatesToTrue() {
        PageObject pageObject = pageObject().invisible().build();
        assertThat(cut.apply(pageObject), is(true));
    }

    @Test
    public void testThatVisiblePageObjectEvaluatesToFalse() {
        PageObject pageObject = pageObject().visible().build();
        assertThat(cut.apply(pageObject), is(false));
    }

}
