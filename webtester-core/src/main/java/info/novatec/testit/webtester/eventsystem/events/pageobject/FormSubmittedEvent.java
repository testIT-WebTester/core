package info.novatec.testit.webtester.eventsystem.events.pageobject;


import static java.lang.String.format;

import info.novatec.testit.webtester.api.events.Event;
import info.novatec.testit.webtester.pageobjects.Form;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * This {@link Event event} occurs whenever a form is submitted.
 *
 * @see Event
 * @see Form
 * @since 1.2
 */
@SuppressWarnings("serial")
public class FormSubmittedEvent extends AbstractPageObjectEvent{

    private static final String MESSAGE_FORMAT = "Submitted form: %s.";

    public FormSubmittedEvent(PageObject pageObject) {
        super(pageObject);
    }

    @Override
    public String getEventMessage() {
        return format(MESSAGE_FORMAT, getSubjectName());
    }

}
