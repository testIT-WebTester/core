package info.novatec.testit.webtester.eventsystem.events.pageobject;

import static java.lang.String.format;

import info.novatec.testit.webtester.api.events.Event;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * This {@link Event event} occurs whenever a text of an element is cleared.
 *
 * @since 0.9.0
 */
@SuppressWarnings("serial")
public class TextClearedEvent extends AbstractPageObjectEvent {

    private static final String MESSAGE_FORMAT = "changed text of %s from '%s' to '%s' by clearing it";

    private String before;
    private String after;

    public TextClearedEvent(PageObject pageObject, String before, String after) {
        super(pageObject);
        this.before = before;
        this.after = after;
    }

    @Override
    public String getEventMessage() {
        return format(MESSAGE_FORMAT, getSubjectName(), before, after);
    }

    public String getBefore() {
        return before;
    }

    public String getAfter() {
        return after;
    }

}
