package info.novatec.testit.webtester.browser.factories;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.browser.BrowserFactory;
import info.novatec.testit.webtester.api.browser.ProxyConfiguration;
import info.novatec.testit.webtester.browser.WebDriverBrowser;


/**
 * Factory class for creating Internet Explorer {@link Browser} objects. Needs
 * the 'webdriver.ie.driver' system property pointing to the driver server
 * executable.
 * <p>
 * <b>Important information on using IE-Driver:</b>
 * https://github.com/SeleniumHQ/selenium/wiki/InternetExplorerDriver
 * </p>
 *
 * @see Browser
 * @see InternetExplorerDriver
 * @since 0.9.1
 */
public class InternetExplorerFactory implements BrowserFactory {

    private ProxyConfiguration proxyConfiguration;

    /**
     * Creates a new {@link Browser} object for a Internet Explorer web browser.
     * Any desired capabilities will be initialized as well.
     * <p>
     * Currently the following capabilities are configurable:
     * <ul>
     * <li>proxy setting using a {@link ProxyConfiguration}</li>
     * </ul>
     *
     * @return the created {@link Browser}.
     */
    @Override
    public Browser createBrowser() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        setOptionalProxyConfiguration(capabilities);

        return createBrowser(capabilities);

    }

    @Override
    public Browser createBrowser(DesiredCapabilities capabilities) {
        return createBrowser(new InternetExplorerDriver(capabilities));
    }

    /**
     * Creates a new {@link Browser} object for a Internet Explorer web browser
     * using the given port for the driver server. Any desired capabilities will
     * be initialized as well.
     * <p>
     * Currently the following capabilities are configurable:
     * <ul>
     * <li>proxy setting using a {@link ProxyConfiguration}</li>
     * </ul>
     *
     * @param port port on which the driver server should listen for commands
     * @return the created {@link Browser}.
     */
    public Browser createBrowser(int port) {

        InternetExplorerDriverService service = InternetExplorerDriverService.createDefaultService();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        setOptionalProxyConfiguration(capabilities);

        return createBrowser(new InternetExplorerDriver(service, capabilities, port));

    }

    @Override
    public Browser createBrowser(WebDriver webDriver) {

        if (!(webDriver instanceof InternetExplorerDriver)) {
            throw new IllegalArgumentException(
                "Wrong WebDriver class: " + webDriver + " only InternetExplorerDriver is allowed!");
        }

        return WebDriverBrowser.buildForWebDriver(webDriver);

    }

    private void setOptionalProxyConfiguration(DesiredCapabilities capabilities) {
        if (proxyConfiguration != null) {
            Proxy proxy = new Proxy();
            proxyConfiguration.configureProxy(proxy);
            capabilities.setCapability(CapabilityType.PROXY, proxy);
        }
    }

    @Override
    public InternetExplorerFactory withProxyConfiguration(ProxyConfiguration configuration) {
        proxyConfiguration = configuration;
        return this;
    }

}
