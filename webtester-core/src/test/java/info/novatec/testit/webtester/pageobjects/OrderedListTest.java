package info.novatec.testit.webtester.pageobjects;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.contains;

import java.lang.invoke.WrongMethodTypeException;
import java.util.LinkedList;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import info.novatec.testit.webtester.AbstractPageObjectTest;
import info.novatec.testit.webtester.api.exceptions.WrongElementClassException;


public class OrderedListTest extends AbstractPageObjectTest{

    @Mock
    ListItem item1;

    @Mock
    ListItem item2;

    @Spy
    java.util.List<ListItem> itemList = new LinkedList<ListItem>();

    @InjectMocks
    OrderedList cut;

    /* empty */

    @Test
    public final void testThatListIsEmpty_EmptyList() {
        assertThat(cut.isEmpty(), is(true));
    }

    @Test
    public final void testThatListIsEmpty_FilledList() {
        addBothItems();
        assertThat(cut.isEmpty(), is(false));
    }

    /* number of items */

    @Test
    public final void testThatListHasCorrectNumberOfItems_EmptyList()  {
        assertThat(cut.getNumberOfItems(), is(0));
    }

    @Test
    public final void testThatListHasCorrectNumberOfItems_FilledList() {
        addBothItems();
        assertThat(cut.getNumberOfItems(), is(2));
    }

    /* get item */

    @Test
    public final void testThatItemCanBeRetrievedCorrectly() {
        addBothItems();
        assertThat(cut.getItem(0), is(item1));
        assertThat(cut.getItem(1), is(item2));
    }

    /* get items */

    @Test(expected = UnsupportedOperationException.class)
    public final void testThatListCanNotBeModifiedAfterReturnedByGetItems() {
        cut.getItems().clear();
    }

    @Test
    public final void testThatListCanBeRetrievedAsAList() {
        addBothItems();
        assertThat(cut.getItems(), contains(item1,item2));
    }

    /* correctness of class */

    @Test
    public final void testCorrectnessOfClassForWebElement_orderedListTag() {
        stubWebElementTag("ol");
        cut.validate(webElement);
    }

    @Test(expected = WrongElementClassException.class)
    public final void testCorrectnessOfClassForWebElement_unorderedListTag() {
        stubWebElementTag("ul");
        cut.validate(webElement);
    }

    @Test(expected = WrongElementClassException.class)
    public final void testCorrectnessOfClassForWebElement_otherTag() {
        stubWebElementTag("other");
        cut.validate(webElement);
    }

    /* utilities */

    private void addBothItems() {
        itemList.add(item1);
        itemList.add(item2);
    }


}
