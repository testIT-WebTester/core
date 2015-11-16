package info.novatec.testit.webtester.utils.conditions.syntax;

import com.google.common.base.Predicate;


/**
 * Predicate which returns the result of another predicate. This is intended to
 * be used to enhance code readability.
 * <p>
 * <b>Example:</b> <code>Waits.waitUntil(textfield, has(text("foo")));</code> is
 * more readable then <code>Waits.waitUntil(textfield, text("foo"));</code>
 *
 * @param <T> type of the wrapped predicate
 * @since 0.9.9
 */
public class Has<T> implements Predicate<T> {

    private Predicate<T> predicate;

    public Has(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean apply(T pageObject) {
        return predicate.apply(pageObject);
    }

    @Override
    public String toString() {
        return "has(" + predicate + ')';
    }

}
