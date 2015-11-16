package info.novatec.testit.webtester.support.hamcrest;

import static info.novatec.testit.webtester.support.hamcrest.CheckboxMatcher.selected;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import info.novatec.testit.webtester.pageobjects.Checkbox;


@RunWith(MockitoJUnitRunner.class)
public class CheckBoxMatcherTest {

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
        assertThat(selectedCheckbox, selected());
    }

    @Test(expected = AssertionError.class)
    public void selectedFalseTest() {
        assertThat(checkbox, selected());
    }

    @Test
    public void notSelectedTrueTest() {
        assertThat(checkbox, not(selected()));
    }

    @Test(expected = AssertionError.class)
    public void notSelectedFalseTest() {
        assertThat(selectedCheckbox, not(selected()));
    }

}
