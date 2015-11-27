package info.novatec.testit.webtester.eventsystem.events.pageobject;

import static java.lang.String.format;
import info.novatec.testit.webtester.api.events.Event;
import info.novatec.testit.webtester.pageobjects.PageObject;

import org.openqa.selenium.support.Color;


/**
 * This {@link Event event} occurs whenever the color of an element is set. It
 * includes the original color and the newly set color as properties.
 * 
 * @since 0.9.0
 */
@SuppressWarnings ( "serial" )
public class ColorSetEvent extends AbstractPageObjectEvent {

    private static final String MESSAGE_FORMAT = "changed color of %s from '%s' to '%s' by trying to set it to '%s'";

    private final Color before;
    private final Color after;
    private final Color colorToSet;

    public ColorSetEvent (PageObject pageObject, Color before, Color after, Color textToSet) {
        super(pageObject);
        this.before = before;
        this.after = after;
        this.colorToSet = textToSet;
    }

    @Override
    public String getEventMessage () {
        return format(MESSAGE_FORMAT, getSubjectName(), before, after, colorToSet);
    }

    public Color getBefore () {
        return before;
    }

    public Color getAfter () {
        return after;
    }

    public Color getTextToSet () {
        return colorToSet;
    }

}
