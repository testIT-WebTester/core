package info.novatec.testit.webtester.utils.conditions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static utils.TestObjectFactory.pageObject;

import org.junit.Test;

import info.novatec.testit.webtester.pageobjects.PageObject;


public class VisibleTextEqualsTest {

    VisibleTextEquals cut = new VisibleTextEquals("foo");

    @Test
    public void testThatMatchingVisibleTextEvaluatesToTrue() {
        PageObject pageObject = pageObject().withVisibleText("foo").build();
        assertThat(cut.apply(pageObject), is(true));
    }

    @Test
    public void testThatNotMatchingVisibleTextEvaluatesToFalse() {
        PageObject pageObject = pageObject().withVisibleText("bar").build();
        assertThat(cut.apply(pageObject), is(false));
    }

}
