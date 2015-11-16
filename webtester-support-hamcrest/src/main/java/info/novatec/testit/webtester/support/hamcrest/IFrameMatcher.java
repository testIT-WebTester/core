package info.novatec.testit.webtester.support.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import info.novatec.testit.webtester.pageobjects.IFrame;


/**
 * Contains {@link Matcher matchers} for {@link IFrame i-frames}.
 *
 * @since 0.9.7
 */
public class IFrameMatcher extends PageObjectMatcher {

    /**
     * Returns whether the source path of the {@link IFrame} matches the
     * expected source path.
     *
     * @param sourcePath the source path to match
     * @return true if the source path matches, otherwise false
     * @since 0.9.7
     */
    public static Matcher<IFrame> hasSourcePath(final String sourcePath) {
        return new TypeSafeMatcher<IFrame>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("source path to be '" + sourcePath + '\'');
            }

            @Override
            protected void describeMismatchSafely(IFrame iFrame, Description mismatchDescription) {
                mismatchDescription.appendText("was '" + iFrame.getSourcePath() + '\'');
            }

            @Override
            protected boolean matchesSafely(IFrame iFrame) {
                return sourcePath.equals(iFrame.getSourcePath());
            }

        };

    }

    protected IFrameMatcher() {
        // utility constructor
    }

}
