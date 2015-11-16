package info.novatec.testit.webtester.eventsystem.events.browser;

import static java.lang.String.format;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.events.Event;


/**
 * This {@link Event event} occurs whenever a switch to another window occurred.
 * It includes the name or handle of that window as a property.
 *
 * @since 0.9.0
 */
@SuppressWarnings("serial")
public class SwitchedToWindowEvent extends AbstractBrowserEvent {

    private String nameOrHandle;

    public SwitchedToWindowEvent(Browser browser, String nameOrHandle) {
        super(browser);
        this.nameOrHandle = nameOrHandle;
    }

    @Override
    public String getEventMessage() {
        return format("switched to frame using name or handle: %s", nameOrHandle);
    }

    public String getNameOrHandle() {
        return nameOrHandle;
    }

}
