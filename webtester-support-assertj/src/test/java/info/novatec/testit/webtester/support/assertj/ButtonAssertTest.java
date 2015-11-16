package info.novatec.testit.webtester.support.assertj;

import static info.novatec.testit.webtester.support.assertj.WebTesterAssertions.assertThat;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import info.novatec.testit.webtester.pageobjects.Button;


@RunWith(MockitoJUnitRunner.class)
public class ButtonAssertTest {

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
        assertThat(button).hasLabel(LABEL);
    }

    @Test(expected = AssertionError.class)
    public void hasLabelFalseTest() {
        assertThat(button).hasLabel(ANOTHER_LABEL);
    }

    @Test
    public void notHasLabelTrueTest() {
        assertThat(button).hasNotLabel(ANOTHER_LABEL);
    }

    @Test(expected = AssertionError.class)
    public void notHasLabelFalseTest() {
        assertThat(button).hasNotLabel(LABEL);
    }

    /* hasValue */

    @Test
    public void hasValueTrueTest() {
        assertThat(button).hasValue(VALUE);
    }

    @Test(expected = AssertionError.class)
    public void hasValueFalseTest() {
        assertThat(button).hasValue(ANOTHER_VALUE);
    }

    @Test
    public void notHasValueTrueTest() {
        assertThat(button).hasNotValue(ANOTHER_VALUE);
    }

    @Test(expected = AssertionError.class)
    public void notHasValueFalseTest() {
        assertThat(button).hasNotValue(VALUE);
    }

}
