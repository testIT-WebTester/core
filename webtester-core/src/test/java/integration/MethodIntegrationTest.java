package integration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.enumerations.Method;
import info.novatec.testit.webtester.pageobjects.Link;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.TextField;


public class MethodIntegrationTest extends AbstractWebTesterIntegrationTest {

    MethodTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(MethodTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/methods.html";
    }

    @Test
    public void testThatIdIdentifierWorks() {
        assertThat(page.byId.getText(), is("id"));
    }

    @Test
    public void testThatIdStartsWithIdentifierWorks() {
        assertThat(page.byIdStartsWith.getText(), is("id starts with"));
    }

    @Test
    public void testThatIdEndsWithIdentifierWorks() {
        assertThat(page.byIdEndsWith.getText(), is("id ends with"));
    }

    @Test
    public void testThatXpathIdentifierWorks() {
        assertThat(page.byXpath.getText(), is("xpath"));
    }

    @Test
    public void testThatCssIdentifierWorks() {
        assertThat(page.byCss.getText(), is("css"));
    }

    @Test
    public void testThatClassIdentifierWorks() {
        assertThat(page.byClass.getText(), is("class"));
    }

    @Test
    public void testThatNameIdentifierWorks() {
        assertThat(page.byName.getText(), is("name"));
    }

    @Test
    public void testThatLinkTextIdentifierWorks() {
        assertThat(page.byLinkText.getVisibleText(), is("link text"));
    }

    @Test
    public void testThatPartialLinkTextIdentifierWorks() {
        assertThat(page.byPartialLinkText.getVisibleText(), is("partial link text"));
    }

    @Test
    public void testThatTagNameIdentifierWorks() {
        assertThat(page.byTagName.getVisibleText(), is("tag name"));
    }

    public static class MethodTestPage extends PageObject {

        @IdentifyUsing(method = Method.ID, value = "id")
        TextField byId;

        @IdentifyUsing(method = Method.ID_STARTS_WITH, value = "prefix-")
        TextField byIdStartsWith;

        @IdentifyUsing(method = Method.ID_ENDS_WITH, value = "-suffix")
        TextField byIdEndsWith;

        @IdentifyUsing(method = Method.XPATH, value = "//div[@id='xpath']/input")
        TextField byXpath;

        @IdentifyUsing(method = Method.CSS, value = "div#css input")
        TextField byCss;

        @IdentifyUsing(method = Method.CLASS_NAME, value = "class")
        TextField byClass;

        @IdentifyUsing(method = Method.NAME, value = "name")
        TextField byName;

        @IdentifyUsing(method = Method.LINK_TEXT, value = "link text")
        Link byLinkText;

        @IdentifyUsing(method = Method.PARTIAL_LINK_TEXT, value = "partial link")
        Link byPartialLinkText;

        @IdentifyUsing(method = Method.TAGNAME, value = "tagname")
        PageObject byTagName;

    }

}
