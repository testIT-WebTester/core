package info.novatec.testit.webtester.browser;

import static info.novatec.testit.webtester.browser.BrowserRegistry.clear;
import static info.novatec.testit.webtester.browser.BrowserRegistry.deregisterBrowser;
import static info.novatec.testit.webtester.browser.BrowserRegistry.executeForAllBrowsers;
import static info.novatec.testit.webtester.browser.BrowserRegistry.lookupBrowser;
import static info.novatec.testit.webtester.browser.BrowserRegistry.registerBrowser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.callbacks.BrowserCallback;


@RunWith(MockitoJUnitRunner.class)
public class BrowserRegistryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrowserRegistryTest.class);

    private static final long ONE_MILLISECOND = 1L;

    private Browser browser1;
    private Browser browser2;

    private boolean exceptionOccured;

    @Before
    public void setUp() {
        BrowserRegistry.clear();
        browser1 = WebDriverBrowser.forWebDriver(mock(WebDriver.class)).build();
        browser2 = WebDriverBrowser.forWebDriver(mock(WebDriver.class)).build();
    }

    @After
    public void tearDown() {
        BrowserRegistry.clear();
    }

    @Test
    public void testThatRegisteredBrowsersCanBeLookedUp() {

        registerBrowser(browser1);
        registerBrowser(browser2);

        Browser resolved1 = lookupBrowser(browser1.getIdentification());
        Browser resolved2 = lookupBrowser(browser2.getIdentification());

        assertThat(resolved1, is(sameInstance(resolved1)));
        assertThat(resolved2, is(sameInstance(resolved2)));

    }

    @Test
    public void testThatUnregisteredBrowsersCanNoLongerBeLookedUp() {

        registerBrowser(browser1);
        registerBrowser(browser2);
        deregisterBrowser(browser2);

        Browser resolved1 = lookupBrowser(browser1.getIdentification());
        Browser resolved2 = lookupBrowser(browser2.getIdentification());

        assertThat(resolved1, is(sameInstance(resolved1)));
        assertThat(resolved2, is(nullValue()));

    }

    @Test
    public void testThatClearingAllListenersNoLongerNotifiesPreviouslyRegisteredListeners() {

        registerBrowser(browser1);
        registerBrowser(browser2);
        clear();

        Browser resolved1 = lookupBrowser(browser1.getIdentification());
        Browser resolved2 = lookupBrowser(browser2.getIdentification());

        assertThat(resolved1, is(nullValue()));
        assertThat(resolved2, is(nullValue()));

    }

    @Test
    public void testThatCallbacksCanBeExecutedForAllBrowsers() {

        registerBrowser(browser1);
        registerBrowser(browser2);

        BrowserCallback callback = mock(BrowserCallback.class);

        executeForAllBrowsers(callback);

        verify(callback).execute(browser1);
        verify(callback).execute(browser2);

    }

    @Test
    public void testThatExceptionsInCallbackExecutionAreIgnored() {

        BrowserCallback callback = mock(BrowserCallback.class);

        registerBrowser(browser1);
        registerBrowser(browser2);

        doThrow(RuntimeException.class).when(callback).execute(browser1);

        executeForAllBrowsers(callback);

        verify(callback).execute(browser1);
        verify(callback).execute(browser2);

    }

    @Test
    public void testThatConcurrentAddingAndRemovingOfBrowsersWhileExecutingCallbacksDoesNotLeadToConcurrencyIssues()
        throws InterruptedException {

        final BrowserCallback callback = mock(BrowserCallback.class);

        Thread thread1 = createAndStartThread(new Runnable() {

            @Override
            public void run() {
                registerBrowser(browser1);
                deregisterBrowser(browser2);
            }

        });

        Thread thread2 = createAndStartThread(new Runnable() {

            @Override
            public void run() {
                registerBrowser(browser2);
                deregisterBrowser(browser1);
            }

        });

        Thread thread3 = createAndStartThread(new Runnable() {

            @Override
            public void run() {
                clear();
            }

        });

        Thread callbackThread = createAndStartThread(new Runnable() {

            @Override
            public void run() {
                executeForAllBrowsers(callback);
            }

        });

        thread1.join();
        thread2.join();
        thread3.join();
        callbackThread.join();

        assertThat(exceptionOccured, is(false));

    }

    private Thread createAndStartThread(final Runnable runnable) {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    for (int i = 0; i < 100; i++) {
                        Thread.sleep(ONE_MILLISECOND);
                        runnable.run();
                    }
                } catch (Exception e) {
                    reportException(e);
                }
            }

        });
        thread.setDaemon(false);
        thread.start();
        return thread;
    }

    private synchronized void reportException(Throwable e) {
        LOGGER.error("exception", e);
        exceptionOccured = true;
    }

}
