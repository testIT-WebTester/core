package info.novatec.testit.webtester.utils.conditions.syntax;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Predicate;


/**
 * Predicate which returns the result of any number of OR evaluated predicates.
 * The first positively evaluated predicate will lead to <code>true</code> being
 * returned. This is intended to be used to enhance code readability.
 * <p>
 * <b>Example:</b>
 * <code>Waits.waitUntil(textfield, has(either(text("foo"), text("bar"))));</code>
 *
 * @param <T> type of the wrapped predicate
 * @since 0.9.9
 */
public class Either<T> implements Predicate<T> {

    private List<Predicate<T>> predicates;

    @SafeVarargs
    public Either(Predicate<T>... predicates) {
        this.predicates = Arrays.asList(predicates);
    }

    @Override
    public boolean apply(T pageObject) {
        for (Predicate<T> predicate : predicates) {
            if (predicate.apply(pageObject)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "either(" + StringUtils.join(predicates, ", ") + ')';
    }

}
