package info.novatec.testit.webtester.support.assertj;

import static info.novatec.testit.webtester.support.assertj.WebTesterAssertions.assertThat;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import info.novatec.testit.webtester.pageobjects.Checkbox;


@RunWith(MockitoJUnitRunner.class)
public class CheckBoxAssertTest {

    @Mock
    Checkbox selectedCheckbox;
    @Mock
    Checkbox checkbox;

    @Before
    public void setUp() {
        doReturn(true).when(selectedCheckbox).isSelected();
    }

    /* selected */

    @Test
    public void selectedTrueTest() {
        assertThat(selectedCheckbox).isSelected();
    }

    @Test(expected = AssertionError.class)
    public void selectedFalseTest() {
        assertThat(checkbox).isSelected();
    }

    @Test
    public void notSelectedTrueTest() {
        assertThat(checkbox).isNotSelected();
    }

    @Test(expected = AssertionError.class)
    public void notSelectedFalseTest() {
        assertThat(selectedCheckbox).isNotSelected();
    }

}
