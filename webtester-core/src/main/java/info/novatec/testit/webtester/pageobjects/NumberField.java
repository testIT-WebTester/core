package info.novatec.testit.webtester.pageobjects;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebElement;

import info.novatec.testit.webtester.api.exceptions.PageObjectIsDisabledException;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsInvisibleException;
import info.novatec.testit.webtester.api.pageobjects.traits.HasValue;


/**
 * Represents a number field (HTML5). The following HTML elements are supported:
 * <ul>
 * <li><b>tag:</b> input <b>type:</b> number</li>
 * </ul>
 *
 * @since 0.9.3
 */
public class NumberField extends TextField implements HasValue<Long> {

    /**
     * Retrieves the value of this {@link NumberField number field}. If there is
     * no value set <code>null</code> is returned.
     *
     * @return the value of this number field
     * @throws NumberFormatException if value is not a numerical value
     * @since 0.9.7
     */
    @Override
    public Long getValue() {
        String value = getText();
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return Long.valueOf(value);
    }

    @Override
    public NumberField clearText() {
        // Overridden for fluent API use
        super.clearText();
        return this;
    }

    /**
     * Sets the given value by replacing whatever value is currently set for the
     * {@link NumberField number field}.
     *
     * @param valueToSet the text to set
     * @return the same instance for fluent API use
     * @throws PageObjectIsDisabledException if the number field is disabled
     * @throws PageObjectIsInvisibleException if the number field is invisible
     * @since 0.9.7
     */
    public NumberField setValue(Long valueToSet) {
        return setText(String.valueOf(valueToSet));
    }

    @Override
    public NumberField setText(String textToSet) {
        // Overridden for fluent API use
        super.setText(textToSet);
        return this;
    }

    @Override
    public NumberField appendText(String textToAppend) {
        // Overridden for fluent API use
        super.appendText(textToAppend);
        return this;
    }

    @Override
    protected boolean isCorrectClassForWebElement(WebElement webElement) {

        String tagName = webElement.getTagName();
        String type = webElement.getAttribute("type");

        boolean isCorrectTag = "input".equalsIgnoreCase(tagName);
        boolean isCorrectType = "number".equalsIgnoreCase(type);

        return isCorrectTag && isCorrectType;

    }

}
