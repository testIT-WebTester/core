package info.novatec.testit.webtester.eventsystem.events.pageobject;

import static java.lang.String.format;

import info.novatec.testit.webtester.api.events.Event;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * This {@link Event event} occurs whenever a double click is executed on an
 * element.
 *
 * @since 0.9.0
 */
@SuppressWarnings("serial")
public class DoubleClickedEvent extends AbstractPageObjectEvent {

    public DoubleClickedEvent(PageObject pageObject) {
        super(pageObject);
    }

    @Override
    public String getEventMessage() {
        return format("doubleclicked on %s", getSubjectName());
    }

}
