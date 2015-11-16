package info.novatec.testit.webtester.api.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * This annotation marks a method or class as experimental to use. These methods or
 * classes should be used with caution. They might be buggy, removed or
 * changed at any time without a proper deprecation process!
 *
 * @since 0.9.9
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
public @interface Experimental {
    // no properties
}
