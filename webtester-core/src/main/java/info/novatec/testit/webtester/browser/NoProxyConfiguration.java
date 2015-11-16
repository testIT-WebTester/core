package info.novatec.testit.webtester.browser;

import org.openqa.selenium.Proxy;

import info.novatec.testit.webtester.api.browser.ProxyConfiguration;


/**
 * Default implementation of a {@link ProxyConfiguration proxy configuration}
 * doing nothing to with {@link Proxy proxy} object.
 */
public class NoProxyConfiguration implements ProxyConfiguration {

    @Override
    public void configureProxy(Proxy proxy) {
        // do nothing
    }

}
