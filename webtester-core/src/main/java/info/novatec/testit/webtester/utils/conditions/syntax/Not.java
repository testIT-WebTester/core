package info.novatec.testit.webtester.utils.conditions.syntax;

import com.google.common.base.Predicate;


/**
 * Predicate which returns the <b>negated</b> result of another predicate. This
 * is intended to be used to check for a condition to <u>not</u> be met.
 * <p>
 * <b>Example:</b> <code>Waits.waitUntil(checkbox, is(not(selected())));</code>
 *
 * @param <T> type of the wrapped predicate
 * @since 0.9.9
 */
public class Not<T> implements Predicate<T> {

    private Predicate<T> predicate;

    public Not(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean apply(T pageObject) {
        return !predicate.apply(pageObject);
    }

    @Override
    public String toString() {
        return "not(" + predicate + ')';
    }

}
