package info.novatec.testit.webtester.eventsystem.listener;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.novatec.testit.webtester.api.annotations.Experimental;
import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.events.Event;
import info.novatec.testit.webtester.api.events.EventListener;


/**
 * This {@link Experimental experimental} {@link EventListener event listener}
 * will collect all occurring events for a given {@link Browser browser} and offers
 * the option to save these events in form of a HTML document.
 */
@Experimental
public class HtmlLoggingListener implements EventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(HtmlLoggingListener.class);

    private static final Charset CHARSET = Charset.forName("UTF-8");

    private static final String HTML_START = "<!DOCTYPE html><html><body><table border=1>";
    private static final String OPEN_ROW = "<tr>";
    private static final String OPEN_COLUMN = "<td>";
    private static final String CLOSE_COLUMN = "</td>";
    private static final String CLOSE_ROW = "</tr>";
    private static final String HTML_END = "</table></body></html>";

    private Browser browser;
    private List<Event> relevantEvents = new LinkedList<Event>();

    public HtmlLoggingListener(Browser browser) {
        this.browser = browser;
    }

    @Override
    public void eventOccurred(Event event) {
        if (browser.getIdentification().equals(event.getBrowserIdentification())) {
            this.relevantEvents.add(event);
        }
    }

    public void save() throws IOException {
        File defaultFolder = browser.getConfiguration().getLogFolder();
        saveToFolder(defaultFolder);
    }

    public void saveToFolder(File folder) throws IOException {
        File defaultFile = new File(folder, System.currentTimeMillis() + ".html");
        saveToFolderWithName(folder, defaultFile);
    }

    public void saveToFolderWithName(File folder, File file) throws IOException {

        if (!relevantEvents.isEmpty()) {

            prepareFolderAndCreateFile(folder, file);

            String html = buildHtmlContent();
            FileUtils.write(file, html, CHARSET);

        } else {
            LOGGER.debug("skipped writing of html log, because no events were recorded.");
        }

    }

    private void prepareFolderAndCreateFile(File folder, File file) throws IOException {

        if (folder.isFile()) {
            throw new IOException(folder + " is a file instead of a directory");
        }

        if (!folder.isDirectory() && folder.mkdirs()) {
            LOGGER.debug("created directories: {}", folder);
        }

        if (file.createNewFile()) {
            LOGGER.debug("created file: {}", file);
        } else {
            throw new IOException("could not create file: " + file);
        }

    }

    private String buildHtmlContent() {

        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append(HTML_START);

        String timestampFormat = browser.getConfiguration().getTimestampPattern();
        SimpleDateFormat formatter = new SimpleDateFormat(timestampFormat);

        String formattedTimestamp;
        for (Event event : relevantEvents) {

            formattedTimestamp = formatter.format(event.getCreationDateAndTime());

            htmlContent.append(OPEN_ROW);

            htmlContent.append(OPEN_COLUMN);
            htmlContent.append(formattedTimestamp);
            htmlContent.append(CLOSE_COLUMN);

            htmlContent.append(OPEN_COLUMN);
            htmlContent.append(event.getEventMessage());
            htmlContent.append(CLOSE_COLUMN);

            htmlContent.append(CLOSE_ROW);

        }

        htmlContent.append(HTML_END);

        return htmlContent.toString();

    }

}
