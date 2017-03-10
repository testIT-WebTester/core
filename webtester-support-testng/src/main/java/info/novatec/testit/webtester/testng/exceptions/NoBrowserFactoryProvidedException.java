package info.novatec.testit.webtester.testng.exceptions;


@SuppressWarnings("serial")
public class NoBrowserFactoryProvidedException extends IllegalTestClassStructureException {

    private static final String MESSAGE =
            "You have to provide a BrowserFactory to use by annotation the Browser field with @CreateUsing!";

    public NoBrowserFactoryProvidedException() {
        super(MESSAGE);
    }

}
