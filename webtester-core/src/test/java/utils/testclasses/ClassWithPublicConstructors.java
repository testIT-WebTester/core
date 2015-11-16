package utils.testclasses;

public class ClassWithPublicConstructors {

    public static final String DEFAULT_VALUE = "default";

    private final String value;

    public ClassWithPublicConstructors() {
        value = DEFAULT_VALUE;
    }

    public ClassWithPublicConstructors(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
