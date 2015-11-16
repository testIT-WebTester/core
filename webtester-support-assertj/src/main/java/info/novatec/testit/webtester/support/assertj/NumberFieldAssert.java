package info.novatec.testit.webtester.support.assertj;

import info.novatec.testit.webtester.internal.Objects;
import info.novatec.testit.webtester.pageobjects.NumberField;


/**
 * Contains assertions for {@link NumberField number fields} .
 *
 * @since 0.9.8
 */
public class NumberFieldAssert extends AbstractTextFieldAssert<NumberFieldAssert, NumberField> {

    public NumberFieldAssert(NumberField actual) {
        super(actual, NumberFieldAssert.class);
    }

    /**
     * Asserts that the {@link NumberField number field}} has a certain value.
     *
     * @param value the expected value
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public NumberFieldAssert hasValue(long value) {
        failOnActualBeingNull();
        Long actualValue = actual.getValue();
        if (!Objects.equals(actualValue, value)) {
            failWithMessage("Expected number field's value to be %s, but it was %s.", value, actualValue);
        }
        return this;
    }

    /**
     * Asserts that the {@link NumberField number field}} hasn't got a certain
     * value.
     *
     * @param value the not expected value
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public NumberFieldAssert hasNotValue(long value) {
        failOnActualBeingNull();
        if (Objects.equals(actual.getValue(), value)) {
            failWithMessage("Expected number field's value not to be '%s', but it was.", value);
        }
        return this;
    }

}
