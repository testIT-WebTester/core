package info.novatec.testit.webtester.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;

import info.novatec.testit.webtester.AbstractPageObjectTest;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsDisabledException;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsInvisibleException;
import info.novatec.testit.webtester.eventsystem.events.pageobject.SelectionChangedEvent;


public class CheckboxTest extends AbstractPageObjectTest {

    @Captor
    ArgumentCaptor<SelectionChangedEvent> selectionChangedCaptor;

    @InjectMocks
    Checkbox cut;

    /* getting selection state */

    @Test
    public void testThatSelectionStateIsRetrievedFromCorrectWebElementMethod() {

        elementIsSelected();
        assertThat(cut.isSelected(), is(true));

        elementIsNotSelected();
        assertThat(cut.isSelected(), is(false));

    }

    /* clicking */

    @Test
    public void testThatClickingACheckboxDelegatesToCorrectMethod() {
        cut.click();
        verify(webElement).click();
    }

    @Test(expected = PageObjectIsInvisibleException.class)
    public void testThatClickingAnInvisibleCheckboxThrowsException() {
        elementIsInvisible();
        cut.click();
    }

    @Test(expected = PageObjectIsDisabledException.class)
    public void testThatClickingADisabledCheckboxThrowsException() {
        elementIsDisabled();
        cut.click();
    }

    /* setting selection state */

    @Test
    public void testThatCheckboxIsOnlyClickedWhenTheClickWouldChangeSelectionToDesiredState_NotSelectedToSelected() {
        elementIsVisibleAndEnabled();
        elementIsNotSelected();
        cut.setSelection(true);
        verify(webElement).click();
    }

    @Test
    public void testThatCheckboxIsOnlyClickedWhenTheClickWouldChangeSelectionToDesiredState_SelectedToNotSelected() {
        elementIsVisibleAndEnabled();
        elementIsSelected();
        cut.setSelection(false);
        verify(webElement).click();
    }

    @Test
    public void testThatCheckboxIsOnlyClickedWhenTheClickWouldChangeSelectionToDesiredState_SelectedToSelected() {
        elementIsVisibleAndEnabled();
        elementIsSelected();
        cut.setSelection(true);
        verify(webElement, never()).click();
    }

    @Test
    public void testThatCheckboxIsOnlyClickedWhenTheClickWouldChangeSelectionToDesiredState_NotSelectedToNotSelected() {
        elementIsVisibleAndEnabled();
        elementIsNotSelected();
        cut.setSelection(false);
        verify(webElement, never()).click();
    }

    @Test(expected = PageObjectIsInvisibleException.class)
    public void testThatSettingSelectionStateOnInvisibleElementLeadsToException() {
        elementIsInvisible();
        elementIsEnabled();
        cut.setSelection(true);
    }

    @Test(expected = PageObjectIsDisabledException.class)
    public void testThatSettingSelectionStateOnDisabledElementLeadsToException() {
        elementIsVisible();
        elementIsDisabled();
        cut.setSelection(true);
    }

    @Test
    public void testThatChaningSelectionFiresCorrectEvent_NotSelectedToSelected() {

        elementIsVisibleAndEnabled();
        stubWebElementBeforeAndAfterSelectionState(false, true);

        cut.setSelection(true);

        verify(listener).eventOccurred(selectionChangedCaptor.capture());

        SelectionChangedEvent event = selectionChangedCaptor.getValue();
        assertThat(event.getBefore(), is(false));
        assertThat(event.getAfter(), is(true));

    }

    @Test
    public void testThatChaningSelectionFiresCorrectEvent_SelectedToNotSelected() {

        elementIsVisibleAndEnabled();
        stubWebElementBeforeAndAfterSelectionState(true, false);

        cut.setSelection(false);

        verify(listener).eventOccurred(selectionChangedCaptor.capture());

        SelectionChangedEvent event = selectionChangedCaptor.getValue();
        assertThat(event.getBefore(), is(true));
        assertThat(event.getAfter(), is(false));

    }

    @Test
    public void testThatChangingSelectionStepsAreExecutedInOrder() {

        elementIsVisibleAndEnabled();
        elementIsNotSelected();

        cut.setSelection(true);

        InOrder inOrder = inOrder(webElement, listener);
        inOrder.verify(webElement).click();
        inOrder.verify(listener).eventOccurred(any(SelectionChangedEvent.class));

    }

    /* correctness of class */

    @Test
    public void testCorrectnessOfClassForWebElement_inputTag_textType() {
        stubWebElementTagAndType("input", "checkbox");
        assertThatCorrectnessOfClassIs(true);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_nonInputTag() {
        stubWebElementTagAndType("other", null);
        assertThatCorrectnessOfClassIs(false);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_inputTag_nonTextFieldType() {
        stubWebElementTagAndType("input", "other");
        assertThatCorrectnessOfClassIs(false);
    }

    /* utilities */

    private void elementIsSelected() {
        doReturn(true).when(webElement).isSelected();
    }

    private void elementIsNotSelected() {
        doReturn(false).when(webElement).isSelected();
    }

    private void stubWebElementBeforeAndAfterSelectionState(boolean before, boolean after) {
        when(webElement.isSelected()).thenReturn(before, after);
    }

    private void assertThatCorrectnessOfClassIs(boolean expected) {
        assertThat(cut.isCorrectClassForWebElement(webElement), is(expected));
    }

}
