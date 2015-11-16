package integration.annotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;

import integration.AbstractWebTesterIntegrationTest;

import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.TextField;


public class FindBySupportIntegrationTest extends AbstractWebTesterIntegrationTest {

    FindBySupportTestPage page;

    @Override
    protected String getHTMLFilePath() {
        return "html/annotations/findby_support.html";
    }

    @Before
    public void initPage() {
        page = getBrowser().create(FindBySupportTestPage.class);
    }

    @Test
    public void findByWithImplicitIdIsNotImplemented() {
        // this Selenium feature is not yet implemented in WebTester
        assertThatPageObjectWasNotResolved(page.textfield);
    }

    @Test
    public void findByWithIdParameter() {
        assertThatPageObjectWasResolved(page.viaId);
    }

    @Test
    public void findByWithCssParameter() {
        assertThatPageObjectWasResolved(page.viaCss);
    }

    @Test
    public void findByWithHowAndUsing() {
        assertThatPageObjectWasResolved(page.viaHowAndUsing);
    }

    @Test
    public void findBysWithMultipleFindBy() {
        assertThatPageObjectWasResolved(page.viaFindBys);
    }

    @Test
    public void findByWithListOfPageObjects() {
        assertThat(page.multipleTextfields, hasSize(3));
    }

    /* details */

    void assertThatPageObjectWasNotResolved(PageObject pageObject) {
        assertThat(pageObject.isVisible(), is(false));
    }

    void assertThatPageObjectWasResolved(PageObject pageObject) {
        assertThat(pageObject.isVisible(), is(true));
    }

    public static class FindBySupportTestPage extends PageObject {

        @FindBy
        TextField textfield;

        @FindBy(id = "textfield")
        TextField viaId;

        @FindBy(css = "#textfield")
        TextField viaCss;

        @FindBy(how = How.ID_OR_NAME, using = "textfield")
        TextField viaHowAndUsing;

        @FindBys({ @FindBy(id = "row"), @FindBy(tagName = "input") })
        TextField viaFindBys;

        @FindBy(className = "multiple")
        List<TextField> multipleTextfields;

    }

}
