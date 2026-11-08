package library;

/** Custom checked exception for library rule violations. */
public class LibraryException extends Exception {
    public LibraryException(String message) { super(message); }
}
