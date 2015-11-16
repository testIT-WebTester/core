package info.novatec.testit.webtester.support.assertj;

import info.novatec.testit.webtester.internal.Objects;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Abstract base class for all of WebTester's {@link PageObject page object}
 * related AssertJ assertion classes.
 *
 * @param <A> the "self" type of this assertion class. Please read &quot;
 * <a href="http://bit.ly/anMa4g" target="_blank">Emulating 'self types'
 * using Java Generics to simplify fluent API implementation</a>&quot;
 * for more details.
 * @param <B> the type of the "actual" value.
 * @since 0.9.8
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class AbstractPageObjectAssert<A extends AbstractPageObjectAssert, B extends PageObject>
    extends AbstractWebTesterAssert<A, B> {

    protected AbstractPageObjectAssert(B actual, Class<A> selfType) {
        super(actual, selfType);
    }

    /**
     * Asserts that the {@link PageObject page object} has a certain attribute
     * with a specific value.
     *
     * @param attributeName the attribute's name
     * @param value the expected value of the attribute - it's toString() method
     * is used to compare it to the actual attribute value!
     * @return same assertion instance for fluent API false
     * @since 0.9.9
     */
    public A hasAttributeWithValue(String attributeName, Object value) {
        failOnActualBeingNull();
        String actualValue = actual.getAttribute(attributeName);
        if (!Objects.equals(actualValue, String.valueOf(value))) {
            failWithMessage("Expected page object's '%s' value to be '%s', but it was '%s'.", attributeName, value,
                actualValue);
        }
        return ( A ) this;
    }

    /**
     * Asserts that the {@link PageObject page object} hasn't got a certain
     * attribute with a specific value.
     *
     * @param attributeName the attribute's name
     * @param value the expected value of the attribute - it's toString() method
     * is used to compare it to the actual attribute value!
     * @return same assertion instance for fluent API false
     * @since 0.9.9
     */
    public A hasNotAttributeWithValue(String attributeName, Object value) {
        failOnActualBeingNull();
        if (Objects.equals(actual.getAttribute(attributeName), String.valueOf(value))) {
            failWithMessage("Expected page object's '%s' value not to be '%s', but it was.", attributeName, value);
        }
        return ( A ) this;
    }

    /**
     * Asserts that the {@link PageObject page object} has a certain visible
     * text.
     *
     * @param text the expected text
     * @return same assertion instance for fluent API false
     * @since 0.9.8
     */
    public A hasVisibleText(String text) {
        failOnActualBeingNull();
        String actualVisibleText = actual.getVisibleText();
        if (!Objects.equals(actualVisibleText, text)) {
            failWithMessage("Expected page object's visible text to be '%s', but it was '%s'.", text, actualVisibleText);
        }
        return ( A ) this;
    }

    /**
     * Asserts that the {@link PageObject page object} hasn't got a certain
     * visible text.
     *
     * @param text the not expected text
     * @return same assertion instance for fluent API false
     * @since 0.9.8
     */
    public A hasNotVisibleText(String text) {
        failOnActualBeingNull();
        if (Objects.equals(actual.getVisibleText(), text)) {
            failWithMessage("Expected page object's visible text not to be '%s', but it was.", text);
        }
        return ( A ) this;
    }

    /**
     * Asserts that the {@link PageObject page object} is visible.
     *
     * @return same assertion instance for fluent API false
     * @since 0.9.8
     */
    public A isVisible() {
        failOnActualBeingNull();
        if (!actual.isVisible()) {
            failWithMessage("Expected page object to be visible, but it wasn't.");
        }
        return ( A ) this;
    }

    /**
     * Asserts that the {@link PageObject page object} isn't visible.
     *
     * @return same assertion instance for fluent API false
     * @since 0.9.8
     */
    public A isNotVisible() {
        failOnActualBeingNull();
        if (actual.isVisible()) {
            failWithMessage("Expected page object not to be visible, but it was.");
        }
        return ( A ) this;
    }

    /**
     * Asserts that the {@link PageObject page object} is enabled.
     *
     * @return same assertion instance for fluent API false
     * @since 0.9.8
     */
    public A isEnabled() {
        failOnActualBeingNull();
        if (!actual.isEnabled()) {
            failWithMessage("Expected page object to be enabled, but it wasn't.");
        }
        return ( A ) this;
    }

    /**
     * Asserts that the {@link PageObject page object} isn't enabled.
     *
     * @return same assertion instance for fluent API false
     * @since 0.9.8
     */
    public A isNotEnabled() {
        failOnActualBeingNull();
        if (actual.isEnabled()) {
            failWithMessage("Expected page object not to be enabled, but it was.");
        }
        return ( A ) this;
    }

}
