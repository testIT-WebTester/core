package info.novatec.testit.webtester.testng.exceptions;

@SuppressWarnings("serial")
public class IllegalTestClassStructureException extends WebTesterTestNGSupportException {

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
