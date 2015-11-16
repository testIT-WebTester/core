package info.novatec.testit.webtester.eventsystem.listener;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.openqa.selenium.WebDriver;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.browser.BrowserRegistry;
import info.novatec.testit.webtester.browser.WebDriverBrowser;
import info.novatec.testit.webtester.eventsystem.events.browser.ExceptionEvent;
import info.novatec.testit.webtester.eventsystem.events.browser.RefreshedPageEvent;


public class TakeScreenshotOnExceptionListenerTest {

    private TakeScreenshotOnExceptionListener cut = new TakeScreenshotOnExceptionListener();

    @Test
    public void testThatExceptionEventsLeadToScreenshots() {
        Browser browser = createSpyableBrowser();
        cut.eventOccurred(new ExceptionEvent(browser, new RuntimeException()));
        verify(browser).takeScreenshot();
    }

    @Test
    public void testThatNonExceptionEventDoesNotLeadToScreenshot() {
        Browser browser = createSpyableBrowser();
        cut.eventOccurred(new RefreshedPageEvent(browser));
        verify(browser, never()).takeScreenshot();
    }

    private Browser createSpyableBrowser() {
        WebDriver webDriver = mock(WebDriver.class);
        Browser browser = spy(WebDriverBrowser.forWebDriver(webDriver).build());
        // override registry entry with spyed instance
        BrowserRegistry.registerBrowser(browser);
        return browser;
    }

}
