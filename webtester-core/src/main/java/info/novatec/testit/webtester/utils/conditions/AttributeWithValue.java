package info.novatec.testit.webtester.utils.conditions;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;

import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Predicate to be used in order to check if a specific attribute of a
 * {@link PageObject page object} has a certain value.
 *
 * @since 0.9.9
 */
public class AttributeWithValue implements Predicate<PageObject> {

    private String attributeName;
    private String expectedValue;

    public AttributeWithValue(String attributeName, Object expectedValue) {
        this.attributeName = attributeName;
        this.expectedValue = String.valueOf(expectedValue);
    }

    @Override
    public boolean apply(PageObject pageObject) {
        String actualValue = pageObject.getAttribute(attributeName);
        return Objects.equal(expectedValue, actualValue);
    }

    @Override
    public String toString() {
        return String.format("attribute '%s' with value '%s'", attributeName, expectedValue);
    }

}
