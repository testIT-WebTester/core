package info.novatec.testit.webtester.pageobjects;

import org.openqa.selenium.WebElement;

import info.novatec.testit.webtester.api.callbacks.PageObjectCallbackWithReturnValue;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsDisabledException;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsInvisibleException;
import info.novatec.testit.webtester.api.pageobjects.traits.Selectable;
import info.novatec.testit.webtester.utils.Asserts;


/**
 * Represents a HTML radio button. The following HTML elements are supported:
 * <ul>
 * <li><b>tag:</b> input <b>type:</b> radio</li>
 * </ul>
 *
 * @since 0.9.0
 */
public class RadioButton extends PageObject implements Selectable {

    /**
     * Returns the selection state of this {@link RadioButton radio button} as a
     * boolean.
     *
     * @return true if the radio button is currently selected, otherwise false
     * @since 0.9.0
     */
    @Override
    public Boolean isSelected() {
        return executeAction(new PageObjectCallbackWithReturnValue<Boolean>() {

            @Override
            public Boolean execute(PageObject pageObject) {
                return getWebElement().isSelected();
            }

        });
    }

    /**
     * Selects this {@linkplain RadioButton radio button}. Will throw an
     * exception if the radio button is invisible or disabled!
     *
     * @return the same instance for fluent API
     * @throws PageObjectIsDisabledException if the button is disabled
     * @throws PageObjectIsInvisibleException if the button is invisible
     * @since 0.9.6
     */
    public RadioButton select() {
        return click();
    }

    /**
     * Executes a click on this {@linkplain RadioButton radio button}. Will
     * throw an exception if the radio button is invisible or disabled!
     *
     * @return the same instance for fluent API
     * @throws PageObjectIsDisabledException if the radio button is disabled
     * @throws PageObjectIsInvisibleException if the radio button is invisible
     * @since 0.9.6
     */
    @Override
    public RadioButton click() {
        Asserts.assertEnabledAndVisible(this);
        super.click();
        return this;
    }

    @Override
    protected boolean isCorrectClassForWebElement(WebElement webElement) {

        String tagName = webElement.getTagName();
        String type = webElement.getAttribute("type");

        boolean isCorrectTag = "input".equalsIgnoreCase(tagName);
        boolean isCorrectType = "radio".equalsIgnoreCase(type);

        return isCorrectTag && isCorrectType;

    }

}
