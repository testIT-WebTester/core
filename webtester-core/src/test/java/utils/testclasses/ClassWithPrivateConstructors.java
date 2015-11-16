package utils.testclasses;

public final class ClassWithPrivateConstructors {

    public static final String DEFAULT_VALUE = "default";

    private final String value;

    private ClassWithPrivateConstructors() {
        value = DEFAULT_VALUE;
    }

    private ClassWithPrivateConstructors(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
