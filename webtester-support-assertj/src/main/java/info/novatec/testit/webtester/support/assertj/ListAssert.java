package info.novatec.testit.webtester.support.assertj;

import info.novatec.testit.webtester.internal.Objects;
import info.novatec.testit.webtester.pageobjects.List;


/**
 * Contains assertions for {@link List lists}.
 *
 * @since 0.9.8
 */
public class ListAssert extends AbstractPageObjectAssert<ListAssert, List> {

    public ListAssert(List actual) {
        super(actual, ListAssert.class);
    }

    /**
     * Asserts that the {@link List list} has a certain number of items.
     *
     * @param numberOfItems the expected number of items
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public ListAssert hasNumberOfItems(int numberOfItems) {
        failOnActualBeingNull();
        Integer actualNumberOfItems = actual.getNumberOfItems();
        if (!Objects.equals(actualNumberOfItems, numberOfItems)) {
            failWithMessage("Expected lists's size to be %s, but it was %s.", numberOfItems, actualNumberOfItems);
        }
        return this;
    }

    /**
     * Asserts that the {@link List list} hasn't got a certain number of items.
     *
     * @param numberOfItems the not expected number of items
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public ListAssert hasNotNumberOfItems(int numberOfItems) {
        failOnActualBeingNull();
        if (Objects.equals(actual.getNumberOfItems(), numberOfItems)) {
            failWithMessage("Expected lists's size not to be %s, but it was.", numberOfItems);
        }
        return this;
    }

    /**
     * Asserts that the {@link List list} is empty.
     *
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public ListAssert isEmpty() {
        failOnActualBeingNull();
        if (!actual.isEmpty()) {
            failWithMessage("Expected list to be empty, but it wasn't.");
        }
        return this;
    }

    /**
     * Asserts that the {@link List list} isn't empty.
     *
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public ListAssert isNotEmpty() {
        failOnActualBeingNull();
        if (actual.isEmpty()) {
            failWithMessage("Expected list not to be empty, but it was.");
        }
        return this;
    }

}
