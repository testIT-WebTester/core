package info.novatec.testit.webtester.eventsystem.events.pageobject;

import static java.lang.String.format;

import info.novatec.testit.webtester.api.events.Event;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * This {@link Event event} occurs whenever a text of an element is appended. It
 * includes the original text and the newly appended text as properties.
 *
 * @since 0.9.0
 */
@SuppressWarnings("serial")
public class TextAppendedEvent extends AbstractPageObjectEvent {

    private static final String MESSAGE_FORMAT = "changed text of %s from '%s' to '%s' by trying to append '%s'";

    private String before;
    private String after;
    private String textToAppend;

    public TextAppendedEvent(PageObject pageObject, String before, String after, String textToAppend) {
        super(pageObject);
        this.before = before;
        this.after = after;
        this.textToAppend = textToAppend;
    }

    @Override
    public String getEventMessage() {
        return format(MESSAGE_FORMAT, getSubjectName(), before, after, textToAppend);
    }

    public String getBefore() {
        return before;
    }

    public String getAfter() {
        return after;
    }

    public String getTextToAppend() {
        return textToAppend;
    }

}
