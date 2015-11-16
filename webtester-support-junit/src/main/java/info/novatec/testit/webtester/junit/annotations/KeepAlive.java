package info.novatec.testit.webtester.junit.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.junit.runner.WebTesterJUnitRunner;


/**
 * This annotation can be applied to a {@link Browser browser} field and will
 * keep the {@link WebTesterJUnitRunner WebTester JUnit runner} from closing
 * that particular browser instance. This is not recommended for regression
 * testing! It is strictly intended to be used during <u>development</u> to
 * preserver the exit state of the browser for further debugging.
 *
 * @since 0.9.1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface KeepAlive {
    // no properties
}
