package info.novatec.testit.webtester.api.browser;

import org.openqa.selenium.Proxy;


/**
 * Classes implementing this interface are used to configure a {@link Proxy
 * proxy}. They can be provided when initializing a browser via a
 * {@link BrowserFactory factory}.
 *
 * @since 0.9.2
 */
public interface ProxyConfiguration {

    /**
     * This is called after the {@link Proxy proxy} object was initialized by
     * the framework. The given proxy should be configured as needed.
     *
     * @param proxy the proxy object to configure
     * @since 0.9.2
     */
    void configureProxy(Proxy proxy);

}
