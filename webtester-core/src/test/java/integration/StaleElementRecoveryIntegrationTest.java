package integration;

import static info.novatec.testit.webtester.utils.Conditions.invisible;
import static info.novatec.testit.webtester.utils.Conditions.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.enumerations.Method;
import info.novatec.testit.webtester.api.exceptions.StaleElementRecoveryException;
import info.novatec.testit.webtester.pageobjects.Button;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.TextField;
import info.novatec.testit.webtester.utils.Waits;


public class StaleElementRecoveryIntegrationTest extends AbstractWebTesterIntegrationTest {

    private StaleElementReferencePage page;

    @Before
    public void setUp() {
        page = getBrowser().create(StaleElementReferencePage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/staleElementRecovery.html";
    }

    /* stale element revovery */

    @Test(expected = StaleElementRecoveryException.class)
    public void testThatRecoveryIsTriedInCaseOfSteleElementReferenceException() {
        page.foo.setText("A");
        page.removeFoo.click();
        page.foo.setText("B");
    }

    @Test
    public void testThatRecoveryIsSuccessfullIfElementCanBeRediscovered() {
        page.foo.setText("A");
        page.removeFoo.click();
        page.addFoo.click();
        page.foo.setText("B");
    }

    /* is invisible */

    @Test
    public void testIsVisible_ElementNeverDisapears() {
        arbitraryInteractionWithElement();
        assertThat(page.waitFoo.isVisible(), is(true));
    }

    @Test
    public void testIsVisible_ElementAlreadyGone() {
        arbitraryInteractionWithElement();
        page.removeFoo.click();
        assertThat(page.waitFoo.isVisible(), is(false));
    }

    @Test
    public void testIsVisible_ElementIsRemovedWithDelay() {
        arbitraryInteractionWithElement();
        page.removeFooDelayed.click();
        assertThat(page.waitFoo.isVisible(), is(true));
    }

    /* wait until invisible */

    @Test(expected = TimeoutException.class)
    public void testWaitUntilInvisible_ElementNeverDisapears_WaitThrowsExceptionAfterTimeout() {
        arbitraryInteractionWithElement();
        Waits.waitUntil(page.waitFoo, is(invisible()));
    }

    @Test
    public void testWaitUntilInvisible_ElementAlreadyGone_WaitMethodReturnsInstantly() throws InterruptedException {
        arbitraryInteractionWithElement();
        page.removeFoo.click();
        Thread.sleep(100);
        Waits.waitUntil(page.waitFoo, is(invisible()));
    }

    @Test
    public void testWaitUntilInvisible_ElementIsRemovedWithDelay_WaitMethodReturnsAfterTheDelayedRemoval() {
        arbitraryInteractionWithElement();
        page.removeFooDelayed.click();
        Waits.waitUntil(page.waitFoo, is(invisible()));
    }

    private void arbitraryInteractionWithElement() {
        page.waitFoo.setText("A");
    }

    /* utilities */

    public static class StaleElementReferencePage extends PageObject {

        @IdentifyUsing("removeFoo")
        Button removeFoo;
        @IdentifyUsing("removeFooDelayed")
        Button removeFooDelayed;
        @IdentifyUsing("addFoo")
        Button addFoo;

        @IdentifyUsing(method = Method.ID_ENDS_WITH, value = "foo")
        TextField foo;

        @IdentifyUsing("container1:foo")
        TextField waitFoo;

    }

}
