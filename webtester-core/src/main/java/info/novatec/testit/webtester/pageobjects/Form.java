package info.novatec.testit.webtester.pageobjects;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.novatec.testit.webtester.api.annotations.Mapping;
import info.novatec.testit.webtester.api.callbacks.PageObjectCallback;
import info.novatec.testit.webtester.eventsystem.events.pageobject.FormSubmittedEvent;
import info.novatec.testit.webtester.utils.Asserts;


/**
 * Represents a form. The following HTML elements are supported:
 * <ul>
 * <li><b>tag:</b> form</li>
 * </ul>
 *
 * @since 0.9.9
 */
@Mapping(tag = "form")
public class Form extends PageObject {

    private static final Logger logger = LoggerFactory.getLogger(TextField.class);

    private static final String SUBMIT = "Submitted form.";

    /**
     * Submits this {@link Form} for processing.
     *
     * @return the same form
     * @see Form
     * @see WebElement#submit()
     * @since 1.2
     */
    public Form submit() {
        executeAction(new AbstractFormCallback() {

            @Override
            protected void executeAction() {
                getWebElement().submit();
            }

            @Override
            protected void executeAfterAction(Form form) {
                logger.debug(SUBMIT);
                fireEventAndMarkAsUsed(new FormSubmittedEvent(form));
            }
        });
        return this;
    }

    /**
     * Specialized abstract {@link PageObjectCallback page object callback} used
     * to handle common behavior for all {@link Form form} actions.
     *
     * @since 1.2
     */
    protected static abstract class AbstractFormCallback implements PageObjectCallback {

        @Override
        public final void execute(PageObject pageObject) {
            Form form = (Form) pageObject;
            Asserts.assertEnabledAndVisible(form);
            executeAction();
            executeAfterAction(form);
        }

        /**
         * Executes an action on a {@link Form form}. This action should not include
         * firing events or logging outputs. Use {@link #executeAfterAction(Form from)}
         * for that.
         *
         */
        protected abstract void executeAction();

        /**
         * Executes after action tasks, including firing events and logging output.
         *
         * @param form the form
         */
        protected abstract void executeAfterAction(Form form);

    }
}
