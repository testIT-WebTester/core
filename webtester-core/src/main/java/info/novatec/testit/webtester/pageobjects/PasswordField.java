package info.novatec.testit.webtester.pageobjects;

import org.openqa.selenium.WebElement;


/**
 * Represents a HTML password field. The following HTML elements are supported:
 * <ul>
 * <li><b>tag:</b> input <b>type:</b> password</li>
 * </ul>
 *
 * @since 0.9.0
 */
public class PasswordField extends TextField {

    @Override
    public PasswordField clearText() {
        // Overridden for fluent API use
        super.clearText();
        return this;
    }

    @Override
    public PasswordField setText(String textToSet) {
        // Overridden for fluent API use
        super.setText(textToSet);
        return this;
    }

    @Override
    public PasswordField appendText(String textToAppend) {
        // Overridden for fluent API use
        super.appendText(textToAppend);
        return this;
    }

    @Override
    protected boolean isCorrectClassForWebElement(WebElement webElement) {

        String tagName = webElement.getTagName();
        String type = webElement.getAttribute("type");

        boolean isCorrectTag = "input".equalsIgnoreCase(tagName);
        boolean isCorrectType = "password".equalsIgnoreCase(type);

        return isCorrectTag && isCorrectType;

    }

}
