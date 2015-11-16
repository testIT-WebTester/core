package info.novatec.testit.webtester.pageobjects;

import org.openqa.selenium.WebElement;

import info.novatec.testit.webtester.api.exceptions.PageObjectIsDisabledException;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsInvisibleException;
import info.novatec.testit.webtester.utils.Asserts;


/**
 * Represents a link. The following HTML elements are supported:
 * <ul>
 * <li><b>tag:</b> a</li>
 * </ul>
 *
 * @since 0.9.0
 */
public class Link extends PageObject {

    /**
     * Executes a click on this {@linkplain Link link}. Will throw an exception
     * if the link is invisible or disabled!
     *
     * @return the same instance for fluent API
     * @throws PageObjectIsDisabledException if the link is disabled
     * @throws PageObjectIsInvisibleException if the link is invisible
     * @since 0.9.6
     */
    @Override
    public Link click() {
        Asserts.assertEnabledAndVisible(this);
        super.click();
        return this;
    }

    @Override
    protected boolean isCorrectClassForWebElement(WebElement webElement) {
        return "a".equalsIgnoreCase(webElement.getTagName());
    }

}
