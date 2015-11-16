package info.novatec.testit.webtester.pageobjects;

import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;

import info.novatec.testit.webtester.api.callbacks.PageObjectCallback;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsDisabledException;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsInvisibleException;
import info.novatec.testit.webtester.api.pageobjects.traits.HasText;
import info.novatec.testit.webtester.eventsystem.events.pageobject.TextAppendedEvent;
import info.novatec.testit.webtester.eventsystem.events.pageobject.TextClearedEvent;
import info.novatec.testit.webtester.eventsystem.events.pageobject.TextSetEvent;
import info.novatec.testit.webtester.utils.Asserts;


/**
 * Represents a HTML text field. The following HTML elements are supported text
 * fields:
 * <ul>
 * <li><b>tag:</b> input <b>type:</b> -</li>
 * <li><b>tag:</b> input <b>type:</b> ""</li>
 * <li><b>tag:</b> input <b>type:</b> text</li>
 * </ul>
 * The following HTML elements are supported but not strictly text fields:
 * <ul>
 * <li><b>tag:</b> input <b>type:</b> password</li>
 * <li><b>tag:</b> input <b>type:</b> number</li>
 * </ul>
 *
 * @since 0.9.0
 */
public class TextField extends PageObject implements HasText {

    private static final Logger logger = LoggerFactory.getLogger(TextField.class);

    private static final String CLEARED_TEXT = "changed text from '{}' to '{}' by clearing it";
    private static final String SET_TEXT = "changed text from '{}' to '{}' by trying to set it to '{}'";
    private static final String APPEND_TEXT = "changed text from '{}' to '{}' by trying to append '{}'";

    private static final String INPUT_TAG = "input";
    private static final Set<String> VALID_TYPES = Sets.newHashSet("", "text", "password", "number");

    /**
     * Retrieves the text value of this {@link TextField text field}. If no text
     * is set an empty string is returned.
     *
     * @return the text of this text field
     * @since 0.9.0
     */
    @Override
    public String getText() {
        return StringUtils.defaultString(getAttribute("value"));
    }

    /**
     * Clears the text of the {@link TextField text field} of any previously set
     * values.
     *
     * @return the same instance for fluent API use
     * @throws PageObjectIsDisabledException if the text field is disabled
     * @throws PageObjectIsInvisibleException if the text field is invisible
     * @since 0.9.0
     */
    public TextField clearText() {
        executeAction(new AbstractTextFieldCallback() {

            @Override
            public void executeAction(TextField textField) {
                getWebElement().clear();
            }

            @Override
            protected void executeAfterAction(TextField textField, String oldText, String newText) {
                logger.debug(logMessage(CLEARED_TEXT), oldText, newText);
                fireEventAndMarkAsUsed(new TextClearedEvent(textField, oldText, newText));
            }

        });
        return this;
    }

    /**
     * Sets the given text by replacing whatever text is currently set for the
     * {@link TextField text field}.
     *
     * @param textToSet the text to set
     * @return the same instance for fluent API use
     * @throws PageObjectIsDisabledException if the text field is disabled
     * @throws PageObjectIsInvisibleException if the text field is invisible
     * @since 0.9.0
     */
    public TextField setText(final String textToSet) {
        executeAction(new AbstractTextFieldCallback() {

            @Override
            public void executeAction(TextField textField) {
                getWebElement().clear();
                getWebElement().sendKeys(textToSet);
            }

            @Override
            protected void executeAfterAction(TextField textField, String oldText, String newText) {
                logger.debug(logMessage(SET_TEXT), oldText, newText, textToSet);
                fireEventAndMarkAsUsed(new TextSetEvent(textField, oldText, newText, textToSet));
            }

        });
        return this;
    }

    /**
     * Appends the given text to whatever text is currently set for the
     * {@link TextField text field}.
     *
     * @param textToAppend the text to append
     * @return the same instance for fluent API use
     * @throws PageObjectIsDisabledException if the text field is disabled
     * @throws PageObjectIsInvisibleException if the text field is invisible
     * @since 0.9.0
     */
    public TextField appendText(final String textToAppend) {
        executeAction(new AbstractTextFieldCallback() {

            @Override
            public void executeAction(TextField textField) {
                getWebElement().sendKeys(textToAppend);
            }

            @Override
            protected void executeAfterAction(TextField textField, String oldText, String newText) {
                logger.debug(logMessage(APPEND_TEXT), oldText, newText, textToAppend);
                fireEventAndMarkAsUsed(new TextAppendedEvent(textField, oldText, newText, textToAppend));
            }

        });
        return this;
    }

    @Override
    protected boolean isCorrectClassForWebElement(WebElement webElement) {

        String tagName = webElement.getTagName();
        String type = StringUtils.defaultString(webElement.getAttribute("type")).toLowerCase();

        boolean isCorrectTag = INPUT_TAG.equalsIgnoreCase(tagName);
        boolean isCorrectType = VALID_TYPES.contains(type);

        return isCorrectTag && isCorrectType;

    }

    /**
     * Specialized abstract {@link PageObjectCallback page object callback} used
     * to handle common behavior for all {@link TextField text field} actions.
     *
     * @since 0.9.6
     */
    protected static abstract class AbstractTextFieldCallback implements PageObjectCallback {

        @Override
        public final void execute(PageObject pageObject) {
            TextField textField = ( TextField ) pageObject;
            Asserts.assertEnabledAndVisible(textField);
            String oldText = textField.getText();
            executeAction(textField);
            String newText = textField.getText();
            executeAfterAction(textField, oldText, newText);
        }

        /**
         * Execute an action on a {@link TextField text field}. This action
         * should not include things like firing events or logging output. Use
         * {@link #executeAfterAction(TextField, String, String)} for that.
         *
         * @param textField the text field to execute the action on
         */
        protected abstract void executeAction(TextField textField);

        /**
         * Execute after action tasks like logging or firing events.
         *
         * @param textField the text field
         * @param oldText the text of the text field before the action was
         * executed
         * @param newText the text of the text field after the action was
         * executed
         */
        protected abstract void executeAfterAction(TextField textField, String oldText, String newText);

    }

}
