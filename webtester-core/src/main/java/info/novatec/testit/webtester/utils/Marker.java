package info.novatec.testit.webtester.utils;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.support.Color;

import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.api.enumerations.CSSProperties;
import info.novatec.testit.webtester.api.utils.CSSProperty;
import info.novatec.testit.webtester.api.utils.StyleChanger;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * This class is used to mark {@link PageObject page object} instances with the
 * colors defined in the page object's browser's {@link Configuration
 * configuration}.
 *
 * @since 0.9.6
 */
public final class Marker {

    private static StyleChanger styleChanger = new JavaScriptStyleChanger();

    /**
     * Marks the given {@link PageObject page object} as 'used' using the
     * configured colors from the page object's browser's {@link Configuration
     * configuration}.
     *
     * @param pageObject the page object to mark.
     * @since 0.9.6
     */
    public static void markAsUsed(PageObject pageObject) {
        Configuration configuration = pageObject.getBrowser().getConfiguration();
        if (configuration.markingsAreActivated()) {
            Color backgroundColor = configuration.getMarkingsColorUsedBackground();
            Color outlineColor = configuration.getMarkingsColorUsedOutline();
            markElement(pageObject, backgroundColor, outlineColor);
        }
    }

    /**
     * Marks the given {@link PageObject page object} as 'read' using the
     * configured colors from the page object's browser's {@link Configuration
     * configuration}.
     *
     * @param pageObject the page object to mark.
     * @since 1.2
     */
    public static void markAsRead(PageObject pageObject) {
        Configuration configuration = pageObject.getBrowser().getConfiguration();
        if (configuration.markingsAreActivated()) {
            Color backgroundColor = configuration.getMarkingsColorReadBackground();
            Color outlineColor = configuration.getMarkingsColorReadOutline();
            markElement(pageObject, backgroundColor, outlineColor);
        }
    }

    private static void markElement(PageObject pageObject, Color backgroundColor, Color outlineColor) {

        Map<CSSProperty, String> cssStyleAttributes = new HashMap<CSSProperty, String>();
        cssStyleAttributes.put(CSSProperties.OUTLINE_STYLE, "solid");
        cssStyleAttributes.put(CSSProperties.OUTLINE_WIDTH, "2px");
        cssStyleAttributes.put(CSSProperties.OUTLINE_COLOR, outlineColor.asHex());
        cssStyleAttributes.put(CSSProperties.BACKGROUND_COLOR, backgroundColor.asHex());

        styleChanger.changeStyleInformation(pageObject, cssStyleAttributes);

    }

    private Marker() {
    }

}
