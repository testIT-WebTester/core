package info.novatec.testit.webtester.junit.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.junit.runner.WebTesterJUnitRunner;


/**
 * This annotation can be applied to a {@link Browser browser} field and will
 * instruct the {@link WebTesterJUnitRunner WebTester JUnit runner} to open the
 * annotation's {@link #value() URL} before each test method is executed. If no
 * value is provided and a default entry point is configured for that browser it
 * will be used.
 *
 * @see Browser
 * @see WebTesterJUnitRunner
 * @since 0.9.1
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
     * @since 0.9.1
     */
    String value() default "";

}
