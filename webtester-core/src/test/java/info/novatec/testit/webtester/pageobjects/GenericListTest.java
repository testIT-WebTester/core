package info.novatec.testit.webtester.pageobjects;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.LinkedList;

import info.novatec.testit.webtester.AbstractPageObjectTest;
import info.novatec.testit.webtester.api.exceptions.WrongElementClassException;


public class GenericListTest extends AbstractPageObjectTest{
    @Mock
    ListItem item1;

    @Mock
    ListItem item2;

    @Spy
    java.util.List<ListItem> listItems = new LinkedList<ListItem>();

    @InjectMocks
    GenericList cut;

    /*  empty   */

    @Test
    public void testThatEmptyStateCanBeRetrieved_empty() {
        assertThat(cut.isEmpty(), is(true));
    }

    @Test
    public void testThatEmptyStateCanBeRetrieved_containsItems() {
        addBothListItems();
        assertThat(cut.isEmpty(), is(false));
    }

    /*  number of items    */

    @Test
    public void testThatNumberOfItemsCanBeRetrieved_empty() {
        assertThat(cut.getNumberOfItems(), is(0));
    }

    @Test
    public void testThatNumberOfItemsCanBeRetrieved_containsItems() {
        addBothListItems();
        assertThat(cut.getNumberOfItems(), is(2));
    }

    /* get item */

    @Test (expected = IndexOutOfBoundsException.class)
    public void testThatItemCanBeRetrievedOutOfList_Empty() {
        assertThat(cut.getItem(0), is(item1));
    }

    @Test
    public void testThatCorrectItemCanBeRetrievedOutOfList_containsItems() {
        addBothListItems();
        assertThat(cut.getItem(0), is(item1));
        assertThat(cut.getItem(1), is(item2));
    }

    /*  get items     */

    @Test
    public void testThatItemsCanBeRetrievedAsList_containsItems() {
        addBothListItems();
        assertThat(cut.getItems(), contains(item1, item2));
    }

    @Test (expected = UnsupportedOperationException.class)
    public void testThatRetrievedItemListCanNotBeModified() {
        addBothListItems();
        cut.getItems().clear();
    }

    /*  correctness of class    */

    @Test
    public void testCorrectnessOfClassForWebElement_orderedList() {
        stubWebElementTag("ol");
        cut.validate(webElement);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_unorderedList() {
        stubWebElementTag("ul");
        cut.validate(webElement);
    }

    @Test (expected = WrongElementClassException.class)
    public void testCorrectnessOfClassForWebElement_other() {
        stubWebElementTag("other");
        cut.validate(webElement);
    }

    /*  utilities    */

    private void addBothListItems() {
        listItems.add(item1);
        listItems.add(item2);
    }
}
