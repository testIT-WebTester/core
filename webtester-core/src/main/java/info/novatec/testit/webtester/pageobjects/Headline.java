package info.novatec.testit.webtester.pageobjects;

import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebElement;

import com.google.common.collect.Sets;


/**
 * Represents a headline. The following HTML elements are supported:
 * <ul>
 * <li><b>tag:</b> h1</li>
 * <li><b>tag:</b> h2</li>
 * <li><b>tag:</b> h3</li>
 * <li><b>tag:</b> h4</li>
 * <li><b>tag:</b> h5</li>
 * <li><b>tag:</b> h6</li>
 * </ul>
 *
 * @since 0.9.0
 */
public class Headline extends PageObject {

    private static final Set<String> VALID_TAGS = Sets.newHashSet("h1", "h2", "h3", "h4", "h5", "h6");

    @Override
    protected boolean isCorrectClassForWebElement(WebElement webElement) {
        String tagName = webElement.getTagName();
        String lowerCaseTagName = StringUtils.defaultString(tagName).toLowerCase();
        return VALID_TAGS.contains(lowerCaseTagName);
    }

}
