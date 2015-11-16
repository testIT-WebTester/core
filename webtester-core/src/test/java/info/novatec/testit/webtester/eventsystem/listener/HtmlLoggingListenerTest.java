package info.novatec.testit.webtester.eventsystem.listener;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.openqa.selenium.WebDriver;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.events.Event;
import info.novatec.testit.webtester.browser.WebDriverBrowser;
import info.novatec.testit.webtester.eventsystem.events.browser.AbstractBrowserEvent;


@RunWith(MockitoJUnitRunner.class)
public class HtmlLoggingListenerTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Mock
    private WebDriver webDriver;

    private Browser browser;
    private HtmlLoggingListener cut;

    @Before
    public void setUp() {
        browser = WebDriverBrowser.forWebDriver(webDriver).build();
        cut = new HtmlLoggingListener(browser);
    }

    @Test
    public void testThatLogFileIsCreatedIfRelevantEventsWereLogged() throws IOException {

        cut.eventOccurred(relevantEvent());
        cut.eventOccurred(relevantEvent());

        File folder = tempFolder.getRoot();
        cut.saveToFolder(folder);
        File[] files = folder.listFiles();
        assertThat(files.length, is(1));
    }

    @Test
    public void testThatLogFileIsNotCreatedIfNoRelevantEventsWereLogged() throws IOException {

        cut.eventOccurred(irrelevantEvent());
        cut.eventOccurred(irrelevantEvent());

        File folder = tempFolder.getRoot();
        cut.saveToFolder(folder);
        File[] files = folder.listFiles();
        assertThat(files.length, is(0));

    }

    @SuppressWarnings("serial")
    private Event relevantEvent() {
        return new AbstractBrowserEvent(browser) {

            @Override
            public String getEventMessage() {
                return "";
            }

        };
    }

    private Event irrelevantEvent() {
        return mock(Event.class);
    }

}
