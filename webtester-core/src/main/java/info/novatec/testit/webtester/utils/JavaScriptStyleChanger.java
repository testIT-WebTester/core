package info.novatec.testit.webtester.utils;

import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.novatec.testit.webtester.api.utils.CSSProperty;
import info.novatec.testit.webtester.api.utils.StyleChanger;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Implements {@link StyleChanger} using JavaScript to change the style of the
 * {@link PageObject page objects}.
 *
 * @see StyleChanger
 * @see PageObject
 * @since 0.9.7
 */
public class JavaScriptStyleChanger implements StyleChanger {

    private static final Logger logger = LoggerFactory.getLogger(JavaScriptStyleChanger.class);

    @Override
    public boolean changeStyleInformation(PageObject pageObject, CSSProperty property, String value) {
        return changeStyleWithScript(scriptCommand(property, value), pageObject);
    }

    @Override
    public boolean changeStyleInformation(PageObject pageObject, Map<? extends CSSProperty, String> cssStyleProperties) {
        return changeStyleWithScript(buildScriptCommands(cssStyleProperties), pageObject);
    }

    private boolean changeStyleWithScript(String javaScript, PageObject pageObject) {
        try {
            pageObject.getBrowser().executeJavaScript(javaScript, pageObject);
            return true;
        } catch (WebDriverException e) {
            logException(e);
        }
        return false;
    }

    private String buildScriptCommands(Map<? extends CSSProperty, String> cssStyleProperties) {
        StringBuilder javaScript = new StringBuilder();
        for (Entry<? extends CSSProperty, String> entry : cssStyleProperties.entrySet()) {
            javaScript.append(scriptCommand(entry.getKey(), entry.getValue()));
        }
        return javaScript.toString();
    }

    private String scriptCommand(CSSProperty property, String value) {
        return String.format("arguments[0].style.%s='%s';", property.getName(), value);
    }

    private void logException(WebDriverException e) {
        logger.warn("Exception while changing the style information of an page object: {}", e.getMessage());
        logger.debug("Stack trace for previous warning:", e);
    }

}
