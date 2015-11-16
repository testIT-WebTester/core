package info.novatec.testit.webtester.eventsystem.events.browser;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.events.Event;
import info.novatec.testit.webtester.eventsystem.events.AbstractEvent;


/**
 * Base class for {@link Event} objects that represent and event of a
 * {@link Browser}.
 *
 * @since 0.9.0
 */
@SuppressWarnings("serial")
public abstract class AbstractBrowserEvent extends AbstractEvent {

    private String handle;

    protected AbstractBrowserEvent(Browser browser) {
        super(browser.getIdentification());
        this.handle = browser.getWebDriver().getWindowHandle();
    }

    @Override
    public String getSubjectName() {
        return "Browser #" + getBrowserIdentification();
    }

    public String getWindowHandle() {
        return handle;
    }

}
