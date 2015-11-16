package info.novatec.testit.webtester.utils.conditions;

import com.google.common.base.Predicate;

import info.novatec.testit.webtester.api.pageobjects.traits.HasValue;
import info.novatec.testit.webtester.internal.Objects;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Predicate to be used in order to check if a {@link PageObject page object}
 * has a certain {@link HasValue value}.
 *
 * @param <T> the type of the value
 * @since 0.9.9
 */
public class Value<T> implements Predicate<HasValue<T>> {

    private T value;

    public Value(T value) {
        this.value = value;
    }

    @Override
    public boolean apply(HasValue<T> hasValue) {
        return Objects.equals(value, hasValue.getValue());
    }

    @Override
    public String toString() {
        return String.format("value: %s", value);
    }

}
