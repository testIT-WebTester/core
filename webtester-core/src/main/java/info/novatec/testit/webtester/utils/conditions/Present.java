package info.novatec.testit.webtester.utils.conditions;

import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Predicate;

import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Predicate to be used in order to check if a {@link PageObject page object} is
 * present. This is determined by checking if the page object's web element can
 * be found.
 * <p>
 * <b>Note:</b> This will reset the page object's cache in order to guarantee
 * the presence of the web element!
 *
 * @since 0.9.9
 */
public class Present implements Predicate<PageObject> {

    private static final Logger logger = LoggerFactory.getLogger(Present.class);

    @Override
    public boolean apply(PageObject pageObject) {
        try {
            pageObject.invalidate();
            pageObject.getWebElement();
            return true;
        } catch (WebDriverException e) {
            logger.trace("could not find web element: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public String toString() {
        return "present";
    }

}
