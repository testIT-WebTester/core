package info.novatec.testit.webtester.testng.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import info.novatec.testit.webtester.api.browser.Browser;

/**
 * This annotation can be applied to a {@link Browser browser} field and will
 * mark that browser as the 'primary' browser for the test class. If your test
 * is not using more then one browser instance - this annotation is not
 * necessary.
 * <p>
 * <b>Usecases:</b>
 * <ul>
 * <li>{@link ConfigurationValue} - the primary browser's configuration is used
 * to inject configuration properties into fields of the test class when
 * injecting is used</li>
 * </ul>
 *
 * @since 1.2
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface Primary {
    // no properties
}
