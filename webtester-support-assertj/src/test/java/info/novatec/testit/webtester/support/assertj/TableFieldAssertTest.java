package info.novatec.testit.webtester.support.assertj;

import static info.novatec.testit.webtester.support.assertj.WebTesterAssertions.assertThat;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import info.novatec.testit.webtester.pageobjects.TableField;


@RunWith(MockitoJUnitRunner.class)
public class TableFieldAssertTest {

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
        assertThat(headerField).isHeaderField();
    }

    @Test(expected = AssertionError.class)
    public void headerFieldFalseTest() {
        assertThat(field).isHeaderField();
    }

    @Test
    public void notHeaderFieldTrueTest() {
        assertThat(field).isNotHeaderField();
    }

    @Test(expected = AssertionError.class)
    public void notHeaderFieldFalseTest() {
        assertThat(headerField).isNotHeaderField();
    }

}
