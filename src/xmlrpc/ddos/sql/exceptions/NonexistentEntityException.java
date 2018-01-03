package xmlrpc.ddos.sql.exceptions;

/**
 *
 * @author Melancholia
 */
public class NonexistentEntityException extends Exception {

   public NonexistentEntityException(String message, Throwable cause) {
      super(message, cause);
   }

   public NonexistentEntityException(String message) {
      super(message);
   }
}
