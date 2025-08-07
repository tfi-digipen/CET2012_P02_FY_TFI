package CustomException;

/**
 * CustomException class implementing Exception interface
 */
public class CustomException extends Exception {
    /**
     * Main constructor for CustomException
     * @param message Exception message
     */
    public CustomException(String message) {
        super(message);
    }
}