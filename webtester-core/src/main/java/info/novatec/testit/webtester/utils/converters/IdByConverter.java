package info.novatec.testit.webtester.utils.converters;

import org.openqa.selenium.By;

import info.novatec.testit.webtester.api.pageobjects.ByConverter;


/**
 * Using the ID Attribute (should be unique on the whole page).
 *
 * @since 0.9.9
 */
public class IdByConverter implements ByConverter {

    @Override
    public By toBy(String value) {
        return By.id(value);
    }

    @Override
    public String getValueFormat() {
        return "ID";
    }

}
