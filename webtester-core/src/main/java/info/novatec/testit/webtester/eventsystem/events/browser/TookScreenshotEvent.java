package info.novatec.testit.webtester.eventsystem.events.browser;

import static java.lang.String.format;

import java.io.File;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.events.Event;


/**
 * This {@link Event event} occurs whenever a screenshot was taken. It includes
 * the file reference to the screenshot as a property.
 *
 * @since 0.9.0
 */
@SuppressWarnings("serial")
public class TookScreenshotEvent extends AbstractBrowserEvent {

    private File screenshotFile;

    public TookScreenshotEvent(Browser browser, File screenshotFile) {
        super(browser);
        this.screenshotFile = screenshotFile;
    }

    @Override
    public String getEventMessage() {
        return format("took screenshot and saved it as: %s", screenshotFile);
    }

    public File getScreenshotFile() {
        return screenshotFile;
    }

}
