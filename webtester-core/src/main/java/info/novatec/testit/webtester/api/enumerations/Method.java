package info.novatec.testit.webtester.api.enumerations;

import org.openqa.selenium.By;

import info.novatec.testit.webtester.api.pageobjects.ByConverter;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.utils.ByConverters;


/**
 * Enumeration of identification methods that can be used to identify a
 * {@link PageObject} on a displayed web page.
 *
 * @since 0.9.0
 */
public enum Method implements ByConverter {

    /**
     * Using the ID Attribute (should be unique on the whole page).
     *
     * @since 0.9.0
     */
    ID(ByConverters.id()),

    /**
     * Using part of an ID to match as the prefix of the ID Attribute.
     *
     * @since 0.9.0
     */
    ID_STARTS_WITH(ByConverters.idStartsWith()),

    /**
     * Using part of an ID to match as the suffix of the ID Attribute.
     *
     * @since 0.9.0
     */
    ID_ENDS_WITH(ByConverters.idEndsWith()),

    /**
     * Using a XPath description (http://www.w3schools.com/xpath/).
     *
     * @since 0.9.0
     */
    XPATH(ByConverters.xpath()),

    /**
     * Using a CSS Selector description.
     *
     * @since 0.9.0
     */
    CSS(ByConverters.css()),

    /**
     * Using the name of the element's class. Does not support multiple class
     * names! Use {@link #CSS} in case multiple class names are needed.
     *
     * @since 0.9.9
     */
    CLASS_NAME(ByConverters.className()),

    /**
     * Using the name of the element.
     *
     * @since 0.9.0
     */
    NAME(ByConverters.name()),

    /**
     * Using the text of a link.
     *
     * @since 0.9.3
     */
    LINK_TEXT(ByConverters.linkText()),

    /**
     * Using a partial text of a link.
     *
     * @since 0.9.3
     */
    PARTIAL_LINK_TEXT(ByConverters.partialLinkText()),

    /**
     * Using the name of the tag of the element.
     *
     * @since 0.9.0
     */
    TAGNAME(ByConverters.tagName());

    private final ByConverter delegateConverter;

    Method(ByConverter converter) {
        this.delegateConverter = converter;
    }

    @Override
    public By toBy(String value) {
        return delegateConverter.toBy(value);
    }

    @Override
    public String getValueFormat() {
        return delegateConverter.getValueFormat();
    }

}
