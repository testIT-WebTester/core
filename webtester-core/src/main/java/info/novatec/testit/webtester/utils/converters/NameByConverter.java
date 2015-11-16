package info.novatec.testit.webtester.utils.converters;

import org.openqa.selenium.By;

import info.novatec.testit.webtester.api.pageobjects.ByConverter;


/**
 * Using the name of the element.
 *
 * @since 0.9.9
 */
public class NameByConverter implements ByConverter {

    @Override
    public By toBy(String value) {
        return By.name(value);
    }

    @Override
    public String getValueFormat() {
        return "Name";
    }

}
