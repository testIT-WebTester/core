package info.novatec.testit.webtester.support.assertj;

import info.novatec.testit.webtester.internal.Objects;
import info.novatec.testit.webtester.pageobjects.GenericList;


/**
 * Contains assertions for {@link GenericList generic lists}.
 *
 * @since 1.2.
 */
public class GenericListAssert extends AbstractPageObjectAssert<GenericListAssert, GenericList> {

    public GenericListAssert(GenericList actual) {
        super(actual, GenericListAssert.class);
    }

    /**
     * Asserts that the {@link GenericList generic list} is empty.
     *
     * @return same assertion instance
     * @since 1.2
     */
    public GenericListAssert isEmpty() {
        failOnActualBeingNull();
        if(!actual.isEmpty()){
            failWithMessage("Expected List to be empty, but was not.");
        }
        return this;
    }

    /**
     * Asserts that the {@link GenericList generic list} is not empty.
     *
     * @return same assertion instance
     * @since 1.2
     */
    public GenericListAssert isNotEmpty() {
        failOnActualBeingNull();
        if(actual.isEmpty()){
            failWithMessage("Expected List not to be empty, but it was.");
        }
        return this;
    }

    /**
     * Asserts that the {@link GenericList generic list} contains a certain numberOfItems.
     *
     * @param numberOfItems the expected number of items
     * @return same assertion instance
     * @since 1.2
     */
    public GenericListAssert hasNumberOfItems(int numberOfItems) {
        failOnActualBeingNull();
        Integer actualNumberOfItems = actual.getNumberOfItems();
        if(!Objects.equals(actualNumberOfItems, numberOfItems)){
            failWithMessage("Expected lists size to be %s items, but it was %s.", numberOfItems, actualNumberOfItems);
        }
        return this;
    }

    /**
     * Asserts that the {@link GenericList generic list} doesn't contain a certain numberOfItems.
     *
     * @param numberOfItems the not expected number if items
     * @return same assertion instance
     * @since 1.2
     */
    public GenericListAssert hasNotNumberOfItems(int numberOfItems) {
        failOnActualBeingNull();
        Integer actualNumberOfItems = actual.getNumberOfItems();
        if(Objects.equals(actualNumberOfItems, numberOfItems)){
            failWithMessage("Expected lists size not to be %s items, but it was.", numberOfItems);
        }
        return this;
    }
}
