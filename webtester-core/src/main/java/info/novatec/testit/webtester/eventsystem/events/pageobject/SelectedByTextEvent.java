package info.novatec.testit.webtester.eventsystem.events.pageobject;

import static java.lang.String.format;

import info.novatec.testit.webtester.api.events.Event;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * This {@link Event event} occurs whenever a select box selection is made by
 * text. It includes the selected text as a property.
 *
 * @since 0.9.0
 */
@SuppressWarnings("serial")
public class SelectedByTextEvent extends AbstractPageObjectEvent {

    private String text;

    public SelectedByTextEvent(PageObject pageObject, String text) {
        super(pageObject);
        this.text = text;
    }

    @Override
    public String getEventMessage() {
        return format("selected text \"%s\" of %s", text, getSubjectName());
    }

    public String getText() {
        return text;
    }

}
