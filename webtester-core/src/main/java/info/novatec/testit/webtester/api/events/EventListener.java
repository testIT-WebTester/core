package info.novatec.testit.webtester.api.events;

import info.novatec.testit.webtester.eventsystem.EventSystem;


/**
 * Defines a listener that can be registered at the {@link EventSystem event
 * system} . If an {@link Event event} is reported by the system the
 * {@link #eventOccurred(Event)} method of all registered listeners is called.
 *
 * @see Event
 * @see EventSystem
 * @since 0.9.0
 */
public interface EventListener {

    /**
     * This method will be called if any {@link Event event} is reported by the
     * {@link EventSystem event system}.
     *
     * @param event the {@link Event event} that occurred.
     * @since 0.9.0
     */
    void eventOccurred(Event event);

}
