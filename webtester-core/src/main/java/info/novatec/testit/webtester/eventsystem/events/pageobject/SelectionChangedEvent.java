package info.novatec.testit.webtester.eventsystem.events.pageobject;

import static java.lang.String.format;

import info.novatec.testit.webtester.api.events.Event;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * This {@link Event event} occurs whenever a boolean selection of an element is
 * changed. It includes the original and the new value as properties.
 *
 * @since 0.9.0
 */
@SuppressWarnings("serial")
public class SelectionChangedEvent extends AbstractPageObjectEvent {

    private boolean before;
    private boolean after;

    public SelectionChangedEvent(PageObject pageObject, boolean before, boolean after) {
        super(pageObject);
        this.before = before;
        this.after = after;
    }

    @Override
    public String getEventMessage() {
        return format("changed selection of %s from %s to %s", getSubjectName(), before, after);
    }

    public boolean getBefore() {
        return before;
    }

    public boolean getAfter() {
        return after;
    }

}
