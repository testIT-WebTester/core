package info.novatec.testit.webtester.junit.exceptions;

@SuppressWarnings("serial")
public class NoStaticPrimaryBrowserException extends IllegalTestClassStructureException {

    public NoStaticPrimaryBrowserException(String message) {
        super(message);
    }

}
