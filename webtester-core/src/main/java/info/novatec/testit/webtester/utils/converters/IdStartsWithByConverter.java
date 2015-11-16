package info.novatec.testit.webtester.utils.converters;

import static java.lang.String.format;

import org.openqa.selenium.By;

import info.novatec.testit.webtester.api.pageobjects.ByConverter;


/**
 * Using part of an ID to match as the prefix of the ID Attribute.
 *
 * @since 0.9.9
 */
public class IdStartsWithByConverter implements ByConverter {

    private static final String ID_STARTS_WITH_PATTERN = "[id^='%s']";

    @Override
    public By toBy(String value) {
        return By.cssSelector(format(ID_STARTS_WITH_PATTERN, value));
    }

    @Override
    public String getValueFormat() {
        return "ID Starts With";
    }

}
