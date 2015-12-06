package info.novatec.testit.webtester.pageobjects.html5;

import info.novatec.testit.webtester.api.callbacks.PageObjectCallback;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsDisabledException;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsInvisibleException;
import info.novatec.testit.webtester.eventsystem.events.pageobject.ColorClearedEvent;
import info.novatec.testit.webtester.eventsystem.events.pageobject.ColorSetEvent;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.TextField;
import info.novatec.testit.webtester.utils.Asserts;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Represents the HTML5 input type Color.
 * <ul>
 * <li><b>tag:</b> input <b>type:</b> color</li>
 * </ul>
 * 
 * <b>Note:</b> Input type Color is currently not supported in IE.
 * 
 * @since 1.1.0
 */
public class ColorField extends TextField {
    private static final Logger logger = LoggerFactory.getLogger(ColorField.class);

    private static final String CLEARED_TEXT = "changed color from '{}' to '{}' by clearing it";
    private static final String SET_COLOR = "changed color from '{}' to '{}' by trying to set it to '{}'";
    private static final String INPUT_TAG = "input";

    /**
     * Retrieves the {@link Color color} of this {@link ColorField color field}.
     * If no color is set the default color #000000 is returned.
     * 
     * @return the color of this color field as color object
     * @since 1.1.0
     */
    public Color getColor() {
        String colorFieldValue = StringUtils.defaultString(getAttribute("value"));
        if (colorFieldValue.isEmpty()) {
            colorFieldValue = "#000000";
        }
        return Color.fromString(colorFieldValue);
    }

    /**
     * Retrieves the {@link Color color} of this {@link ColorField color field}.
     * If no color is set #000000 is returned.
     * 
     * @return the color of this color field as String
     * @since 1.1.0
     */
    public String getColorAsString() {
        return StringUtils.defaultString(getAttribute("value"));
    }

    /**
     * Resets this {@link ColorField field} to the default {@link Color color}
     * of #000000.
     * 
     * @return the same instance for fluent API use
     * @throws PageObjectIsDisabledException if the color field is disabled
     * @throws PageObjectIsInvisibleException if the color field is invisible
     * @since 1.1.0
     */
    public ColorField reset() {
        executeAction(new AbstractColorFieldCallback() {

            @Override
            public void executeAction(ColorField colorField) {
                getBrowser().executeJavaScript("arguments[0].value=''", getWebElement());
            }

            @Override
            protected void executeAfterAction(ColorField colorField, Color oldColor, Color newColor) {
                logger.debug(logMessage(CLEARED_TEXT), oldColor, newColor);
                fireEventAndMarkAsUsed(new ColorClearedEvent(colorField, oldColor, newColor));
            }

        });
        return this;
    }

    /**
     * Sets the given {@link Color color} by replacing whatever color is
     * currently set for the {@link ColorField color field}.
     * 
     * @param color(#rrggbb) the color to be set
     * @return the same instance for fluent API use
     * @throws PageObjectIsDisabledException if the color field is disabled
     * @throws PageObjectIsInvisibleException if the color field is invisible
     * @since 1.1.0
     */
    public ColorField setColor(final Color color) {
        executeAction(new AbstractColorFieldCallback() {
            @Override
            public void executeAction(ColorField colorField) {
                getBrowser().executeJavaScript("arguments[0].value='" + color.asHex() + "'", getWebElement());
            }

            @Override
            protected void executeAfterAction(ColorField colorField, Color oldColor, Color newColor) {
                logger.debug(logMessage(SET_COLOR), oldColor, newColor, color);
                fireEventAndMarkAsUsed(new ColorSetEvent(colorField, oldColor, newColor, color));
            }

        });
        return this;
    }

    @Override
    protected boolean isCorrectClassForWebElement(WebElement webElement) {

        String tagName = webElement.getTagName();
        String type = StringUtils.defaultString(webElement.getAttribute("type")).toLowerCase();

        boolean isCorrectTag = INPUT_TAG.equalsIgnoreCase(tagName);
        boolean isCorrectType = "color".equalsIgnoreCase(type);

        return isCorrectTag && isCorrectType;

    }

    /**
     * Specialized abstract {@link PageObjectCallback page object callback} used
     * to handle common behavior for all {@link ColorField color field} actions.
     * 
     * @since 1.1.0
     */
    protected static abstract class AbstractColorFieldCallback implements PageObjectCallback {

        @Override
        public final void execute(PageObject pageObject) {
            ColorField colorField = ( ColorField ) pageObject;
            Asserts.assertEnabledAndVisible(colorField);
            Color oldColor = colorField.getColor();
            executeAction(colorField);
            Color newColor = colorField.getColor();
            executeAfterAction(colorField, oldColor, newColor);
        }

        /**
         * Execute an action on a {@link ColorField color field}. This action
         * should not include things like firing events or logging output. Use
         * {@link #executeAfterAction(ColorField, String, String)} for that.
         * 
         * @param colorField the color field to execute the action on
         */
        protected abstract void executeAction(ColorField colorField);

        /**
         * Execute after action tasks like logging or firing events.
         * 
         * @param ColorField the color field
         * @param oldColor the color of the color field before the action was
         * executed
         * @param newColor the color of the color field after the action was
         * executed
         * 
         */
        protected abstract void executeAfterAction(ColorField colorField, Color oldColor, Color newColor);

    }
}
