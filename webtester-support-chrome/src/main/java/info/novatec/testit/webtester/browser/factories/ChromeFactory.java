package info.novatec.testit.webtester.browser.factories;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.browser.BrowserFactory;
import info.novatec.testit.webtester.api.browser.ProxyConfiguration;
import info.novatec.testit.webtester.browser.WebDriverBrowser;


/**
 * Factory class for creating Chrome {@link Browser} objects. Needs the
 * 'webdriver.chrome.driver' system property pointing to the driver server
 * executable.
 * <p>
 * <b>Important information on using IE-Driver:</b>
 * https://github.com/SeleniumHQ/selenium/wiki/ChromeDriver
 * </p>
 *
 * @see Browser
 * @see ChromeDriver
 * @since 0.9.3
 */
public class ChromeFactory implements BrowserFactory {

    private ProxyConfiguration proxyConfiguration;

    /**
     * Creates a new {@link Browser} object for a Chrome web browser. Any
     * desired capabilities will be initialized as well.
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
        return createBrowser(new ChromeDriver(capabilities));
    }

    @Override
    public Browser createBrowser(WebDriver webDriver) {

        if (!(webDriver instanceof ChromeDriver)) {
            throw new IllegalArgumentException("Wrong WebDriver class: " + webDriver + " only ChromeDriver is allowed!");
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
    public ChromeFactory withProxyConfiguration(ProxyConfiguration configuration) {
        proxyConfiguration = configuration;
        return this;
    }

}
