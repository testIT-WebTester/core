package info.novatec.testit.webtester.testng.annotations;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.testng.listener.WebTesterTestNGListener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation can be applied to a {@link Browser browser} field and will
 * keep the {@link WebTesterTestNGListener webtester testng listener} from closing
 * that particular browser instance. This is not recommended for regression
 * testing! It is strictly intended to be used during <u>development</u> to
 * preserver the exit state of the browser for further debugging.
 *
 * @since 1.2
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface KeepAlive {
    // no properties
}
