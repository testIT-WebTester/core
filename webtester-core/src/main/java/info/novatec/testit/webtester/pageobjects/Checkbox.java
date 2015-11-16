package info.novatec.testit.webtester.pageobjects;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.novatec.testit.webtester.api.callbacks.PageObjectCallback;
import info.novatec.testit.webtester.api.callbacks.PageObjectCallbackWithReturnValue;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsDisabledException;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsInvisibleException;
import info.novatec.testit.webtester.api.pageobjects.traits.Selectable;
import info.novatec.testit.webtester.eventsystem.events.pageobject.SelectionChangedEvent;
import info.novatec.testit.webtester.utils.Asserts;


/**
 * Represents a HTML checkbox. The following HTML elements are supported:
 * <ul>
 * <li><b>tag:</b> input <b>type:</b> checkbox</li>
 * </ul>
 *
 * @since 0.9.0
 */
public class Checkbox extends PageObject implements Selectable {

    private static final Logger logger = LoggerFactory.getLogger(Checkbox.class);

    private static final String CHANGED_SELECTION = "changed selection from '{}' to '{}'";

    /**
     * Returns the selection state of this {@link Checkbox checkbox} as a
     * boolean.
     *
     * @return true if the checkbox is currently selected, otherwise false
     * @since 0.9.0
     */
    @Override
    public Boolean isSelected() {
        return executeAction(new PageObjectCallbackWithReturnValue<Boolean>() {

            @Override
            public Boolean execute(PageObject pageObject) {
                return pageObject.getWebElement().isSelected();
            }

        });
    }

    /**
     * Sets the selection state of the {@link Checkbox checkbox}. If the
     * checkbox already has the given state nothing will be done!
     *
     * @param selected the state in which the {@link Checkbox} selection should
     * be afterwards
     * @return the same checkbox for fluent API
     * @throws PageObjectIsDisabledException if the text field is disabled
     * @throws PageObjectIsInvisibleException if the text field is invisible
     * @since 0.9.0
     */
    public Checkbox setSelection(final boolean selected) {
        executeAction(new PageObjectCallback() {

            @Override
            public void execute(PageObject pageObject) {
                Checkbox checkbox = ( Checkbox ) pageObject;
                Boolean oldState = checkbox.isSelected();
                if (oldState != selected) {

                    Asserts.assertEnabledAndVisible(checkbox);
                    checkbox.getWebElement().click();

                    Boolean newState = isSelected();
                    logger.debug(logMessage(CHANGED_SELECTION), oldState, newState);
                    fireEventAndMarkAsUsed(new SelectionChangedEvent(checkbox, oldState, newState));

                }
            }

        });
        return this;
    }

    /**
     * Executes a click on this {@linkplain Checkbox checkbox}. Will throw an
     * exception if the checkbox is invisible or disabled!
     *
     * @return the same instance for fluent API
     * @throws PageObjectIsDisabledException if the checkbox is disabled
     * @throws PageObjectIsInvisibleException if the checkbox is invisible
     * @since 0.9.6
     */
    @Override
    public Checkbox click() {
        Asserts.assertEnabledAndVisible(this);
        super.click();
        return this;
    }

    @Override
    protected boolean isCorrectClassForWebElement(WebElement webElement) {

        String tagName = webElement.getTagName();
        String type = webElement.getAttribute("type");

        boolean isCorrectTag = "input".equalsIgnoreCase(tagName);
        boolean isCorrectType = "checkbox".equalsIgnoreCase(type);

        return isCorrectTag && isCorrectType;

    }

}
