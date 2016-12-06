package integration.pageobjects;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.exceptions.WrongElementClassException;
import info.novatec.testit.webtester.pageobjects.GenericList;
import info.novatec.testit.webtester.pageobjects.PageObject;
import integration.AbstractWebTesterIntegrationTest;
import org.junit.Before;
import org.junit.Test;


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
    public final void orderedListTypeIsValidMapping() {
        assertPageObjectCanBeInitialized(page.orderedList);
    }

    @Test
    public final void unorderedListTypeIsValidMapping() {
        assertPageObjectCanBeInitialized(page.unorderedList);
    }

    @Test (expected = WrongElementClassException.class)
    public final void nonListTypeIsInvalidMapping() {
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
