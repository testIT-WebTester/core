package info.novatec.testit.webtester.pageobjects;

import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebElement;

import com.google.common.collect.Sets;

import info.novatec.testit.webtester.api.exceptions.PageObjectIsDisabledException;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsInvisibleException;
import info.novatec.testit.webtester.api.pageobjects.traits.HasLabel;
import info.novatec.testit.webtester.api.pageobjects.traits.HasValue;
import info.novatec.testit.webtester.utils.Asserts;


/**
 * Represents a button. The following HTML elements are supported:
 * <ul>
 * <li><b>tag:</b> button</li>
 * <li><b>tag:</b> input <b>type:</b> button</li>
 * <li><b>tag:</b> input <b>type:</b> reset</li>
 * <li><b>tag:</b> input <b>type:</b> submit</li>
 * </ul>
 *
 * @since 0.9.0
 */
public class Button extends PageObject implements HasValue<String>, HasLabel {

    private static final String BUTTON_TAG = "button";
    private static final String INPUT_TAG = "input";
    private static final Set<String> VALID_TYPES = Sets.newHashSet("reset", "submit", "button");

    /**
     * Retrieves the value of the {@link Button button}. If no value is set an
     * empty string is returned.
     *
     * @return the value of the button
     * @since 0.9.7
     */
    @Override
    public String getValue() {
        return StringUtils.defaultString(getAttribute("value"));
    }

    /**
     * Retrieves the label of the {@link Button button}. If no label is set an
     * empty string is returned.
     * <p>
     * What property of the button is returned as it's label depends on the kind
     * of button this page object represents. Input buttons (tag: "input") use
     * their value property as the displayed label while buttons with the tag
     * "button" use the text between the opening an closing tags.
     * <p>
     * This method is preferred over {@link #getVisibleText()} because it makes
     * that distinction for you.
     *
     * @return the label of the button
     * @since 0.9.6
     */
    @Override
    public String getLabel() {
        // For buttons created with <input>, the value is shown as the label
        if (INPUT_TAG.equalsIgnoreCase(getTagName())) {
            return getValue();
        }
        return StringUtils.defaultString(getVisibleText());
    }

    /**
     * Executes a click on this {@linkplain Button button}. Will throw an
     * exception if the button is invisible or disabled!
     *
     * @return the same instance for fluent API
     * @throws PageObjectIsDisabledException if the button is disabled
     * @throws PageObjectIsInvisibleException if the button is invisible
     * @since 0.9.6
     */
    @Override
    public Button click() {
        Asserts.assertEnabledAndVisible(this);
        super.click();
        return this;
    }

    @Override
    protected boolean isCorrectClassForWebElement(WebElement webElement) {

        String tagName = webElement.getTagName();
        String type = StringUtils.defaultString(webElement.getAttribute("type")).toLowerCase();

        if (INPUT_TAG.equalsIgnoreCase(tagName)) {
            return VALID_TYPES.contains(type);
        }

        return BUTTON_TAG.equalsIgnoreCase(tagName);
    }

}
