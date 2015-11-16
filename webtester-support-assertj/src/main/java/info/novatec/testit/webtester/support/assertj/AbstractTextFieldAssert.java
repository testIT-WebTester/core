package info.novatec.testit.webtester.support.assertj;

import info.novatec.testit.webtester.internal.Objects;
import info.novatec.testit.webtester.pageobjects.TextField;


/**
 * Abstract base class for all of WebTester's {@link TextField text field}
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
public abstract class AbstractTextFieldAssert<A extends AbstractTextFieldAssert, B extends TextField>
    extends AbstractPageObjectAssert<A, B> {

    protected AbstractTextFieldAssert(B actual, Class<A> selfType) {
        super(actual, selfType);
    }

    /**
     * Asserts that the {@link TextField text field} has a certain text.
     *
     * @param text the expected text
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public A hasText(String text) {
        failOnActualBeingNull();
        String actualText = actual.getText();
        if (!Objects.equals(actualText, text)) {
            failWithMessage("Expected text field's text to be '%s', but it was '%s'.", text, actualText);
        }
        return ( A ) this;
    }

    /**
     * Asserts that the {@link TextField text field} hasn't got a certain text.
     *
     * @param text not the expected text
     * @return same assertion instance for fluent API false
     * @since 0.9.8
     */
    public A hasNotText(String text) {
        failOnActualBeingNull();
        if (Objects.equals(actual.getText(), text)) {
            failWithMessage("Expected text field's text not to be '%s', but it was.", text);
        }
        return ( A ) this;
    }

}
