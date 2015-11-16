package info.novatec.testit.webtester.api.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


/**
 * Implementations of this interface are used to initialize {@link WebDriver web
 * driver} instances and package them as {@link Browser browsers}.
 * <p>
 * The most important method of a browser factory is {@link #createBrowser()}.
 * It is used by all kinds of reflection based operations to initialize a
 * browser using all the implemented defaults of the actual factory
 * implementation. For more flexibility the
 * {@link #createBrowser(DesiredCapabilities)} and
 * {@link #createBrowser(WebDriver)} methods can be used to create instances
 * based on pre-initialized objects while still setting some common properties
 * with a default implementation.
 * <p>
 * The difference between a browser factory and a {@link BrowserBuilder browser
 * builder} is in how they are used. Factories are used to initialize a
 * {@link WebDriver web driver} with a specific implementation and certain
 * capabilities and then packaging that driver as a browser using a builder.
 * Builders are used to wrap any web driver in a browser instance and set
 * certain service implementations needed for the browser abstraction to
 * function. In short:
 * <ul>
 * <li>Browser factory configures web driver and uses browser builder to
 * initialize browser</li>
 * <li>Browser builder configures browser (if needed with sensible default
 * services)</li>
 * </ul>
 * <p>
 * <b>Here are some examples of how to initialize a browser using a browser
 * factory:</b>
 * <ul>
 * <li><code>new BrowserFactoryImpl().createBrowser();</code> <br>
 * create web driver and browser with implemented defaults</li>
 * <li>
 * <code>new BrowserFactoryImpl().withProxyConfiguration(configuration).createBrowser();</code>
 * <br>
 * create web driver and browser with specific {@link ProxyConfiguration proxy
 * configuration}</li>
 * <li><code>new BrowserFactoryImpl().createBrowser(webDriver);</code> <br>
 * create browser using the given web driver instance</li>
 * </ul>
 *
 * @see #withProxyConfiguration(ProxyConfiguration)
 * @see WebDriver
 * @see Browser
 * @see BrowserBuilder
 * @since 0.9.0
 */
public interface BrowserFactory {

    /**
     * Creates a {@link Browser browser} using the {@link BrowserFactory
     * factory's} defaults.
     *
     * @return the created browser
     * @since 0.9.0
     */
    Browser createBrowser();

    /**
     * Creates a {@link Browser browser} using the given pre-initialized
     * {@link WebDriver web driver} and the {@link BrowserFactory factory's}
     * defaults.
     *
     * @param webDriver the web driver to use
     * @return the created browser
     * @since 0.9.0
     */
    Browser createBrowser(WebDriver webDriver);

    /**
     * Creates a {@link Browser browser} with the given
     * {@link DesiredCapabilities desired capabilities} and the
     * {@link BrowserFactory factory's} defaults. As the name suggests these
     * capabilities might not actually be available in which case the
     * unavailable ones will be ignored.
     *
     * @param capabilities the desired capabilities.
     * @return the created browser
     * @since 0.9.6
     */
    Browser createBrowser(DesiredCapabilities capabilities);

    /**
     * Defines a {@link ProxyConfiguration proxy configuration} to use when
     * creating a {@link Browser browser} with this {@link BrowserFactory
     * factory}.
     * <p>
     * This method only takes effect if it is used before any of the
     * createBrowser(...) methods is called.
     *
     * @param configuration the proxy configuration
     * @return the same factory instance for fluent API
     */
    BrowserFactory withProxyConfiguration(ProxyConfiguration configuration);

}
