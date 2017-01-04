package integration.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;

import integration.AbstractWebTesterIntegrationTest;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.exceptions.WrongElementClassException;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.SingleSelect;



public class SingleSelectIntegrationTest extends AbstractWebTesterIntegrationTest {

    SingleSelectTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(SingleSelectTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobjects/singleSelect.html";
    }

    /* select by text */

    @Test
    public final void testThatOptionCanBeSelectedByText_singleSelect() {
        page.singleSelect.selectByText("two");
        assertThat(page.singleSelect.getSelectionText(), is("two"));
    }

    @Test
    public final void testThatOptionCanBeSelectedByText_singleSelectWithSelection() {
        page.singleSelectWithSelection.selectByText("one");
        assertThat(page.singleSelectWithSelection.getSelectionText(), is("one"));
    }

    @Test(expected = NoSuchElementException.class)
    public final void testThatOptionCanNotBeSelectedByUnknownText() {
        page.singleSelect.selectByText("unknown");
    }

    /* select by value */

    @Test
    public final void testThatOptionCanBeSelectedByValue_singleSelect() {
        page.singleSelect.selectByValue("2");
        assertThat(page.singleSelect.getSelectionValue(), is("2"));
    }

    @Test
    public final void testThatOptionCanBeSelectedByValue_singleSelectWithSelection() {
        page.singleSelectWithSelection.selectByValue("1");
        assertThat(page.singleSelectWithSelection.getSelectionValue(), is("1"));
    }

    @Test(expected = NoSuchElementException.class)
    public final void testThatOptionCanNotBeSelectedByUnknownValue() {
        page.singleSelect.selectByValue("unknown");
    }

    /* select by index */

    @Test
    public final void testThatOptionCanBeSelectedByIndex_singleSelect() {
        page.singleSelect.selectByIndex(1);
        assertThat(page.singleSelect.getSelectionIndex(), is(1));
    }

    @Test
    public final void testThatOptionCanBeSelectedByIndex_singleSelectWithSelection() {
        page.singleSelectWithSelection.selectByIndex(0);
        assertThat(page.singleSelectWithSelection.getSelectionIndex(), is(0));
    }

    @Test(expected = NoSuchElementException.class)
    public final void testThatOptionCanNotBeSelectedByUnknownIndex() {
        page.singleSelect.selectByIndex(42);
    }

    /* get selected text */

    @Test
    public final void testThatTextIsReturnedCorrectly_singleSelect() {
        // if nothing is selected the first element is selected by default
        String text = page.singleSelect.getSelectionText();
        assertThat(text, is("one"));
    }

    @Test
    public final void testThatTextIsReturnedCorrectly_singleSelectWithSelection() {
        String text = page.singleSelectWithSelection.getSelectionText();
        assertThat(text, is("two"));
    }

    @Test(expected = NullPointerException.class)
    public final void testThatSelectedTextIsEmpty_emptySelect() {
        String text = page.emptySelect.getSelectionText();
        assertThat(text.isEmpty(), is(true));
    }

    /* get selected value */

    @Test
    public final void testThatValueIsReturnedCorrectly_singleSelect() {
        String value = page.singleSelect.getSelectionValue();
        assertThat(value, is("1"));
    }

    @Test
    public final void testThatValueIsReturnedCorrectly_singleSelectWithSelection() {
        String value = page.singleSelectWithSelection.getSelectionValue();
        assertThat(value, is("2"));
    }

    @Test(expected = NullPointerException.class)
    public final void testThatSelectedValueIsEmpty_emptySelect() {
        String value = page.emptySelect.getSelectionValue();
        assertThat(value.isEmpty(), is(true));
    }

    /* get selected index */

    @Test
    public final void testThatIndexIsReturnedCorrectly_singleSelect() {
        Integer index = page.singleSelect.getSelectionIndex();
        assertThat(index, is(0));
    }

    @Test
    public final void testThatIndexIsReturnedCorrectly_singleSelectWithSelection() {
        Integer index = page.singleSelectWithSelection.getSelectionIndex();
        assertThat(index, is(1));
    }

    @Test(expected = NullPointerException.class)
    public final void testThatIndexIsNull_emptySelect() {
        Integer index = page.emptySelect.getSelectionIndex();
        assertThat(index.equals(null), is(true));
    }

    /* validation of mapping */

    @Test
    public final void testValidationOfMapping_singleSelect() {
        assertPageObjectCanBeInitialized(page.singleSelect);
    }

    @Test(expected = WrongElementClassException.class)
    public final void testValidationOfMapping_multiSelect() {
        assertPageObjectCanBeInitialized(page.multiSelect);
    }

    @Test(expected = WrongElementClassException.class)
    public final void testValidationOfMapping_noSelect() {
        assertPageObjectCanBeInitialized(page.notASelect);
    }

    /* utilities */

    public static class SingleSelectTestPage extends PageObject {

        @IdentifyUsing("emptySelect")
        SingleSelect emptySelect;

        @IdentifyUsing("singleSelect")
        SingleSelect singleSelect;

        @IdentifyUsing("singleSelectWithSelection")
        SingleSelect singleSelectWithSelection;

        @IdentifyUsing("multiSelect")
        SingleSelect multiSelect;

        @IdentifyUsing("notASelect")
        SingleSelect notASelect;
    }
}
