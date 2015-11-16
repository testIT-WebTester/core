package info.novatec.testit.webtester.support.assertj;

import static info.novatec.testit.webtester.support.assertj.WebTesterAssertions.assertThat;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import info.novatec.testit.webtester.pageobjects.PageObject;


@RunWith(MockitoJUnitRunner.class)
public class PageObjectAssertTest {

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
        assertThat(pageObject).hasAttributeWithValue(ATTRIBUTE, VALUE);
    }

    @Test(expected = AssertionError.class)
    public void hasAttributeWithValueFalseTest() {
        assertThat(pageObject).hasAttributeWithValue(ATTRIBUTE, ANOTHER_VALUE);
    }

    @Test
    public void notHasAttributeWithValueTrueTest() {
        assertThat(pageObject).hasNotAttributeWithValue(ATTRIBUTE, ANOTHER_VALUE);
    }

    @Test(expected = AssertionError.class)
    public void notHasAttributeWithValueFalseTest() {
        assertThat(pageObject).hasNotAttributeWithValue(ATTRIBUTE, VALUE);
    }

    /* hasVisibleText */

    @Test
    public void hasVisibleTextTrueTest() {
        assertThat(pageObject).hasVisibleText(TEXT);
    }

    @Test(expected = AssertionError.class)
    public void hasVisibleTextFalseTest() {
        assertThat(pageObject).hasVisibleText(ANOTHER_TEXT);
    }

    @Test
    public void notHasVisibleTextTrueTest() {
        assertThat(pageObject).hasNotVisibleText(ANOTHER_TEXT);
    }

    @Test(expected = AssertionError.class)
    public void notHasVisibleTextFalseTest() {
        assertThat(pageObject).hasNotVisibleText(TEXT);
    }

    /* visible */

    @Test
    public void visibleTrueTest() {
        doReturn(true).when(pageObject).isVisible();
        assertThat(pageObject).isVisible();
    }

    @Test(expected = AssertionError.class)
    public void visibleFalseTest() {
        assertThat(pageObject).isVisible();
    }

    @Test
    public void notVisibleTrueTest() {
        assertThat(pageObject).isNotVisible();
    }

    @Test(expected = AssertionError.class)
    public void notVisibleFalseTest() {
        doReturn(true).when(pageObject).isVisible();
        assertThat(pageObject).isNotVisible();
    }

    /* enabled */

    @Test
    public void enabledTrueTest() {
        doReturn(true).when(pageObject).isEnabled();
        assertThat(pageObject).isEnabled();
    }

    @Test(expected = AssertionError.class)
    public void enabledFalseTest() {
        assertThat(pageObject).isEnabled();
    }

    @Test
    public void notEnabledTrueTest() {
        assertThat(pageObject).isNotEnabled();
    }

    @Test(expected = AssertionError.class)
    public void notEnabledFalseTest() {
        doReturn(true).when(pageObject).isEnabled();
        assertThat(pageObject).isNotEnabled();
    }

}
