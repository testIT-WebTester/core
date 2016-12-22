package integration.pageobjects;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.exceptions.WrongElementClassException;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.SearchField;
import integration.AbstractWebTesterIntegrationTest;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.PostConstruct;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SearchFieldIntegrationTest extends AbstractWebTesterIntegrationTest {
    SearchFieldTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(SearchFieldIntegrationTest.SearchFieldTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobjects/searchField.html";
    }

    /*  getting text    */

    @Test
    public final void testThatGettingTextWorksWithParentImplementation() {
        assertThat(page.withValue.getText(), is("value"));
    }

    /*  appending text  */

    @Test
    public final void testThatAppendingOfTextWorksWithParentImplementation() {
        SearchField element = page.withValue.appendText(" with appended text");
        assertThat(element.getText(), is("value with appended text"));
    }

    /*  setting text    */

    @Test
    public final void testThatSettingOfTextWorksWithParentImplementation() {
        SearchField element = page.empty.setText("no longer empty");
        assertThat(element.getText(), is("no longer empty"));
    }

    @Test
    public final void testThatSettingTextWithValueWorksWithParentImplementation() {
        SearchField element = page.withValue.setText("still value");
        assertThat(element.getText(), is("still value"));
    }


    /*  clearing text   */

    @Test
    public final void testThatClearingOfTextWorksWithParentImplementation() {
        SearchField element = page.withValue.clearText();
        assertThat(element.getText(), is(""));
    }

    /* validation of mapping */

    @Test
    public final void testValidationOfMapping_SearchField() {
        assertPageObjectCanBeInitialized(page.empty);
    }

    @Test(expected = WrongElementClassException.class)
    public final void testValidationOfMapping_noSearchField() {
        assertPageObjectCanBeInitialized(page.notASearchField);
    }

    /*  utilities   */

    public static class SearchFieldTestPage extends PageObject {
        @IdentifyUsing("empty")
        SearchField empty;

        @IdentifyUsing("withValue")
        SearchField withValue;

        @IdentifyUsing("notASearchField")
        SearchField notASearchField;

        @PostConstruct
        void checkStartingConditions() {
            assertThat(empty.isVisible(), is(true));
            assertThat(withValue.isVisible(), is(true));

            assertThat(empty.getText(), is(StringUtils.EMPTY));
            assertThat(withValue.getText(), is("value"));
        }
    }
}


