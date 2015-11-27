package info.novatec.testit.webtester.pageobjects.html5;

import info.novatec.testit.webtester.api.callbacks.PageObjectCallback;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsDisabledException;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsInvisibleException;
import info.novatec.testit.webtester.eventsystem.events.pageobject.RangeClearedEvent;
import info.novatec.testit.webtester.eventsystem.events.pageobject.RangeSetEvent;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.TextField;
import info.novatec.testit.webtester.utils.Asserts;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Represents the HTML5 input type Range.
 * <ul>
 * <li><b>tag:</b> input <b>type:</b> range</li>
 * </ul>
 * 
 * <b>Note:</b> Input type Range is currently not supported in IE.
 * 
 * @since 1.1.0
 */
public class RangeField extends TextField {
    private static final Logger logger = LoggerFactory.getLogger(RangeField.class);

    private static final String CLEARED_TEXT = "changed range from '{}' to '{}' by clearing it";
    private static final String SET_RANGE = "changed range from '{}' to '{}' by trying to set it to '{}'";
    private static final String INPUT_TAG = "input";

    /**
     * Retrieves the {@link Integer range} of this {@link RangeField range
     * field} .
     * 
     * @return the range of this range field as integer
     * @since 1.1.0
     */
    public Integer getRange() {
        String value = getText();
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return Integer.valueOf(value);
    }

    /**
     * Retrieves the {@link Integer max range} of this {@link RangeField range
     * field} .
     * 
     * @return the max range of this range field as integer
     * @since 1.1.0
     */
    public Integer getMaxRange() {
        String value = getAttribute("max");
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return Integer.valueOf(value);
    }

    /**
     * Retrieves the {@link Integer min range} of this {@link RangeField range
     * field} .
     * 
     * @return the min range of this range field as integer
     * @since 1.1.0
     */
    public Integer getMinRange() {
        String value = getAttribute("min");
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return Integer.valueOf(value);
    }

    /**
     * Resets this {@link RangeField field} to the default {@link String range}
     * of 0.
     * 
     * @return the same instance for fluent API use
     * @throws PageObjectIsDisabledException if the range field is disabled
     * @throws PageObjectIsInvisibleException if the range field is invisible
     * @since 1.1.0
     */
    public RangeField reset() {
        executeAction(new AbstractRangeFieldCallback() {

            @Override
            public void executeAction(RangeField rangeField) {
                getBrowser().executeJavaScript("arguments[0].value='0'", getWebElement());
            }

            @Override
            protected void executeAfterAction(RangeField rangeField, String oldRange, String newRange) {
                logger.debug(logMessage(CLEARED_TEXT), oldRange, newRange);
                fireEventAndMarkAsUsed(new RangeClearedEvent(rangeField, oldRange, newRange));
            }

        });
        return this;
    }

    /**
     * Sets the given {@link int range} by replacing whatever range is currently
     * set for the {@link RangeField range field}.
     * 
     * @param range the range to be set
     * @return the same instance for fluent API use
     * @throws PageObjectIsDisabledException if the range field is disabled
     * @throws PageObjectIsInvisibleException if the range field is invisible
     * @since 1.1.0
     */
    public RangeField setRange(final int range) {
        reset();
        executeAction(new AbstractRangeFieldCallback() {

            @Override
            public void executeAction(RangeField rangeField) {
                getBrowser().executeJavaScript("arguments[0].value='" + range + "'", getWebElement());
            }

            @Override
            protected void executeAfterAction(RangeField RangeField, String oldRange, String newRange) {
                logger.debug(logMessage(SET_RANGE), oldRange, newRange, "" + range);
                fireEventAndMarkAsUsed(new RangeSetEvent(RangeField, oldRange, newRange, "" + range));
            }

        });
        return this;
    }

    @Override
    public boolean isCorrectClassForWebElement(WebElement webElement) {

        String tagName = webElement.getTagName();
        String type = StringUtils.defaultString(webElement.getAttribute("type")).toLowerCase();

        boolean isCorrectTag = INPUT_TAG.equalsIgnoreCase(tagName);
        boolean isCorrectType = "range".equalsIgnoreCase(type);

        return isCorrectTag && isCorrectType;

    }

    /**
     * Specialized abstract {@link PageObjectCallback page object callback} used
     * to handle common behavior for all {@link RangeField range field} actions.
     * 
     * @since 1.1.0
     */
    protected static abstract class AbstractRangeFieldCallback implements PageObjectCallback {

        @Override
        public final void execute(PageObject pageObject) {
            RangeField rangeField = ( RangeField ) pageObject;
            Asserts.assertEnabledAndVisible(rangeField);
            String oldRange = rangeField.getText();
            executeAction(rangeField);
            String newRange = rangeField.getText();
            executeAfterAction(rangeField, oldRange, newRange);
        }

        /**
         * Execute an action on a {@link RangeField range field}. This action
         * should not include things like firing events or logging output. Use
         * {@link #executeAfterAction(RangeField, String, String)} for that.
         * 
         * @param rangeField the range field to execute the action on
         */
        protected abstract void executeAction(RangeField rangeField);

        /**
         * Execute after action tasks like logging or firing events.
         * 
         * @param RangeField the range field
         * @param oldRange the range of the range field before the action was
         * executed
         * @param newRange the range of the range field after the action was
         * executed
         * 
         */
        protected abstract void executeAfterAction(RangeField rangeField, String oldRange, String newRange);

    }
}
