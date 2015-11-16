package info.novatec.testit.webtester.eventsystem.listener;

import static info.novatec.testit.webtester.browser.BrowserRegistry.lookupBrowser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.events.Event;
import info.novatec.testit.webtester.api.events.EventListener;
import info.novatec.testit.webtester.eventsystem.events.browser.ExceptionEvent;


/**
 * This {@link EventListener event listener} takes a screenshot on
 * each occurrence of an {@link ExceptionEvent exception event} and
 * stores it in the configured default screenshot folder.
 */
public class TakeScreenshotOnExceptionListener implements EventListener {

    private static final Logger logger = LoggerFactory.getLogger(TakeScreenshotOnExceptionListener.class);

    @Override
    public void eventOccurred(Event event) {
        if (isException(event)) {
            takeScreenshot(event);
        }
    }

    private void takeScreenshot(Event event) {
        Browser browser = lookupBrowser(event.getBrowserIdentification());
        if (browser != null) {
            logger.debug("taking screenshot because of exception event: {}", event);
            browser.takeScreenshot();
        } else {
            logger.warn("could not take screenshot because event's browser is no longer registered: {}", event);
        }
    }

    private boolean isException(Event event) {
        return event instanceof ExceptionEvent;
    }

}
