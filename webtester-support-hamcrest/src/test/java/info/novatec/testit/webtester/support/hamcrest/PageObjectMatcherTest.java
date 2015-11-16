package info.novatec.testit.webtester.support.hamcrest;

import static info.novatec.testit.webtester.support.hamcrest.PageObjectMatcher.enabled;
import static info.novatec.testit.webtester.support.hamcrest.PageObjectMatcher.hasAttributeWithValue;
import static info.novatec.testit.webtester.support.hamcrest.PageObjectMatcher.hasVisibleText;
import static info.novatec.testit.webtester.support.hamcrest.PageObjectMatcher.visible;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import info.novatec.testit.webtester.pageobjects.PageObject;


@RunWith(MockitoJUnitRunner.class)
public class PageObjectMatcherTest {

    static final String TEXT = "text";
    static final String ANOTHER_TEXT = "another text";
    static final String ATTRIBUTE = "attribute";
    static final String VALUE = "value";
    static final String ANOTHER_VALUE = "anotherValue";

    @Mock
    PageObject pageObject;

    @Before
    public void setUp() {
        doReturn(TEXT).when(pageObject).getVisibleText();
        doReturn(VALUE).when(pageObject).getAttribute(ATTRIBUTE);
    }

    /* hasAttributeWithValue */

    @Test
    public void hasAttributeWithValueTrueTest() {
        assertThat(pageObject, hasAttributeWithValue(ATTRIBUTE, VALUE));
    }

    @Test(expected = AssertionError.class)
    public void hasAttributeWithValueFalseTest() {
        assertThat(pageObject, hasAttributeWithValue(ATTRIBUTE, ANOTHER_VALUE));
    }

    @Test
    public void notHasAttributeWithValueTrueTest() {
        assertThat(pageObject, not(hasAttributeWithValue(ATTRIBUTE, ANOTHER_VALUE)));
    }

    @Test(expected = AssertionError.class)
    public void notHasAttributeWithValueFalseTest() {
        assertThat(pageObject, not(hasAttributeWithValue(ATTRIBUTE, VALUE)));
    }

    /* hasVisibleText */

    @Test
    public void hasVisibleTextTrueTest() {
        assertThat(pageObject, hasVisibleText(TEXT));
    }

    @Test(expected = AssertionError.class)
    public void hasVisibleTextFalseTest() {
        assertThat(pageObject, hasVisibleText(ANOTHER_TEXT));
    }

    @Test
    public void notHasVisibleTextTrueTest() {
        assertThat(pageObject, not(hasVisibleText(ANOTHER_TEXT)));
    }

    @Test(expected = AssertionError.class)
    public void notHasVisibleTextFalseTest() {
        assertThat(pageObject, not(hasVisibleText(TEXT)));
    }

    /* visible */

    @Test
    public void visibleTrueTest() {
        doReturn(true).when(pageObject).isVisible();
        assertThat(pageObject, visible());
    }

    @Test(expected = AssertionError.class)
    public void visibleFalseTest() {
        assertThat(pageObject, visible());
    }

    @Test
    public void notVisibleTrueTest() {
        assertThat(pageObject, not(visible()));
    }

    @Test(expected = AssertionError.class)
    public void notVisibleFalseTest() {
        doReturn(true).when(pageObject).isVisible();
        assertThat(pageObject, not(visible()));
    }

    /* enabled */

    @Test
    public void enabledTrueTest() {
        doReturn(true).when(pageObject).isEnabled();
        assertThat(pageObject, enabled());
    }

    @Test(expected = AssertionError.class)
    public void enabledFalseTest() {
        assertThat(pageObject, enabled());
    }

    @Test
    public void notEnabledTrueTest() {
        assertThat(pageObject, not(enabled()));
    }

    @Test(expected = AssertionError.class)
    public void notEnabledFalseTest() {
        doReturn(true).when(pageObject).isEnabled();
        assertThat(pageObject, not(enabled()));
    }

}
