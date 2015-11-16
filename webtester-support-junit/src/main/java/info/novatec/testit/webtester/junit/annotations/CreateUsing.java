package info.novatec.testit.webtester.junit.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.browser.BrowserFactory;
import info.novatec.testit.webtester.api.browser.ProxyConfiguration;
import info.novatec.testit.webtester.browser.NoProxyConfiguration;
import info.novatec.testit.webtester.junit.runner.WebTesterJUnitRunner;


/**
 * This annotation can be applied to a {@link Browser browser} field and will
 * instruct the {@link WebTesterJUnitRunner WebTester JUnit runner} to create
 * the browser using the given {@link BrowserFactory factory} class. This is
 * done by calling the factory's {@link BrowserFactory#createBrowser()
 * createBrowser()} method.
 *
 * @see WebTesterJUnitRunner
 * @since 0.9.2
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface CreateUsing {

    /**
     * The {@link BrowserFactory factory} to use when initializing the annotated
     * {@link Browser browser} field.
     *
     * @return the factory to use
     * @since 0.9.2
     */
    Class<? extends BrowserFactory> value();

    /**
     * The {@link ProxyConfiguration proxy configuration} to use when
     * initializing the annotated {@link Browser browser} field.
     *
     * @return the proxy configuration to use
     * @since 0.9.7
     */
    Class<? extends ProxyConfiguration> proxy() default NoProxyConfiguration.class;

}
