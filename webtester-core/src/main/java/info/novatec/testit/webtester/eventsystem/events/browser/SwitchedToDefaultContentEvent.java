package info.novatec.testit.webtester.eventsystem.events.browser;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.events.Event;


/**
 * This {@link Event event} occurs whenever a switch to the default content
 * occurred.
 *
 * @since 0.9.0
 */
@SuppressWarnings("serial")
public class SwitchedToDefaultContentEvent extends AbstractBrowserEvent {

    public SwitchedToDefaultContentEvent(Browser browser) {
        super(browser);
    }

    @Override
    public String getEventMessage() {
        return "switched to default content";
    }

}
