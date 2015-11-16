package info.novatec.testit.webtester.eventsystem.events;

import java.util.Date;

import info.novatec.testit.webtester.api.browser.BrowserIdentification;
import info.novatec.testit.webtester.api.events.Event;


/**
 * Base class for {@link Event} implementations. Provides the basic properties
 * like {@link BrowserIdentification} and creation date and time.
 *
 * @since 0.9.0
 */
@SuppressWarnings("serial")
public abstract class AbstractEvent implements Event {

    private BrowserIdentification browserIdentification;
    private long creationTimestamp;

    public AbstractEvent(BrowserIdentification browserIdentification) {
        this.browserIdentification = browserIdentification;
        this.creationTimestamp = System.currentTimeMillis();
    }

    @Override
    public BrowserIdentification getBrowserIdentification() {
        return browserIdentification;
    }

    @Override
    public Date getCreationDateAndTime() {
        return new Date(creationTimestamp);
    }

    @Override
    public String toString() {
        return getEventMessage();
    }

}
