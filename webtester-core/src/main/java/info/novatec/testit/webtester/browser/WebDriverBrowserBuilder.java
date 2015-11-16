package info.novatec.testit.webtester.browser;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.browser.BrowserBuilder;
import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.api.pageobjects.PageObjectFactory;
import info.novatec.testit.webtester.config.DefaultConfigurationBuilder;
import info.novatec.testit.webtester.internal.pageobjects.DefaultPageObjectFactory;


/**
 * Each browser instance has to have certain other services set to make them
 * functionally complete (i.e. a {@link PageObjectFactory page object factory}).
 * In order to allow for flexibility as well as configurability this builder
 * approach was chosen. In comparison to a simple public constructor the builder
 * allows for default implementations of these services to be used in case no
 * alternative is given.
 *
 * @since 0.9.6
 */
public class WebDriverBrowserBuilder implements BrowserBuilder {

    private static final Logger logger = LoggerFactory.getLogger(WebDriverBrowserBuilder.class);

    private WebDriver webDriver;
    private PageObjectFactory customPageObjectFactory;
    private Configuration customConfiguration;

    public WebDriverBrowserBuilder(WebDriver webDriver) {
        this.webDriver = webDriver;
        logger.trace("started the build of a new browser using: {}", webDriver);
    }

    @Override
    public BrowserBuilder withFactory(PageObjectFactory factory) {
        this.customPageObjectFactory = factory;
        logger.trace("set page object factory for builder: {}", factory);
        return this;
    }

    @Override
    public BrowserBuilder withConfiguration(Configuration configuration) {
        this.customConfiguration = configuration;
        logger.trace("set configuration for builder: {}", configuration);
        return this;
    }

    @Override
    public Browser build() {
        logger.trace("building new browser using: {}", WebDriverBrowser.class);
        WebDriverBrowser browser = new WebDriverBrowser(webDriver);
        setPageObjectFactory(browser);
        setConfiguration(browser);
        addShutdownHook(browser);
        return browser;
    }

    private void setPageObjectFactory(WebDriverBrowser browser) {
        if (customPageObjectFactory != null) {
            browser.setPageObjectFactory(customPageObjectFactory);
            logger.trace("using custom page object factory: {}", customPageObjectFactory);
        } else {
            browser.setPageObjectFactory(new DefaultPageObjectFactory());
            logger.trace("using new default page object factory: {}", DefaultPageObjectFactory.class);
        }
    }

    private void setConfiguration(WebDriverBrowser browser) {
        if (customConfiguration != null) {
            browser.setConfiguration(customConfiguration);
            logger.trace("using custom configuration: {}", customConfiguration);
        } else {
            browser.setConfiguration(DefaultConfigurationBuilder.create());
            logger.trace("using new default configuration from: {}", DefaultConfigurationBuilder.class);
        }
    }

    private void addShutdownHook(final WebDriverBrowser browser) {
        if (browser.getConfiguration().browserShouldCloseAutomatically()) {
            Runtime.getRuntime().addShutdownHook(new Thread() {

                @Override
                public void run() {
                    browser.close();
                }

            });
            logger.trace("added shutdown hook for browser: {}", browser);
        }
    }

}
