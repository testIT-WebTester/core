package info.novatec.testit.webtester.api.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * This annotation can be added to fields of {@link PageObject} subclasses.
 * These fields will then be checked for visibility via reflection by the
 * framework. After it completed the initialization of the {@link PageObject}.
 * <p>
 * <b>Notes:</b>
 * <ul>
 * <li>The visibility of the field (private, protected, public) will be ignored
 * by this mechanism.</li>
 * <li>The field must have PageObject or a subclass there of as type.</li>
 * </ul>
 *
 * @since 0.9.3
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Visible {

    /**
     * Only applies to lists of {@link PageObject page objects}!
     *
     * @return the number of elements of a list expected to be visible.
     * @since 0.9.8
     */
    int value() default 0;

}
