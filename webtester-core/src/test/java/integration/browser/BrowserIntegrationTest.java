package integration.browser;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import integration.AbstractWebTesterIntegrationTest;


public class BrowserIntegrationTest extends AbstractWebTesterIntegrationTest {

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    @Override
    protected String getHTMLFilePath() {
        return "html/empty.html";
    }

    /* testAcceptAlertIfVisible */

    @Test
    public final void testAcceptAlertIfVisible_Alert_AlertAccapted() throws InterruptedException {
        String javaScript = "alert('accapt this message!')";
        getBrowser().executeJavaScript(javaScript);
        Thread.sleep(100);
        getBrowser().acceptAlertIfVisible();
    }

    @Test
    public final void testAcceptAlertIfVisible_NoAlert_NothingHappened() {
        getBrowser().acceptAlertIfVisible();
    }

    /* testDeclineAlertIfVisible */

    @Test
    public final void testDeclineAlertIfVisible_Alert_AlertAccapted() throws InterruptedException {
        String javaScript = "alert('decline this message!')";
        getBrowser().executeJavaScript(javaScript);
        Thread.sleep(100);
        getBrowser().declineAlertIfVisible();
    }

    @Test
    public final void testDeclineAlertIfVisible_NoAlert_NothingHappened() {
        getBrowser().declineAlertIfVisible();
    }

    /* testTakeScreenshot */

    @Test
    public final void testTakeScreenshot() {
        File file = getBrowser().takeScreenshot();
        assertThat(file.isFile(), is(true));
    }

    @Test
    public final void testTakeScreenshotWithCustomFolder() {

        String targetFolder = temp.getRoot().getAbsolutePath();

        File file = getBrowser().takeScreenshot(targetFolder);

        assertThat(file.isFile(), is(true));
        assertThat(file.getParentFile(), is(temp.getRoot()));

    }

    @Test
    public final void testTakeScreenshotWithCustomFolderAndFileName() {

        String targetFolder = temp.getRoot().getAbsolutePath();
        String fileNameWithoutSuffix = "fooBar";

        File file = getBrowser().takeScreenshot(targetFolder, fileNameWithoutSuffix);

        assertThat(file.isFile(), is(true));
        assertThat(file.getParentFile(), is(temp.getRoot()));
        assertThat(file.getName(), is(fileNameWithoutSuffix + ".jpg"));

    }

    /* testSaveSourceCode */

    @Test
    public final void testSaveSourceCode() {
        File file = getBrowser().saveSourceCode();
        assertThat(file.isFile(), is(true));
    }

    @Test
    public final void testSaveSourceCodeWithCustomFolder() {

        String targetFolder = temp.getRoot().getAbsolutePath();

        File file = getBrowser().saveSourceCode(targetFolder);

        assertThat(file.isFile(), is(true));
        assertThat(file.getParentFile(), is(temp.getRoot()));

    }

    @Test
    public final void testSaveSourceCodeWithCustomFolderAndFileName() {

        String targetFolder = temp.getRoot().getAbsolutePath();
        String fileNameWithoutSuffix = "fooBar";

        File file = getBrowser().saveSourceCode(targetFolder, fileNameWithoutSuffix);

        assertThat(file.isFile(), is(true));
        assertThat(file.getParentFile(), is(temp.getRoot()));
        assertThat(file.getName(), is(fileNameWithoutSuffix + ".html"));

    }

    /* testExecuteJavaScript */

    @Test
    public final void testExecuteJavaScript_WithoutParameters() throws InterruptedException {
        String javaScript = "alert('Hello World!')";
        getBrowser().executeJavaScript(javaScript);
        Thread.sleep(100);
        getBrowser().acceptAlertIfVisible();
    }

    @Test
    public final void testExecuteJavaScript_WithParameters() throws InterruptedException {
        String message = "Hello World!";
        String javaScript = "alert(arguments[0])";
        getBrowser().executeJavaScript(javaScript, message);
        Thread.sleep(100);
        getBrowser().acceptAlertIfVisible();
    }

}
