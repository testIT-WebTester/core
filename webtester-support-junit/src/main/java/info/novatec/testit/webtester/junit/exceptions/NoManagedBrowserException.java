package info.novatec.testit.webtester.junit.exceptions;

import info.novatec.testit.webtester.junit.runner.WebTesterJUnitRunner;


@SuppressWarnings("serial")
public class NoManagedBrowserException extends IllegalTestClassStructureException {

    private static final String MESSAGE =
        "There are no manged browsers! Without at least one managed browser some features of the "
            + WebTesterJUnitRunner.class.getSimpleName() + " can't be used.";

    public NoManagedBrowserException() {
        super(MESSAGE);
    }

}
