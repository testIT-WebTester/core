package integration.pageobjects;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.exceptions.WrongElementClassException;
import info.novatec.testit.webtester.pageobjects.EmailField;
import info.novatec.testit.webtester.pageobjects.PageObject;
import integration.AbstractWebTesterIntegrationTest;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.PostConstruct;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EmailFieldIntegrationTest extends AbstractWebTesterIntegrationTest{
    EmailFieldTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(EmailFieldTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobjects/emailField.html";
    }

    /*  getting text    */

    @Test
    public final void testThatGettingTextWorksWithParentImplementation() {
        assertThat(page.withValue.getText(), is("value@test"));
    }

    /*  appending text  */

    @Test
    public final void testThatAppendingOfTextWorksWithParentImplementation() {
        EmailField element = page.withValue.appendText(".de");
        assertThat(element.getText(), is("value@test.de"));
    }

    /*  setting text    */

    @Test
    public final void testThatSettingOfTextWorksWithParentImplementation() {
        EmailField element = page.empty.setText("value@test.de");
        assertThat(element.getText(), is("value@test.de"));
    }

    @Test
    public final void testThatSettingTextWithValueWorksWithParentImplementation() {
        EmailField element = page.withValue.setText("still value@test");
        assertThat(element.getText(), is("still value@test"));
    }


    /*  clearing text   */

    @Test
    public final void testThatClearingOfTextWorksWithParentImplementation() {
        EmailField element = page.withValue.clearText();
        assertThat(element.getText(), is(""));
    }

    /* validation of mapping */

    @Test()
    public final void testValidationOfMapping_EmailField() {
        assertPageObjectCanBeInitialized(page.empty);
    }

    @Test(expected = WrongElementClassException.class)
    public final void testValidationOfMapping_noEmailField() {
        assertPageObjectCanBeInitialized(page.noEmailField);
    }

    /*  utilities   */

    public static class EmailFieldTestPage extends PageObject {
        @IdentifyUsing("empty")
        EmailField empty;

        @IdentifyUsing("withValue")
        EmailField withValue;

        @IdentifyUsing("nonEmailField")
        EmailField noEmailField;

        @PostConstruct
        void checkStartingConditions(){
            assertThat(empty.isVisible(), is(true));
            assertThat(withValue.isVisible(), is(true));

            assertThat(empty.getText(), is(StringUtils.EMPTY));
            assertThat(withValue.getText(), is("value@test"));
        }
    }
}
