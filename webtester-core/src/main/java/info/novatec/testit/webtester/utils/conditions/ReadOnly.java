package info.novatec.testit.webtester.utils.conditions;

import com.google.common.base.Predicate;

import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Predicate to be used in order to check if a {@link PageObject page object} is
 * 'read-only'. I.g. read-only elements are input elements which are locked
 * against value change but can still be edited.
 * <p>
 * This condition is true in case the 'readonly' attribute is either 'true'
 * (HTML) or 'readonly' (XHTML).
 *
 * @since 0.9.9
 */
public class ReadOnly implements Predicate<PageObject> {

    @Override
    public boolean apply(PageObject pageObject) {
        String attributeValue = pageObject.getAttribute("readonly");
        return isHtmlReadOnly(attributeValue) || isXhtmlReadOnly(attributeValue);
    }

    private boolean isHtmlReadOnly(String value) {
        return "true".equals(value);
    }

    private boolean isXhtmlReadOnly(String value) {
        return "readonly".equals(value);
    }

    @Override
    public String toString() {
        return "read-only";
    }

}
