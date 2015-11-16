package info.novatec.testit.webtester;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.browser.BrowserIdentification;
import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.api.events.EventListener;
import info.novatec.testit.webtester.eventsystem.EventSystem;
import info.novatec.testit.webtester.internal.pageobjects.PageObjectModel;


@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractPageObjectTest {

    @Mock
    protected Configuration configuration;
    @Mock
    protected WebDriver webDriver;
    @Mock
    protected WebElement webElement;
    @Mock
    protected EventListener listener;

    protected Browser browser = mock(Browser.class);
    @Spy
    protected PageObjectModel metaData = PageObjectModel.forPage(browser);

    @Before
    public void setUpMockedBrowser() {

        doReturn(webDriver).when(browser).getWebDriver();
        doReturn("handle").when(webDriver).getWindowHandle();
        doReturn(new BrowserIdentification()).when(browser).getIdentification();

        doReturn(configuration).when(browser).getConfiguration();

    }

    @Before
    public void makeWebElementVisibleAndEnabled() {
        elementIsVisibleAndEnabled();
    }

    @Before
    public void registerEventListenerMock() {
        EventSystem.registerListener(listener);
    }

    @After
    public void unregisterEventListenerMock() {
        EventSystem.deregisterListener(listener);
    }

    /* utilities */

    protected void elementIsVisibleAndEnabled() {
        elementIsVisible();
        elementIsEnabled();
    }

    protected void elementIsVisible() {
        doReturn(true).when(webElement).isDisplayed();
    }

    protected void elementIsInvisible() {
        doReturn(false).when(webElement).isDisplayed();
    }

    protected void elementIsEnabled() {
        doReturn(true).when(webElement).isEnabled();
    }

    protected void elementIsDisabled() {
        doReturn(false).when(webElement).isEnabled();
    }

    protected void stubWebElementTagAndType(String tag, String type) {
        stubWebElementTag(tag);
        doReturn(type).when(webElement).getAttribute("type");
    }

    protected void stubWebElementTag(String tag) {
        doReturn(tag).when(webElement).getTagName();
    }

}
