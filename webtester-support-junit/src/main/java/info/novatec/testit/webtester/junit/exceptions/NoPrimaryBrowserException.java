package info.novatec.testit.webtester.junit.exceptions;

import info.novatec.testit.webtester.junit.annotations.Primary;


@SuppressWarnings("serial")
public class NoPrimaryBrowserException extends IllegalTestClassStructureException {

    private static final String MESSAGE =
        "There are more then one managed browsers. For the feature you wanted to use a primary browser needs to be declared. Use @"
            + Primary.class.getSimpleName() + " to declare on of your browsers as primary.";

    public NoPrimaryBrowserException() {
        super(MESSAGE);
    }

}
