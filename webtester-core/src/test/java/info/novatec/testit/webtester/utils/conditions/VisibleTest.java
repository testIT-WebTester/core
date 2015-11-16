package info.novatec.testit.webtester.utils.conditions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static utils.TestObjectFactory.pageObject;

import org.junit.Test;

import info.novatec.testit.webtester.pageobjects.PageObject;


public class VisibleTest {

    Visible cut = new Visible();

    @Test
    public void testThatVisiblePageObjectEvaluatesToTrue() {
        PageObject pageObject = pageObject().visible().build();
        assertThat(cut.apply(pageObject), is(true));
    }

    @Test
    public void testThatInvisiblePageObjectEvaluatesToFalse() {
        PageObject pageObject = pageObject().invisible().build();
        assertThat(cut.apply(pageObject), is(false));
    }

}
