package utils.testclasses;

public class ClassWithMethods {

    public ClassWithMethods() {
    }

    public String publicMethod() {
        return "public";
    }

    @SuppressWarnings("unused")
    private String privateMethod() {
        return "private";
    }

    protected String protectedMethod() {
        return "protected";
    }

    String packageProtectedMethod() {
        return "package protected";
    }

}
