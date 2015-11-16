package info.novatec.testit.webtester.eventsystem.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.novatec.testit.webtester.api.events.Event;
import info.novatec.testit.webtester.api.events.EventListener;


/**
 * This {@link EventListener event listener} logs every occurrence
 * of any {@link Event event} as DEBUG output. The log message will be
 * the event's {@link Event#getEventMessage() message}.
 */
public class DebugLoggingListener implements EventListener {

    private static final Logger logger = LoggerFactory.getLogger(DebugLoggingListener.class);

    @Override
    public void eventOccurred(Event event) {
        logger.debug(event.getEventMessage());
    }

}
