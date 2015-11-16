package info.novatec.testit.webtester.eventsystem.events.browser;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.events.Event;


/**
 * This {@link Event event} occurs whenever a navigate forward was executed.
 *
 * @since 0.9.0
 */
@SuppressWarnings("serial")
public class NavigatedForwardsEvent extends AbstractBrowserEvent {

    public NavigatedForwardsEvent(Browser browser) {
        super(browser);
    }

    @Override
    public String getEventMessage() {
        return "navigated forward in the browser history";
    }

}
