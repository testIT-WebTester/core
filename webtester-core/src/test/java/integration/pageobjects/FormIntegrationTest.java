package integration.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Before;
import org.junit.Test;

import integration.AbstractWebTesterIntegrationTest;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.exceptions.WrongElementClassException;
import info.novatec.testit.webtester.pageobjects.Form;
import info.novatec.testit.webtester.pageobjects.PageObject;



public class FormIntegrationTest extends AbstractWebTesterIntegrationTest{

    FormTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(FormTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobjects/form.html";
    }

    /* submit */

    @Test
    public final void testThatSubmitDelegatesToTargetPage() {
        page.form.submit();
        assertThat(getBrowser().getPageTitle().equals("Target Page"), is(true));
    }

    @Test(expected = WrongElementClassException.class)
    public final void testThatSubmitFailsIfNoFormIsGiven() {
        page.notAForm.submit();
    }

    /* validation of mapping */

    @Test
    public final void testValidationOfMapping_form() {
        assertPageObjectCanBeInitialized(page.form);
    }

    @Test(expected = WrongElementClassException.class)
    public final void testValidationOfMapping_noForm() {
        assertPageObjectCanBeInitialized(page.notAForm);
    }

    /* utilities */
    
    public static class FormTestPage extends PageObject {

        @IdentifyUsing("form")
        Form form;

        @IdentifyUsing("notAForm")
        Form notAForm;

    }
}
