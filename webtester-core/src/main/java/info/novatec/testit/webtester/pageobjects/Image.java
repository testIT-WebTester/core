package info.novatec.testit.webtester.pageobjects;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebElement;

import info.novatec.testit.webtester.api.callbacks.PageObjectCallbackWithReturnValue;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsDisabledException;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsInvisibleException;
import info.novatec.testit.webtester.api.pageobjects.traits.HasFileName;
import info.novatec.testit.webtester.api.pageobjects.traits.HasSourcePath;
import info.novatec.testit.webtester.utils.Asserts;


/**
 * Represents an image. The following HTML elements are supported:
 * <ul>
 * <li><b>tag:</b> img</li>
 * </ul>
 *
 * @since 0.9.0
 */
public class Image extends PageObject implements HasSourcePath, HasFileName {

    /**
     * Returns the complete path to the source file e.g. "../resource/test.png".
     * If no source path is set an empty string is returned.
     *
     * @return the source path
     * @since 0.9.0
     */
    @Override
    public String getSourcePath() {
        return StringUtils.defaultString(getAttribute("src"));
    }

    /**
     * Returns the name of the source file without its path. If no source file
     * is set an empty string is returned.
     *
     * @return the name of source file e.g. "test.png"
     * @since 0.9.0
     */
    @Override
    public String getFileName() {
        /* done in two distinct steps in order to allow for separate exception
         * traceability */
        final String sourcePath = getSourcePath();
        return executeAction(new PageObjectCallbackWithReturnValue<String>() {

            @Override
            public String execute(PageObject pageObject) {
                if (StringUtils.isNotBlank(sourcePath)) {
                    return new File(sourcePath).getName();
                }
                return StringUtils.EMPTY;
            }

        });
    }

    /**
     * Executes a click on this {@linkplain Image image}. Will throw an
     * exception if the image is invisible or disabled!
     *
     * @return the same instance for fluent API
     * @throws PageObjectIsDisabledException if the image is disabled
     * @throws PageObjectIsInvisibleException if the image is invisible
     * @since 0.9.6
     */
    @Override
    public Image click() {
        Asserts.assertEnabledAndVisible(this);
        super.click();
        return this;
    }

    @Override
    protected boolean isCorrectClassForWebElement(WebElement webElement) {
        return "img".equalsIgnoreCase(webElement.getTagName());
    }

}
