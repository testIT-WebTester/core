package info.novatec.testit.webtester.eventsystem.listener;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.Test;

import info.novatec.testit.webtester.api.events.Event;


public class DebugLoggingListenerTest {

    private static final String MESSAGE = "foo bar";

    private DebugLoggingListener cut = new DebugLoggingListener();

    @Test
    public void testThatEventMessageIsUsed() {

        Event event = mock(Event.class);
        doReturn(MESSAGE).when(event).getEventMessage();

        cut.eventOccurred(event);

        verify(event).getEventMessage();
        verifyNoMoreInteractions(event);

    }

}
