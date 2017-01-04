package integration.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;

import integration.AbstractWebTesterIntegrationTest;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.exceptions.WrongElementClassException;
import info.novatec.testit.webtester.pageobjects.MultiSelect;
import info.novatec.testit.webtester.pageobjects.PageObject;


public class MultiSelectIntegrationTest extends AbstractWebTesterIntegrationTest {

    MultiSelectTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(MultiSelectTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobjects/multiSelect.html";
    }

    /* select by text */

    @Test
    public final void testThatNothingChangesIfNoTextAreGivenAsParameter() {
        page.multiSelectWithSelection.selectByText();
        assertThat(page.multiSelectWithSelection.getAllSelectedTexts(), contains("two", "three"));
    }

    @Test
    public final void testThatOptionsAreSelectedCorrectlyByTexts_multiSelect() {
        page.multiSelect.selectByText("one", "three");
        assertThat(page.multiSelect.getAllSelectedTexts(), contains("one", "three"));
    }

    @Test
    public final void testThatSelectionByTextsIsAdditive_multiSelectWithSelection() {
        page.multiSelectWithSelection.selectByText("one");
        assertThat(page.multiSelectWithSelection.getAllSelectedTexts(), contains("one", "two", "three"));
    }

    @Test
    public final void testThatDoubleSelectByTextDoNotDeselectOption_multiSelectWithSelection() {
        page.multiSelectWithSelection.selectByText("one", "three");
        assertThat(page.multiSelectWithSelection.getAllSelectedTexts(), contains("one", "two", "three"));
    }

    @Test(expected = NoSuchElementException.class)
    public final void testThatCorrectExceptionIsThrownBySelectingWithUnknownText() {
        page.multiSelect.selectByText("unknown");
    }

    /* select by value */

    @Test
    public final void testThatNothingChangesIfNoValuesAreGivenAsParameter() {
        page.multiSelectWithSelection.selectByValues();
        assertThat(page.multiSelectWithSelection.getAllSelectedValues(), contains("2", "3"));
    }

    @Test
    public final void testThatOptionsAreSelectedCorrectlyByValues_multiSelect() {
        page.multiSelect.selectByValues("1", "3");
        assertThat(page.multiSelect.getAllSelectedValues(), contains("1", "3"));
    }

    @Test
    public final void testThatSelectionByValuesIsAdditive_multiSelectWithSelection() {
        page.multiSelectWithSelection.selectByValues("1");
        assertThat(page.multiSelectWithSelection.getAllSelectedValues(), contains("1", "2", "3"));
    }

    @Test
    public final void testThatDoubleSelectByValueDoNotDeselectOption_multiSelectWithSelection() {
        page.multiSelectWithSelection.selectByValues("1", "3");
        assertThat(page.multiSelectWithSelection.getAllSelectedValues(), contains("1", "2", "3"));
    }

    @Test(expected = NoSuchElementException.class)
    public final void testThatCorrectExceptionIsThrownByUnknownValue_select() {
        page.multiSelect.selectByValues("42");
    }

    /* select by index */

    @Test
    public final void testThatNothingChangesIfNoIndicesAreGivenAsParameter() {
        page.multiSelectWithSelection.selectByIndices();
        assertThat(page.multiSelectWithSelection.getAllSelectedIndices(), contains(1, 2));
    }

    @Test
    public final void testThatOptionsAreSelectedCorrectlyByIndices_multiSelect() {
        page.multiSelect.selectByIndices(0, 2);
        assertThat(page.multiSelect.getAllSelectedIndices(), contains(0, 2));
    }

    @Test
    public final void testThatSelectionByIndicesIsAdditive_multiSelectWithSelection() {
        page.multiSelectWithSelection.selectByIndices(0);
        assertThat(page.multiSelectWithSelection.getAllSelectedIndices(), contains(0, 1,2));
    }

    @Test
    public final void testThatDoubleSelectByIndexDoNotDeselectOption_multiSelectWithSelection() {
        page.multiSelectWithSelection.selectByIndices(0, 2);
        assertThat(page.multiSelectWithSelection.getAllSelectedIndices(), contains(0, 1, 2));
    }

    @Test(expected = NoSuchElementException.class)
    public final void testThatCorrectExceptionIsThrownByUnknownIndex_select() {
        page.multiSelect.selectByIndices(42);
    }

    /* deselect all */

    @Test
    public final void testThatAllOptionsAreDeselected() {
        page.multiSelectWithSelection.deselectAll();
        assertThat(page.multiSelectWithSelection.getAllSelectedOptions(), is(empty()));
    }

    /* deselect by text */

    @Test
    public final void testThatOptionsAreDeselectedCorrectlyByTexts_singleDeselect() {
        page.multiSelectWithSelection.deselectByTexts("two");
        assertThat(page.multiSelectWithSelection.getAllSelectedTexts(), contains("three"));
    }
    @Test
    public final void testThatOptionsAreDeselectedCorrectlyByTexts_multipleDeselect() {
        page.multiSelectWithSelection.deselectByTexts("two", "three");
        assertThat(page.multiSelectWithSelection.getAllSelectedTexts(), is(empty()));
    }

    @Test(expected = NoSuchElementException.class)
    public final void testThatCorrectExceptionIsThrownByUnknownText_deselect() {
        page.multiSelectWithSelection.deselectByTexts("unknown");
    }

    /* deselect by value */

    @Test
    public final void testThatOptionsAreDeselectedCorrectlyByValue_singleDeselect() {
        page.multiSelectWithSelection.deselectByValues("2");
        assertThat(page.multiSelectWithSelection.getAllSelectedValues(), contains("3"));
    }

    @Test
    public final void testThatOptionsAreDeselectedCorrectlyByValue_multipleDeselect() {
        page.multiSelectWithSelection.deselectByValues("2", "3");
        assertThat(page.multiSelectWithSelection.getAllSelectedValues(), is(empty()));
    }

    @Test(expected = NoSuchElementException.class)
    public final void testThatCorrectExceptionIsThrownByUnknownValue_deselect() {
        page.multiSelectWithSelection.deselectByValues("42");
    }

    /* deselect by indices */

    @Test
    public final void testThatCorrectOptionsAreDeselectedCorrectlyByIndices_singleDeselect() {
        page.multiSelectWithSelection.deselectByIndices(1);
        assertThat(page.multiSelectWithSelection.getAllSelectedIndices(), contains(2));
    }

    @Test
    public final void testThatCorrectOptionsAreDeselectedCorrectlyByIndices_multipleDeselect() {
        page.multiSelectWithSelection.deselectByIndices(1, 2);
        assertThat(page.multiSelectWithSelection.getAllSelectedIndices(), is(empty()));
    }

    @Test(expected = NoSuchElementException.class)
    public final void testThatExceptionIsThrownByUnknownIndex_deselect() {
        page.multiSelectWithSelection.deselectByIndices(42);
    }

    /* get all selected texts */

    @Test
    public final void testThatListContainsCorrectTexts() {
        assertThat(page.multiSelectWithSelection.getAllSelectedTexts(), contains("two", "three"));
    }

    @Test
    public final void testThatSelectWithNoSelectionsReturnsEmptyList_getAllSelectedTexts() {
        assertThat(page.multiSelect.getAllSelectedTexts(), is(empty()));
    }

    @Test
    public final void testThatSelectWithNoOptionsReturnsEmptyList_getAllSelectedTexts() {
        assertThat(page.emptySelect.getAllSelectedTexts(), is(empty()));
    }

    /* get all selected values */

    @Test
    public final void testThatListContainsCorrectValues() {
        assertThat(page.multiSelectWithSelection.getAllSelectedValues(), contains("2", "3"));
    }

    @Test
    public final void testThatSelectWithNoSelectionsReturnsEmptyList_getAllSelectedValues() {
        assertThat(page.multiSelect.getAllSelectedTexts(), is(empty()));
    }

    @Test
    public final void testThatSelectWithNoOptionsReturnsEmptyList_getAllSelectedValues() {
        assertThat(page.emptySelect.getAllSelectedTexts(), is(empty()));
    }

    /* get all selected indices */

    @Test
    public final void testThatListContainsCorrectIndices() {
        assertThat(page.multiSelectWithSelection.getAllSelectedIndices(), contains(1, 2));
    }

    @Test
    public final void testThatSelectWithNoSelectionsReturnsEmptyList_getAllSelectedIndices() {
        assertThat(page.multiSelect.getAllSelectedTexts(), is(empty()));
    }

    @Test
    public final void testThatSelectWithNoOptionsReturnsEmptyList_getAllSelectedIndices() {
        assertThat(page.emptySelect.getAllSelectedTexts(), is(empty()));
    }

    /* get number of options */

    @Test
    public final void testThatCorrectNumberOfOptionsIsReturned() {
        assertThat(page.multiSelect.getNumberOfOptions(), is(3));
    }

    /* get number of selected options */

    @Test
    public final void testThatCorrectNumberOfSelectedOptionsIsReturned() {
        assertThat(page.multiSelectWithSelection.getNumberOfSelectedOptions(), is(2));
    }

    /* validation of mapping */

    @Test
    public final void testValidationOfMapping_multiSelect() {
        assertPageObjectCanBeInitialized(page.multiSelect);
    }

    @Test(expected = WrongElementClassException.class)
    public final void testValidationOfMapping_singleSelect() {
        assertPageObjectCanBeInitialized(page.singleSelect);
    }

    @Test(expected = WrongElementClassException.class)
    public final void testValidationOfMapping_noSelect() {
        assertPageObjectCanBeInitialized(page.notASelect);
    }

    /* utilities */

    public static class MultiSelectTestPage extends PageObject {
        @IdentifyUsing("emptySelect")
        MultiSelect emptySelect;

        @IdentifyUsing("multiSelect")
        MultiSelect multiSelect;

        @IdentifyUsing("multiSelectWithSelection")
        MultiSelect multiSelectWithSelection;

        @IdentifyUsing("singleSelect")
        MultiSelect singleSelect;

        @IdentifyUsing("notASelect")
        MultiSelect notASelect;
    }
}
