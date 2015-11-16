package info.novatec.testit.webtester.eventsystem.events.pageobject;

import static java.lang.String.format;

import info.novatec.testit.webtester.api.events.Event;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * This {@link Event event} occurs whenever a select box selection is made by
 * value. It includes the selected value as a property.
 *
 * @since 0.9.0
 */
@SuppressWarnings("serial")
public class SelectedByValueEvent extends AbstractPageObjectEvent {

    private String value;

    public SelectedByValueEvent(PageObject pageObject, String value) {
        super(pageObject);
        this.value = value;
    }

    @Override
    public String getEventMessage() {
        return format("selected value \"%s\" of %s", value, getSubjectName());
    }

    public String getValue() {
        return value;
    }

}
