package info.novatec.testit.webtester.pageobjects;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.annotations.Mapping;
import info.novatec.testit.webtester.api.annotations.Mappings;
import info.novatec.testit.webtester.api.callbacks.PageObjectCallbackWithReturnValue;
import info.novatec.testit.webtester.api.enumerations.Method;

import java.util.Collections;

@Mappings({@Mapping(tag = "ol"), @Mapping(tag = "ul")})
public class GenericList extends PageObject{

    /**
     * Returns a list of this {@link GenericList list's} {@link ListItem items}.
     *
     * These are only the direct children of this list. Nested lists will not be resolved!
     *
     * @return the list item children of this list
     * @see GenericList
     * @see ListItem
     * @see OrderedList
     * @see UnorderedList
     * @since 1.2
     */
    @IdentifyUsing(method = Method.XPATH, value = "./li")
    private java.util.List<ListItem> listItems;


    /**
     * Returns whether or not the {@link List list} is empty (does not contain
     * any {@link ListItem list items}).
     *
     * @return true if empty otherwise false
     * @see GenericList
     * @see ListItem
     * @see OrderedList
     * @see UnorderedList
     * @since 1.2
     */
    public Boolean isEmpty() {
        return listItems.isEmpty();
    }

    /**
     * Returns the number of {@link ListItem list items} of this {@link List
     * list}.
     *
     * @return the number of items in this list
     * @see GenericList
     * @see ListItem
     * @see OrderedList
     * @see UnorderedList
     * @since 1.2
     */
    public Integer getNumberOfItems() {
        return listItems.size();
    }

    /**
     * Returns the {@link ListItem list item} for the given index.
     *
     * @param index of the list item to return
     * @return the list item
     * @throws IndexOutOfBoundsException if given index does not exist
     * @see GenericList
     * @see ListItem
     * @see OrderedList
     * @see UnorderedList
     * @since 1.2
     */
    public ListItem getItem(final int index) {
        return executeAction(new PageObjectCallbackWithReturnValue<ListItem>() {

            @Override
            public ListItem execute(PageObject pageObject) {
                return listItems.get(index);
            }

        });
    }

    /**
     * Returns all {@link ListItem list items} of this {@link List list} as an
     * unmodifiable list (collection).
     *
     * @return list of all direct list items of this list
     * @see ListItem
     * @see GenericList
     * @see OrderedList
     * @see UnorderedList
     * @since 1.2
     */
    public java.util.List<ListItem> getItems() {
        return Collections.unmodifiableList(listItems);
    }




}
