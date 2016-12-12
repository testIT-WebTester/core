package integration.pageobjects;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.exceptions.WrongElementClassException;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.UrlField;
import integration.AbstractWebTesterIntegrationTest;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.PostConstruct;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UrlFieldIntegrationTest extends AbstractWebTesterIntegrationTest{
    UrlFieldTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(UrlFieldTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobjects/urlField.html";
    }

    /*  getting text    */

    @Test
    public final void testThatGettingTextWorksWithParentImplementation() {
        assertThat(page.withValue.getText(), is("www.test.de"));
    }

    /*  appending text  */

    @Test
    public final void testThatAppendingOfTextWorksWithParentImplementation() {
        UrlField element = page.withValue.appendText("/tester");
        assertThat(element.getText(), is("www.test.de/tester"));
    }

    /*  setting text    */

    @Test
    public final void testThatSettingOfTextWorksWithParentImplementation() {
        UrlField element = page.empty.setText("www.test.de/tester");
        assertThat(element.getText(), is("www.test.de/tester"));
    }

    @Test
    public final void testThatSettingTextWithValueWorksWithParentImplementation() {
        UrlField element = page.withValue.setText("still www.test.de");
        assertThat(element.getText(), is("still www.test.de"));
    }


    /*  clearing text   */

    @Test
    public final void testThatClearingOfTextWorksWithParentImplementation() {
        UrlField element = page.withValue.clearText();
        assertThat(element.getText(), is(""));
    }

    /* validation of mapping */

    @Test
    public final void testValidationOfMapping_urlField() {
        assertPageObjectCanBeInitialized(page.empty);
    }

    @Test(expected = WrongElementClassException.class)
    public final void testValidationOfMapping_noUrlField() {
        assertPageObjectCanBeInitialized(page.notAnUrlField);
    }

    /*  utilities   */

    public static class UrlFieldTestPage extends PageObject {
        @IdentifyUsing("empty")
        UrlField empty;

        @IdentifyUsing("withValue")
        UrlField withValue;

        @IdentifyUsing("notAnUrlField")
        UrlField notAnUrlField;

        @PostConstruct
        void checkStartingConditions() {
            assertThat(empty.isVisible(), is(true));
            assertThat(withValue.isVisible(), is(true));

            assertThat(empty.getText(), is(StringUtils.EMPTY));
            assertThat(withValue.getText(), is("www.test.de"));
        }
    }
}
