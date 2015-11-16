package info.novatec.testit.webtester.eventsystem.events.browser;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.events.Event;


/**
 * This {@link Event event} occurs whenever a browser was closed.
 *
 * @since 0.9.0
 */
@SuppressWarnings("serial")
public class ClosedBrowserEvent extends AbstractBrowserEvent {

    public ClosedBrowserEvent(Browser browser) {
        super(browser);
    }

    @Override
    public String getEventMessage() {
        return "closed browser";
    }

}
