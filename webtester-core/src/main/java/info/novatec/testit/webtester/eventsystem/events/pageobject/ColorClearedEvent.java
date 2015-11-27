package info.novatec.testit.webtester.eventsystem.events.pageobject;

import static java.lang.String.format;
import info.novatec.testit.webtester.api.events.Event;
import info.novatec.testit.webtester.pageobjects.PageObject;

import org.openqa.selenium.support.Color;


/**
 * This {@link Event event} occurs whenever the color of an element is cleared.
 * 
 * @since 0.9.0
 */
@SuppressWarnings("serial")
public class ColorClearedEvent extends AbstractPageObjectEvent {

    private static final String MESSAGE_FORMAT = "changed color of %s from '%s' to '%s' by clearing it";

    private final String before;
    private final String after;

    public ColorClearedEvent(PageObject pageObject, Color before, Color after) {
        super(pageObject);
        this.before = before.asHex();
        this.after = after.asHex();
    }

    @Override
    public String getEventMessage() {
        return format(MESSAGE_FORMAT, getSubjectName(), before, after);
    }

    public Color getBefore() {
        return Color.fromString(before);
    }

    public Color getAfter() {
        return Color.fromString(after);
    }

}
