package info.novatec.testit.webtester.api.pageobjects;

import org.openqa.selenium.By;


/**
 * Wrapper class for storing page object identification data. With this data
 * specific elements in the pages DOM can be identified.
 *
 * @since 0.9.9
 */
public class Identification {

    private static final String TO_STRING_MSG = "%s using '%s'";

    private final By seleniumBy;
    private final String toStringMessage;

    /**
     * Create an {@link Identification identification} using the given Selenium
     * {@link By}. This constructor is intended to be used in cases where an
     * existing {@link By} has to be transformed into an identification.
     *
     * @param seleniumBy the {@link By} to store
     * @since 0.9.9
     */
    public Identification(By seleniumBy) {
        this.seleniumBy = seleniumBy;
        this.toStringMessage = seleniumBy.toString();
    }

    /**
     * Create an {@link Identification identification} using the given
     * {@link ByConverter converter} and value.
     *
     * @param converter the converter to use when creating a Selenium {@link By}
     * from the given value
     * @param value the value to be used by the converter to create a Selenium
     * {@link By}
     * @since 0.9.9
     */
    public Identification(ByConverter converter, String value) {
        this.seleniumBy = converter.toBy(value);
        this.toStringMessage = String.format(TO_STRING_MSG, converter.getValueFormat(), value);
    }

    /**
     * Creates a Selenium {@link By} from the data of this {@link Identification
     * identification}.
     *
     * @return the created {@link By}
     * @since 0.9.9
     */
    public By getSeleniumBy() {
        return seleniumBy;
    }

    @Override
    public String toString() {
        return toStringMessage;
    }

}
