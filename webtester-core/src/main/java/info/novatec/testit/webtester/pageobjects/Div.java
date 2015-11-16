package info.novatec.testit.webtester.pageobjects;

import org.openqa.selenium.WebElement;


/**
 * Represents a div element. The following HTML elements are supported:
 * <ul>
 * <li><b>tag:</b> div</li>
 * </ul>
 *
 * @since 0.9.9
 */
public class Div extends PageObject {

    @Override
    protected boolean isCorrectClassForWebElement(WebElement webElement) {
        return "div".equalsIgnoreCase(webElement.getTagName());
    }

}
