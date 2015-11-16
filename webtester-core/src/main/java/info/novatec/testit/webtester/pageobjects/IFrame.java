package info.novatec.testit.webtester.pageobjects;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebElement;

import info.novatec.testit.webtester.api.pageobjects.traits.HasSourcePath;


/**
 * Represents an iframe. The following HTML elements are supported:
 * <ul>
 * <li><b>tag:</b> iframe</li>
 * </ul>
 *
 * @since 0.9.0
 */
public class IFrame extends PageObject implements HasSourcePath {

    /**
     * Returns the complete path to the source file e.g. "../frames/menu.html".
     * If no source path is set an empty string is returned.
     *
     * @return the source path
     * @since 0.9.0
     */
    @Override
    public String getSourcePath() {
        return StringUtils.defaultString(getAttribute("src"));
    }

    @Override
    protected boolean isCorrectClassForWebElement(WebElement webElement) {
        return "iframe".equalsIgnoreCase(webElement.getTagName());
    }

}
