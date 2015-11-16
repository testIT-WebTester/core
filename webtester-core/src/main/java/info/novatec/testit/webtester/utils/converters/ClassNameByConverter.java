package info.novatec.testit.webtester.utils.converters;

import org.openqa.selenium.By;

import info.novatec.testit.webtester.api.pageobjects.ByConverter;


/**
 * Using the name of the element's class. Does not support multiple class names!
 * Use {@link CssByConverter} in case multiple class names are needed.
 *
 * @since 0.9.9
 */
public class ClassNameByConverter implements ByConverter {

    @Override
    public By toBy(String value) {
        return By.className(value);
    }

    @Override
    public String getValueFormat() {
        return "Class Name";
    }

}
