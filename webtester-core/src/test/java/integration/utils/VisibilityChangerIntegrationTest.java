package integration.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.annotation.PostConstruct;

import org.junit.Test;

import integration.AbstractWebTesterIntegrationTest;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.utils.VisibilityChanger;


public class VisibilityChangerIntegrationTest extends AbstractWebTesterIntegrationTest {

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobject/base/index.html";
    }

    /* testMakeVisible */

    @Test
    public final void testMakeVisible_ElementIsInvisible_ElementVisible() {
        PageObject element = page().spanIsNotVisible;
        VisibilityChanger.makeVisible(element);
        assertThat(element.isVisible(), is(true));
    }

    @Test
    public final void testMakeVisible_ElementIsVisible_ElementVisible() {
        PageObject element = page().spanIsVisible;
        VisibilityChanger.makeVisible(element);
        assertThat(element.isVisible(), is(true));
    }

    /* testMakeInvisible */

    @Test
    public final void testMakeInvisible_ElementIsVisible_ElementInvisible() {
        PageObject element = page().spanIsVisible;
        VisibilityChanger.makeInvisible(element);
        assertThat(element.isVisible(), is(false));
    }

    @Test
    public final void testMakeInvisible_ElementIsInvisible_ElementInvisible() {
        PageObject element = page().spanIsNotVisible;
        VisibilityChanger.makeInvisible(element);
        assertThat(element.isVisible(), is(false));
    }

    public VisibilityChangerTestPage page() {
        return getBrowser().create(VisibilityChangerTestPage.class);
    }

    public static class VisibilityChangerTestPage extends PageObject {

        @IdentifyUsing("spanIsVisible")
        PageObject spanIsVisible;

        @IdentifyUsing("spanIsNotVisible")
        PageObject spanIsNotVisible;

        @PostConstruct
        void checkThatButtonsHaveCorrectStateForTest() {
            assertThat(spanIsVisible.isVisible(), is(true));
            assertThat(spanIsNotVisible.isVisible(), is(false));
        }

    }

}
