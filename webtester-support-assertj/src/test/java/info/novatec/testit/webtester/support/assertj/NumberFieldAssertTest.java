package info.novatec.testit.webtester.support.assertj;

import static info.novatec.testit.webtester.support.assertj.WebTesterAssertions.assertThat;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import info.novatec.testit.webtester.pageobjects.NumberField;


@RunWith(MockitoJUnitRunner.class)
public class NumberFieldAssertTest {

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
        assertThat(numberField).hasValue(VALUE);
    }

    @Test(expected = AssertionError.class)
    public void hasValueFalseTest() {
        assertThat(numberField).hasValue(ANOTHER_VALUE);
    }

    @Test
    public void notHasValueTrueTest() {
        assertThat(numberField).hasNotValue(ANOTHER_VALUE);
    }

    @Test(expected = AssertionError.class)
    public void notHasValueFalseTest() {
        assertThat(numberField).hasNotValue(VALUE);
    }

}
