package info.novatec.testit.webtester.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.mockito.InjectMocks;

import info.novatec.testit.webtester.AbstractPageObjectTest;


public class IFrameTest extends AbstractPageObjectTest {

    @InjectMocks
    IFrame cut;

    /* source path */

    @Test
    public void testThatSourcePathIsRetrievedFromTheCorrectAttribute() {
        doReturn("/foo/bar.html").when(webElement).getAttribute("src");
        String sourcePath = cut.getSourcePath();
        assertThat(sourcePath, is("/foo/bar.html"));
    }

    @Test
    public void testThatEmptyStringIsReturnedIfNoSourcePathIsAvailable() {
        doReturn(null).when(webElement).getAttribute("src");
        String sourcePath = cut.getSourcePath();
        assertThat(sourcePath, is(StringUtils.EMPTY));
    }

    /* correctness of class */

    @Test
    public void testCorrectnessOfClassForWebElement_iframeTag() {
        stubWebElementTag("iframe");
        assertThatCorrectnessOfClassIs(true);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_otherTag() {
        stubWebElementTag("other");
        assertThatCorrectnessOfClassIs(false);
    }

    /* utilities */

    private void assertThatCorrectnessOfClassIs(boolean expected) {
        assertThat(cut.isCorrectClassForWebElement(webElement), is(expected));
    }

}
