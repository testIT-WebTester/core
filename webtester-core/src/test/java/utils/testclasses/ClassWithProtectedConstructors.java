package utils.testclasses;

public class ClassWithProtectedConstructors {

    public static final String DEFAULT_VALUE = "default";

    private final String value;

    protected ClassWithProtectedConstructors() {
        value = DEFAULT_VALUE;
    }

    protected ClassWithProtectedConstructors(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
