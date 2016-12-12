package integration.pageobjects;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.exceptions.WrongElementClassException;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.TelephoneField;
import integration.AbstractWebTesterIntegrationTest;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.PostConstruct;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class TelephoneFieldIntegrationTest extends AbstractWebTesterIntegrationTest{
    TelephoneFieldTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(TelephoneFieldTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobjects/telephoneField.html";
    }

    /*  getting text    */

    @Test
    public final void testThatGettingTextWorksWithParentImplementation() {
        assertThat(page.withValue.getText(), is("0123456789"));
    }

    /*  appending text  */

    @Test
    public final void testThatAppendingOfTextWorksWithParentImplementation() {
        TelephoneField element = page.withValue.appendText("000");
        assertThat(element.getText(), is("0123456789000"));
    }

    /*  setting text    */

    @Test
    public final void testThatSettingOfTextWorksWithParentImplementation() {
        TelephoneField element = page.empty.setText("0123456789");
        assertThat(element.getText(), is("0123456789"));
    }

    @Test
    public final void testThatSettingTextWithValueWorksWithParentImplementation() {
        TelephoneField element = page.withValue.setText("still 0123456789");
        assertThat(element.getText(), is("still 0123456789"));
    }


    /*  clearing text   */

    @Test
    public final void testThatClearingOfTextWorksWithParentImplementation() {
        TelephoneField element = page.withValue.clearText();
        assertThat(element.getText(), is(""));
    }

    /* validation of mapping */

    @Test
    public final void testValidationOfMapping_telephoneField() {
        assertPageObjectCanBeInitialized(page.empty);
    }

    @Test(expected = WrongElementClassException.class)
    public final void testValidationOfMapping_noTelephoneField() {
        assertPageObjectCanBeInitialized(page.notATelephoneField);
    }

    /*  utilities   */

    public static class TelephoneFieldTestPage extends PageObject {
        @IdentifyUsing("empty")
        TelephoneField empty;

        @IdentifyUsing("withValue")
        TelephoneField withValue;

        @IdentifyUsing("notATelephoneField")
        TelephoneField notATelephoneField;

        @PostConstruct
        void checkStartingConditions() {
            assertThat(empty.isVisible(), is(true));
            assertThat(withValue.isVisible(), is(true));

            assertThat(empty.getText(), is(StringUtils.EMPTY));
            assertThat(withValue.getText(), is("0123456789"));
        }
    }
}
