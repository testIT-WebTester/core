package integration.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

import integration.AbstractWebTesterIntegrationTest;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.pageobjects.List;
import info.novatec.testit.webtester.pageobjects.ListItem;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * These tests check aspects of the {@link List list} page object which can only
 * be verified by using them on a live web-site with a real {@link Browser
 * browser}. Some of them might also check if we are using Selenium features
 * correctly.
 */
public class ListIntegrationTest extends AbstractWebTesterIntegrationTest {

    ListTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(ListTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobjects/list.html";
    }

    /* list items */

    /**
     * This test verifies that list items can be retrieved per index and as a
     * list.
     */
    @Test
    public final void testThatListItemsCanBeRetrieved() {

        ListItem listItem1 = page.fullOrderedList.getItem(0);
        ListItem listItem2 = page.fullOrderedList.getItem(1);
        ListItem listItem3 = page.fullOrderedList.getItem(2);

        assertThat(page.fullOrderedList.getItems(), contains(listItem1, listItem2, listItem3));

    }

    /**
     * This test verifies that list items are initialized correctly by using the
     * textual content of a specific item to check wheather or not the correct
     * item was loaded.
     */
    @Test
    public final void testThatListItemIsInitializedCorrectly() {
        ListItem listItem1 = page.fullUnorderedList.getItem(0);
        assertThat(listItem1.getVisibleText(), is("unordered item 1"));
    }

    /**
     * This test verifies that nested lists are handled correctly. 'Correctly'
     * in this case means: only the list items of the orignal list are found.
     * The nested list's items are ignored.
     */
    @Test
    public final void testThatNestedListsAreHandledCorrectly() {
        assertThat(page.nestedLists.getNumberOfItems(), is(3));
        assertThat(page.nestedLists.getItem(0).getVisibleText(), is("One\nTwo\nThree"));
        assertThat(page.nestedLists.getItem(1).getVisibleText(), is("Four"));
        assertThat(page.nestedLists.getItem(2).getVisibleText(), is("Five"));
    }

    /* number of items */

    /**
     * This test verifies that the number of list items can be retrieved. This
     * is done for both types of lists and in a empty/not empty combination.
     */
    @Test
    public final void testThatNumberOfListItemsIsCalculatedCorrectly() {
        assertThat(page.emptyOrderedList.getNumberOfItems(), is(0));
        assertThat(page.fullOrderedList.getNumberOfItems(), is(3));
        assertThat(page.emptyUnorderedList.getNumberOfItems(), is(0));
        assertThat(page.fullUnorderedList.getNumberOfItems(), is(2));
    }

    /* empty */

    /**
     * This test verifies that empty lists are recognized as such. This is done
     * for both types of lists and in a empty/not empty combination.
     */
    @Test
    public final void testThatEmptinessOfListsIsRecognizedCorrectly() {
        assertThat(page.emptyOrderedList.isEmpty(), is(true));
        assertThat(page.fullOrderedList.isEmpty(), is(false));
        assertThat(page.emptyUnorderedList.isEmpty(), is(true));
        assertThat(page.fullUnorderedList.isEmpty(), is(false));
    }

    /* utilities */

    public static class ListTestPage extends PageObject {

        @IdentifyUsing("emptyOrderedList")
        List emptyOrderedList;
        @IdentifyUsing("fullOrderedList")
        List fullOrderedList;
        @IdentifyUsing("emptyUnorderedList")
        List emptyUnorderedList;
        @IdentifyUsing("fullUnorderedList")
        List fullUnorderedList;

        @IdentifyUsing("nestedLists")
        List nestedLists;

    }

}
