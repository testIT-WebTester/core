package info.novatec.testit.webtester.junit.exceptions;

import info.novatec.testit.webtester.junit.annotations.Primary;


@SuppressWarnings("serial")
public class NoUniquePrimaryBrowserException extends IllegalTestClassStructureException {

    private static final String MESSAGE =
        "There is no unique primary browser! @" + Primary.class.getSimpleName() + " must not be declared more then once!";

    public NoUniquePrimaryBrowserException() {
        super(MESSAGE);
    }

}
