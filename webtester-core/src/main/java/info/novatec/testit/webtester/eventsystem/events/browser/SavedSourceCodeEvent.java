package info.novatec.testit.webtester.eventsystem.events.browser;

import static java.lang.String.format;

import java.io.File;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.events.Event;


/**
 * This {@link Event event} occurs whenever the source code of a page was saved.
 * It includes the file reference to the source code file as a property.
 *
 * @since 0.9.0
 */
@SuppressWarnings("serial")
public class SavedSourceCodeEvent extends AbstractBrowserEvent {

    private File pageSourceFile;

    public SavedSourceCodeEvent(Browser browser, File pageSourceFile) {
        super(browser);
        this.pageSourceFile = pageSourceFile;
    }

    @Override
    public String getEventMessage() {
        return format("saved page source and saved it as: %s", pageSourceFile);
    }

    public File getPageSourceFile() {
        return pageSourceFile;
    }

}
