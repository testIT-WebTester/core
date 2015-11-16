package info.novatec.testit.webtester.utils.converters;

import org.openqa.selenium.By;

import info.novatec.testit.webtester.api.pageobjects.ByConverter;


/**
 * Using a XPath description (http://www.w3schools.com/xpath/).
 *
 * @since 0.9.9
 */
public class XpathByConverter implements ByConverter {

    @Override
    public By toBy(String value) {
        return By.xpath(value);
    }

    @Override
    public String getValueFormat() {
        return "XPath";
    }

}
