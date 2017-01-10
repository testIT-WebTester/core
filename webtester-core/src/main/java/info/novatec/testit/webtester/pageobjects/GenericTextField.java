package info.novatec.testit.webtester.pageobjects;

import info.novatec.testit.webtester.api.annotations.Mapping;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.novatec.testit.webtester.api.callbacks.PageObjectCallback;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsDisabledException;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsInvisibleException;
import info.novatec.testit.webtester.api.pageobjects.traits.HasText;
import info.novatec.testit.webtester.eventsystem.events.pageobject.TextAppendedEvent;
import info.novatec.testit.webtester.eventsystem.events.pageobject.TextClearedEvent;
import info.novatec.testit.webtester.eventsystem.events.pageobject.TextSetEvent;
import info.novatec.testit.webtester.utils.Asserts;


/**
 * Represents a HTML generic text field. The following HTML elements are supported generic text
 * fields:
 * <ul>
 * <li><b>tag:</b> input <b>type:</b> -</li>
 * <li><b>tag:</b> input <b>type:</b> ""</li>
 * <li><b>tag:</b> input <b>type:</b> text</li>
 * </ul>
 * The following HTML elements are supported but not strictly generic text fields:
 * <ul>
 * <li><b>tag:</b> input <b>type:</b> password</li>
 * <li><b>tag:</b> input <b>type:</b> number</li>
 * </ul>
 *
 * @since 1.2.0
 */
@Mapping(tag = "input", attribute = "type", values = { "", "text", "password", "number" })
public class GenericTextField <T extends GenericTextField<T>> extends PageObject implements HasText {

    private static final Logger logger = LoggerFactory.getLogger(TextField.class);

    private static final String CLEARED_TEXT = "changed text from '{}' to '{}' by clearing it";
    private static final String SET_TEXT = "changed text from '{}' to '{}' by trying to set it to '{}'";
    private static final String APPEND_TEXT = "changed text from '{}' to '{}' by trying to append '{}'";

    /**
     * Retrieves the text value of this {@link GenericTextField text field}. If no text
     * is set an empty string is returned.
     *
     * @return the text of this text field
     * @since 1.2.0
     */
    @Override
    public String getText() {
        this.markAsRead();
        return StringUtils.defaultString(getAttribute("value"));
    }

    /**
     * Clears the text of the {@link GenericTextField text field} of any previously set
     * values.
     *
     * @return the same instance for fluent API use
     * @throws PageObjectIsDisabledException if the text field is disabled
     * @throws PageObjectIsInvisibleException if the text field is invisible
     * @since 1.2.0
     */
    public T clearText() {
        executeAction(new AbstractTextFieldCallback() {

                             @Override
            public void executeAction(GenericTextField genericTextField) {
                getWebElement().clear();
            }

            @Override
            protected void executeAfterAction(GenericTextField genericTextField, String oldText, String newText) {
                logger.debug(logMessage(CLEARED_TEXT), oldText, newText);
                fireEventAndMarkAsUsed(new TextClearedEvent(genericTextField, oldText, newText));
            }

        });
        return (T) this;
    }

    /**
     * Sets the given text by replacing whatever text is currently set for the
     * {@link GenericTextField text field}.
     * <p>
     * <b>Note:</b> is is not advised to try and send {@link Keys} via this method!
     * Doing so may in some cases lead to unintended side effects.
     *
     * @param textToSet the text to set
     * @return the same instance for fluent API use
     * @throws PageObjectIsDisabledException if the text field is disabled
     * @throws PageObjectIsInvisibleException if the text field is invisible
     * @since 1.2.0
     */
    public T setText(final String textToSet) {
        executeAction(new AbstractTextFieldCallback() {

            @Override
            public void executeAction(GenericTextField genericTextField) {
                getWebElement().clear();
                getWebElement().sendKeys(textToSet);
            }

            @Override
            protected void executeAfterAction(GenericTextField genericTextField, String oldText, String newText) {
                logger.debug(logMessage(SET_TEXT), oldText, newText, textToSet);
                fireEventAndMarkAsUsed(new TextSetEvent(genericTextField, oldText, newText, textToSet));
            }

        });
        return (T) this;
    }

    /**
     * Appends the given text to whatever text is currently set for the
     * {@link GenericTextField text field}.
     * <p>
     * <b>Note:</b> is is not advised to try and send {@link Keys} via this method!
     * Doing so may in some cases lead to unintended side effects.
     *
     * @param textToAppend the text to append
     * @return the same instance for fluent API use
     * @throws PageObjectIsDisabledException if the text field is disabled
     * @throws PageObjectIsInvisibleException if the text field is invisible
     * @since 1.2.0
     */
    public T appendText(final String textToAppend) {
        executeAction(new AbstractTextFieldCallback() {

            @Override
            public void executeAction(GenericTextField genericTextField) {
                getWebElement().sendKeys(textToAppend);
            }

            @Override
            protected void executeAfterAction(GenericTextField genericTextField, String oldText, String newText) {
                logger.debug(logMessage(APPEND_TEXT), oldText, newText, textToAppend);
                fireEventAndMarkAsUsed(new TextAppendedEvent(genericTextField, oldText, newText, textToAppend));
            }

        });
        return (T) this;
    }

    /**
     * Presses enter on this generic text field. This can be for example be used to send a form where the text
     * field is included.
     * <p>
     * This method does <u>not</u> return this instance for fluent API because pressing ENTER is usually done in
     * order to send a form or otherwise execute a potentially terminal action.
     *
     * @since 1.2.0
     */
    public void pressEnter() {
        executeAction(new PageObjectCallback() {

            @Override
            public void execute(PageObject pageObject) {
                pageObject.markAsUsed();
                pageObject.getWebElement().sendKeys(Keys.ENTER);
            }

        });
    }

    /**
     * Specialized abstract {@link PageObjectCallback page object callback} used
     * to handle common behavior for all {@link GenericTextField text field} actions.
     *
     * @since 1.2.0
     */
    protected static abstract class AbstractTextFieldCallback implements PageObjectCallback {

        @Override
        public final void execute(PageObject pageObject) {
            GenericTextField genericTextField = ( GenericTextField ) pageObject;
            Asserts.assertEnabledAndVisible(genericTextField);
            String oldText = genericTextField.getText();
            executeAction(genericTextField);
            String newText = genericTextField.getText();
            executeAfterAction(genericTextField, oldText, newText);
        }

        /**
         * Execute an action on a {@link GenericTextField text field}. This action
         * should not include things like firing events or logging output. Use
         * {@link #executeAfterAction(GenericTextField, String, String)} for that.
         *
         * @param genericTextField the text field to execute the action on
         */
        protected abstract void executeAction(GenericTextField genericTextField);

        /**
         * Execute after action tasks like logging or firing events.
         *
         * @param genericTextField the generic text field
         * @param oldText the text of the text field before the action was
         * executed
         * @param newText the text of the text field after the action was
         * executed
         */
        protected abstract void executeAfterAction(GenericTextField genericTextField, String oldText, String newText);

    }
}
