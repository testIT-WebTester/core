package info.novatec.testit.webtester.utils.conditions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static utils.TestObjectFactory.pageObject;

import org.junit.Test;

import info.novatec.testit.webtester.pageobjects.PageObject;


public class EditableTest {

    Editable cut = new Editable();

    @Test
    public void testThatEnabledVisibleWritableElementEvaluatesToTrue() {
        PageObject pageObject = pageObject().editable().build();
        assertThat(cut.apply(pageObject), is(true));
    }

    @Test
    public void testThatDisabledPageObjectEvaluatesToFalse() {
        PageObject pageObject = pageObject().editable().disabled().build();
        assertThat(cut.apply(pageObject), is(false));
    }

    @Test
    public void testThatInvisiblePageObjectEvaluatesToFalse() {
        PageObject pageObject = pageObject().editable().invisible().build();
        assertThat(cut.apply(pageObject), is(false));
    }

    @Test
    public void testThatReadOnlyPageObjectEvaluatesToFalse() {
        PageObject pageObject = pageObject().editable().readOnly().build();
        assertThat(cut.apply(pageObject), is(false));
    }

}
