package info.novatec.testit.webtester.eventsystem.events.pageobject;

import static java.lang.String.format;
import info.novatec.testit.webtester.api.events.Event;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * This {@link Event event} occurs whenever a text of an element is cleared.
 * 
 * @since 0.9.7
 */
@SuppressWarnings ( "serial" )
public class EmailClearedEvent extends AbstractPageObjectEvent {
    private static final String MESSAGE_FORMAT = "changed text of %s from '%s' to '%s' by clearing it";

    private final String before;
    private final String after;

    public EmailClearedEvent (PageObject pageObject, String before, String after) {
        super(pageObject);
        this.before = before;
        this.after = after;
    }

    @Override
    public String getEventMessage () {
        return format(MESSAGE_FORMAT, getSubjectName(), before, after);
    }

    public String getBefore () {
        return before;
    }

    public String getAfter () {
        return after;
    }
}
