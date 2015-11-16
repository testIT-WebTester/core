package info.novatec.testit.webtester.eventsystem;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.novatec.testit.webtester.api.events.Event;
import info.novatec.testit.webtester.api.events.EventListener;


/**
 * The central class of the event system. It provides methods to
 * {@link #registerListener(EventListener) register} and
 * {@link #deregisterListener(EventListener) deregister} an {@link EventListener
 * event listener}. These listeners are informed of {@link Event events} which
 * are {@link #fireEvent(Event) fired} in via this class as well.
 * <p>
 * Event listeners will be informed of events in the order they were registered.
 * Each call to an event listener is done synchronously.
 *
 * @since 0.9.0
 */
public final class EventSystem {

    private static final Logger logger = LoggerFactory.getLogger(EventSystem.class);
    private static final List<EventListener> LISTENERS = new LinkedList<EventListener>();

    /**
     * Registers an {@link EventListener event listener}. It will be informed of
     * any {@link Event events} that are reported by the framework.
     *
     * @param listener the {@link EventListener event listener} to register.
     * @since 0.9.0
     */
    public static void registerListener(EventListener listener) {
        synchronized ( LISTENERS ) {
            LISTENERS.add(listener);
        }
        logger.debug("registered listener: {}", listener);
    }

    /**
     * Deregisters an {@link EventListener event listener}. It will no longer be
     * informed of any {@link Event events} that are reported by the framework.
     *
     * @param listener the {@link EventListener event listener} to unregister.
     * @since 0.9.0
     */
    public static void deregisterListener(EventListener listener) {
        synchronized ( LISTENERS ) {
            LISTENERS.remove(listener);
        }
        logger.debug("deregistered listener: {}", listener);
    }

    /**
     * Removes all {@link EventListener event listeners} from the registry.
     *
     * @since 0.9.0
     */
    public static void clearListeners() {
        synchronized ( LISTENERS ) {
            LISTENERS.clear();
        }
        logger.debug("cleared all listener");
    }

    /**
     * Informs all of the currently registered {@link EventListener event
     * listeners} of the given {@link Event event}. Any {@link RuntimeException
     * undeclared exceptions} thrown by any of the registered listeners will be
     * logged and otherwise ignored.
     *
     * @param event the {@link Event event} to fire.
     * @since 0.9.0
     */
    public static void fireEvent(Event event) {
        logger.debug("firing event: {}", event);
        for (EventListener listener : threadSafelyGetEventListeners()) {
            tryToInformListenerOfEvent(event, listener);
        }
    }

    private static List<EventListener> threadSafelyGetEventListeners() {
        synchronized ( LISTENERS ) {
            return new LinkedList<EventListener>(LISTENERS);
        }
    }

    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    private static void tryToInformListenerOfEvent(Event event, EventListener listener) {
        logger.trace("informing listener {} about event {}", listener, event);
        try {
            listener.eventOccurred(event);
        } catch (RuntimeException e) {
            logger.warn("exception while calling event listener: " + listener, e);
        }
    }

    private EventSystem() {
        // utility class constructor
    }

}
