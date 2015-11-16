package info.novatec.testit.webtester.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.mockito.InjectMocks;

import info.novatec.testit.webtester.AbstractPageObjectTest;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsDisabledException;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsInvisibleException;


public class ImageTest extends AbstractPageObjectTest {

    @InjectMocks
    Image cut;

    /* source path */

    @Test
    public void testThatSourcePathIsRetrievedFromTheCorrectAttribute() {
        stubImageSource("/foo/bar.png");
        String sourcePath = cut.getSourcePath();
        assertThat(sourcePath, is("/foo/bar.png"));
    }

    @Test
    public void testThatEmptyStringIsReturnedIfNoSourcePathIsAvailable() {
        stubImageSource(null);
        String sourcePath = cut.getSourcePath();
        assertThat(sourcePath, is(StringUtils.EMPTY));
    }

    /* file name */

    @Test
    public void testThatFileNameIsCalculatedCorrectly_DirectImage() {
        stubImageSource("bar.png");
        String fileName = cut.getFileName();
        assertThat(fileName, is("bar.png"));
    }

    @Test
    public void testThatFileNameIsCalculatedCorrectly_PathToImage() {
        stubImageSource("foo/bar.png");
        String fileName = cut.getFileName();
        assertThat(fileName, is("bar.png"));
    }

    @Test
    public void testThatFileNameIsCalculatedCorrectly_UrlImage() {
        stubImageSource("http://www.foo.de/bar.png");
        String fileName = cut.getFileName();
        assertThat(fileName, is("bar.png"));
    }

    @Test
    public void testThatEmptySourcePathLeadsToEmptyFileName() {
        stubImageSource(StringUtils.EMPTY);
        String fileName = cut.getFileName();
        assertThat(fileName, is(StringUtils.EMPTY));
    }

    @Test
    public void testThatBlankSourcePathLeadsToEmptyFileName() {
        stubImageSource("   ");
        String fileName = cut.getFileName();
        assertThat(fileName, is(StringUtils.EMPTY));
    }

    /* clicking */

    @Test
    public void testThatClickingAnImageDelegatesToCorrectMethod() {
        cut.click();
        verify(webElement).click();
    }

    @Test(expected = PageObjectIsInvisibleException.class)
    public void testThatClickingAnInvisibleImageThrowsException() {
        elementIsInvisible();
        cut.click();
    }

    @Test(expected = PageObjectIsDisabledException.class)
    public void testThatClickingADisabledImageThrowsException() {
        elementIsDisabled();
        cut.click();
    }

    /* correctness of class */

    @Test
    public void testCorrectnessOfClassForWebElement_imageTag() {
        stubWebElementTag("img");
        assertThatCorrectnessOfClassIs(true);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_otherTag() {
        stubWebElementTag("other");
        assertThatCorrectnessOfClassIs(false);
    }

    /* utilities */

    private void stubImageSource(String src) {
        doReturn(src).when(webElement).getAttribute("src");
    }

    private void assertThatCorrectnessOfClassIs(boolean expected) {
        assertThat(cut.isCorrectClassForWebElement(webElement), is(expected));
    }

}
