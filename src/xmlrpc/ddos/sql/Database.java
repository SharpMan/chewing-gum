package xmlrpc.ddos.sql;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import xmlrpc.ddos.sql.RpcWpBotJpaController;

/**
 *
 * @author Melancholia
 */
public class Database {

   private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("XMLRPC-DDoSPU");
   public final RpcWpBotJpaController RpcWpBotJpaController;


   public Database() {
      this.RpcWpBotJpaController = new RpcWpBotJpaController(this.emf);
   }
}
