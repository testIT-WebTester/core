package info.novatec.testit.webtester.browser.factories;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.browser.BrowserFactory;
import info.novatec.testit.webtester.api.browser.ProxyConfiguration;
import info.novatec.testit.webtester.browser.WebDriverBrowser;


/**
 * Factory class for creating Firefox {@link Browser} objects.
 *
 * @see Browser
 * @see FirefoxDriver
 * @see FirefoxProfile
 * @since 0.9.0
 */
public class FirefoxFactory implements BrowserFactory {

    private ProxyConfiguration proxyConfiguration;

    /**
     * Creates a new {@link Browser} object for a Firefox web browser with a
     * default {@link FirefoxProfile}. Any desired capabilities will be
     * initialized as well.
     * <p>
     * Currently the following capabilities are configurable:
     * <ul>
     * <li>proxy setting using a {@link ProxyConfiguration}</li>
     * </ul>
     * The default profile includes the deactivation of native events for more
     * stability and a faster execution as well as the acceptance of untrusted
     * certificates.
     *
     * @return the created {@link Browser}.
     */
    @Override
    public Browser createBrowser() {

        FirefoxProfile profile = new FirefoxProfile();
        profile.setEnableNativeEvents(false);
        profile.setAcceptUntrustedCertificates(true);

        return createBrowser(profile);

    }

    /**
     * Creates a new {@link Browser} object for a Firefox web browser with the
     * given {@link FirefoxProfile}. Any desired capabilities will be
     * initialized as well.
     * <p>
     * Currently the following capabilities are configurable:
     * <ul>
     * <li>proxy setting using a {@link ProxyConfiguration}</li>
     * </ul>
     *
     * @param profile the {@link FirefoxProfile} to be used when starting the
     * web browser.
     * @return the created {@link Browser}.
     */
    public Browser createBrowser(FirefoxProfile profile) {

        FirefoxBinary binary = new FirefoxBinary();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        setOptionalProxyConfiguration(capabilities);

        return createBrowser(new FirefoxDriver(binary, profile, capabilities));

    }

    @Override
    public Browser createBrowser(DesiredCapabilities capabilities) {
        return createBrowser(new FirefoxDriver(capabilities));
    }

    @Override
    public Browser createBrowser(WebDriver webDriver) {

        if (!(webDriver instanceof FirefoxDriver)) {
            throw new IllegalArgumentException("Wrong WebDriver class: " + webDriver + " only FirefoxDriver is allowed!");
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
    public FirefoxFactory withProxyConfiguration(ProxyConfiguration configuration) {
        proxyConfiguration = configuration;
        return this;
    }

}
