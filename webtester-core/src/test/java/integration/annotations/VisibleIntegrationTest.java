package integration.annotations;

import java.util.List;

import org.junit.Test;

import integration.AbstractWebTesterIntegrationTest;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.annotations.Visible;
import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.enumerations.Method;
import info.novatec.testit.webtester.api.exceptions.PageObjectFactoryException.VisiblePageObjectFieldException;
import info.novatec.testit.webtester.api.exceptions.PageObjectFactoryException.VisiblePageObjectListFieldException;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.TextField;


public class VisibleIntegrationTest extends AbstractWebTesterIntegrationTest {

    @Override
    protected String getHTMLFilePath() {
        return "html/annotations/visible.html";
    }

    @Test
    public void testThatVisibleAnnotationIsUsedToCheckVisibilityOfPageObject() {
        getBrowser().create(SingleVisiblePage.class);
        // nothing to assert because initialization was successful
    }

    @Test
    public void testThatVisibleAnnotationIsUsedToCheckVisibilityOfPageObjectList() {
        getBrowser().create(MultiVisiblePage.class);
        // nothing to assert because initialization was successful
    }

    @Test(expected = VisiblePageObjectFieldException.class)
    public void testThatVisibleAnnotationOnNonExistingPageObjectLeadsToException() {
        Browser browser = getBrowser();
        browser.getConfiguration().setWaitTimeout(1);
        browser.open(getFormattedTestResourcePath("html/empty.html"), SingleVisiblePage.class);
    }

    @Test(expected = VisiblePageObjectListFieldException.class)
    public void testThatVisibleAnnotationOnPageObjectListLeadsToExceptionIfThereAreNotEnoughVisibleObjects() {
        getBrowser().create(MultiVisiblePageWrongCount.class);
    }

    public static class SingleVisiblePage extends PageObject {

        @Visible
        @IdentifyUsing("textfield")
        TextField textfield;

    }

    public static class MultiVisiblePage extends PageObject {

        @Visible(2)
        @IdentifyUsing(method = Method.ID_STARTS_WITH, value = "multi:textfield")
        List<TextField> multipleTextFields;

    }

    public static class MultiVisiblePageWrongCount extends PageObject {

        @Visible(3)
        @IdentifyUsing(method = Method.ID_STARTS_WITH, value = "multi:textfield")
        List<TextField> multipleTextFields;

    }

}
