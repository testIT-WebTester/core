package info.novatec.testit.webtester.eventsystem.events.pageobject;

import info.novatec.testit.webtester.api.events.Event;
import info.novatec.testit.webtester.eventsystem.events.browser.AbstractBrowserEvent;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Base class for {@link Event} objects that represent and event of a
 * {@link PageObject}.
 *
 * @since 0.9.0
 */
@SuppressWarnings("serial")
public abstract class AbstractPageObjectEvent extends AbstractBrowserEvent {

    private String subjectName;

    protected AbstractPageObjectEvent(PageObject pageObject) {
        super(pageObject.getBrowser());
        subjectName = pageObject.toString();
    }

    @Override
    public String getSubjectName() {
        return subjectName;
    }

}
