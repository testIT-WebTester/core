package info.novatec.testit.webtester.utils.conditions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static utils.TestObjectFactory.hasFileName;

import org.junit.Test;

import info.novatec.testit.webtester.api.pageobjects.traits.HasFileName;


public class FileNameTest {

    FileName cut = new FileName("foo.html");

    @Test
    public void testThatMatchingFileNameEvaluatesToTrue() {
        HasFileName hasFileName = hasFileName("foo.html");
        assertThat(cut.apply(hasFileName), is(true));
    }

    @Test
    public void testThatNotMatchingFileNameEvaluatesToFalse() {
        HasFileName hasFileName = hasFileName("bar.html");
        assertThat(cut.apply(hasFileName), is(false));
    }

}
