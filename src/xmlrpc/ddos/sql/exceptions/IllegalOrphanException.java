package xmlrpc.ddos.sql.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Melancholia
 */
public class IllegalOrphanException extends Exception {

   private List messages;


   public IllegalOrphanException(List messages) {
      super(messages != null && messages.size() > 0?(String)messages.get(0):null);
      if(messages == null) {
         this.messages = new ArrayList();
      } else {
         this.messages = messages;
      }

   }

   public List getMessages() {
      return this.messages;
   }
}
