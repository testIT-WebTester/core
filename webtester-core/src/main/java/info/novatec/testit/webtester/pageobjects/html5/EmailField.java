package info.novatec.testit.webtester.pageobjects.html5;

import info.novatec.testit.webtester.api.callbacks.PageObjectCallback;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsDisabledException;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsInvisibleException;
import info.novatec.testit.webtester.eventsystem.events.pageobject.EmailClearedEvent;
import info.novatec.testit.webtester.eventsystem.events.pageobject.EmailSetEvent;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.utils.Asserts;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Represents the HTML5 input type Email.
 * <ul>
 * <li><b>tag:</b> input <b>type:</b> email</li>
 * </ul>
 * 
 * <b>Note:</b> Input type Email is currently not supported in IE 9 and below.
 * 
 * @since 1.1.0
 */
public class EmailField extends PageObject {
    private static final Logger logger = LoggerFactory.getLogger(EmailField.class);

    private static final String CLEARED_TEXT = "changed email from '{}' to '{}' by clearing it";
    private static final String SET_EMAIL = "changed email from '{}' to '{}' by trying to set it to '{}'";
    private static final String INPUT_TAG = "input";

    /**
     * Retrieves the {@link String email} of this {@link EmailField email field}
     * . If no value is set an empty string will be returned.
     * 
     * @return the value of this email field as string
     * @since 1.1.0
     */
    public String getEmail() {
        return StringUtils.defaultString(getAttribute("value"));
    }

    /**
     * Clears this {@link EmailField field}.
     * 
     * @return the same instance for fluent API use
     * @throws PageObjectIsDisabledException if the email field is disabled
     * @throws PageObjectIsInvisibleException if the email field is invisible
     * @since 1.1.0
     */
    public EmailField clear() {
        executeAction(new AbstractEmailFieldCallback() {

            @Override
            public void executeAction(EmailField emailField) {
                getWebElement().clear();
            }

            @Override
            protected void executeAfterAction(EmailField emailField, String oldEmail, String newEmail) {
                logger.debug(logMessage(CLEARED_TEXT), oldEmail, newEmail);
                fireEventAndMarkAsUsed(new EmailClearedEvent(emailField, oldEmail, newEmail));
            }

        });
        return this;
    }

    /**
     * Sets the given {@link String email} by replacing whatever value is
     * currently set for the {@link EmailField email field}.
     * 
     * @param String the email address to be set
     * @return the same instance for fluent API use
     * @throws PageObjectIsDisabledException if the email field is disabled
     * @throws PageObjectIsInvisibleException if the email field is invisible
     * @since 1.1.0
     */
    public EmailField setEmailAdress(final String emailAdress) {
        executeAction(new AbstractEmailFieldCallback() {

            @Override
            public void executeAction(EmailField emailField) {
                getWebElement().clear();
                getWebElement().sendKeys(emailAdress);
            }

            @Override
            protected void executeAfterAction(EmailField emailField, String oldEmail, String newEmail) {
                logger.debug(logMessage(SET_EMAIL), oldEmail, newEmail, emailAdress);
                fireEventAndMarkAsUsed(new EmailSetEvent(emailField, oldEmail, newEmail, emailAdress));
            }

        });
        return this;
    }

    @Override
    protected boolean isCorrectClassForWebElement(WebElement webElement) {

        String tagName = webElement.getTagName();
        String type = StringUtils.defaultString(webElement.getAttribute("type")).toLowerCase();

        boolean isCorrectTag = INPUT_TAG.equalsIgnoreCase(tagName);
        boolean isCorrectType = "email".equalsIgnoreCase(type);

        return isCorrectTag && isCorrectType;

    }

    /**
     * Specialized abstract {@link PageObjectCallback page object callback} used
     * to handle common behavior for all {@link EmailField email field} actions.
     * 
     * @since 1.1.0
     */
    protected static abstract class AbstractEmailFieldCallback implements PageObjectCallback {

        @Override
        public final void execute(PageObject pageObject) {
            EmailField emailField = ( EmailField ) pageObject;
            Asserts.assertEnabledAndVisible(emailField);
            String oldEmail = emailField.getEmail();
            executeAction(emailField);
            String newEmail = emailField.getEmail();
            executeAfterAction(emailField, oldEmail, newEmail);
        }

        /**
         * Execute an action on a {@link EmailField email field}. This action
         * should not include things like firing events or logging output. Use
         * {@link #executeAfterAction(EmailField, String, String)} for that.
         * 
         * @param emailField the email field to execute the action on
         */
        protected abstract void executeAction(EmailField emailField);

        /**
         * Execute after action tasks like logging or firing events.
         * 
         * @param EmailField the email field
         * @param oldEmail the email of the email field before the action was
         * executed
         * @param newEmail the email of the email field after the action was
         * executed
         * 
         */
        protected abstract void executeAfterAction(EmailField emailField, String oldEmail, String newEmail);

    }
}
