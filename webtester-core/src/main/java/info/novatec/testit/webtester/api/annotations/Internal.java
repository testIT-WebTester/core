package info.novatec.testit.webtester.api.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * This annotation marks a method or class for internal use. These methods or
 * classes should not be used by outside components. They might be removed or
 * changed at any time without a proper deprecation process!
 *
 * @since 0.9.6
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
public @interface Internal {
    // no properties
}
