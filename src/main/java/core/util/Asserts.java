package core.util;

public class Asserts {
    public static void assertEquals(Object expected, Object actual) {
        if (!expected.equals(actual)) {
            throw new RuntimeException("Objects are not equal!");
        }
    }

    public static void assertNull(Object actual) {
        if (actual != null) {
            throw new RuntimeException("Actual object is expected to be null!");
        }
    }
}
