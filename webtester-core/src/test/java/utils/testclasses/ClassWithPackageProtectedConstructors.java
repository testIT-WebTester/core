package utils.testclasses;

public class ClassWithPackageProtectedConstructors {

    public static final String DEFAULT_VALUE = "default";

    private final String value;

    ClassWithPackageProtectedConstructors() {
        value = DEFAULT_VALUE;
    }

    ClassWithPackageProtectedConstructors(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
