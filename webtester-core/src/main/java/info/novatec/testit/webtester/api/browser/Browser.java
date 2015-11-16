package info.novatec.testit.webtester.api.browser;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.WebDriver;

import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.api.pageobjects.Identification;
import info.novatec.testit.webtester.api.pageobjects.PageObjectFactory;
import info.novatec.testit.webtester.api.pageobjects.PageObjectList;
import info.novatec.testit.webtester.browser.BrowserRegistry;
import info.novatec.testit.webtester.eventsystem.events.browser.AbstractBrowserEvent;
import info.novatec.testit.webtester.pageobjects.GenericElement;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.utils.Identifications;
import info.novatec.testit.webtester.utils.PageObjectFinder;
import info.novatec.testit.webtester.utils.PageObjectFinder.IdentificationFinder;
import info.novatec.testit.webtester.utils.PageObjectFinder.TypedFinder;


/**
 * Abstraction layer over Selenium's {@link WebDriver web driver} API. Offers
 * simple methods for commonly used functions which in some cases are no simple
 * single method calls when Selenium is used directly.
 * <p>
 * Also offers methods for the creation of {@link PageObject page object}
 * instances like {@link #create(Class)} and {@link #open(String, Class)} for an
 * optimal integration into the page object pattern.
 * <p>
 * The browser also represents the single point of contact for most tests. So it
 * manages services instances like the used {@link PageObjectFactory page object
 * factory} and {@link Configuration configuration}. Each browser has its own
 * instances of these services. This means that every browser can be configured
 * separately from all the others. Which is very imported in case tests are
 * executed in parallel.
 * <p>
 * In general browsers should be constructed using a {@link BrowserFactory
 * factory} or at least by using a {@link BrowserBuilder builder}. For an
 * explanation of what the difference between these two classes are take a look
 * at their JavaDoc.
 *
 * @see WebDriver
 * @see BrowserBuilder
 * @see BrowserFactory
 * @see PageObjectFactory
 * @see Configuration
 * @since 0.9.0
 */
public interface Browser {

    /**
     * Returns the {@link Browser browser's} unique {@link BrowserIdentification
     * ID}. This ID is generated when the browser is created for the first time.
     * It can be used to identify a browser in the {@link BrowserRegistry
     * registry} or match {@link AbstractBrowserEvent browser events} to their
     * origin.
     *
     * @return the unique ID
     * @since 0.9.0
     */
    BrowserIdentification getIdentification();

    /**
     * Navigates to the default entry point configured in
     * {@link Configuration#getDefaultApplicationEntryPoint()}. If the
     * {@link Browser} wasn't started yet it will be.
     *
     * @return the same browser instance for fluent API use
     * @since 0.9.6 fluent API support
     */
    Browser openDefaultEntryPoint();

    /**
     * Navigates to the default entry point configured in
     * {@link Configuration#getDefaultApplicationEntryPoint()}
     * and returns an instance of the specified {@link PageObject page object}.
     * If the {@link Browser} wasn't started yet it will be.
     *
     * @param <T> the type of the page object to return
     * @param pageObjectClass the class of the page object to return
     * @return an instance of the specified page object
     * @since 0.9.9
     */
    <T extends PageObject> T openDefaultEntryPoint(Class<T> pageObjectClass);

    /**
     * Navigates to the given {@link URL}. If the {@link Browser} wasn't started
     * yet it will be.
     *
     * @param url the {@link URL} to be opened
     * @return the same browser instance for fluent API use
     * @since 0.9.6 fluent API support
     */
    Browser open(URL url);

    /**
     * Navigates to the given {@link URL} and returns an instance of the
     * specified {@link PageObject page object}. If the {@link Browser} wasn't
     * started yet it will be.
     *
     * @param <T> the type of the page object to return
     * @param url the {@link URL} to be opened
     * @param pageObjectClass the class of the page object to return
     * @return an instance of the specified page object
     * @since 0.9.6
     */
    <T extends PageObject> T open(URL url, Class<T> pageObjectClass);

    /**
     * Navigates to the given URL. If the {@link Browser} wasn't started yet it
     * will be.
     *
     * @param url the String representation of the URL to be opened.
     * @return the same browser instance for fluent API use
     * @since 0.9.6 fluent API support
     */
    Browser open(String url);

    /**
     * Navigates to the given URL and returns an instance of the specified
     * {@link PageObject page object}. If the {@link Browser} wasn't started yet
     * it will be.
     *
     * @param <T> the type of the page object to return
     * @param url the String representation of the URL to be opened.
     * @param pageObjectClass the class of the page object to return
     * @return an instance of the specified page object
     * @since 0.9.6
     */
    <T extends PageObject> T open(String url, Class<T> pageObjectClass);

    /**
     * Creates an instance of the given {@link PageObject page object} class.
     * This is the preferred way of initializing page objects. In earlier
     * versions a static factory class was used to initialize page objects. This
     * is now considered bad practice and the class was deprecated.
     *
     * @param <T> the type of the page object to return
     * @param pageObjectClass the page object class to be initialized
     * @return an initialize page object of the given class
     * @since 0.9.6
     */
    <T extends PageObject> T create(Class<T> pageObjectClass);

    /**
     * Closes the {@link Browser} and all its windows. Also shuts down the
     * {@link WebDriver} controlling it.
     *
     * @since 0.9.0
     */
    void close();

    /**
     * Closes the currently active window and switches the focus back to the
     * default content. Should only be used to close child windows spawned by
     * the application under test.
     * <p>
     * If this method is used to close the main application window it might shut
     * down the browser and make this instance unusable. If you want to shut
     * down the whole {@link Browser browser} use {@link #close()} instead.
     *
     * @since 0.9.2
     */
    void closeCurrentWindow();

    /**
     * @return the title of the current page, if no title is set an empty String
     * will be returned
     * @since 0.9.0
     */
    String getPageTitle();

    /**
     * @return the string representation of the current page URL
     * @since 0.9.0
     */
    String getUrl();

    /**
     * Navigates back in the {@link Browser} history. If there is no history
     * available, this method will do nothing.
     *
     * @return the same browser instance for fluent API use
     * @since 0.9.6 fluent API support
     */
    Browser navigateBackwards();

    /**
     * Navigates forward in the {@link Browser} history. If there is no forward
     * page available, this method will do nothing.
     *
     * @return the same browser instance for fluent API use
     * @since 0.9.6 fluent API support
     */
    Browser navigateForwards();

    /**
     * Refreshes the current page.
     *
     * @return the same browser instance for fluent API use
     * @since 0.9.6 fluent API support
     */
    Browser refresh();

    /**
     * If any alert is displayed (Yes/No; Confirmation etc.) it will be accepted
     * (Yes, Ok etc.).
     *
     * @return the same browser instance for fluent API use
     * @since 0.9.6 fluent API support
     */
    Browser acceptAlertIfVisible();

    /**
     * If any alert is displayed (Yes/No; Confirmation etc.) it will be declined
     * (No, Cancel etc.).
     *
     * @return the same browser instance for fluent API use
     * @since 0.9.6 fluent API support
     */
    Browser declineAlertIfVisible();

    /**
     * Tries to take a screenshot of the currently displayed browser window and
     * saves it to the {@link Configuration#getScreenshotFolder() configured
     * default directory}.
     *
     * @return {@link File} Object for the screenshot file or null if screenshot
     * could not be saved
     * @since 0.9.0
     */
    File takeScreenshot();

    /**
     * Tries to take a screenshot of the currently displayed browser window and
     * saves it to the given directory. The filename is generated by using the
     * current system timestamp.
     *
     * @param targetFolder path to the directory under which the screenshot
     * should be saved
     * @return {@link File} Object for the screenshot file or null if screenshot
     * could not be saved
     * @since 0.9.0
     */
    File takeScreenshot(String targetFolder);

    /**
     * Tries to take a screenshot of the currently displayed browser window and
     * saves it to the given directory under the given name.
     *
     * @param targetFolder path to the directory under which the screenshot
     * should be saved
     * @param fileNameWithoutSuffix the name of the screenshot's file - without
     * suffixes like <code>.jpg</code>, <code>.png</code> etc.
     * @return {@link File} Object for the screenshot file or null if screenshot
     * could not be saved
     * @since 0.9.2
     */
    File takeScreenshot(String targetFolder, String fileNameWithoutSuffix);

    /**
     * Tries to save the HTML source of the currently displayed page and saves
     * it to the configured default directory.
     *
     * @return {@link File} Object for the HTML source file or null if it could
     * not be saved
     * @since 0.9.0
     */
    File saveSourceCode();

    /**
     * Tries to save the HTML source of the currently displayed page and saves
     * it to the given directory.
     *
     * @param targetFolder path to the directory under which the HTML source
     * file should be saved
     * @return {@link File} Object for the HTML source file or null if it could
     * not be saved
     * @since 0.9.0
     */
    File saveSourceCode(String targetFolder);

    /**
     * Tries to save the HTML source of the currently displayed page and saves
     * it to the given directory.
     *
     * @param targetFolder path to the directory under which the HTML source
     * file should be saved
     * @param fileNameWithoutSuffix the name of the source codes's file -
     * without suffixes like <code>.htm</code>, <code>.html</code> etc.
     * @return {@link File} Object for the HTML source file or null if it could
     * not be saved
     * @since 0.9.2
     */
    File saveSourceCode(String targetFolder, String fileNameWithoutSuffix);

    /**
     * @return the currently displayed page's source code as a string.
     * @since 0.9.0
     */
    String getPageSource();

    /**
     * Executes the given JavaScript code for the given {@link PageObject}
     * (available in script as arguments[0]) with the given parameters
     * (accessible as arguments[1] - arguments[n]).
     *
     * @param script the JavaScript code to be executed on the current page
     * @param pageObject the target {@link PageObject}
     * @param parameters any of Boolean, Long, String, List, WebElement or null.
     * @return the same browser instance for fluent API use
     * @since 0.9.6 fluent API support
     */
    Browser executeJavaScript(String script, PageObject pageObject, Object... parameters);

    /**
     * Executes the given JavaScript code with the given parameters (accessible
     * as arguments[0] - arguments[n]).
     *
     * @param script the JavaScript code to be executed on the current page
     * @param parameters any of Boolean, Long, String, List, WebElement or null.
     * @return the same browser instance for fluent API use
     * @since 0.9.6 fluent API support
     */
    Browser executeJavaScript(String script, Object... parameters);

    /**
     * Maximizes the current {@link Browser browser} window.
     *
     * @return the same browser instance for fluent API use
     * @since 0.9.6 fluent API support
     */
    Browser maximizeCurrentWindow();

    /**
     * Attempts to switch to full screen mode by pressing F11.
     * <p>
     * <b>Note:</b> Might not work in every browser.
     * </p>
     *
     * @return the same browser instance for fluent API use
     * @since 0.9.6 fluent API support
     */
    Browser switchToFullScreen();

    /**
     * Sets the focus on the specified frame. The frame is identified by its
     * zero based index. All future requests to the Browser will be executed on
     * this frame. Use {@link #setFocusOnDefaultContent()} to return to the main
     * document.
     *
     * @param index zero based index of the frame to focus on
     * @return the same browser instance for fluent API use
     * @since 0.9.6 fluent API support
     */
    Browser setFocusOnFrame(int index);

    /**
     * Sets the focus on the specified frame. The frame is identified by its
     * name or ID. All future requests to the Browser will be executed on this
     * frame. Use {@link #setFocusOnDefaultContent()} to return to the main
     * document.
     *
     * @param nameOrId name or ID of the frame to focus on
     * @return the same browser instance for fluent API use
     * @since 0.9.6 fluent API support
     */
    Browser setFocusOnFrame(String nameOrId);

    /**
     * Sets the focus on the specified window. The window is identified by its
     * name or handle. All future requests to the Browser will be executed on
     * this window. Use {@link #setFocusOnDefaultContent()} to return to the
     * main window.
     *
     * @param nameOrHandle the name or handle of the window to focus on
     * @return the same browser instance for fluent API use
     * @since 0.9.6 fluent API support
     */
    Browser setFocusOnWindow(String nameOrHandle);

    /**
     * Sets the focus on the main window / main document depending on what is
     * currently focused (other windows or frames).
     *
     * @return the same browser instance for fluent API use
     * @since 0.9.6 fluent API support
     */
    Browser setFocusOnDefaultContent();

    /**
     * Creates a new {@link PageObjectFinder page object finder} for this
     * browser. This finder can be used to programmatically identify and create
     * {@link PageObject page objects}.
     *
     * @return the newly create finder
     * @since 0.9.9
     */
    PageObjectFinder finder();

    /**
     * Shorthand method for finding a {@link GenericElement generic page
     * element} via a CSS-Selector expression.
     * <p>
     * <b>Examples:</b>
     * <ul>
     * <li><code>browser.find("#username").sendKeys("testuser");</code></li>
     * <li><code>browser.find("#button").click();</code></li>
     * <li><code>browser.find("#headline").getVisibleText();</code></li>
     * </ul>
     *
     * @param cssSelector the CSS-Selector expression to use
     * @return the generic element for the given selector
     * @since 0.9.9
     */
    GenericElement find(String cssSelector);

    /**
     * Shorthand method for finding a {@link PageObjectList list} of
     * {@link GenericElement generic page elements} via a CSS-Selector
     * expression.
     * <p>
     * <b>Examples:</b>
     * <ul>
     * <li><code>browser.findMany(".button").filter(is(visible()));</code></li>
     * </ul>
     *
     * @param cssSelector the CSS-Selector expression to use
     * @return the list of generic elements for the given selector
     * @since 0.9.9
     */
    PageObjectList<GenericElement> findMany(String cssSelector);

    /**
     * Shorthand method for creating a new {@link IdentificationFinder
     * identification based finder}. Matching {@link Identification
     * identification} instances can be created using the
     * {@link Identifications} utility class.
     * <p>
     * <b>Examples:</b>
     * <ul>
     * <li>
     * <code>browser.findBy(id("username")).as(TextField.class).setText("testuser");</code>
     * </li>
     * <li><code>browser.findBy(css("#button")).as(Button.class).click();</code>
     * </li>
     * <li>
     * <code>browser.findBy(xpath(".//h1")).asGeneric().getVisibleText();</code>
     * </li>
     * </ul>
     *
     * @param identification the identification to use when identifying an
     * element
     * @return the new identification finder
     * @since 0.9.9
     */
    IdentificationFinder findBy(Identification identification);

    /**
     * Shorthand method for creating a new {@link TypedFinder type based finder}
     * . The given page object class is used for all subsequent operations on
     * the finder.
     * <p>
     * <b>Examples:</b>
     * <ul>
     * <li>
     * <code>browser.find(TextField.class).by(id("username")).setText("testuser");</code>
     * </li>
     * <li><code>browser.find(Button.class).by(css("#button")).click();</code>
     * </li>
     * <li>
     * <code>browser.find(GenericElement.class).by(xpath(".//h1")).getVisibleText();</code>
     * </li>
     * </ul>
     *
     * @param <T> the type of the page object to create a finder for
     * @param pageObjectClass the page object class to use when creating an
     * element
     * @return the new type finder
     * @since 0.9.9
     */
    <T extends PageObject> TypedFinder<T> find(Class<T> pageObjectClass);

    /**
     * Returns the underlying Selenium {@link WebDriver web driver}.
     * <p>
     * <b>Note to developers and testers:</b> Needing to use this method outside
     * of {@link PageObject page object} subclasses (i.e. tests) is a signal
     * that the WebTester abstraction <i>might</i> be missing a feature. In
     * these cases contact the WebTester team and make a feature request.
     *
     * @return the underlying web driver
     */
    WebDriver getWebDriver();

    /**
     * Returns the {@link PageObjectFactory page object factory} used by this
     * browser instance.
     * <p>
     * This factory is usually set while building the instance using a
     * {@link BrowserBuilder browser builder}.
     *
     * @return the page object factory
     */
    PageObjectFactory getPageObjectFactory();

    /**
     * Returns the {@link Configuration configuration} used by this browser
     * instance.
     * <p>
     * This configuration is usually set while building the instance using a
     * {@link BrowserBuilder browser builder}.
     *
     * @return the configuration
     */
    Configuration getConfiguration();

}
