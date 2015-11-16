package info.novatec.testit.webtester.eventsystem;

import static info.novatec.testit.webtester.eventsystem.EventSystem.clearListeners;
import static info.novatec.testit.webtester.eventsystem.EventSystem.deregisterListener;
import static info.novatec.testit.webtester.eventsystem.EventSystem.fireEvent;
import static info.novatec.testit.webtester.eventsystem.EventSystem.registerListener;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.events.Event;
import info.novatec.testit.webtester.api.events.EventListener;
import info.novatec.testit.webtester.browser.WebDriverBrowser;
import info.novatec.testit.webtester.eventsystem.events.browser.RefreshedPageEvent;


@RunWith(MockitoJUnitRunner.class)
public class EventSystemTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventSystemTest.class);

    private static final long ONE_MILLISECOND = 1L;

    @Mock
    private EventListener listener1;
    @Mock
    private EventListener listener2;
    @Mock
    private Event event;

    private boolean exceptionOccured;

    @Before
    @After
    public void clearRegistry() {
        EventSystem.clearListeners();
    }

    @Test
    public void testThatListenersAreNotifiedOfEventsInOrderOfTheirRegistration() {

        registerListener(listener1);
        registerListener(listener2);
        fireEvent(event);

        InOrder inOrder = inOrder(listener1, listener2);
        inOrder.verify(listener1).eventOccurred(event);
        inOrder.verify(listener2).eventOccurred(event);
        inOrder.verifyNoMoreInteractions();

    }

    @Test
    public void testThatUnregisteredListenersAreNoLongerNotifiedOfEvents() {

        registerListener(listener1);
        registerListener(listener2);
        fireEvent(event);
        deregisterListener(listener1);
        fireEvent(event);

        InOrder inOrder = inOrder(listener1, listener2);
        inOrder.verify(listener1).eventOccurred(event);
        inOrder.verify(listener2, times(2)).eventOccurred(event);
        inOrder.verifyNoMoreInteractions();

    }

    @Test
    public void testThatAllExceptionsWhileInformingAListenerOfAnEventAreIgnored() {

        doThrow(RuntimeException.class).when(listener1).eventOccurred(event);

        registerListener(listener1);
        registerListener(listener2);
        fireEvent(event);

        verify(listener1).eventOccurred(event);
        verify(listener2).eventOccurred(event);
        verifyNoMoreInteractions(listener1, listener2);

    }

    @Test
    public void testThatClearingAllListenersNoLongerNotifiesPreviouslyRegisteredListeners() {

        registerListener(listener1);
        registerListener(listener2);
        clearListeners();
        fireEvent(event);

        verifyZeroInteractions(listener1, listener2);

    }

    @Test
    public void testThatConcurrentAddingAndRemovingOfListenersWhileFireingEventsDoesNotLeadToConcurrencyIssues()
        throws InterruptedException {

        final WebDriver webDriver = mock(WebDriver.class);
        final Browser browser = WebDriverBrowser.forWebDriver(webDriver).build();

        Thread thread1 = createAndStartThread(new Runnable() {

            @Override
            public void run() {
                registerListener(listener1);
                deregisterListener(listener2);
            }

        });

        Thread thread2 = createAndStartThread(new Runnable() {

            @Override
            public void run() {
                registerListener(listener2);
                deregisterListener(listener1);
            }

        });

        Thread thread3 = createAndStartThread(new Runnable() {

            @Override
            public void run() {
                clearListeners();
            }

        });

        Thread eventThread = createAndStartThread(new Runnable() {

            @Override
            public void run() {
                fireEvent(new RefreshedPageEvent(browser));
            }

        });

        thread1.join();
        thread2.join();
        thread3.join();
        eventThread.join();

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
