package info.novatec.testit.webtester.support.assertj;

import info.novatec.testit.webtester.internal.Objects;
import info.novatec.testit.webtester.pageobjects.Image;


/**
 * Contains assertions for {@link Image images}.
 *
 * @since 0.9.8
 */
public class ImageAssert extends AbstractPageObjectAssert<ImageAssert, Image> {

    public ImageAssert(Image actual) {
        super(actual, ImageAssert.class);
    }

    /**
     * Asserts that the {@link Image image} has a certain source path.
     *
     * @param path the expected source path
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public ImageAssert hasSourcePath(String path) {
        failOnActualBeingNull();
        String actualPath = actual.getSourcePath();
        if (!Objects.equals(actualPath, path)) {
            failWithMessage("Expected image's source path to be '%s', but it was '%s'.", path, actualPath);
        }
        return this;
    }

    /**
     * Asserts that the {@link Image image} hasn't got a certain source path.
     *
     * @param path the not expected source path
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public ImageAssert hasNotSourcePath(String path) {
        failOnActualBeingNull();
        if (Objects.equals(actual.getSourcePath(), path)) {
            failWithMessage("Expected image's source path not to be '%s', but it was.", path);
        }
        return this;
    }

    /**
     * Asserts that the {@link Image image} has a certain file name.
     *
     * @param fileName the file name to match
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public ImageAssert hasFileName(String fileName) {
        failOnActualBeingNull();
        String actualFileName = actual.getFileName();
        if (!Objects.equals(actualFileName, fileName)) {
            failWithMessage("Expected image's filename to be '%s', but it was '%s'.", fileName, actualFileName);
        }
        return this;
    }

    /**
     * Asserts that the {@link Image image} hasn't got a certain file name.
     *
     * @param filename the file name not to match
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public ImageAssert hasNotFileName(String filename) {
        failOnActualBeingNull();
        if (Objects.equals(actual.getFileName(), filename)) {
            failWithMessage("Expected image's filename not to be '%s', but it was.", filename);
        }
        return this;
    }

}
