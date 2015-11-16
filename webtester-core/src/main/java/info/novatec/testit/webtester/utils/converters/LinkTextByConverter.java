package info.novatec.testit.webtester.utils.converters;

import org.openqa.selenium.By;

import info.novatec.testit.webtester.api.pageobjects.ByConverter;


/**
 * Using the text of a link.
 *
 * @since 0.9.9
 */
public class LinkTextByConverter implements ByConverter {

    @Override
    public By toBy(String value) {
        return By.linkText(value);
    }

    @Override
    public String getValueFormat() {
        return "Link Text";
    }

}
