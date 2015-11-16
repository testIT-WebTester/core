package info.novatec.testit.webtester.eventsystem.events.pageobject;

import static java.lang.String.format;

import info.novatec.testit.webtester.api.events.Event;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * This {@link Event event} occurs whenever a select box selection is made by
 * index. It includes the selected index as a property.
 *
 * @since 0.9.0
 */
@SuppressWarnings("serial")
public class SelectedByIndexEvent extends AbstractPageObjectEvent {

    private int index;

    public SelectedByIndexEvent(PageObject pageObject, int index) {
        super(pageObject);
        this.index = index;
    }

    @Override
    public String getEventMessage() {
        return format("selected index %s of %s", index, getSubjectName());
    }

    public int getIndex() {
        return index;
    }

}
