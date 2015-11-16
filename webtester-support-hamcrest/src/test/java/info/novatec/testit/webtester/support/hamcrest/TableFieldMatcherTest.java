package info.novatec.testit.webtester.support.hamcrest;

import static info.novatec.testit.webtester.support.hamcrest.TableFieldMatcher.headerField;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import info.novatec.testit.webtester.pageobjects.TableField;


@RunWith(MockitoJUnitRunner.class)
public class TableFieldMatcherTest {

    @Mock
    TableField headerField;
    @Mock
    TableField field;

    @Before
    public void setUp() {
        doReturn(true).when(headerField).isHeaderField();
    }

    /* headerField */

    @Test
    public void headerFieldTrueTest() {
        assertThat(headerField, headerField());
    }

    @Test(expected = AssertionError.class)
    public void headerFieldFalseTest() {
        assertThat(field, headerField());
    }

    @Test
    public void notHeaderFieldTrueTest() {
        assertThat(field, not(headerField()));
    }

    @Test(expected = AssertionError.class)
    public void notHeaderFieldFalseTest() {
        assertThat(headerField, not(headerField()));
    }

}
