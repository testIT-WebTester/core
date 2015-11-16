package info.novatec.testit.webtester.support.assertj;

import static info.novatec.testit.webtester.support.assertj.WebTesterAssertions.assertThat;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import info.novatec.testit.webtester.pageobjects.Image;


@RunWith(MockitoJUnitRunner.class)
public class ImageAssertTest {

    static final String SOURCE_PATH = "http://source-path.net/image.png";
    static final String ANOTHER_SOURCE_PATH = "http://source-path.net/another-image.png";
    static final String FILE_NAME = "image.png";
    static final String ANOTHER_FILE_NAME = "another-image.png";

    @Mock
    Image image;

    @Before
    public void setUp() {
        doReturn(SOURCE_PATH).when(image).getSourcePath();
        doReturn(FILE_NAME).when(image).getFileName();
    }

    /* hasSourcePath */

    @Test
    public final void hasSourcePathTrueTest() {
        assertThat(image).hasSourcePath(SOURCE_PATH);
    }

    @Test(expected = AssertionError.class)
    public final void hasSourcePathFalseTest() {
        assertThat(image).hasSourcePath(ANOTHER_SOURCE_PATH);
    }

    @Test
    public final void notHasSourcePathTrueTest() {
        assertThat(image).hasNotSourcePath(ANOTHER_SOURCE_PATH);
    }

    @Test(expected = AssertionError.class)
    public final void notHasSourcePathFalseTest() {
        assertThat(image).hasNotSourcePath(SOURCE_PATH);
    }

    /* hasFileName */

    @Test
    public final void hasFileNameTrueTest() {
        assertThat(image).hasFileName(FILE_NAME);
    }

    @Test(expected = AssertionError.class)
    public final void hasFileNameFalseTest() {
        assertThat(image).hasFileName(ANOTHER_FILE_NAME);
    }

    @Test
    public final void notHasFileNameTrueTest() {
        assertThat(image).hasNotFileName(ANOTHER_FILE_NAME);
    }

    @Test(expected = AssertionError.class)
    public final void notHasFileNameFalseTest() {
        assertThat(image).hasNotFileName(FILE_NAME);
    }

}
