package info.novatec.testit.webtester.testng.annotations;


import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.testng.listener.WebTesterTestNGListener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation can be applied to a {@link Browser browser} field and will
 * instruct the {@link WebTesterTestNGListener webtester testng listener} to open the
 * annotation's {@link #value() URL} before each test method is executed. If no
 * value is provided and a default entry point is configured for that browser it
 * will be used.
 *
 * @see Browser
 * @see WebTesterTestNGListener
 * @since 1.2
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface EntryPoint {

    /**
     * The URL to open at the beginning of each test.
     * <p>
     * Defaults to empty string which triggers the use of the default entry
     * point provided by the {@link Browser browser's} {@link Configuration
     * configuration}.
     *
     * @return the URL
     * @see Browser
     * @see Configuration
     * @since 1.2
     */
    String value() default "";

}
