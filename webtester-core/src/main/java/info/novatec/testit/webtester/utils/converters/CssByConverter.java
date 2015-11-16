package info.novatec.testit.webtester.utils.converters;

import org.openqa.selenium.By;

import info.novatec.testit.webtester.api.pageobjects.ByConverter;


/**
 * Using a CSS Selector description.
 *
 * @since 0.9.9
 */
public class CssByConverter implements ByConverter {

    @Override
    public By toBy(String value) {
        return By.cssSelector(value);
    }

    @Override
    public String getValueFormat() {
        return "CSS";
    }

}
