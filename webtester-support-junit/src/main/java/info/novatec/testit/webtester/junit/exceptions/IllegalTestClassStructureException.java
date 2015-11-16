package info.novatec.testit.webtester.junit.exceptions;

@SuppressWarnings("serial")
public class IllegalTestClassStructureException extends WebTesterJUnitSupportException {

    public IllegalTestClassStructureException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalTestClassStructureException(String message) {
        super(message);
    }

    public IllegalTestClassStructureException(Throwable cause) {
        super(cause);
    }

}
