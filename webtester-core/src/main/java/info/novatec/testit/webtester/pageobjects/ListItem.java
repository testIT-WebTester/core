package info.novatec.testit.webtester.pageobjects;

import org.openqa.selenium.WebElement;


/**
 * Represents a list item. The following HTML elements are supported:
 * <ul>
 * <li><b>tag:</b> li</li>
 * </ul>
 *
 * @since 0.9.0
 */
public class ListItem extends PageObject {

    @Override
    protected boolean isCorrectClassForWebElement(WebElement webElement) {
        return "li".equalsIgnoreCase(webElement.getTagName());
    }

}
