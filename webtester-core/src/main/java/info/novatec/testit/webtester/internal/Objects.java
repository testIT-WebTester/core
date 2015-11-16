package info.novatec.testit.webtester.internal;

public final class Objects {

    public static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }

    private Objects() {
        // utility constructor
    }

}
