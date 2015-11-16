package integration.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

import integration.AbstractWebTesterIntegrationTest;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.exceptions.WrongElementClassException;
import info.novatec.testit.webtester.pageobjects.Button;
import info.novatec.testit.webtester.pageobjects.GenericElement;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.TextField;


public class GenericElementIntegrationTest extends AbstractWebTesterIntegrationTest {

    GenericElementTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(GenericElementTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobjects/genericElement.html";
    }

    /* casting to other page object class */

    @Test
    public final void testThatGenericElementCanBeCastToOtherPageObject() {
        Button asButton = page.button.as(Button.class);
        assertThat(asButton.getLabel(), is("Button"));
    }

    @Test(expected = WrongElementClassException.class)
    public final void testThatCastGenericElementHasToConformToTheRestrictionsSetByTheNewType() {
        page.button.as(TextField.class).getWebElement();
    }

    /* utilities */

    public static class GenericElementTestPage extends PageObject {

        @IdentifyUsing("button")
        GenericElement button;

    }

}
