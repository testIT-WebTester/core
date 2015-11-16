package utils.testclasses;

public class ClassWithFields {

    public String publicValue;

    private String privateValue;

    protected String protectedValue;

    String packageProtectedValue;

    public ClassWithFields() {
    }

    public String getPublicValue() {
        return publicValue;
    }

    public String getPrivateValue() {
        return privateValue;
    }

    public String getProtectedValue() {
        return protectedValue;
    }

    public String getPackageProtectedValue() {
        return packageProtectedValue;
    }

}
