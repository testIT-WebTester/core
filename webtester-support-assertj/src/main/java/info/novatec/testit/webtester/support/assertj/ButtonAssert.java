package info.novatec.testit.webtester.support.assertj;

import info.novatec.testit.webtester.internal.Objects;
import info.novatec.testit.webtester.pageobjects.Button;


/**
 * Contains assertions for {@link Button buttons}.
 *
 * @since 0.9.8
 */
public class ButtonAssert extends AbstractPageObjectAssert<ButtonAssert, Button> {

    public ButtonAssert(Button actual) {
        super(actual, ButtonAssert.class);
    }

    /**
     * Asserts that the {@link Button button} has a certain label.
     *
     * @param label the expected label
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public ButtonAssert hasLabel(String label) {
        failOnActualBeingNull();
        String actualLabel = actual.getLabel();
        if (!Objects.equals(actualLabel, label)) {
            failWithMessage("Expected buttons's label to be '%s', but it was '%s'.", label, actualLabel);
        }
        return this;
    }

    /**
     * Asserts that the {@link Button button} hasn't got a certain label.
     *
     * @param label the not expected label
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public ButtonAssert hasNotLabel(String label) {
        failOnActualBeingNull();
        if (Objects.equals(actual.getLabel(), label)) {
            failWithMessage("Expected buttons's label not to be '%s', but it was.", label);
        }
        return this;
    }

    /**
     * Asserts that the {@link Button button} has a certain value.
     *
     * @param value the asserted value
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public ButtonAssert hasValue(String value) {
        failOnActualBeingNull();
        String actualValue = actual.getValue();
        if (!Objects.equals(actualValue, value)) {
            failWithMessage("Expected buttons's value to be '%s', but it was '%s'.", value, actualValue);
        }
        return this;
    }

    /**
     * Asserts that the {@link Button button} hasn't got a certain value.
     *
     * @param value the asserted value
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public ButtonAssert hasNotValue(String value) {
        failOnActualBeingNull();
        if (Objects.equals(actual.getValue(), value)) {
            failWithMessage("Expected buttons's value not to be '%s', but it was.", value);
        }
        return this;
    }

}
