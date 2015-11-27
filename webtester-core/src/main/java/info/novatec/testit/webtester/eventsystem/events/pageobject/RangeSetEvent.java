package info.novatec.testit.webtester.eventsystem.events.pageobject;

import static java.lang.String.format;
import info.novatec.testit.webtester.api.events.Event;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * This {@link Event event} occurs whenever a text of an element is set. It
 * includes the original text and the newly set text as properties.
 * 
 * @since 0.9.7
 */
@SuppressWarnings ( "serial" )
public class RangeSetEvent extends AbstractPageObjectEvent {

    private static final String MESSAGE_FORMAT = "changed range of %s from '%s' to '%s' by trying to set it to '%s'";

    private final String before;
    private final String after;
    private final String textToSet;

    public RangeSetEvent (PageObject pageObject, String before, String after, String textToSet) {
        super(pageObject);
        this.before = before;
        this.after = after;
        this.textToSet = textToSet;
    }

    @Override
    public String getEventMessage () {
        return format(MESSAGE_FORMAT, getSubjectName(), before, after, textToSet);
    }

    public String getBefore () {
        return before;
    }

    public String getAfter () {
        return after;
    }

    public String getTextToSet () {
        return textToSet;
    }
}
