package info.novatec.testit.webtester.support.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import info.novatec.testit.webtester.pageobjects.Image;


/**
 * Contains {@link Matcher matchers} for {@link Image images}.
 *
 * @since 0.9.7
 */
public class ImageMatcher extends PageObjectMatcher {

    /**
     * Returns whether the source path of the {@link Image image} matches the
     * expected source path.
     *
     * @param sourcePath the expected source path
     * @return true if the source path matches, otherwise false
     * @since 0.9.7
     */
    public static Matcher<Image> hasSourcePath(final String sourcePath) {
        return new TypeSafeMatcher<Image>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("source path to be '" + sourcePath + '\'');
            }

            @Override
            protected void describeMismatchSafely(Image image, Description mismatchDescription) {
                mismatchDescription.appendText("was '" + image.getSourcePath() + '\'');
            }

            @Override
            protected boolean matchesSafely(Image image) {
                return sourcePath.equals(image.getSourcePath());
            }
        };
    }

    /**
     * Returns whether the file name of the {@link Image image} matches the
     * expected file name.
     *
     * @param name the file name to match
     * @return true if the file name matches, otherwise false
     * @since 0.9.7
     */
    public static Matcher<Image> hasFileName(final String name) {
        return new TypeSafeMatcher<Image>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("file name to be '" + name + '\'');
            }

            @Override
            protected void describeMismatchSafely(Image image, Description mismatchDescription) {
                mismatchDescription.appendText("was '" + image.getFileName() + '\'');
            }

            @Override
            protected boolean matchesSafely(Image image) {
                return name.equals(image.getFileName());
            }
        };
    }

    protected ImageMatcher() {
        // utility constructor
    }

}
