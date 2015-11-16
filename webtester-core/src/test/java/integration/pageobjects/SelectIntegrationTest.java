package integration.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

import integration.AbstractWebTesterIntegrationTest;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.Select;


/**
 * These tests check aspects of the {@link Select select} page object which can
 * only be verified by using them on a live web-site with a real {@link Browser
 * browser}. Some of them might also check if we are using Selenium features
 * correctly.
 */
public class SelectIntegrationTest extends AbstractWebTesterIntegrationTest {

    SelectTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(SelectTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobjects/select.html";
    }

    /* select by text */

    @Test
    public void testThatSelectByTextWorksAsIntended_SingleSelect() {
        page.singleSelectWithSelection.selectByText("one", "three");
        assertThat(page.singleSelectWithSelection.getAllSelectedTexts(), contains("three"));
    }

    @Test
    public void testThatSelectByTextWorksAsIntended_MultiSelect() {
        page.multiSelectWithSelection.selectByText("one", "three");
        assertThat(page.multiSelectWithSelection.getAllSelectedTexts(), contains("one", "three"));
    }

    /* select by value */

    @Test
    public void testThatSelectByValueWorksAsIntended_SingleSelect() {
        page.singleSelectWithSelection.selectByValue("1", "3");
        assertThat(page.singleSelectWithSelection.getAllSelectedValues(), contains("3"));
    }

    @Test
    public void testThatSelectByValueWorksAsIntended_MultiSelect() {
        page.multiSelectWithSelection.selectByValue("1", "3");
        assertThat(page.multiSelectWithSelection.getAllSelectedValues(), contains("1", "3"));
    }

    /* select by index */

    @Test
    public void testThatSelectByIndexWorksAsIntended_SingleSelect() {
        page.singleSelectWithSelection.selectByIndex(0, 2);
        assertThat(page.singleSelectWithSelection.getAllSelectedIndices(), contains(2));
    }

    @Test
    public void testThatSelectByIndexWorksAsIntended_MultiSelect() {
        page.multiSelectWithSelection.selectByIndex(0, 2);
        assertThat(page.multiSelectWithSelection.getAllSelectedIndices(), contains(0, 2));
    }

    /* first selected text */

    @Test
    public void testThatGettingFirstSelectedTextWorksAsIntended_SingleSelect() {
        assertThat(page.singleSelectWithSelection.getFirstSelectedText(), is("two"));
    }

    @Test
    public void testThatGettingFirstSelectedTextWorksAsIntended_MultiSelect() {
        assertThat(page.multiSelectWithSelection.getFirstSelectedText(), is("two"));
    }

    /* all selected texts */

    @Test
    public void testThatGettingAllSelectedTextsWorksAsIntended_SingleSelect() {
        assertThat(page.singleSelectWithSelection.getAllSelectedTexts(), contains("two"));
    }

    @Test
    public void testThatGettingallSelectedTextsWorksAsIntended_MultiSelect() {
        assertThat(page.multiSelectWithSelection.getAllSelectedTexts(), contains("two", "three"));
    }

    /* first selected value */

    @Test
    public void testThatGettingFirstSelectedValueWorksAsIntended_SingleSelect() {
        assertThat(page.singleSelectWithSelection.getFirstSelectedValue(), is("2"));
    }

    @Test
    public void testThatGettingFirstSelectedValueWorksAsIntended_MultiSelect() {
        assertThat(page.multiSelectWithSelection.getFirstSelectedValue(), is("2"));
    }

    /* all selected values */

    @Test
    public void testThatGettingAllSelectedValuesWorksAsIntended_SingleSelect() {
        assertThat(page.singleSelectWithSelection.getAllSelectedValues(), contains("2"));
    }

    @Test
    public void testThatGettingallSelectedValuesWorksAsIntended_MultiSelect() {
        assertThat(page.multiSelectWithSelection.getAllSelectedValues(), contains("2", "3"));
    }

    /* first selected index */

    @Test
    public void testThatGettingFirstSelectedIndexWorksAsIntended_SingleSelect() {
        assertThat(page.singleSelectWithSelection.getFirstSelectedIndex(), is(1));
    }

    @Test
    public void testThatGettingFirstSelectedIndexWorksAsIntended_MultiSelect() {
        assertThat(page.multiSelectWithSelection.getFirstSelectedIndex(), is(1));
    }

    /* all selected indices */

    @Test
    public void testThatGettingAllSelectedIndicesWorksAsIntended_SingleSelect() {
        assertThat(page.singleSelectWithSelection.getAllSelectedIndices(), contains(1));
    }

    @Test
    public void testThatGettingallSelectedIndicesWorksAsIntended_MultiSelect() {
        assertThat(page.multiSelectWithSelection.getAllSelectedIndices(), contains(1, 2));
    }

    /* all texts */

    @Test
    public void testThatGettingAllTextsWorksAsIntended_SingleSelect() {
        assertThat(page.singleSelect.getAllTexts(), contains("one", "two", "three"));
    }

    @Test
    public void testThatGettingallTextsWorksAsIntended_MultiSelect() {
        assertThat(page.multiSelect.getAllTexts(), contains("one", "two", "three"));
    }

    /* all values */

    @Test
    public void testThatGettingAllValuesWorksAsIntended_SingleSelect() {
        assertThat(page.singleSelect.getAllValues(), contains("1", "2", "3"));
    }

    @Test
    public void testThatGettingallValuesWorksAsIntended_MultiSelect() {
        assertThat(page.multiSelect.getAllValues(), contains("1", "2", "3"));
    }

    /* number of selected options */

    @Test
    public void testThatGettingTheNumberOfSelectedOptionsWorksAsIntended_SingleSelect() {
        assertThat(page.singleSelectWithSelection.getNumberOfSelectedOptions(), is(1));
    }

    @Test
    public void testThatGettingTheNumberOfSelectedOptionsWorksAsIntended_MultiSelect() {
        assertThat(page.multiSelectWithSelection.getNumberOfSelectedOptions(), is(2));
    }

    /* number of options */

    @Test
    public void testThatGettingTheNumberOfOptionsWorksAsIntended_SingleSelect() {
        assertThat(page.singleSelect.getNumberOfOptions(), is(3));
    }

    @Test
    public void testThatGettingTheNumberOfOptionsWorksAsIntended_MultiSelect() {
        assertThat(page.multiSelect.getNumberOfOptions(), is(3));
    }

    /* utilities */

    public static class SelectTestPage extends PageObject {

        @IdentifyUsing("singleSelect")
        Select singleSelect;
        @IdentifyUsing("singleSelectWithSelection")
        Select singleSelectWithSelection;

        @IdentifyUsing("multiSelect")
        Select multiSelect;
        @IdentifyUsing("multiSelectWithSelection")
        Select multiSelectWithSelection;

    }

}
