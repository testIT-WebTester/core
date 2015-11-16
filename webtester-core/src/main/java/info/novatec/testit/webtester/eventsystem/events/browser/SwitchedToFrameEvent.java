package info.novatec.testit.webtester.eventsystem.events.browser;

import static java.lang.String.format;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.events.Event;


/**
 * This {@link Event event} occurs whenever a switch to another frame occurred.
 * It includes the name / ID or the index of that frame as a property -
 * depending on which was used for the switch.
 *
 * @since 0.9.0
 */
@SuppressWarnings("serial")
public class SwitchedToFrameEvent extends AbstractBrowserEvent {

    private Integer index;
    private String nameOrId;

    public SwitchedToFrameEvent(Browser browser, String nameOrId) {
        super(browser);
        this.nameOrId = nameOrId;
    }

    public SwitchedToFrameEvent(Browser browser, int index) {
        super(browser);
        this.index = index;
    }

    @Override
    public String getEventMessage() {

        if (index != null) {
            return format("switched to frame using index: %s", index);
        } else if (nameOrId != null) {
            return format("switched to frame using name or id: %s", nameOrId);
        }

        return "illegal event state: neither index nor name / id is set!";

    }

    /**
     * @return the name or ID of the frame, if the frame was identified by name
     * or ID. Otherwise null is returned.
     */
    public String getNameOrId() {
        return nameOrId;
    }

    /**
     * @return the index of the frame, if the frame was identified by index.
     * Otherwise null is returned.
     */
    public Integer getIndex() {
        return index;
    }

}
