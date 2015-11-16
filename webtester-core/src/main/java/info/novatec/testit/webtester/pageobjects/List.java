package info.novatec.testit.webtester.pageobjects;

import java.util.Collections;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebElement;

import com.google.common.collect.Sets;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.callbacks.PageObjectCallbackWithReturnValue;
import info.novatec.testit.webtester.api.enumerations.Method;


/**
 * Represents a HTML list. The following HTML elements are supported:
 * <ul>
 * <li><b>tag:</b> ul</li>
 * <li><b>tag:</b> ol</li>
 * </ul>
 *
 * @since 0.9.0
 */
public class List extends PageObject {

    private static final Set<String> VALID_TAGS = Sets.newHashSet("ul", "ol");

    @IdentifyUsing(method = Method.XPATH, value = "./li")
    private java.util.List<ListItem> listItems;

    /**
     * Returns the number of {@link ListItem list items} of this {@link List
     * list}.
     *
     * @return the number of items in this list
     * @since 0.9.6
     */
    public Integer getNumberOfItems() {
        return listItems.size();
    }

    /**
     * Returns all {@link ListItem list items} of this {@link List list} as an
     * unmodifiable list (collection).
     *
     * @return list of all direct list items of this list
     * @since 0.9.6
     */
    public java.util.List<ListItem> getItems() {
        return Collections.unmodifiableList(listItems);
    }

    /**
     * Returns the {@link ListItem list item} for the given index.
     *
     * @param index of the list item to return
     * @return the list item
     * @throws IndexOutOfBoundsException if given index does not exist
     * @since 0.9.6
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
     * Returns whether or not the {@link List list} is empty (does not contain
     * any {@link ListItem list items}).
     *
     * @return true if empty otherwise false
     * @since 0.9.6
     */
    public Boolean isEmpty() {
        return listItems.isEmpty();
    }

    @Override
    protected boolean isCorrectClassForWebElement(WebElement webElement) {
        String tagName = StringUtils.defaultString(webElement.getTagName());
        String lowerCasedTagName = tagName.toLowerCase();
        return VALID_TAGS.contains(lowerCasedTagName);
    }

}
