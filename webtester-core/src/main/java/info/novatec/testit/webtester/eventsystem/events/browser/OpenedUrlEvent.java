package info.novatec.testit.webtester.eventsystem.events.browser;

import static java.lang.String.format;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.events.Event;


/**
 * This {@link Event event} occurs whenever an URL was opened (navigated to).
 *
 * @since 0.9.0
 */
@SuppressWarnings("serial")
public class OpenedUrlEvent extends AbstractBrowserEvent {

    private String url;

    public OpenedUrlEvent(Browser browser, String url) {
        super(browser);
        this.url = url;
    }

    @Override
    public String getEventMessage() {
        return format("opened url %s", url);
    }

    public String getUrl() {
        return url;
    }

}
