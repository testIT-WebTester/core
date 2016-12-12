package integration.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Before;
import org.junit.Test;

import integration.AbstractWebTesterIntegrationTest;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.exceptions.WrongElementClassException;
import info.novatec.testit.webtester.pageobjects.ListItem;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.UnorderedList;

import java.util.List;

/**
 * These tests check aspects of the {@link UnorderedList unordered list} page object which can only
 * be verified by using them on a live web-site with a real {@link Browser
 * browser}. Some of them might also check if we are using Selenium features
 * correctly.
 */
public class UnorderedListIntegrationTest extends AbstractWebTesterIntegrationTest {

    UnorderedListTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(UnorderedListTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobjects/unorderedList.html";
    }

    /* get item */

    /**
     * This test verifies that the correct items are retrieved by the
     * operation getItem(int index).
     */
    @Test
    public final void testThatListItemsCanBeRetrievedByIndex() {
        assertThat(page.fullUnorderedList.getItem(0).getVisibleText(), is("unordered item 1"));
        assertThat(page.fullUnorderedList.getItem(1).getVisibleText(), is("unordered item 2"));
        assertThat(page.fullUnorderedList.getItem(2).getVisibleText(), is("unordered item 3"));
    }

    /**
     * This test verifies that an IndexOutOfBoundsException is
     * thrown if the index is higher or equal to the lists size.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public final void testThatWrongIndexThrowsException() {
        List<ListItem> items = page.fullUnorderedList.getItems();
        assertThat(items.get(3).getVisibleText(), is("unordered item 1"));
    }

    /**
     * This test verifies that an IndexOutOfBoundsException ist thrown
     * even if the passed index is negative.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public final void testThatNegativeIndexThrowsException () {
        List<ListItem> items = page.fullUnorderedList.getItems();
        items.get(-1).getVisibleText();
    }

    /* empty */

    /**
     * This test verifies that 'true' is returned if the list is empty.
     */
    @Test
    public final void testThatEmptyListsReturnsTrueWhenAskedIfEmpty() {
        assertThat(page.emptyList.isEmpty(), is(true));
    }

    /**
     * This test verifies that 'false' is returned if the list is not empty
     */
    @Test
    public final void testThatFullListsReturnsFalseWhenAskedIfEmpty(){
        assertThat(page.fullUnorderedList.isEmpty(), is(false));
    }

    /* number of items */

    /**
     * This test verifies that the number of items of an empty list
     * is returned correctly.
     */
    @Test
    public final void estThatNumberOfItemsFromEmptyListReturnsCorrectSize() {
        assertThat(page.emptyList.getNumberOfItems(), is(0));
    }

    /**
     * This test verifies that the number of items of an filled list
     * is returned correctly.
     */
    @Test
    public final void testThatNumberOfItemsFromFullOrderedListReturnsCorrectSize() {
        assertThat(page.fullUnorderedList.getNumberOfItems(), is(3));
    }

    /* get items */

    /**
     * This test verifies that the returned list can not be modified.
     */
    @Test (expected =UnsupportedOperationException.class)
    public final void testThatReturnedListCanNotBeModified() {
        List<ListItem> items = page.fullUnorderedList.getItems();
        items.clear();
    }

    /**
     * This test verifies that {@link List list} functions can be used
     * with the returned list.
     */
    @Test
    public final void testThatElementCanBeRetrievedAsList() {
        List<ListItem> items = page.fullUnorderedList.getItems();
        assertThat(items.get(0).getVisibleText(), is("unordered item 1"));
        assertThat(items.get(1).getVisibleText(), is("unordered item 2"));
        assertThat(items.get(2).getVisibleText(), is("unordered item 3"));
    }

    /* nested lists */

    /**
     * This test verifies that nested items are ignored.
     */
    @Test
    public final void testThatNumberOfItemsFromNestedListsReturnsCorrectSize() {
        assertThat(page.nestedLists.getNumberOfItems(), is(3));
    }

    /**
     * This test verifies that nested lists are handled correctly. 'Correctly'
     * in this case means: only the list items of the original list are found.
     */
    @Test
    public final void testThatNestedListsReturnsCorrectItems() {
        assertThat(page.nestedLists.getItem(0).getVisibleText(), is("One\nTwo\nThree"));
        assertThat(page.nestedLists.getItem(1).getVisibleText(), is("Four"));
        assertThat(page.nestedLists.getItem(2).getVisibleText(), is("Five"));
    }
    
    /* validation of class */

    @Test
    public final void testValidationOfClass_orderedList() {
        assertPageObjectCanBeInitialized(page.fullUnorderedList);
    }

    @Test(expected = WrongElementClassException.class)
    public final void testValidationOfClass_unorderedList() {
        assertPageObjectCanBeInitialized(page.orderedList);
    }

    @Test(expected = WrongElementClassException.class)
    public final void testValidationOfClass_noList() {
        assertPageObjectCanBeInitialized(page.notAList);
    }

    /* utilities   */

    public static class UnorderedListTestPage extends PageObject{

        @IdentifyUsing("emptyUnorderedList")
        UnorderedList emptyList;

        @IdentifyUsing("fullUnorderedList")
        UnorderedList fullUnorderedList;

        @IdentifyUsing("nestedLists")
        UnorderedList nestedLists;

        @IdentifyUsing("orderedList")
        UnorderedList orderedList;

        @IdentifyUsing("notAList")
        UnorderedList notAList;
    }
}
