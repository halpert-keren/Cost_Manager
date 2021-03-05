/**
 * @author Halpert, Keren (313604621)
 * @author Yechezkel, Danit Noa (203964036)
 */

package il.ac.shenkar.project.costmanager.model;

/**
 * The CostManagerException class defines a custom exception for the Cost Manager application.
 */
public class CostManagerException extends Exception {
    /**
     * The method to throw an exception specifying an exception message.
     *
     * @param message the message of the exception to throw.
     */
    public CostManagerException(String message) {
        super(message);
    }

    /**
     * The method to throw an exception specifying an exception message and the exception cause.
     *
     * @param message the message of the exception to throw.
     * @param cause   the cause of the exception to throw.
     */
    public CostManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}