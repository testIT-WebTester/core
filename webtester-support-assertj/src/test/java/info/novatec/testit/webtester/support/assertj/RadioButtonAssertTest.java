package info.novatec.testit.webtester.support.assertj;

import static info.novatec.testit.webtester.support.assertj.WebTesterAssertions.assertThat;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import info.novatec.testit.webtester.pageobjects.RadioButton;


@RunWith(MockitoJUnitRunner.class)
public class RadioButtonAssertTest {

    @Mock
    RadioButton selectedRadioButton;
    @Mock
    RadioButton radioButton;

    @Before
    public void setUp() {
        doReturn(true).when(selectedRadioButton).isSelected();
    }

    /* selected */

    @Test
    public void selectedTrueTest() {
        assertThat(selectedRadioButton).isSelected(true);
    }

    @Test(expected = AssertionError.class)
    public void selectedFalseTest() {
        assertThat(radioButton).isSelected(true);
    }

    @Test
    public void notSelectedTrueTest() {
        assertThat(radioButton).isNotSelected(true);
    }

    @Test(expected = AssertionError.class)
    public void notSelectedFalseTest() {
        assertThat(selectedRadioButton).isNotSelected(true);
    }

}
