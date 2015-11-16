package integration.utils.conditions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

import integration.AbstractWebTesterIntegrationTest;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.pageobjects.Checkbox;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.TextField;
import info.novatec.testit.webtester.utils.Conditions;


public class ReadOnlyIntegrationTest extends AbstractWebTesterIntegrationTest {

    ReadOnlyTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(ReadOnlyTestPage.class);
    }

    /**
     * This test verifies that we are using the correct trigger values when
     * working with HTML4/5. In HTML the readonly attribute is a boolean.
     */
    @Test
    public void testThatReadOnlyIsRecognizedForHtmlElement() {
        open("html/utils/conditions_readonly.html"); // HTML 4/5
        assertThat(Conditions.readOnly().apply(page.switchable), is(true));
        page.switchCheckbox.click();
        assertThat(Conditions.readOnly().apply(page.switchable), is(false));
    }

    /**
     * This test verifies that we are using the correct trigger values when
     * working with XHTML. In XHTML the readonly attribute is set if it has the
     * value 'readonly'.
     */
    @Test
    public void testThatReadOnlyIsRecognizedForXhtmlElement() {
        open("html/utils/conditions_readonly.xhtml"); // XHTML
        assertThat(Conditions.readOnly().apply(page.switchable), is(true));
        page.switchCheckbox.click();
        assertThat(Conditions.readOnly().apply(page.switchable), is(false));
    }

    static class ReadOnlyTestPage extends PageObject {

        @IdentifyUsing("switchable")
        TextField switchable;
        @IdentifyUsing("switch")
        Checkbox switchCheckbox;

    }

}
