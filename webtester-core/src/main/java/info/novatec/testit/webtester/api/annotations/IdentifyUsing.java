package info.novatec.testit.webtester.api.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.commons.lang.StringUtils;

import info.novatec.testit.webtester.api.enumerations.Caching;
import info.novatec.testit.webtester.api.enumerations.Method;
import info.novatec.testit.webtester.api.events.Event;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * This annotation can be added to fields of {@link PageObject} subclasses.
 * These fields will be initialized via reflection by the framework.
 * <p>
 * <b>Notes:</b>
 * <ul>
 * <li>The visibility (private, protected, public) will be ignored by this
 * mechanism.</li>
 * <li>The field must be a {@link PageObject} or a subclass there of.</li>
 * <li>That subclass must have a default constructor!.</li>
 * <li>The default identification method is {@link Method#ID}!</li>
 * </ul>
 *
 * @since 0.9.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IdentifyUsing {

    /**
     * @return the {@link Caching} setting used when deciding whether or not
     * this page object's web element should be cached.
     * @since 0.9.9
     */
    Caching caching() default Caching.DEFAULT;

    /**
     * @return the {@link Method} to be used when identifying the
     * {@link PageObject}.
     * @since 0.9.0
     */
    Method method() default Method.ID;

    /**
     * @return the value to be used by the identification {@link Method}.
     * @since 0.9.0
     */
    String value();

    /**
     * @return the human readable name of the {@link PageObject} to be used when
     * referring to it in {@link Event Events} or other logging
     * mechanisms.
     * @since 0.9.0
     */
    String elementname() default StringUtils.EMPTY;

}
