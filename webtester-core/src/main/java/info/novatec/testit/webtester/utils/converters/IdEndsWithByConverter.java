package info.novatec.testit.webtester.utils.converters;

import static java.lang.String.format;

import org.openqa.selenium.By;

import info.novatec.testit.webtester.api.pageobjects.ByConverter;


/**
 * Using part of an ID to match as the suffix of the ID Attribute.
 *
 * @since 0.9.9
 */
public class IdEndsWithByConverter implements ByConverter {

    private static final String ID_ENDS_WITH_PATTERN = "[id$='%s']";

    @Override
    public By toBy(String value) {
        return By.cssSelector(format(ID_ENDS_WITH_PATTERN, value));
    }

    @Override
    public String getValueFormat() {
        return "ID Ends With";
    }

}
