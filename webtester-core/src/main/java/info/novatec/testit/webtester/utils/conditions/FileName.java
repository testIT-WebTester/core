package info.novatec.testit.webtester.utils.conditions;

import com.google.common.base.Predicate;

import info.novatec.testit.webtester.api.pageobjects.traits.HasFileName;
import info.novatec.testit.webtester.internal.Objects;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Predicate to be used in order to check if a {@link PageObject page object}
 * has a certain {@link HasFileName file name}.
 *
 * @since 0.9.9
 */
public class FileName implements Predicate<HasFileName> {

    private String fileName;

    public FileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean apply(HasFileName hasFileName) {
        return Objects.equals(fileName, hasFileName.getFileName());
    }

    @Override
    public String toString() {
        return String.format("file name: %s", fileName);
    }

}
