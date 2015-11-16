package info.novatec.testit.webtester.eventsystem.events.browser;

import static java.lang.String.format;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.events.Event;


/**
 * This {@link Event event} occurs whenever an alert dialog was declined. It
 * includes the alert's message as a property.
 *
 * @since 0.9.0
 */
@SuppressWarnings("serial")
public class DeclinedAlertEvent extends AbstractBrowserEvent {

    private String alertMessage;

    public DeclinedAlertEvent(Browser browser, String alertMessage) {
        super(browser);
        this.alertMessage = alertMessage;
    }

    @Override
    public String getEventMessage() {
        return format("declined an alert message with message: %s", alertMessage);
    }

    public String getAlertMessage() {
        return alertMessage;
    }

}
