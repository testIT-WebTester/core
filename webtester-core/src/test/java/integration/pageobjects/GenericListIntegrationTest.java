package integration.pageobjects;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.exceptions.WrongElementClassException;
import info.novatec.testit.webtester.pageobjects.GenericList;
import info.novatec.testit.webtester.pageobjects.List;
import info.novatec.testit.webtester.pageobjects.PageObject;
import integration.AbstractWebTesterIntegrationTest;
import org.junit.Before;
import org.junit.Test;

/**
 * These tests check aspects of the {@link List list} page object which can only
 * be verified by using them on a live web-site with a real {@link Browser
 * browser}. Some of them might also check if we are using Selenium features
 * correctly.
 */
public class GenericListIntegrationTest extends AbstractWebTesterIntegrationTest {

    GenericListTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(GenericListTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobjects/genericList.html";
    }

    /*  validation of mapping   */

    @Test
    public final void testValidationOfMapping_orderedList() {
        assertPageObjectCanBeInitialized(page.orderedList);
    }

    @Test
    public final void testValidationOfMapping_unorderedList() {
        assertPageObjectCanBeInitialized(page.unorderedList);
    }

    @Test (expected = WrongElementClassException.class)
    public final void testValidationOfMapping_noList() {
        assertPageObjectCanBeInitialized(page.noList);
    }

    /*  utilities   */

    public static class GenericListTestPage extends PageObject{
        @IdentifyUsing("orderedList")
        GenericList orderedList;

        @IdentifyUsing("unorderedList")
        GenericList unorderedList;

        @IdentifyUsing("noList")
        GenericList noList;
    }
}
