package info.novatec.testit.webtester.pageobjects;

import org.openqa.selenium.WebElement;


/**
 * Represents a form. The following HTML elements are supported:
 * <ul>
 * <li><b>tag:</b> form</li>
 * </ul>
 *
 * @since 0.9.9
 */
public class Form extends PageObject {

    @Override
    protected boolean isCorrectClassForWebElement(WebElement webElement) {
        return "form".equalsIgnoreCase(webElement.getTagName());
    }

}
