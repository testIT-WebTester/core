package info.novatec.testit.webtester.support.assertj;

import static info.novatec.testit.webtester.support.assertj.WebTesterAssertions.assertThat;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import info.novatec.testit.webtester.pageobjects.IFrame;


@RunWith(MockitoJUnitRunner.class)
public class IFrameAssertTest {

    static final String SOURCE_PATH = "http://www.novatec-gmbh.de/";
    static final String ANOTHER_SOURCE_PATH = "https://www.wikipedia.org/";

    @Mock
    IFrame iframe;

    @Before
    public void setUp() {
        doReturn(SOURCE_PATH).when(iframe).getSourcePath();
    }

    /* hasSourcePath */

    @Test
    public final void hasSourcePathTrueTest() {
        assertThat(iframe).hasSourcePath(SOURCE_PATH);
    }

    @Test(expected = AssertionError.class)
    public final void hasSourcePathFalseTest() {
        assertThat(iframe).hasSourcePath(ANOTHER_SOURCE_PATH);
    }

    @Test
    public final void notHasSourcePathTrueTest() {
        assertThat(iframe).hasNotSourcePath(ANOTHER_SOURCE_PATH);
    }

    @Test(expected = AssertionError.class)
    public final void notHasSourcePathFalseTest() {
        assertThat(iframe).hasNotSourcePath(SOURCE_PATH);
    }

}
