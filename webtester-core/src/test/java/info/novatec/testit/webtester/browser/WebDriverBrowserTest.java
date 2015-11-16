package info.novatec.testit.webtester.browser;

import static info.novatec.testit.webtester.eventsystem.EventSystem.deregisterListener;
import static info.novatec.testit.webtester.eventsystem.EventSystem.registerListener;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebDriverException;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.callbacks.BrowserCallback;
import info.novatec.testit.webtester.api.callbacks.BrowserCallbackWithReturnValue;
import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.api.events.Event;
import info.novatec.testit.webtester.api.events.EventListener;
import info.novatec.testit.webtester.eventsystem.events.browser.ClosedBrowserEvent;
import info.novatec.testit.webtester.eventsystem.events.browser.ClosedWindowEvent;
import info.novatec.testit.webtester.eventsystem.events.browser.ExceptionEvent;
import info.novatec.testit.webtester.eventsystem.events.browser.MaximizedWindowEvent;
import info.novatec.testit.webtester.eventsystem.events.browser.NavigatedBackwardsEvent;
import info.novatec.testit.webtester.eventsystem.events.browser.NavigatedForwardsEvent;
import info.novatec.testit.webtester.eventsystem.events.browser.OpenedUrlEvent;
import info.novatec.testit.webtester.eventsystem.events.browser.RefreshedPageEvent;
import info.novatec.testit.webtester.eventsystem.events.browser.SavedSourceCodeEvent;
import info.novatec.testit.webtester.eventsystem.events.browser.SwitchedToDefaultContentEvent;
import info.novatec.testit.webtester.eventsystem.events.browser.SwitchedToFrameEvent;
import info.novatec.testit.webtester.eventsystem.events.browser.SwitchedToWindowEvent;


@RunWith(MockitoJUnitRunner.class)
public class WebDriverBrowserTest {

    private static final int INDEX = 42;
    private static final String NAME_OR_ID = "fooBar";
    private static final String NAME_OR_HANDLE = NAME_OR_ID;
    private static final String PAGE_URL = "http://www.foo-bar.de";
    private static final String WINDOW_HANDLE = "foo";
    private static final String PAGE_TITLE = "Foo Bar";
    private static final String SOURCE_CODE = "foo bar, 42";

    @ClassRule
    public static TemporaryFolder tempFolder = new TemporaryFolder();

    @Mock
    private Configuration configuration;
    @Mock
    private WebDriver webDriver;
    @Mock
    private EventListener listener;

    @Captor
    private ArgumentCaptor<Event> eventCaptor;

    @InjectMocks
    private WebDriverBrowser cut;

    @Before
    public void setUp() {
        registerListener(listener);
        doReturn(WINDOW_HANDLE).when(webDriver).getWindowHandle();
        cut.setConfiguration(configuration);
        doReturn(tempFolder.getRoot()).when(configuration).getSourceCodeFolder();
        doReturn(tempFolder.getRoot()).when(configuration).getScreenshotFolder();
    }

    @After
    public void tearDown() {
        deregisterListener(listener);
    }

    /* creation */

    @Test
    public void testThatCreatedBrowsersAreRegistered() {
        WebDriverBrowser resolved = ( WebDriverBrowser ) BrowserRegistry.lookupBrowser(cut.getIdentification());
        assertThat(resolved, is(sameInstance(cut)));
    }

    /* open */

    @Test
    public void testThatOpeningTheDefaultEntryPointUsesCorrectConfigurationProperty() {
        doReturn(PAGE_URL).when(configuration).getDefaultApplicationEntryPoint();
        cut.openDefaultEntryPoint();
        verify(webDriver).get(PAGE_URL);
    }

    @Test
    public void testThatOpeningAnUrlInvokesCorrectWebDriverMethod() throws MalformedURLException {
        cut.open(new URL(PAGE_URL));
        verify(webDriver).get(PAGE_URL);
    }

    @Test
    public void testThatOpeningAnUrlStringInvokesCorrectWebDriverMethod() {
        cut.open(PAGE_URL);
        verify(webDriver).get(PAGE_URL);
    }

    @Test
    public void testThatOpeningAnUrlFiresEvent() {

        cut.open(PAGE_URL);

        OpenedUrlEvent event = verifyFired(OpenedUrlEvent.class);
        assertThat(event.getUrl(), is(PAGE_URL));

    }

    /* close */

    @Test
    public void testThatClosingABrowserInvokesCorrectWebDriverMethod() {
        cut.close();
        verify(webDriver).quit();
    }

    @Test
    public void testThatClosingABrowserFiresEvent() {
        cut.close();
        verifyFired(ClosedBrowserEvent.class);
    }

    @Test
    public void testThatClosingABrowserUnregistersItFromTheBrowserRegistry() {
        cut.close();
        Browser resolved = BrowserRegistry.lookupBrowser(cut.getIdentification());
        assertThat(resolved, is(nullValue()));
    }

    /* current window */

    @Test
    public void testThatClosingTheCurrentWindowInvokesCorrectWebDriverMethods() {
        TargetLocator locator = mockTargetLocator();
        cut.closeCurrentWindow();
        verify(webDriver).close();
        verify(locator).defaultContent();
    }

    @Test
    public void testThatClosingTheCurrentWindowFiresEvent() {
        mockTargetLocator();
        cut.closeCurrentWindow();
        verifyFired(ClosedWindowEvent.class);
    }

    @Test
    public void testThatMaximizingTheCurrentWindowInvokesCorrectWebDriverMethods() {
        Window window = mockWindow();
        cut.maximizeCurrentWindow();
        verify(window).maximize();
    }

    @Test
    public void testThatMaximizingTheCurrentWindowFiresEvent() {
        mockWindow();
        cut.maximizeCurrentWindow();
        verifyFired(MaximizedWindowEvent.class);
    }

    /* page information */

    @Test
    public void testThatPageTitleIsRetrievedByInvokingTheCorrectWebDriverMethod() {
        doReturn(PAGE_TITLE).when(webDriver).getTitle();
        String pageTitle = cut.getPageTitle();
        assertThat(pageTitle, is(PAGE_TITLE));
    }

    @Test
    public void testThatNoPageTitleIsReturnedAsEmptyString() {
        doReturn(null).when(webDriver).getTitle();
        String pageTitle = cut.getPageTitle();
        assertThat(pageTitle, is(StringUtils.EMPTY));
    }

    @Test
    public void testThatUrlIsRetrievedByInvokingTheCorrectWebDriverMethod() {
        doReturn(PAGE_URL).when(webDriver).getCurrentUrl();
        String url = cut.getUrl();
        assertThat(url, is(PAGE_URL));
    }

    /* navigation */

    @Test
    public void testThatNavigatingBackInvokesCorrectWebDriverMethods() {
        Navigation navigation = mockNavigation();
        cut.navigateBackwards();
        verify(navigation).back();
    }

    @Test
    public void testThatNavigatingBackFiresEvent() {
        mockNavigation();
        cut.navigateBackwards();
        verifyFired(NavigatedBackwardsEvent.class);
    }

    @Test
    public void testThatNavigatingForwardsInvokesCorrectWebDriverMethods() {
        Navigation navigation = mockNavigation();
        cut.navigateForwards();
        verify(navigation).forward();
    }

    @Test
    public void testThatNavigatingForwardsFiresEvent() {
        mockNavigation();
        cut.navigateForwards();
        verifyFired(NavigatedForwardsEvent.class);
    }

    @Test
    public void testThatRefreshingInvokesCorrectWebDriverMethods() {
        Navigation navigation = mockNavigation();
        cut.refresh();
        verify(navigation).refresh();
    }

    @Test
    public void testThatRefreshingFiresEvent() {
        mockNavigation();
        cut.refresh();
        verifyFired(RefreshedPageEvent.class);
    }

    /* screenshots */

    @Test
    public void testThatTakingScreenshotsReturnsNullIfBrowserDoesNotSupportIt() {
        File takeScreenshot = cut.takeScreenshot();
        assertThat(takeScreenshot, is(nullValue()));
    }

    /* page source */

    @Test
    public void testSavingSourceCodeToDefaultFolder_CodeIsSavedCorrectly() throws IOException {
        doReturn(SOURCE_CODE).when(webDriver).getPageSource();
        File file = cut.saveSourceCode();
        assertThat(FileUtils.readFileToString(file), is(SOURCE_CODE));
    }

    @Test
    public void testSavingSourceCodeToDefaultFolder_CorrectEventIsFired() {
        File file = cut.saveSourceCode();
        SavedSourceCodeEvent event = verifyFired(SavedSourceCodeEvent.class);
        assertThat(event.getPageSourceFile(), is(equalTo(file)));
    }

    @Test
    public void testSavingSourceCodeToDefaultFolder_IOExceptionsAreHandled() {
        doThrow(IOException.class).when(webDriver).getPageSource();
        File file = cut.saveSourceCode();
        assertThat(file, is(nullValue()));
    }

    /* java script */

    @Test(expected = UnsupportedOperationException.class)
    public void testThatExecutingJavaScriptThrowsExceptionIfBrowserDoesNotSupportIt() {
        cut.executeJavaScript(StringUtils.EMPTY);
    }

    /* switches to and from other content */

    @Test
    public void testThatSwitchingToFrameWithIndexInvokesCorrectLocatorMethod() {
        TargetLocator locator = mockTargetLocator();
        cut.setFocusOnFrame(INDEX);
        verify(locator).frame(INDEX);
    }

    @Test
    public void testThatSwitchingToFrameWithIndexFiresEvent() {

        mockTargetLocator();
        cut.setFocusOnFrame(INDEX);

        SwitchedToFrameEvent event = verifyFired(SwitchedToFrameEvent.class);
        assertThat(event.getIndex(), is(INDEX));

    }

    @Test(expected = NoSuchFrameException.class)
    public void testExceptionHandlingInCaseAFrameIsNotFoundForTheGivenIndex() {

        TargetLocator locator = mockTargetLocator();
        NoSuchFrameException exception = createSeleniumExceptionOfClass(NoSuchFrameException.class);
        doThrow(exception).when(locator).frame(INDEX);

        try {
            cut.setFocusOnFrame(INDEX);
        } finally {
            verifyLastEventFiredWasExceptionEventOf(exception);
        }

    }

    @Test
    public void testThatSwitchingToFrameWithNameOrIdInvokesCorrectLocatorMethod() {
        TargetLocator locator = mockTargetLocator();
        cut.setFocusOnFrame(NAME_OR_ID);
        verify(locator).frame(NAME_OR_ID);
    }

    @Test
    public void testThatSwitchingToFrameWithNameOrIdFiresNoEvents() {

        mockTargetLocator();
        cut.setFocusOnFrame(NAME_OR_ID);

        SwitchedToFrameEvent event = verifyFired(SwitchedToFrameEvent.class);
        assertThat(event.getNameOrId(), is(NAME_OR_ID));

    }

    @Test(expected = NoSuchFrameException.class)
    public void testExceptionHandlingInCaseAFrameIsNotFoundForTheGivenNameOrId() {

        TargetLocator locator = mockTargetLocator();
        NoSuchFrameException exception = createSeleniumExceptionOfClass(NoSuchFrameException.class);
        doThrow(exception).when(locator).frame(NAME_OR_ID);

        try {
            cut.setFocusOnFrame(NAME_OR_ID);
        } finally {
            verifyLastEventFiredWasExceptionEventOf(exception);
        }

    }

    @Test
    public void testThatSwitchingToWindowWithNameOrHanldeInvokesCorrectLocatorMethod() {
        TargetLocator locator = mockTargetLocator();
        cut.setFocusOnWindow(NAME_OR_HANDLE);
        verify(locator).window(NAME_OR_HANDLE);
    }

    @Test
    public void testThatSwitchingToWindowWithNameOrHanldeFiresNoEvents() {

        mockTargetLocator();
        cut.setFocusOnWindow(NAME_OR_HANDLE);

        SwitchedToWindowEvent event = verifyFired(SwitchedToWindowEvent.class);
        assertThat(event.getNameOrHandle(), is(NAME_OR_HANDLE));

    }

    @Test(expected = NoSuchWindowException.class)
    public void testExceptionHandlingInCaseAWindowIsNotFoundForTheGivenNameOrHandle() {

        TargetLocator locator = mockTargetLocator();
        NoSuchWindowException exception = createSeleniumExceptionOfClass(NoSuchWindowException.class);
        doThrow(exception).when(locator).window(NAME_OR_HANDLE);

        try {
            cut.setFocusOnWindow(NAME_OR_HANDLE);
        } finally {
            verifyLastEventFiredWasExceptionEventOf(exception);
        }

    }

    @Test
    public void testThatSwitchingToDefaultContentInvokesCorrectLocatorMethod() {
        TargetLocator locator = mockTargetLocator();
        cut.setFocusOnDefaultContent();
        verify(locator).defaultContent();
    }

    @Test
    public void testThatSwitchingToDefaultContentFiresNoEvents() {
        mockTargetLocator();
        cut.setFocusOnDefaultContent();
        verifyFired(SwitchedToDefaultContentEvent.class);
    }

    /* action template methods */

    @Test
    public void testThatBrowserCallbackActionExecutesCallback() {

        BrowserCallback callback = mock(BrowserCallback.class);

        cut.executeAction(callback);

        verify(callback).execute(cut);
        verifyNoMoreInteractions(callback);

    }

    @Test(expected = RuntimeException.class)
    public void testThatBrowserCallbackActionHandlesExceptionsCorrectly() {

        RuntimeException exception = new RuntimeException();

        BrowserCallback callback = mock(BrowserCallback.class);
        doThrow(exception).when(callback).execute(cut);

        try {
            cut.executeAction(callback);
        } finally {
            verifyLastEventFiredWasExceptionEventOf(exception);
        }

    }

    @Test
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void testThatBrowserCallbackWithReturnValueActionExecutesCallback() {

        Object value = new Object();

        BrowserCallbackWithReturnValue callback = mock(BrowserCallbackWithReturnValue.class);
        doReturn(value).when(callback).execute(cut);

        Object returnedObject = cut.executeAction(callback);

        assertThat(returnedObject, is(sameInstance(value)));
        verify(callback).execute(cut);
        verifyNoMoreInteractions(callback);

    }

    @Test(expected = RuntimeException.class)
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void testThatBrowserCallbackWithReturnValueActionHandlesExceptionsCorrectly() {

        RuntimeException exception = new RuntimeException();

        BrowserCallbackWithReturnValue callback = mock(BrowserCallbackWithReturnValue.class);
        doThrow(exception).when(callback).execute(cut);

        try {
            cut.executeAction(callback);
        } finally {
            verifyLastEventFiredWasExceptionEventOf(exception);
        }

    }

    /* utilities */

    private TargetLocator mockTargetLocator() {
        TargetLocator locator = mock(TargetLocator.class);
        doReturn(locator).when(webDriver).switchTo();
        return locator;
    }

    private Navigation mockNavigation() {
        Navigation navigation = mock(Navigation.class);
        doReturn(navigation).when(webDriver).navigate();
        return navigation;
    }

    private Window mockWindow() {
        Options options = mock(Options.class);
        doReturn(options).when(webDriver).manage();
        Window window = mock(Window.class);
        doReturn(window).when(options).window();
        return window;
    }

    /**
     * Original Selenium exceptions execute methods on the Browser in order to
     * gather more info on the context of the exception. Since this is a unit
     * test with a mocked WebDriver, calling 'new' on any Selenium exception
     * class will lead to a NullPointerException! This is why the following
     * Exception is a mock.
     *
     * @param exceptionClass class of the Selenium exception to create
     * @return the mocked Selenium Exception
     */
    private <T extends WebDriverException> T createSeleniumExceptionOfClass(Class<T> exceptionClass) {
        return mock(exceptionClass);
    }

    private void verifyLastEventFiredWasExceptionEventOf(Throwable ex) {
        verify(listener).eventOccurred(eventCaptor.capture());
        Event event = eventCaptor.getValue();
        assertThat(event, is(instanceOf(ExceptionEvent.class)));
        assertThat((( ExceptionEvent ) event).getException(), is(sameInstance(ex)));
    }

    @SuppressWarnings("unchecked")
    private <T extends Event> T verifyFired(Class<T> eventClass) {

        verify(listener).eventOccurred(eventCaptor.capture());

        Event event = eventCaptor.getValue();
        assertThat(event, is(instanceOf(eventClass)));
        return ( T ) event;

    }

    @SuppressWarnings("unused")
    private void verifyThatNoEventsWereFired() {
        verify(listener, never()).eventOccurred(any(Event.class));
    }

}
