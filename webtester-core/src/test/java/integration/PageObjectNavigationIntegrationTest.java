package integration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import utils.pageobjects.PageOne;
import utils.pageobjects.PageTwo;


public class PageObjectNavigationIntegrationTest extends AbstractWebTesterIntegrationTest {

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobject/pageOne.html";
    }

    @Test
    public final void testPageObject() {

        PageOne pageOne = getBrowser().create(PageOne.class);
        PageTwo pageTwo = pageOne.executeLogin("hello", "world");

        assertThat(pageTwo.getMessageValue(), is(equalTo("hello world!")));

        String[] expectedArray = new String[] { "Item 1", "Item 2", "Item 3" };
        assertThat(pageTwo.getListItemValues(), is(equalTo(expectedArray)));

    }

}
