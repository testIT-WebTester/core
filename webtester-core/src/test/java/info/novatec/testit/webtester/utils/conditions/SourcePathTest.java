package info.novatec.testit.webtester.utils.conditions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static utils.TestObjectFactory.hasSourcePath;

import org.junit.Test;

import info.novatec.testit.webtester.api.pageobjects.traits.HasSourcePath;


public class SourcePathTest {

    SourcePath cut = new SourcePath("foo/bar.html");

    @Test
    public void testThatMatchingSourcePathEvaluatesToTrue() {
        HasSourcePath hasSourcePath = hasSourcePath("foo/bar.html");
        assertThat(cut.apply(hasSourcePath), is(true));
    }

    @Test
    public void testThatNotMatchingSourcePathEvaluatesToFalse() {
        HasSourcePath hasSourcePath = hasSourcePath("bar/foo.html");
        assertThat(cut.apply(hasSourcePath), is(false));
    }

}
