package integration.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
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
        assertThat(texts.get(0), is("one"));
        assertThat(texts.get(1), is("two"));
        assertThat(texts.get(2), is("three"));
    }

    @Test
    public final void testThatListWithOptionTextsIsReturnedCorrectly_multiSelect() {
        List<String> texts = page.multiSelect.getAllOptionTexts();
        assertThat(texts.get(0), is("one"));
        assertThat(texts.get(1), is("two"));
        assertThat(texts.get(2), is("three"));
    }

    @Test
    public final void testThatListWithOptionTextsIsReturnedCorrectly_emptySelect() {
        List<String> optionTexts = page.emptySelect.getAllOptionTexts();
        assertThat(optionTexts, is(empty()));
    }

    /* get all option values */

    @Test
    public final void testThatListWithOptionValuesIsReturnedCorrectly_singleSelect() {
        List<String> values = page.singleSelect.getAllOptionValues();
        assertThat(values.get(0), is("1"));
        assertThat(values.get(1), is("2"));
        assertThat(values.get(2), is("3"));
    }

    @Test
    public final void testThatListWithOptionValuesIsReturnedCorrectly_multiSelect() {
        List<String> values = page.multiSelect.getAllOptionValues();
        assertThat(values.get(0), is("1"));
        assertThat(values.get(1), is("2"));
        assertThat(values.get(2), is("3"));
    }


    @Test
    public final void testThatListWithOptionValuesIsReturnedCorrectly_emptySelect() {
        List<String> optionValues = page.emptySelect.getAllOptionValues();
        assertThat(optionValues, is(empty()));
    }

    /* number of options */

    @Test
    public final void testThatCorrectNumberOfOptionsIsReturned_singelSelect() {
        Integer numberOfOptions = page.singleSelect.getNumberOfOptions();
        assertThat(numberOfOptions, is(3));
    }

    @Test
    public final void testThatCorrectNumberOfOptionsIsReturned_multiSelect() {
        Integer numberOfOptions = page.multiSelect.getNumberOfOptions();
        assertThat(numberOfOptions, is(3));
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
        assertPageObjectCanBeInitialized(page.notASelect);
    }

    /* utilities */

    private static class GenericSelectTestPage extends PageObject{
        @IdentifyUsing("singleSelect")
        GenericSelect singleSelect;

        @IdentifyUsing("multiSelect")
        GenericSelect multiSelect;

        @IdentifyUsing("emptySelect")
        GenericSelect emptySelect;

        @IdentifyUsing("notASelect")
        GenericSelect notASelect;

    }
}


