package info.novatec.testit.webtester.eventsystem.events.browser;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.events.Event;


/**
 * This {@link Event event} occurs whenever a page was refreshed.
 *
 * @since 0.9.0
 */
@SuppressWarnings("serial")
public class RefreshedPageEvent extends AbstractBrowserEvent {

    public RefreshedPageEvent(Browser browser) {
        super(browser);
    }

    @Override
    public String getEventMessage() {
        return "refreshed the page";
    }

}
