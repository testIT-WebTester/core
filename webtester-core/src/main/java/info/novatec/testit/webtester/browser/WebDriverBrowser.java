package info.novatec.testit.webtester.browser;

import static info.novatec.testit.webtester.eventsystem.EventSystem.fireEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.browser.BrowserBuilder;
import info.novatec.testit.webtester.api.browser.BrowserFactory;
import info.novatec.testit.webtester.api.browser.BrowserIdentification;
import info.novatec.testit.webtester.api.callbacks.BrowserCallback;
import info.novatec.testit.webtester.api.callbacks.BrowserCallbackWithReturnValue;
import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.api.pageobjects.Identification;
import info.novatec.testit.webtester.api.pageobjects.PageObjectFactory;
import info.novatec.testit.webtester.api.pageobjects.PageObjectList;
import info.novatec.testit.webtester.eventsystem.events.browser.AcceptedAlertEvent;
import info.novatec.testit.webtester.eventsystem.events.browser.ClosedBrowserEvent;
import info.novatec.testit.webtester.eventsystem.events.browser.ClosedWindowEvent;
import info.novatec.testit.webtester.eventsystem.events.browser.DeclinedAlertEvent;
import info.novatec.testit.webtester.eventsystem.events.browser.ExceptionEvent;
import info.novatec.testit.webtester.eventsystem.events.browser.MaximizedWindowEvent;
import info.novatec.testit.webtester.eventsystem.events.browser.NavigatedBackwardsEvent;
import info.novatec.testit.webtester.eventsystem.events.browser.NavigatedForwardsEvent;
import info.novatec.testit.webtester.eventsystem.events.browser.OpenedUrlEvent;
import info.novatec.testit.webtester.eventsystem.events.browser.RefreshedPageEvent;
import info.novatec.testit.webtester.eventsystem.events.browser.SavedSourceCodeEvent;
import info.novatec.testit.webtester.eventsystem.events.browser.SwitchedToDefaultContentEvent;
import info.novatec.testit.webtester.eventsystem.events.browser.SwitchedToFrameEvent;
import info.novatec.testit.webtester.eventsystem.events.browser.SwitchedToWindowEvent;
import info.novatec.testit.webtester.eventsystem.events.browser.TookScreenshotEvent;
import info.novatec.testit.webtester.internal.pageobjects.PageObjectModel;
import info.novatec.testit.webtester.pageobjects.GenericElement;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.utils.PageObjectFinder;
import info.novatec.testit.webtester.utils.PageObjectFinder.IdentificationFinder;
import info.novatec.testit.webtester.utils.PageObjectFinder.TypedFinder;


/**
 * Implements {@link Browser browser} by using a wrapped Selenium
 * {@link WebDriver web driver}.
 * <p>
 * This class can be initialized using the static factory methods:
 * <ul>
 * <li><code>WebDriverBrowser.buildForWebDriver(webDriver)</code></li>
 * <li><code>WebDriverBrowser.forWebDriver(webDriver).withConfiguration(config).
 * build()</code></li>
 * </ul>
 *
 * @since 0.9.6
 */
public class WebDriverBrowser implements Browser {

    private static final Logger logger = LoggerFactory.getLogger(WebDriverBrowser.class);

    /**
     * Starts the creation of a new {@link WebDriverBrowser browser} by creating
     * a {@link WebDriverBrowserBuilder builder} using the given
     * {@link WebDriver web driver} as a starting point.
     *
     * @param webDriver the web driver to use
     * @return the created builder
     * @since 0.9.6
     */
    public static BrowserBuilder forWebDriver(WebDriver webDriver) {
        return new WebDriverBrowserBuilder(webDriver);
    }

    /**
     * Creates a new {@link WebDriverBrowser browser} using the given
     * {@link WebDriver web driver}.
     *
     * @param webDriver the web driver to use
     * @return the created browser
     * @since 0.9.6
     */
    public static Browser buildForWebDriver(WebDriver webDriver) {
        return forWebDriver(webDriver).build();
    }

    private PageObjectFactory pageObjectFactory;
    private Configuration configuration;

    private final WebDriver webDriver;
    private final BrowserIdentification identification;

    /**
     * Flag to mark if this {@link WebDriverBrowser browser} is closed.
     */
    private boolean closed;

    /**
     * Creates a new {@link WebDriverBrowser} instance wrapping the given
     * {@link WebDriver}. {@link WebDriverBrowser} instantiation is usually done
     * by a {@link BrowserFactory}. Each new {@link WebDriverBrowser} will be
     * registered at the {@link BrowserRegistry}.
     *
     * @param webDriver the {@link WebDriver} to be wrapped by this
     * {@link WebDriverBrowser}.
     * @since 0.9.6
     */
    protected WebDriverBrowser(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.identification = new BrowserIdentification();
        BrowserRegistry.registerBrowser(this);
    }

    @Override
    public WebDriverBrowser openDefaultEntryPoint() {
        return open(configuration.getDefaultApplicationEntryPoint());
    }

    @Override
    public <T extends PageObject> T openDefaultEntryPoint(Class<T> pageObjectClass) {
        return open(configuration.getDefaultApplicationEntryPoint(), pageObjectClass);
    }

    @Override
    public WebDriverBrowser open(URL url) {
        return open(url.toString());
    }

    @Override
    public <T extends PageObject> T open(URL url, Class<T> pageObjectClass) {
        return open(url.toString(), pageObjectClass);
    }

    @Override
    public WebDriverBrowser open(final String url) {
        executeAction(new BrowserCallback() {

            @Override
            public void execute(Browser browser) {
                getWebDriver().get(url);
                fireEvent(new OpenedUrlEvent(browser, url));
            }

        });
        return this;
    }

    @Override
    public <T extends PageObject> T open(final String url, final Class<T> pageObjectClass) {
        return executeAction(new BrowserCallbackWithReturnValue<T>() {

            @Override
            public T execute(Browser browser) {
                getWebDriver().get(url);
                fireEvent(new OpenedUrlEvent(browser, url));
                return browser.create(pageObjectClass);
            }

        });
    }

    @Override
    public <T extends PageObject> T create(Class<T> pageObjectClass) {
        return pageObjectFactory.create(pageObjectClass, PageObjectModel.forPage(this));
    }

    @Override
    public void close() {
        executeAction(new BrowserCallback() {

            @Override
            public void execute(Browser browser) {
                if (!closed) {
                    BrowserRegistry.deregisterBrowser(browser);
                    try {
                        fireEvent(new ClosedBrowserEvent(browser));
                    } finally {
                        getWebDriver().quit();
                        closed = true;
                    }
                }
            }

        });
    }

    @Override
    public void closeCurrentWindow() {
        executeAction(new BrowserCallback() {

            @Override
            public void execute(Browser browser) {
                ClosedWindowEvent event = new ClosedWindowEvent(browser);
                getWebDriver().close();
                getWebDriver().switchTo().defaultContent();
                fireEvent(event);
            }

        });
    }

    @Override
    public WebDriverBrowser maximizeCurrentWindow() {
        executeAction(new BrowserCallback() {

            @Override
            public void execute(Browser browser) {
                getWebDriver().manage().window().maximize();
                fireEvent(new MaximizedWindowEvent(browser));
            }

        });
        return this;
    }

    @Override
    public String getPageTitle() {
        return executeAction(new BrowserCallbackWithReturnValue<String>() {

            @Override
            public String execute(Browser browser) {
                return StringUtils.defaultString(getWebDriver().getTitle());
            }

        });
    }

    @Override
    public String getUrl() {
        return executeAction(new BrowserCallbackWithReturnValue<String>() {

            @Override
            public String execute(Browser browser) {
                return getWebDriver().getCurrentUrl();
            }

        });
    }

    @Override
    public WebDriverBrowser navigateBackwards() {
        executeAction(new BrowserCallback() {

            @Override
            public void execute(Browser browser) {
                getWebDriver().navigate().back();
                fireEvent(new NavigatedBackwardsEvent(browser));
            }

        });
        return this;
    }

    @Override
    public WebDriverBrowser navigateForwards() {
        executeAction(new BrowserCallback() {

            @Override
            public void execute(Browser browser) {
                getWebDriver().navigate().forward();
                fireEvent(new NavigatedForwardsEvent(browser));
            }

        });
        return this;
    }

    @Override
    public WebDriverBrowser refresh() {
        executeAction(new BrowserCallback() {

            @Override
            public void execute(Browser browser) {
                getWebDriver().navigate().refresh();
                fireEvent(new RefreshedPageEvent(browser));
            }

        });
        return this;
    }

    @Override
    public WebDriverBrowser switchToFullScreen() {
        executeAction(new BrowserCallback() {

            @Override
            public void execute(Browser browser) {
                getWebDriver().findElement(By.tagName("html")).sendKeys(Keys.F11);
            }

        });
        return this;
    }

    @Override
    public WebDriverBrowser acceptAlertIfVisible() {
        executeAction(new BrowserCallback() {

            @Override
            public void execute(Browser browser) {
                if (isAlertVisible()) {
                    Alert alert = getWebDriver().switchTo().alert();
                    String text = alert.getText();
                    alert.accept();
                    fireEvent(new AcceptedAlertEvent(browser, text));
                }
            }

        });
        return this;
    }

    @Override
    public WebDriverBrowser declineAlertIfVisible() {
        executeAction(new BrowserCallback() {

            @Override
            public void execute(Browser browser) {
                if (isAlertVisible()) {
                    Alert alert = getWebDriver().switchTo().alert();
                    String text = alert.getText();
                    alert.dismiss();
                    fireEvent(new DeclinedAlertEvent(browser, text));
                }
            }

        });
        return this;
    }

    private boolean isAlertVisible() {
        try {
            long timeout = configuration.getWaitTimeout();
            long interval = configuration.getWaitInterval();
            new WebDriverWait(getWebDriver(), timeout, interval).until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Override
    public File takeScreenshot() {
        File defaultFolder = configuration.getScreenshotFolder();
        return takeScreenshot(defaultFolder.getAbsolutePath());
    }

    @Override
    public File takeScreenshot(final String targetFolder) {
        return takeScreenshot(targetFolder, String.valueOf(System.currentTimeMillis()));
    }

    @Override
    public File takeScreenshot(final String targetFolder, final String fileNameWithoutSuffix) {
        return executeAction(new BrowserCallbackWithReturnValue<File>() {

            @Override
            public File execute(Browser browser) {

                if (!(getWebDriver() instanceof TakesScreenshot)) {
                    return null;
                }

                TakesScreenshot takesScreenshot = ( TakesScreenshot ) getWebDriver();
                File tempScreenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);

                String fileName = fileNameWithoutSuffix + ".jpg";
                File screenshot = new File(targetFolder, fileName);

                try {
                    FileUtils.moveFile(tempScreenshot, screenshot);
                } catch (IOException e) {
                    logger.warn("Exception while creating screenshot, returning null.", e);
                    return null;
                }

                fireEvent(new TookScreenshotEvent(browser, screenshot));
                return screenshot;

            }

        });
    }

    @Override
    public File saveSourceCode() {
        File defaultFolder = configuration.getSourceCodeFolder();
        return saveSourceCode(defaultFolder.getAbsolutePath());
    }

    @Override
    public File saveSourceCode(final String targetFolder) {
        return saveSourceCode(targetFolder, String.valueOf(System.currentTimeMillis()));
    }

    @Override
    public File saveSourceCode(final String targetFolder, final String fileNameWithoutSuffix) {
        return executeAction(new BrowserCallbackWithReturnValue<File>() {

            @Override
            public File execute(Browser browser) {

                String fileName = fileNameWithoutSuffix + ".html";
                File pageSource = new File(targetFolder, fileName);

                try {
                    FileUtils.write(pageSource, getPageSource());
                } catch (IOException e) {
                    logger.warn("Exception while saving page source, returning null.", e);
                    return null;
                }

                fireEvent(new SavedSourceCodeEvent(browser, pageSource));
                return pageSource;

            }

        });
    }

    @Override
    public String getPageSource() {
        return getWebDriver().getPageSource();
    }

    @Override
    public WebDriverBrowser executeJavaScript(String script, PageObject pageObject, Object... parameters) {

        Object[] parameterArray = new Object[parameters.length + 1];
        parameterArray[0] = pageObject.getWebElement();

        System.arraycopy(parameters, 0, parameterArray, 1, parameters.length);

        return executeJavaScript(script, parameterArray);

    }

    @Override
    public WebDriverBrowser executeJavaScript(final String script, final Object... parameters) {
        executeAction(new BrowserCallback() {

            @Override
            public void execute(Browser browser) {
                if (!(getWebDriver() instanceof JavascriptExecutor)) {
                    throw new UnsupportedOperationException("WebDriver does not support JavaScript execution!");
                }
                (( JavascriptExecutor ) getWebDriver()).executeScript(script, parameters);
            }

        });
        return this;
    }

    @Override
    public WebDriverBrowser setFocusOnFrame(final int index) {
        executeAction(new BrowserCallback() {

            @Override
            public void execute(Browser browser) {
                browser.getWebDriver().switchTo().frame(index);
                fireEvent(new SwitchedToFrameEvent(browser, index));
            }

        });
        return this;
    }

    @Override
    public WebDriverBrowser setFocusOnFrame(final String nameOrId) {
        executeAction(new BrowserCallback() {

            @Override
            public void execute(Browser browser) {
                browser.getWebDriver().switchTo().frame(nameOrId);
                fireEvent(new SwitchedToFrameEvent(browser, nameOrId));
            }

        });
        return this;
    }

    @Override
    public WebDriverBrowser setFocusOnWindow(final String nameOrHandle) {
        executeAction(new BrowserCallback() {

            @Override
            public void execute(Browser browser) {
                browser.getWebDriver().switchTo().window(nameOrHandle);
                fireEvent(new SwitchedToWindowEvent(browser, nameOrHandle));
            }

        });
        return this;
    }

    @Override
    public WebDriverBrowser setFocusOnDefaultContent() {
        executeAction(new BrowserCallback() {

            @Override
            public void execute(Browser browser) {
                browser.getWebDriver().switchTo().defaultContent();
                fireEvent(new SwitchedToDefaultContentEvent(browser));
            }

        });
        return this;
    }

    @Override
    public PageObjectFinder finder() {
        return new PageObjectFinder(this);
    }

    @Override
    public GenericElement find(String cssSelector) {
        return finder().findGeneric().by(cssSelector);
    }

    @Override
    public PageObjectList<GenericElement> findMany(String cssSelector) {
        return finder().findGeneric().manyBy(cssSelector);
    }

    @Override
    public IdentificationFinder findBy(Identification identifier) {
        return finder().findBy(identifier);
    }

    @Override
    public <T extends PageObject> TypedFinder<T> find(Class<T> pageObjectClass) {
        return finder().find(pageObjectClass);
    }

    @Override
    public WebDriver getWebDriver() {
        return webDriver;
    }

    @Override
    public BrowserIdentification getIdentification() {
        return identification;
    }

    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    protected final void executeAction(BrowserCallback callback) {
        try {
            callback.execute(this);
        } catch (RuntimeException e) {
            fireEvent(new ExceptionEvent(this, e));
            throw e;
        }
    }

    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    protected final <B> B executeAction(BrowserCallbackWithReturnValue<B> callback) {
        B value;
        try {
            value = callback.execute(this);
        } catch (RuntimeException e) {
            fireEvent(new ExceptionEvent(this, e));
            throw e;
        }
        return value;
    }

    @Override
    public PageObjectFactory getPageObjectFactory() {
        return pageObjectFactory;
    }

    public void setPageObjectFactory(PageObjectFactory pageObjectFactory) {
        this.pageObjectFactory = pageObjectFactory;
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

}
