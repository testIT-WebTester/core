package info.novatec.testit.webtester.internal.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a field as 'injected'. This annotation is not used pragmatically.
 * It exists just to clarify code pieces where some 'magic' happens.
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface SetViaInjection {
    // NOPROPS
}
