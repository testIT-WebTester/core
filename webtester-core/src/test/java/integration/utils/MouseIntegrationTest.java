package integration.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.annotation.PostConstruct;

import org.junit.Test;

import integration.AbstractWebTesterIntegrationTest;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.pageobjects.Button;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.utils.Mouse;


public class MouseIntegrationTest extends AbstractWebTesterIntegrationTest {

    @Override
    protected String getHTMLFilePath() {
        return "html/utils/mouse.html";
    }

    @Test
    public void testThatMovingTheMouseInSequenceIsPossible() {

        MoveTestPage page = getBrowser().create(MoveTestPage.class);

        /* Move to each button. Every move will trigger the display of the next
         * button. */
        Mouse.moveToEach(page.moveToStartButton, page.moveToInstantButton, page.moveToDelayedButton);

        /* Every button should be displayed after all moves are done. */
        assertThat(page.moveToStartButton.isVisible(), is(true));
        assertThat(page.moveToInstantButton.isVisible(), is(true));
        assertThat(page.moveToDelayedButton.isVisible(), is(true));

    }

    @Test
    public void testThatClickingAPageObjectWithTheMouseIsPossible() {

        MoveTestPage page = getBrowser().create(MoveTestPage.class);

        /* Click the single click button. */
        Mouse.click(page.clickSingleClickButton);

        /* The target button should now be displayed after. */
        assertThat(page.clickTargetButton.isVisible(), is(true));

    }

    @Test
    public void testThatDoubleClickingAPageObjectWithTheMouseIsPossible() {

        MoveTestPage page = getBrowser().create(MoveTestPage.class);

        /* Double click the double click button. */
        Mouse.doubleClick(page.clickDoubleClickButton);

        /* The target button should now be displayed after. */
        assertThat(page.clickTargetButton.isVisible(), is(true));

    }

    public static class MoveTestPage extends PageObject {

        @IdentifyUsing("moveTo_startButton")
        Button moveToStartButton;
        @IdentifyUsing("moveTo_instantButton")
        Button moveToInstantButton;
        @IdentifyUsing("moveTo_delayedButton")
        Button moveToDelayedButton;

        @IdentifyUsing("click_singleClickButton")
        Button clickSingleClickButton;
        @IdentifyUsing("click_doubleClickButton")
        Button clickDoubleClickButton;
        @IdentifyUsing("click_targetButton")
        Button clickTargetButton;

        @PostConstruct
        void checkThatButtonsHaveCorrectStateForTest() {

            assertThat(moveToStartButton.isVisible(), is(true));
            assertThat(moveToInstantButton.isVisible(), is(false));
            assertThat(moveToDelayedButton.isVisible(), is(false));

            assertThat(clickSingleClickButton.isVisible(), is(true));
            assertThat(clickDoubleClickButton.isVisible(), is(true));
            assertThat(clickTargetButton.isVisible(), is(false));

        }

    }

}
