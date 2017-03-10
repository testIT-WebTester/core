package info.novatec.testit.webtester.testng.annotations;


import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.browser.BrowserFactory;
import info.novatec.testit.webtester.api.browser.ProxyConfiguration;
import info.novatec.testit.webtester.browser.NoProxyConfiguration;
import info.novatec.testit.webtester.testng.listener.WebTesterTestNGListener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation can be applied to a {@link Browser browser} field and will
 * instruct the {@link WebTesterTestNGListener webtester testng listener} to create
 * the browser using the given {@link BrowserFactory factory} class. This is
 * done by calling the factory's {@link BrowserFactory#createBrowser()
 * createBrowser()} method.
 *
 * @see WebTesterTestNGListener
 * @since 1.2
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface CreateUsing {

    /**
     * The {@link BrowserFactory factory} to use when initializing the annotated
     * {@link Browser browser} field.
     *
     * @return the factory to use
     * @since 1.2
     */
    Class<? extends BrowserFactory> value();

    /**
     * The {@link ProxyConfiguration proxy configuration} to use when
     * initializing the annotated {@link Browser browser} field.
     *
     * @return the proxy configuration to use
     * @since 1.2
     */
    Class<? extends ProxyConfiguration> proxy() default NoProxyConfiguration.class;

}
