package info.novatec.testit.webtester.support.hamcrest;

import static info.novatec.testit.webtester.support.hamcrest.NumberFieldMatcher.hasValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import info.novatec.testit.webtester.pageobjects.NumberField;


@RunWith(MockitoJUnitRunner.class)
public class NumberFieldMatcherTest {

    static final long ANOTHER_VALUE = 5;
    static final long VALUE = 3;

    @Mock
    NumberField numberField;

    @Before
    public void setUp() {
        doReturn(VALUE).when(numberField).getValue();
    }

    /* hasValue */

    @Test
    public void hasValueTrueTest() {
        assertThat(numberField, hasValue(VALUE));
    }

    @Test(expected = AssertionError.class)
    public void hasValueFalseTest() {
        assertThat(numberField, hasValue(ANOTHER_VALUE));
    }

    @Test
    public void notHasValueTrueTest() {
        assertThat(numberField, not(hasValue(ANOTHER_VALUE)));
    }

    @Test(expected = AssertionError.class)
    public void notHasValueFalseTest() {
        assertThat(numberField, not(hasValue(VALUE)));
    }

}
