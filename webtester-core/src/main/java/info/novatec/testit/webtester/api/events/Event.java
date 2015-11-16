package info.novatec.testit.webtester.api.events;

import java.io.Serializable;
import java.util.Date;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.browser.BrowserIdentification;


/**
 * Represents an event in the framework. An event can be something simple as
 * clicking a button or something as complex as taking a screenshot.
 *
 * @since 0.9.0
 */
public interface Event extends Serializable {

    /**
     * Returns a message describing the {@link Event event} in a human readable
     * format.
     *
     * @return the message
     * @since 0.9.0
     */
    String getEventMessage();

    /**
     * Returns the {@link BrowserIdentification identification} of the
     * {@link Browser browser} the {@link Event event} occurred in.
     *
     * @return the identification
     * @since 0.9.0
     */
    BrowserIdentification getBrowserIdentification();

    /**
     * Returns the date and time when the {@link Event event} occurred.
     *
     * @return the date and time.
     * @since 0.9.0
     */
    Date getCreationDateAndTime();

    /**
     * Returns the name of the {@link Event events} subject. Generally this
     * should be either the name of a specific element or the identification
     * parameters used to find it in the DOM.
     *
     * @return the event's subject's name
     * @since 0.9.0
     */
    String getSubjectName();

}
