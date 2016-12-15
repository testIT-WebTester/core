package integration.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import integration.AbstractWebTesterIntegrationTest;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.exceptions.WrongElementClassException;
import info.novatec.testit.webtester.pageobjects.GenericSelect;
import info.novatec.testit.webtester.pageobjects.PageObject;


public class GenericSelectIntegrationTest extends AbstractWebTesterIntegrationTest {

    GenericSelectTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(GenericSelectTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobjects/genericSelect.html";
    }

    /* get all option texts */

    @Test
    public final void testThatListWithOptionTextsIsReturnedCorrectly_singleSelect() {
        List<String> texts = page.singleSelect.getAllOptionTexts();
        assertThat(texts.get(0).equals("one"), is(true));
        assertThat(texts.get(1).equals("two"), is(true));
        assertThat(texts.get(2).equals("three"), is(true));
    }

    @Test
    public final void testThatListWithOptionTextsIsReturnedCorrectly_multiSelect() {
        List<String> texts = page.multiSelect.getAllOptionTexts();
        assertThat(texts.get(0).equals("one"), is(true));
        assertThat(texts.get(1).equals("two"), is(true));
        assertThat(texts.get(2).equals("three"), is(true));
    }

    @Test
    public final void testThatListWithOptionTextsIsReturnedCorrectly_emptySelect() {
        List<String> optionTexts = page.emptySelect.getAllOptionTexts();
        assertThat(optionTexts.isEmpty(), is(true));
    }

    /* get all option values */

    @Test
    public final void testThatListWithOptionValuesIsReturnedCorrectly_singleSelect() {
        List<String> values = page.singleSelect.getAllOptionValues();
        assertThat(values.get(0).equals("1"), is(true));
        assertThat(values.get(1).equals("2"), is(true));
        assertThat(values.get(2).equals("3"), is(true));
    }

    @Test
    public final void testThatListWithOptionValuesIsReturnedCorrectly_multiSelect() {
        List<String> values = page.multiSelect.getAllOptionValues();
        assertThat(values.get(0).equals("1"), is(true));
        assertThat(values.get(1).equals("2"), is(true));
        assertThat(values.get(2).equals("3"), is(true));
    }


    @Test
    public final void testThatListWithOptionValuesIsReturnedCorrectly_emptySelect() {
        List<String> optionValues = page.emptySelect.getAllOptionValues();
        assertThat(optionValues.isEmpty(), is(true));
    }

    /* number of options */

    @Test
    public final void testThatCorrectNumberOfOptionsIsReturned_singelSelect() {
        Integer numberOfOptions = page.singleSelect.getNumberOfOptions();
        assertThat(numberOfOptions.equals(3), is(true));
    }

    @Test
    public final void testThatCorrectNumberOfOptionsIsReturned_multiSelect() {
        Integer numberOfOptions = page.multiSelect.getNumberOfOptions();
        assertThat(numberOfOptions.equals(3), is(true));
    }

    /* validation of mapping */

    @Test
    public final void testValidationOfMapping_singleSelect() {
        assertPageObjectCanBeInitialized(page.singleSelect);
    }

    @Test
    public final void testValidationOfMapping_multiSelect() {
        assertPageObjectCanBeInitialized(page.multiSelect);
    }

    @Test(expected = WrongElementClassException.class)
    public final void testValidationOfClass_noSelect() {
        assertPageObjectCanBeInitialized(page.noSelect);
    }

    /* utilities */

    private static class GenericSelectTestPage extends PageObject{
        @IdentifyUsing("singleSelect")
        GenericSelect singleSelect;

        @IdentifyUsing("multiSelect")
        GenericSelect multiSelect;

        @IdentifyUsing("emptySelect")
        GenericSelect emptySelect;

        @IdentifyUsing("noSelect")
        GenericSelect noSelect;

    }
}


