package info.novatec.testit.webtester.utils.conditions;

import com.google.common.base.Predicate;

import info.novatec.testit.webtester.api.pageobjects.traits.HasSourcePath;
import info.novatec.testit.webtester.internal.Objects;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Predicate to be used in order to check if a {@link PageObject page object}
 * has a certain {@link HasSourcePath source path}.
 *
 * @since 0.9.9
 */
public class SourcePath implements Predicate<HasSourcePath> {

    private String sourcePath;

    public SourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    @Override
    public boolean apply(HasSourcePath hasSourcePath) {
        return Objects.equals(sourcePath, hasSourcePath.getSourcePath());
    }

    @Override
    public String toString() {
        return String.format("source path: %s", sourcePath);
    }

}
