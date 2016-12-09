package integration.pageobjects;

import org.junit.Before;
import org.junit.Test;

import integration.AbstractWebTesterIntegrationTest;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.exceptions.WrongElementClassException;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.Paragraph;


public class ParagraphIntegrationTest extends AbstractWebTesterIntegrationTest {

    ParagraphTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(ParagraphTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobjects/paragraph.html";
    }

    /* correctness of mapping */

    @Test
    public final void testCorrectnessOfMapping_Paragraph() {
        assertPageObjectCanBeInitialized(page.paragraph);
    }

    @Test(expected = WrongElementClassException.class)
    public final void testCorrectnessOfMapping_NoParagraph() {
        assertPageObjectCanBeInitialized(page.noParagraph);
    }

    private static class ParagraphTestPage extends PageObject {
        @IdentifyUsing("paragraph")
        Paragraph paragraph;

        @IdentifyUsing("noParagraph")
        Paragraph noParagraph;
    }
}
