package info.novatec.testit.webtester.pageobjects.html5;

import info.novatec.testit.webtester.api.callbacks.PageObjectCallback;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsDisabledException;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsInvisibleException;
import info.novatec.testit.webtester.eventsystem.events.pageobject.UrlClearedEvent;
import info.novatec.testit.webtester.eventsystem.events.pageobject.UrlSetEvent;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.TextField;
import info.novatec.testit.webtester.utils.Asserts;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Represents the HTML5 input type Url.
 * <ul>
 * <li><b>tag:</b> input <b>type:</b> url</li>
 * </ul>
 * 
 * <b>Note:</b> Input type Url is currently not supported in Safari.
 * 
 * @since 1.1.0
 */
public class UrlField extends TextField {
    private static final Logger logger = LoggerFactory.getLogger(UrlField.class);

    private static final String CLEARED_TEXT = "changed url from '{}' to '{}' by clearing it";
    private static final String SET_RANGE = "changed url from '{}' to '{}' by trying to set it to '{}'";
    private static final String INPUT_TAG = "input";

    /**
     * Retrieves the {@link String url} of this {@link UrlField url field} .
     * 
     * @return the url of this url field as String
     * @since 1.1.0
     */
    public String getUrl() {
        return getAttribute("value");
    }

    /**
     * Resets this {@link UrlField field} to the default {@link String url} of
     * ''.
     * 
     * @return the same instance for fluent API use
     * @throws PageObjectIsDisabledException if the url field is disabled
     * @throws PageObjectIsInvisibleException if the url field is invisible
     * @since 1.1.0
     */
    public UrlField reset() {
        executeAction(new AbstractUrlFieldCallback() {

            @Override
            public void executeAction(UrlField urlField) {
                getBrowser().executeJavaScript("arguments[0].value=''", getWebElement());
            }

            @Override
            protected void executeAfterAction(UrlField urlField, String oldUrl, String newUrl) {
                logger.debug(logMessage(CLEARED_TEXT), oldUrl, newUrl);
                fireEventAndMarkAsUsed(new UrlClearedEvent(urlField, oldUrl, newUrl));
            }

        });
        return this;
    }

    /**
     * Sets the given {@link String url} by replacing whatever url is currently
     * set for the {@link UrlField url field}.
     * 
     * @param url the url to be set
     * @return the same instance for fluent API use
     * @throws PageObjectIsDisabledException if the url field is disabled
     * @throws PageObjectIsInvisibleException if the url field is invisible
     * @since 1.1.0
     */
    public UrlField setUrl(final String url) {
        reset();
        executeAction(new AbstractUrlFieldCallback() {

            @Override
            public void executeAction(UrlField urlField) {
                getBrowser().executeJavaScript("arguments[0].value='" + url + "'", getWebElement());
            }

            @Override
            protected void executeAfterAction(UrlField UrlField, String oldUrl, String newUrl) {
                logger.debug(logMessage(SET_RANGE), oldUrl, newUrl, url);
                fireEventAndMarkAsUsed(new UrlSetEvent(UrlField, oldUrl, newUrl, url));
            }

        });
        return this;
    }

    @Override
    public boolean isCorrectClassForWebElement(WebElement webElement) {

        String tagName = webElement.getTagName();
        String type = StringUtils.defaultString(webElement.getAttribute("type")).toLowerCase();

        boolean isCorrectTag = INPUT_TAG.equalsIgnoreCase(tagName);
        boolean isCorrectType = "url".equalsIgnoreCase(type);

        return isCorrectTag && isCorrectType;

    }

    /**
     * Specialized abstract {@link PageObjectCallback page object callback} used
     * to handle common behavior for all {@link UrlField url field} actions.
     * 
     * @since 1.1.0
     */
    protected static abstract class AbstractUrlFieldCallback implements PageObjectCallback {

        @Override
        public final void execute(PageObject pageObject) {
            UrlField urlField = ( UrlField ) pageObject;
            Asserts.assertEnabledAndVisible(urlField);
            String oldUrl = urlField.getText();
            executeAction(urlField);
            String newUrl = urlField.getText();
            executeAfterAction(urlField, oldUrl, newUrl);
        }

        /**
         * Execute an action on a {@link UrlField url field}. This action should
         * not include things like firing events or logging output. Use
         * {@link #executeAfterAction(UrlField, String, String)} for that.
         * 
         * @param urlField the url field to execute the action on
         */
        protected abstract void executeAction(UrlField urlField);

        /**
         * Execute after action tasks like logging or firing events.
         * 
         * @param UrlField the url field
         * @param oldUrl the url of the url field before the action was executed
         * @param newUrl the url of the url field after the action was executed
         * 
         */
        protected abstract void executeAfterAction(UrlField urlField, String oldUrl, String newUrl);

    }
}
