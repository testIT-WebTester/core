package info.novatec.testit.webtester.support.hamcrest;

import static info.novatec.testit.webtester.support.hamcrest.IFrameMatcher.hasSourcePath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import info.novatec.testit.webtester.pageobjects.IFrame;


@RunWith(MockitoJUnitRunner.class)
public class IFrameMatcherTest {

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
        assertThat(iframe, hasSourcePath(SOURCE_PATH));
    }

    @Test(expected = AssertionError.class)
    public final void hasSourcePathFalseTest() {
        assertThat(iframe, hasSourcePath(ANOTHER_SOURCE_PATH));
    }

    @Test
    public final void notHasSourcePathTrueTest() {
        assertThat(iframe, not(hasSourcePath(ANOTHER_SOURCE_PATH)));
    }

    @Test(expected = AssertionError.class)
    public final void notHasSourcePathFalseTest() {
        assertThat(iframe, not(hasSourcePath(SOURCE_PATH)));
    }

}
