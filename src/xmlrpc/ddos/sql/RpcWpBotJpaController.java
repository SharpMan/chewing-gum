package xmlrpc.ddos.sql;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import xmlrpc.ddos.sql.RpcWpBot;
import xmlrpc.ddos.sql.exceptions.NonexistentEntityException;

/**
 *
 * @author Melancholia
 */
public class RpcWpBotJpaController implements Serializable {

   private EntityManagerFactory emf = null;


   public RpcWpBotJpaController(EntityManagerFactory emf) {
      this.emf = emf;
   }

   public EntityManager getEntityManager() {
      return this.emf.createEntityManager();
   }

   public void create(RpcWpBot rpcWpBot) {
      EntityManager em = null;

      try {
         em = this.getEntityManager();
         em.getTransaction().begin();
         em.persist(rpcWpBot);
         em.getTransaction().commit();
      } finally {
         if(em != null) {
            em.close();
         }

      }

   }

   public void edit(RpcWpBot rpcWpBot) throws NonexistentEntityException, Exception {
      EntityManager em = null;

      try {
         em = this.getEntityManager();
         em.getTransaction().begin();
         rpcWpBot = (RpcWpBot)em.merge(rpcWpBot);
         em.getTransaction().commit();
      } catch (Exception var9) {
         String msg = var9.getLocalizedMessage();
         if(msg == null || msg.length() == 0) {
            Long id = rpcWpBot.getId();
            if(this.findRpcWpBot(id) == null) {
               throw new NonexistentEntityException("The rpcWpBot with id " + id + " no longer exists.");
            }
         }

         throw var9;
      } finally {
         if(em != null) {
            em.close();
         }

      }

   }

   public void destroy(Long id) throws NonexistentEntityException {
      EntityManager em = null;

      try {
         em = this.getEntityManager();
         em.getTransaction().begin();

         RpcWpBot rpcWpBot;
         try {
            rpcWpBot = (RpcWpBot)em.getReference(RpcWpBot.class, id);
            rpcWpBot.getId();
         } catch (EntityNotFoundException var8) {
            throw new NonexistentEntityException("The rpcWpBot with id " + id + " no longer exists.", var8);
         }

         em.remove(rpcWpBot);
         em.getTransaction().commit();
      } finally {
         if(em != null) {
            em.close();
         }

      }

   }

   public List findRpcWpBotEntities() {
      return this.findRpcWpBotEntities(true, -1, -1);
   }

   public List findRpcWpBotEntities(int maxResults, int firstResult) {
      return this.findRpcWpBotEntities(false, maxResults, firstResult);
   }

   private List findRpcWpBotEntities(boolean all, int maxResults, int firstResult) {
      EntityManager em = this.getEntityManager();

      List var8;
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         cq.select(cq.from(RpcWpBot.class));
         TypedQuery q = em.createQuery(cq);
         if(!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
         }

         var8 = q.getResultList();
      } finally {
         em.close();
      }

      return var8;
   }

   public RpcWpBot findRpcWpBot(Long id) {
      EntityManager em = this.getEntityManager();

      RpcWpBot var4;
      try {
         var4 = (RpcWpBot)em.find(RpcWpBot.class, id);
      } finally {
         em.close();
      }

      return var4;
   }

   public int getRpcWpBotCount() {
      EntityManager em = this.getEntityManager();

      int var6;
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         Root rt = cq.from(RpcWpBot.class);
         cq.select(em.getCriteriaBuilder().count(rt));
         TypedQuery q = em.createQuery(cq);
         var6 = ((Long)q.getSingleResult()).intValue();
      } finally {
         em.close();
      }

      return var6;
   }
}
