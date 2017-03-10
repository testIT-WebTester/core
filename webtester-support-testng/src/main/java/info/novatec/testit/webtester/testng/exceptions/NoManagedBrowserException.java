package info.novatec.testit.webtester.testng.exceptions;


import info.novatec.testit.webtester.testng.listener.WebTesterTestNGListener;

@SuppressWarnings("serial")
public class NoManagedBrowserException extends IllegalTestClassStructureException {

    private static final String MESSAGE =
            "There are no managed browsers! Without at least one managed browser some features of the "
                    + WebTesterTestNGListener.class.getSimpleName() + " can't be used.";

    public NoManagedBrowserException() {
        super(MESSAGE);
    }

}
