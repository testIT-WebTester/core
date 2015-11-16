package info.novatec.testit.webtester.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import info.novatec.testit.webtester.AbstractPageObjectTest;
import info.novatec.testit.webtester.eventsystem.events.pageobject.SelectedByIndexEvent;
import info.novatec.testit.webtester.eventsystem.events.pageobject.SelectedByTextEvent;
import info.novatec.testit.webtester.eventsystem.events.pageobject.SelectedByValueEvent;


public class SelectTest extends AbstractPageObjectTest {

    @Captor
    ArgumentCaptor<SelectedByTextEvent> selectedByTextCaptor;
    @Captor
    ArgumentCaptor<SelectedByValueEvent> selectedByValueCaptor;
    @Captor
    ArgumentCaptor<SelectedByIndexEvent> selectedByIndexCaptor;

    @Mock
    org.openqa.selenium.support.ui.Select select;

    @InjectMocks
    Select cut;

    /* select by text */

    @Test
    public void GivenSingleSelect_WhenSelectingNothingByText_NoSelectionIsMade() {

        selectIsSingleSelect();
        cut.selectByText();

        InOrder inOrder = inOrder(select, listener);
        inOrder.verify(select).isMultiple();
        inOrder.verifyNoMoreInteractions();

    }

    @Test
    public void GivenSingleSelect_WhenSelectingOneByText_OneSelectionIsMade() {

        selectIsSingleSelect();
        cut.selectByText("foo");

        InOrder inOrder = inOrder(select, listener);
        inOrder.verify(select).isMultiple();
        inOrder.verify(select).selectByVisibleText("foo");
        inOrder.verify(listener).eventOccurred(any(SelectedByTextEvent.class));
        inOrder.verifyNoMoreInteractions();

    }

    @Test
    public void GivenSingleSelect_WhenSelectingMoreThenOneByText_AllSelectionsAreMadeInSequence() {

        selectIsSingleSelect();
        cut.selectByText("foo", "bar");

        InOrder inOrder = inOrder(select, listener);
        inOrder.verify(select).isMultiple();
        inOrder.verify(select).selectByVisibleText("foo");
        inOrder.verify(listener).eventOccurred(any(SelectedByTextEvent.class));
        inOrder.verify(select).selectByVisibleText("bar");
        inOrder.verify(listener).eventOccurred(any(SelectedByTextEvent.class));
        inOrder.verifyNoMoreInteractions();

    }

    @Test
    public void GivenMultiSelect_WhenSelectingNothingByText_AllSelectionIsRemovedAndNothingMoreIsDone() {

        selectIsMultiSelect();
        cut.selectByText();

        InOrder inOrder = inOrder(select, listener);
        inOrder.verify(select).isMultiple();
        inOrder.verify(select).deselectAll();
        inOrder.verifyNoMoreInteractions();

    }

    @Test
    public void GivenMultiSelect_WhenSelectingOneByText_OneSelectionIsMade() {

        selectIsMultiSelect();
        cut.selectByText("foo");

        InOrder inOrder = inOrder(select, listener);
        inOrder.verify(select).isMultiple();
        inOrder.verify(select).deselectAll();
        inOrder.verify(select).selectByVisibleText("foo");
        inOrder.verify(listener).eventOccurred(any(SelectedByTextEvent.class));
        inOrder.verifyNoMoreInteractions();

    }

    @Test
    public void GivenMultiSelect_WhenSelectingMoreThenOneByText_AllSelectionsAreMadeInSequence() {

        selectIsMultiSelect();
        cut.selectByText("foo", "bar");

        InOrder inOrder = inOrder(select, listener);
        inOrder.verify(select).isMultiple();
        inOrder.verify(select).deselectAll();
        inOrder.verify(select).selectByVisibleText("foo");
        inOrder.verify(listener).eventOccurred(any(SelectedByTextEvent.class));
        inOrder.verify(select).selectByVisibleText("bar");
        inOrder.verify(listener).eventOccurred(any(SelectedByTextEvent.class));
        inOrder.verifyNoMoreInteractions();

    }

    @Test
    public void testThatCorrectSelectByTextEventIsFired() {
        cut.selectByText("foo");
        verify(listener).eventOccurred(selectedByTextCaptor.capture());
        assertThat(selectedByTextCaptor.getValue().getText(), is("foo"));
    }

    /* select by value */

    @Test
    public void GivenSingleSelect_WhenSelectingNothingByValue_NoSelectionIsMade() {

        selectIsSingleSelect();
        cut.selectByValue();

        InOrder inOrder = inOrder(select, listener);
        inOrder.verify(select).isMultiple();
        inOrder.verifyNoMoreInteractions();

    }

    @Test
    public void GivenSingleSelect_WhenSelectingOneByValue_OneSelectionIsMade() {

        selectIsSingleSelect();
        cut.selectByValue("42");

        InOrder inOrder = inOrder(select, listener);
        inOrder.verify(select).isMultiple();
        inOrder.verify(select).selectByValue("42");
        inOrder.verify(listener).eventOccurred(any(SelectedByValueEvent.class));
        inOrder.verifyNoMoreInteractions();

    }

    @Test
    public void GivenSingleSelect_WhenSelectingMoreThenOneByValue_AllSelectionsAreMadeInSequence() {

        selectIsSingleSelect();
        cut.selectByValue("42", "84");

        InOrder inOrder = inOrder(select, listener);
        inOrder.verify(select).isMultiple();
        inOrder.verify(select).selectByValue("42");
        inOrder.verify(listener).eventOccurred(any(SelectedByValueEvent.class));
        inOrder.verify(select).selectByValue("84");
        inOrder.verify(listener).eventOccurred(any(SelectedByValueEvent.class));
        inOrder.verifyNoMoreInteractions();

    }

    @Test
    public void GivenMultiSelect_WhenSelectingNothingByValue_AllSelectionIsRemovedAndNothingMoreIsDone() {

        selectIsMultiSelect();
        cut.selectByValue();

        InOrder inOrder = inOrder(select, listener);
        inOrder.verify(select).isMultiple();
        inOrder.verify(select).deselectAll();
        inOrder.verifyNoMoreInteractions();

    }

    @Test
    public void GivenMultiSelect_WhenSelectingOneByValue_OneSelectionIsMade() {

        selectIsMultiSelect();
        cut.selectByValue("42");

        InOrder inOrder = inOrder(select, listener);
        inOrder.verify(select).isMultiple();
        inOrder.verify(select).deselectAll();
        inOrder.verify(select).selectByValue("42");
        inOrder.verify(listener).eventOccurred(any(SelectedByValueEvent.class));
        inOrder.verifyNoMoreInteractions();

    }

    @Test
    public void GivenMultiSelect_WhenSelectingMoreThenOneByValue_AllSelectionsAreMadeInSequence() {

        selectIsMultiSelect();
        cut.selectByValue("42", "84");

        InOrder inOrder = inOrder(select, listener);
        inOrder.verify(select).isMultiple();
        inOrder.verify(select).deselectAll();
        inOrder.verify(select).selectByValue("42");
        inOrder.verify(listener).eventOccurred(any(SelectedByValueEvent.class));
        inOrder.verify(select).selectByValue("84");
        inOrder.verify(listener).eventOccurred(any(SelectedByValueEvent.class));
        inOrder.verifyNoMoreInteractions();

    }

    @Test
    public void testThatCorrectSelectByValueEventIsFired() {
        cut.selectByValue("42");
        verify(listener).eventOccurred(selectedByValueCaptor.capture());
        assertThat(selectedByValueCaptor.getValue().getValue(), is("42"));
    }

    /* select by index */

    @Test
    public void GivenSingleSelect_WhenSelectingNothingByIndex_NoSelectionIsMade() {

        selectIsSingleSelect();
        cut.selectByIndex();

        InOrder inOrder = inOrder(select, listener);
        inOrder.verify(select).isMultiple();
        inOrder.verifyNoMoreInteractions();

    }

    @Test
    public void GivenSingleSelect_WhenSelectingOneByIndex_OneSelectionIsMade() {

        selectIsSingleSelect();
        cut.selectByIndex(42);

        InOrder inOrder = inOrder(select, listener);
        inOrder.verify(select).isMultiple();
        inOrder.verify(select).selectByIndex(42);
        inOrder.verify(listener).eventOccurred(any(SelectedByIndexEvent.class));
        inOrder.verifyNoMoreInteractions();

    }

    @Test
    public void GivenSingleSelect_WhenSelectingMoreThenOneByIndex_AllSelectionsAreMadeInSequence() {

        selectIsSingleSelect();
        cut.selectByIndex(42, 84);

        InOrder inOrder = inOrder(select, listener);
        inOrder.verify(select).isMultiple();
        inOrder.verify(select).selectByIndex(42);
        inOrder.verify(listener).eventOccurred(any(SelectedByIndexEvent.class));
        inOrder.verify(select).selectByIndex(84);
        inOrder.verify(listener).eventOccurred(any(SelectedByIndexEvent.class));
        inOrder.verifyNoMoreInteractions();

    }

    @Test
    public void GivenMultiSelect_WhenSelectingNothingByIndex_AllSelectionIsRemovedAndNothingMoreIsDone() {

        selectIsMultiSelect();
        cut.selectByIndex();

        InOrder inOrder = inOrder(select, listener);
        inOrder.verify(select).isMultiple();
        inOrder.verify(select).deselectAll();
        inOrder.verifyNoMoreInteractions();

    }

    @Test
    public void GivenMultiSelect_WhenSelectingOneByIndex_OneSelectionIsMade() {

        selectIsMultiSelect();
        cut.selectByIndex(42);

        InOrder inOrder = inOrder(select, listener);
        inOrder.verify(select).isMultiple();
        inOrder.verify(select).deselectAll();
        inOrder.verify(select).selectByIndex(42);
        inOrder.verify(listener).eventOccurred(any(SelectedByIndexEvent.class));
        inOrder.verifyNoMoreInteractions();

    }

    @Test
    public void GivenMultiSelect_WhenSelectingMoreThenOneByIndex_AllSelectionsAreMadeInSequence() {

        selectIsMultiSelect();
        cut.selectByIndex(42, 84);

        InOrder inOrder = inOrder(select, listener);
        inOrder.verify(select).isMultiple();
        inOrder.verify(select).deselectAll();
        inOrder.verify(select).selectByIndex(42);
        inOrder.verify(listener).eventOccurred(any(SelectedByIndexEvent.class));
        inOrder.verify(select).selectByIndex(84);
        inOrder.verify(listener).eventOccurred(any(SelectedByIndexEvent.class));
        inOrder.verifyNoMoreInteractions();

    }

    @Test
    public void testThatCorrectSelectByIndexEventIsFired() {
        cut.selectByIndex(42);
        verify(listener).eventOccurred(selectedByIndexCaptor.capture());
        assertThat(selectedByIndexCaptor.getValue().getIndex(), is(42));
    }

    /* first selected text */

    @Test
    public void GivenSelectWithoutSelectedOption_WhenRetrievingTheFirstSelectedOptionsText_NullIsReturned() {
        selectHasSelectedOptions();
        String firstSelectedText = cut.getFirstSelectedText();
        assertThat(firstSelectedText, is(nullValue()));
    }

    @Test
    public void GivenSelectWithOneSelectedOption_WhenRetrievingTheFirstSelectedOptionsText_TextIsReturned() {
        selectHasSelectedOptions(optionWithText("foo"));
        String firstSelectedText = cut.getFirstSelectedText();
        assertThat(firstSelectedText, is("foo"));
    }

    @Test
    public void GivenSelectWithTwoSelectedOptions_WhenRetrievingTheFirstSelectedOptionsText_TextIsReturned() {
        selectHasSelectedOptions(optionWithText("foo"), optionWithText("bar"));
        String firstSelectedText = cut.getFirstSelectedText();
        assertThat(firstSelectedText, is("foo"));
    }

    /* all selected texts */

    @Test
    public void GivenSelectWithoutSelectedOption_WhenRetrievingAllSelectedOptionsText_EmptyListIsReturned() {
        selectHasSelectedOptions();
        List<String> slectedTexts = cut.getAllSelectedTexts();
        assertThat(slectedTexts, is(empty()));
    }

    @Test
    public void GivenSelectWithOneSelectedOption_WhenRetrievingAllSelectedOptionsText_TextIsReturned() {
        selectHasSelectedOptions(optionWithText("foo"));
        List<String> slectedTexts = cut.getAllSelectedTexts();
        assertThat(slectedTexts, contains("foo"));
    }

    @Test
    public void GivenSelectWithTwoSelectedOptions_WhenRetrievingAllSelectedOptionsText_TextsAreReturned() {
        selectHasSelectedOptions(optionWithText("foo"), optionWithText("bar"));
        List<String> slectedTexts = cut.getAllSelectedTexts();
        assertThat(slectedTexts, contains("foo", "bar"));
    }

    /* first selected value */

    @Test
    public void GivenSelectWithoutSelectedOption_WhenRetrievingTheFirstSelectedOptionsValue_NullIsReturned() {
        selectHasSelectedOptions();
        String firstSelectedValue = cut.getFirstSelectedValue();
        assertThat(firstSelectedValue, is(nullValue()));
    }

    @Test
    public void GivenSelectWithOneSelectedOption_WhenRetrievingTheFirstSelectedOptionsValue_ValueIsReturned() {
        selectHasSelectedOptions(optionWithValue("foo"));
        String firstSelectedValue = cut.getFirstSelectedValue();
        assertThat(firstSelectedValue, is("foo"));
    }

    @Test
    public void GivenSelectWithTwoSelectedOptions_WhenRetrievingTheFirstSelectedOptionsValue_ValueIsReturned() {
        selectHasSelectedOptions(optionWithValue("foo"), optionWithValue("bar"));
        String firstSelectedValue = cut.getFirstSelectedValue();
        assertThat(firstSelectedValue, is("foo"));
    }

    /* all selected value */

    @Test
    public void GivenSelectWithoutSelectedOption_WhenRetrievingAllSelectedOptionsValue_EmptyListIsReturned() {
        selectHasSelectedOptions();
        List<String> slectedValues = cut.getAllSelectedValues();
        assertThat(slectedValues, is(empty()));
    }

    @Test
    public void GivenSelectWithOneSelectedOption_WhenRetrievingAllSelectedOptionsValue_ValueIsReturned() {
        selectHasSelectedOptions(optionWithValue("foo"));
        List<String> slectedValues = cut.getAllSelectedValues();
        assertThat(slectedValues, contains("foo"));
    }

    @Test
    public void GivenSelectWithTwoSelectedOptions_WhenRetrievingAllSelectedOptionsValue_ValuesAreReturned() {
        selectHasSelectedOptions(optionWithValue("foo"), optionWithValue("bar"));
        List<String> slectedValues = cut.getAllSelectedValues();
        assertThat(slectedValues, contains("foo", "bar"));
    }

    /* first selected index */

    @Test
    public void GivenSelectWithoutOptions_WhenRetrievingTheFirstSelectedOptionsIndex_NullIsReturned() {
        selectHasOptions();
        Integer firstSelectedIndex = cut.getFirstSelectedIndex();
        assertThat(firstSelectedIndex, is(nullValue()));
    }

    @Test
    public void GivenSelectWithoutSelectedOptions_WhenRetrievingTheFirstSelectedOptionsIndex_NullIsReturned() {
        selectHasOptions(optionWithText("foo"));
        selectHasSelectedOptions();
        Integer firstSelectedIndex = cut.getFirstSelectedIndex();
        assertThat(firstSelectedIndex, is(nullValue()));
    }

    @Test
    public void GivenSelectWithOneSelectedOption_WhenRetrievingTheFirstSelectedOptionsIndex_IndexIsReturned() {

        WebElement option1 = optionWithText("foo");
        WebElement option2 = optionWithText("bar");
        selectHasOptions(option1, option2);
        selectHasSelectedOptions(option1);

        Integer firstSelectedIndex = cut.getFirstSelectedIndex();
        assertThat(firstSelectedIndex, is(0));

    }

    @Test
    public void GivenSelectWithTwoSelectedOptions_WhenRetrievingTheFirstSelectedOptionsIndex_IndexIsReturned() {

        WebElement option1 = optionWithText("foo");
        WebElement option2 = optionWithText("bar");
        selectHasOptions(option1, option2);
        selectHasSelectedOptions(option1, option2);

        Integer firstSelectedIndex = cut.getFirstSelectedIndex();
        assertThat(firstSelectedIndex, is(0));

    }

    /* all selected indices */

    @Test
    public void GivenSelectWithoutOption_WhenRetrievingAllSelectedOptionsIndices_EmptyListIsReturned() {
        selectHasOptions();
        List<Integer> slectedIndexs = cut.getAllSelectedIndices();
        assertThat(slectedIndexs, is(empty()));
    }

    @Test
    public void GivenSelectWithoutSelectedOption_WhenRetrievingAllSelectedOptionsIndices_EmptyListIsReturned() {
        selectHasOptions(optionWithText("foo"));
        selectHasSelectedOptions();
        List<Integer> slectedIndexs = cut.getAllSelectedIndices();
        assertThat(slectedIndexs, is(empty()));
    }

    @Test
    public void GivenSelectWithOneSelectedOption_WhenRetrievingAllSelectedOptionsIndices_IndexIsReturned() {

        WebElement option1 = optionWithText("foo");
        WebElement option2 = optionWithText("bar");
        selectHasOptions(option1, option2);
        selectHasSelectedOptions(option1);

        List<Integer> slectedIndexs = cut.getAllSelectedIndices();
        assertThat(slectedIndexs, contains(0));

    }

    @Test
    public void GivenSelectWithTwoSelectedOptions_WhenRetrievingAllSelectedOptionsIndices_IndexsAreReturned() {

        WebElement option1 = optionWithText("foo");
        WebElement option2 = optionWithText("bar");
        selectHasOptions(option1, option2);
        selectHasSelectedOptions(option1, option2);

        List<Integer> slectedIndexs = cut.getAllSelectedIndices();
        assertThat(slectedIndexs, contains(0, 1));

    }

    /* all texts */

    @Test
    public void GivenSelectWithoutOptions_WhenRetrievingAllOptionsTexts_EmptyListIsReturned() {
        selectHasOptions();
        List<String> slectedTexts = cut.getAllTexts();
        assertThat(slectedTexts, is(empty()));
    }

    @Test
    public void GivenSelectWithOneOption_WhenRetrievingAllOptionsTexts_TextIsReturned() {
        selectHasOptions(optionWithText("foo"));
        List<String> slectedTexts = cut.getAllTexts();
        assertThat(slectedTexts, contains("foo"));
    }

    @Test
    public void GivenSelectWithTwoOptions_WhenRetrievingAllOptionsTexts_TextsAreReturned() {
        selectHasOptions(optionWithText("foo"), optionWithText("bar"));
        List<String> slectedTexts = cut.getAllTexts();
        assertThat(slectedTexts, contains("foo", "bar"));
    }

    /* all values */

    @Test
    public void GivenSelectWithoutOptions_WhenRetrievingAllOptionsValues_EmptyListIsReturned() {
        selectHasOptions();
        List<String> slectedValues = cut.getAllValues();
        assertThat(slectedValues, is(empty()));
    }

    @Test
    public void GivenSelectWithOneOption_WhenRetrievingAllOptionsValues_ValueIsReturned() {
        selectHasOptions(optionWithValue("42"));
        List<String> slectedValues = cut.getAllValues();
        assertThat(slectedValues, contains("42"));
    }

    @Test
    public void GivenSelectWithTwoOptions_WhenRetrievingAllOptionsValues_ValuesAreReturned() {
        selectHasOptions(optionWithValue("42"), optionWithValue("84"));
        List<String> slectedValues = cut.getAllValues();
        assertThat(slectedValues, contains("42", "84"));
    }

    /* number of selected options */

    @Test
    public void GivenSelectWithTwoSelectedOptions_WhenRetrievingTheNumberOfSelectedOptions_TwoIsReturned() {
        selectHasSelectedOptions(optionWithText("foo"), optionWithText("bar"));
        Integer numberOfSelectedOptions = cut.getNumberOfSelectedOptions();
        assertThat(numberOfSelectedOptions, is(2));
    }

    /* number of options */

    @Test
    public void GivenSelectWithTwoOptions_WhenRetrievingTheNumberOfOptions_TwoIsReturned() {
        selectHasOptions(optionWithText("foo"), optionWithText("bar"));
        Integer numberOfOptions = cut.getNumberOfOptions();
        assertThat(numberOfOptions, is(2));
    }

    /* correctness of class */

    @Test
    public void testCorrectnessOfClassForWebElement_selectTag() {
        stubWebElementTag("select");
        assertThatCorrectnessOfClassIs(true);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_otherTag() {
        stubWebElementTag("other");
        assertThatCorrectnessOfClassIs(false);
    }

    /* utilities */

    private void selectIsSingleSelect() {
        doReturn(false).when(select).isMultiple();
    }

    private void selectIsMultiSelect() {
        doReturn(true).when(select).isMultiple();
    }

    private WebElement optionWithText(String text) {
        WebElement option = mock(WebElement.class);
        doReturn(text).when(option).getText();
        return option;
    }

    private WebElement optionWithValue(String value) {
        WebElement option = mock(WebElement.class);
        doReturn(value).when(option).getAttribute("value");
        return option;
    }

    private void selectHasSelectedOptions(WebElement... options) {
        doReturn(Arrays.asList(options)).when(select).getAllSelectedOptions();
        if (options.length > 0) {
            doReturn(options[0]).when(select).getFirstSelectedOption();
        } else {
            doThrow(NoSuchElementException.class).when(select).getFirstSelectedOption();
        }
    }

    private void selectHasOptions(WebElement... options) {
        doReturn(Arrays.asList(options)).when(select).getOptions();
    }

    private void assertThatCorrectnessOfClassIs(boolean expected) {
        assertThat(cut.isCorrectClassForWebElement(webElement), is(expected));
    }

}
