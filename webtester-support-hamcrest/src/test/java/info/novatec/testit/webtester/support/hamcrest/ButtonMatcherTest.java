package info.novatec.testit.webtester.support.hamcrest;

import static info.novatec.testit.webtester.support.hamcrest.ButtonMatcher.hasLabel;
import static info.novatec.testit.webtester.support.hamcrest.ButtonMatcher.hasValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import info.novatec.testit.webtester.pageobjects.Button;


@RunWith(MockitoJUnitRunner.class)
public class ButtonMatcherTest {

    static final String LABEL = "label";
    static final String ANOTHER_LABEL = "not label";
    static final String VALUE = "value";
    static final String ANOTHER_VALUE = "not value";

    @Mock
    Button button;

    @Before
    public void setUp() {
        doReturn(LABEL).when(button).getLabel();
        doReturn(VALUE).when(button).getValue();
    }

    /* hasLabel */

    @Test
    public void hasLabelTrueTest() {
        assertThat(button, hasLabel(LABEL));
    }

    @Test(expected = AssertionError.class)
    public void hasLabelFalseTest() {
        assertThat(button, hasLabel(ANOTHER_LABEL));
    }

    @Test
    public void notHasLabelTrueTest() {
        assertThat(button, not(hasLabel(ANOTHER_LABEL)));
    }

    @Test(expected = AssertionError.class)
    public void notHasLabelFalseTest() {
        assertThat(button, not(hasLabel(LABEL)));
    }

    /* hasValue */

    @Test
    public void hasValueTrueTest() {
        assertThat(button, hasValue(VALUE));
    }

    @Test(expected = AssertionError.class)
    public void hasValueFalseTest() {
        assertThat(button, hasValue(ANOTHER_VALUE));
    }

    @Test
    public void notHasValueTrueTest() {
        assertThat(button, not(hasValue(ANOTHER_VALUE)));
    }

    @Test(expected = AssertionError.class)
    public void notHasValueFalseTest() {
        assertThat(button, not(hasValue(VALUE)));
    }

}
