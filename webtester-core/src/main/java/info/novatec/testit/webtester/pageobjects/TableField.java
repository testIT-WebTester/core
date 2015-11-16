package info.novatec.testit.webtester.pageobjects;

import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebElement;

import com.google.common.collect.Sets;

import info.novatec.testit.webtester.api.callbacks.PageObjectCallbackWithReturnValue;


/**
 * Represents the field of a HTML table. The following HTML elements are
 * supported:
 * <ul>
 * <li><b>tag:</b> th</li>
 * <li><b>tag:</b> td</li>
 * </ul>
 *
 * @since 0.9.0
 */
public class TableField extends PageObject {

    private static final Set<String> VALID_TAGS = Sets.newHashSet("th", "td");

    /**
     * Returns whether or not this {@link TableField field} is is a header field
     * (TH).
     *
     * @return true if this is a header field, otherwise false
     * @since 0.9.0
     */
    public boolean isHeaderField() {
        return executeAction(new PageObjectCallbackWithReturnValue<Boolean>() {

            @Override
            public Boolean execute(PageObject pageObject) {
                return "th".equalsIgnoreCase(getWebElement().getTagName());
            }

        });
    }

    @Override
    protected boolean isCorrectClassForWebElement(WebElement webElement) {
        String tagName = StringUtils.defaultString(webElement.getTagName());
        String lowerCasedTagName = tagName.toLowerCase();
        return VALID_TAGS.contains(lowerCasedTagName);
    }

}
