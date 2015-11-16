package info.novatec.testit.webtester.api.utils;

import java.util.Map;

import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Implementations of this interface are used to change the style information of
 * {@link PageObject page objects}.
 *
 * @since 0.9.7 changed from utility class to service with interface
 */
public interface StyleChanger {

    /**
     * Change the CSS style information of the given {@link PageObject page
     * object} using the given {@link CSSProperty property} and value.
     * <p>
     * Since this is regarded as an optional operation, exceptions which occur
     * while changing the style will be logged but not delegated upstream. You
     * can check the return value if this method's success is important.
     *
     * @param pageObject the page object
     * @param property the CSS property to change
     * @param value the value to change it to
     * @return true if the style was changed, false otherwise
     * @since 0.9.7
     */
    boolean changeStyleInformation(PageObject pageObject, CSSProperty property, String value);

    /**
     * Change the CSS style information of the given {@link PageObject page
     * object} using the given {@link CSSProperty property} to value map.
     * <p>
     * Since this is regarded as an optional operation, exceptions which occur
     * while changing the style will be logged but not delegated upstream. You
     * can check the return value if this method's success is important.
     *
     * @param pageObject the {@link PageObject} the CSS style should be changed
     * for.
     * @param cssStyleProperties a map of {@link CSSProperty} to value entries
     * describing which properties to change and which values to use.
     * @return true if the style was changed, false otherwise
     * @since 0.9.6
     */
    boolean changeStyleInformation(PageObject pageObject, Map<? extends CSSProperty, String> cssStyleProperties);

}
