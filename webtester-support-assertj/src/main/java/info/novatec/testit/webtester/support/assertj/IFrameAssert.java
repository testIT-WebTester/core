package info.novatec.testit.webtester.support.assertj;

import info.novatec.testit.webtester.internal.Objects;
import info.novatec.testit.webtester.pageobjects.IFrame;


/**
 * Contains assertions for {@link IFrame iframes}.
 *
 * @since 0.9.8
 */
public class IFrameAssert extends AbstractPageObjectAssert<IFrameAssert, IFrame> {

    public IFrameAssert(IFrame actual) {
        super(actual, IFrameAssert.class);
    }

    /**
     * Asserts that the {@link IFrame iframe} has a certain source path.
     *
     * @param path the source path to match
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public IFrameAssert hasSourcePath(String path) {
        failOnActualBeingNull();
        String actualPath = actual.getSourcePath();
        if (!Objects.equals(actualPath, path)) {
            failWithMessage("Expected frame's source path to be '%s', but it was '%s'.", path, actualPath);
        }
        return this;
    }

    /**
     * Asserts that the {@link IFrame iframe} hasn't got a certain source path.
     *
     * @param path the source path not to match
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public IFrameAssert hasNotSourcePath(String path) {
        failOnActualBeingNull();
        if (Objects.equals(actual.getSourcePath(), path)) {
            failWithMessage("Expected IFrame's source path not to be '%s', but it was.", path);
        }
        return this;
    }

}
