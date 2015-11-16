package info.novatec.testit.webtester.support.hamcrest;

import static info.novatec.testit.webtester.support.hamcrest.RadioButtonMatcher.selected;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import info.novatec.testit.webtester.pageobjects.RadioButton;


@RunWith(MockitoJUnitRunner.class)
public class RadioButtonMatcherTest {

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
        assertThat(selectedRadioButton, selected());
    }

    @Test(expected = AssertionError.class)
    public void selectedFalseTest() {
        assertThat(radioButton, selected());
    }

    @Test
    public void notSelectedTrueTest() {
        assertThat(radioButton, not(selected()));
    }

    @Test(expected = AssertionError.class)
    public void notSelectedFalseTest() {
        assertThat(selectedRadioButton, not(selected()));
    }

}
