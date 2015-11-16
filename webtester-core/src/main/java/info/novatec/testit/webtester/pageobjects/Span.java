package info.novatec.testit.webtester.pageobjects;

import org.openqa.selenium.WebElement;


/**
 * Represents a span element. The following HTML elements are supported:
 * <ul>
 * <li><b>tag:</b> span</li>
 * </ul>
 *
 * @since 0.9.9
 */
public class Span extends PageObject {

    @Override
    protected boolean isCorrectClassForWebElement(WebElement webElement) {
        return "span".equalsIgnoreCase(webElement.getTagName());
    }

}
