package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.browser.BrowserFactory;
import info.novatec.testit.webtester.api.browser.ProxyConfiguration;
import info.novatec.testit.webtester.browser.WebDriverBrowser;


public class TestBrowserFactory implements BrowserFactory {

    @Override
    public Browser createBrowser() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setAcceptUntrustedCertificates(true);
        profile.setEnableNativeEvents(false);
        return createBrowser(new FirefoxDriver(profile));
    }

    @Override
    public Browser createBrowser(DesiredCapabilities capabilities) {
        return createBrowser(new FirefoxDriver(capabilities));
    }

    @Override
    public Browser createBrowser(WebDriver webDriver) {
        return WebDriverBrowser.forWebDriver(webDriver).build();
    }

    @Override
    public BrowserFactory withProxyConfiguration(ProxyConfiguration configuration) {
        // proxies are ignored for tests
        return this;
    }

}
