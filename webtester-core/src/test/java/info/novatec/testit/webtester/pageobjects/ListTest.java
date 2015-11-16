package info.novatec.testit.webtester.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;

import java.util.LinkedList;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import info.novatec.testit.webtester.AbstractPageObjectTest;


public class ListTest extends AbstractPageObjectTest {

    @Mock
    ListItem listItem1;
    @Mock
    ListItem listItem2;

    @Spy
    java.util.List<ListItem> listItems = new LinkedList<ListItem>();

    @InjectMocks
    List cut;

    /* number of items */

    @Test
    public void testThatNumberOfItemsCanBeRetrieved() {
        addBothItems();
        assertThat(cut.getNumberOfItems(), is(2));
    }

    /* items */

    @Test
    public void testThatItemsCanBeRetrievedAsAList() {
        addBothItems();
        assertThat(cut.getItems(), contains(listItem1, listItem2));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testThatReturnedItemListCantBeModified() {
        addBothItems();
        cut.getItems().clear();
    }

    @Test
    public void testThatAnItemCanBeRetrievedByItsIndex() {
        addBothItems();
        assertThat(cut.getItem(0), is(sameInstance(listItem1)));
        assertThat(cut.getItem(1), is(sameInstance(listItem2)));
    }

    /* empty */

    @Test
    public void testThatEmptyStateCanBeRetrieved_Empty() {
        assertThat(cut.isEmpty(), is(true));
    }

    @Test
    public void testThatEmptyStateCanBeRetrieved_ContainsItem() {
        addBothItems();
        assertThat(cut.isEmpty(), is(false));
    }

    /* correctness of class */

    @Test
    public void testCorrectnessOfClassForWebElement_orderedListTag() {
        stubWebElementTag("ol");
        assertThatCorrectnessOfClassIs(true);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_unorderedListTag() {
        stubWebElementTag("ul");
        assertThatCorrectnessOfClassIs(true);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_otherTag() {
        stubWebElementTag("other");
        assertThatCorrectnessOfClassIs(false);
    }

    /* utilities */

    private void addBothItems() {
        listItems.add(listItem1);
        listItems.add(listItem2);
    }

    private void assertThatCorrectnessOfClassIs(boolean expected) {
        assertThat(cut.isCorrectClassForWebElement(webElement), is(expected));
    }

}
