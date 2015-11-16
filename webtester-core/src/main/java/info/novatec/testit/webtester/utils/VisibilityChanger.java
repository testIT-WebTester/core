package info.novatec.testit.webtester.utils;

import java.util.HashMap;
import java.util.Map;

import info.novatec.testit.webtester.api.enumerations.CSSProperties;
import info.novatec.testit.webtester.api.utils.CSSProperty;
import info.novatec.testit.webtester.api.utils.StyleChanger;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * This class is used to change the visibility of {@link PageObject page
 * objects}.
 *
 * @since 0.9.6
 */
public final class VisibilityChanger {

    private static StyleChanger styleChanger = new JavaScriptStyleChanger();

    /**
     * Makes the given {@linkplain PageObject page object} visible.
     *
     * @param pageObject the page object
     * @since 0.9.6
     */
    public static void makeVisible(PageObject pageObject) {
        changeVisibility(pageObject, "inline", "visible");
    }

    /**
     * Makes the given {@linkplain PageObject page object} invisible.
     *
     * @param pageObject the page object
     * @since 0.9.6
     */
    public static void makeInvisible(PageObject pageObject) {
        changeVisibility(pageObject, "none", "hidden");
    }

    private static void changeVisibility(PageObject pageObject, String displayValue, String visibilityValue) {
        Map<CSSProperty, String> cssStyleAttributes = new HashMap<CSSProperty, String>();
        cssStyleAttributes.put(CSSProperties.DISPLAY, displayValue);
        cssStyleAttributes.put(CSSProperties.VISIBILITY, visibilityValue);
        styleChanger.changeStyleInformation(pageObject, cssStyleAttributes);
    }

    private VisibilityChanger() {
        // utility class constructor
    }

}
