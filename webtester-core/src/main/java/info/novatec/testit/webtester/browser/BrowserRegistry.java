package info.novatec.testit.webtester.browser;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.browser.BrowserIdentification;
import info.novatec.testit.webtester.api.callbacks.BrowserCallback;


/**
 * This registry holds references to every {@link Browser browser} currently
 * active. It allows for registration, deregistration an retrieval of specific
 * browser instances via their unique {@link BrowserIdentification ID}.
 *
 * @since 0.9.0
 */
public final class BrowserRegistry {

    private static final Logger logger = LoggerFactory.getLogger(BrowserRegistry.class);

    private static final Map<BrowserIdentification, Browser> ACTIVE_BROWSER =
        new ConcurrentHashMap<BrowserIdentification, Browser>();

    /**
     * Add the given {@link Browser} to the registry.
     *
     * @param browser the {@link Browser} to add.
     * @since 0.9.0
     */
    public static void registerBrowser(Browser browser) {
        ACTIVE_BROWSER.put(browser.getIdentification(), browser);
        logger.debug("registered browser: {}", browser);
    }

    /**
     * Remove (deregister) the given {@link Browser} from the registry.
     *
     * @param browser the {@link Browser} to remove.
     * @since 0.9.9
     */
    public static void deregisterBrowser(Browser browser) {
        ACTIVE_BROWSER.remove(browser.getIdentification());
        logger.debug("deregistered browser : {}", browser);
    }

    /**
     * Removes all {@link Browser browsers} from the registry.
     *
     * @since 0.9.0
     */
    public static void clear() {
        ACTIVE_BROWSER.clear();
        logger.debug("cleared all browsers");
    }

    /**
     * Tries to find a {@link Browser} for the given
     * {@link BrowserIdentification}. If no browser could be found, null is
     * returned.
     *
     * @param identification the {@link BrowserIdentification} of the
     * {@link Browser} to look up.
     * @return the {@link Browser} if it was found, otherwise null.
     * @since 0.9.0
     */
    public static Browser lookupBrowser(BrowserIdentification identification) {
        Browser browser = ACTIVE_BROWSER.get(identification);
        logTraceInformationAboutBrowserLookup(identification, browser);
        return browser;
    }

    static void logTraceInformationAboutBrowserLookup(BrowserIdentification identification, Browser browser) {
        if (browser != null) {
            logger.trace("looking up browser for identifier: {} - FOUND", identification);
        } else {
            logger.trace("looking up browser for identifier: {} - NOT FOUND", identification);
        }
    }

    /**
     * Executes the given {@link BrowserCallback callback} on all of the
     * currently registered {@link Browser browsers}.
     *
     * @param callback the {@link BrowserCallback callback} to execute.
     * @since 0.9.0
     */
    public static void executeForAllBrowsers(BrowserCallback callback) {
        logger.debug("executing callback on all browsers: {}", callback);
        for (Browser browser : threadSafelyGetBrowsers()) {
            tryToExecuteCallbackForBrowser(callback, browser);
        }
    }

    static List<Browser> threadSafelyGetBrowsers() {
        return new LinkedList<Browser>(ACTIVE_BROWSER.values());
    }

    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    private static void tryToExecuteCallbackForBrowser(BrowserCallback callback, Browser browser) {
        logger.trace("executed callback {} on browser {}", callback, browser);
        try {
            callback.execute(browser);
        } catch (RuntimeException e) {
            logger.warn("exception while executing callback on browser: " + browser, e);
        }
    }

    private BrowserRegistry() {
    }

}
