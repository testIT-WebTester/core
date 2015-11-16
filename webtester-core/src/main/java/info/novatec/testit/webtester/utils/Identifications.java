package info.novatec.testit.webtester.utils;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.pageobjects.Identification;
import info.novatec.testit.webtester.internal.conversion.FindByConverter;
import info.novatec.testit.webtester.internal.conversion.FindBysConverter;


/**
 * Utility class used to produce all kinds of {@link Identification
 * identification} instances.
 *
 * @since 0.9.9
 */
public final class Identifications {

    private Identifications() {
        // utility class constructor
    }

    /**
     * Creates a new {@link Identification identification} using the name of the
     * element's class. Does not support multiple class names! Use {@link #css}
     * in case multiple class names are needed.
     *
     * @param value the class name
     * @return the created identification
     * @since 0.9.9
     */
    public static Identification className(String value) {
        return new Identification(ByConverters.className(), value);
    }

    /**
     * Creates a new {@link Identification identification} using a CSS Selector
     * expression.
     *
     * @param value a css selector expression
     * @return the created identification
     * @since 0.9.9
     */
    public static Identification css(String value) {
        return new Identification(ByConverters.css(), value);
    }

    /**
     * Creates a new {@link Identification identification} using part of an ID
     * to match as the suffix of the ID Attribute.
     *
     * @param value the end of an ID
     * @return the created identification
     * @since 0.9.9
     */
    public static Identification idEndsWith(String value) {
        return new Identification(ByConverters.idEndsWith(), value);
    }

    /**
     * Creates a new {@link Identification identification} using part of an ID
     * to match as the prefix of the ID Attribute.
     *
     * @param value the beginning of an ID
     * @return the created identification
     * @since 0.9.9
     */
    public static Identification idStartsWith(String value) {
        return new Identification(ByConverters.idStartsWith(), value);
    }

    /**
     * Creates a new {@link Identification identification} using the ID
     * Attribute (should be unique on the whole page).
     *
     * @param value the ID
     * @return the created identification
     * @since 0.9.9
     */
    public static Identification id(String value) {
        return new Identification(ByConverters.id(), value);
    }

    /**
     * Creates a new {@link Identification identification} using the text of a
     * link.
     *
     * @param value the link text
     * @return the created identification
     * @since 0.9.9
     */
    public static Identification linkText(String value) {
        return new Identification(ByConverters.linkText(), value);
    }

    /**
     * Creates a new {@link Identification identification} using the name of the
     * element.
     *
     * @param value the name
     * @return the created identification
     * @since 0.9.9
     */
    public static Identification name(String value) {
        return new Identification(ByConverters.name(), value);
    }

    /**
     * Creates a new {@link Identification identification} using a partial text
     * of a link.
     *
     * @param value the partial link text
     * @return the created identification
     * @since 0.9.9
     */
    public static Identification partialLinkText(String value) {
        return new Identification(ByConverters.partialLinkText(), value);
    }

    /**
     * Creates a new {@link Identification identification} using the tag name of
     * an element.
     *
     * @param value the teag name
     * @return the created identification
     * @since 0.9.9
     */
    public static Identification tagName(String value) {
        return new Identification(ByConverters.tagName(), value);
    }

    /**
     * Creates a new {@link Identification identification} using a XPath
     * expression (http://www.w3schools.com/xpath/).
     *
     * @param value the XPath expression
     * @return the created identification
     * @since 0.9.9
     */
    public static Identification xpath(String value) {
        return new Identification(ByConverters.xpath(), value);
    }

    /**
     * Creates an {@link Identification identification} from the given
     * {@link IdentifyUsing @IdentifyUsing} instance.
     *
     * @param identifyUsing the annotation instance to use.
     * @return the created identification
     * @since 0.9.9
     */
    public static Identification fromAnnotation(IdentifyUsing identifyUsing) {
        return new Identification(identifyUsing.method(), identifyUsing.value());
    }

    /**
     * Creates an {@link Identification identification} from the given
     * {@link FindBy @FindBy} instance.
     *
     * @param findBy the annotation instance to use.
     * @return the created identification
     * @since 0.9.9
     */
    public static Identification fromAnnotation(FindBy findBy) {
        return new Identification(new FindByConverter(findBy).buildBy());
    }

    /**
     * Creates an {@link Identification identification} from the given
     * {@link FindBys @FindBys} instance.
     *
     * @param findBys the annotation instance to use.
     * @return the created identification
     * @since 0.9.9
     */
    public static Identification fromAnnotation(FindBys findBys) {
        return new Identification(new FindBysConverter(findBys).buildBy());
    }

}
