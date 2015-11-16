package info.novatec.testit.webtester.api.pageobjects;

import org.openqa.selenium.By;


/**
 * Classes implementing this interface provide the means to convert an input
 * string into a Selenium {@link By} instance to be used to identify elements on
 * a page.
 *
 * @since 0.9.9
 */
public interface ByConverter {

    /**
     * Converts the given value into a Selenium {@link By}.
     *
     * @param value the value to convert
     * @return the By
     * @since 0.9.9
     */
    By toBy(String value);

    /**
     * @return a string representing what kind of value format is used by the
     * converter
     * @since 0.9.9
     */
    String getValueFormat();

}
