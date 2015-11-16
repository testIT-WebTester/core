package info.novatec.testit.webtester.utils.converters;

import org.openqa.selenium.By;

import info.novatec.testit.webtester.api.pageobjects.ByConverter;


/**
 * Using a partial text of a link.
 *
 * @since 0.9.9
 */
public class PartialLinkTextByConverter implements ByConverter {

    @Override
    public By toBy(String value) {
        return By.partialLinkText(value);
    }

    @Override
    public String getValueFormat() {
        return "Partial Link Text";
    }

}
