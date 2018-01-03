package xmlrpc.ddos.sql;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Melancholia
 */
@Entity
@Table(
   name = "rpc_wp_bots"
)
@XmlRootElement
@NamedQueries({   @NamedQuery(
      name = "RpcWpBot.findAll",
      query = "SELECT r FROM RpcWpBot r"
   ),    @NamedQuery(
      name = "RpcWpBot.findById",
      query = "SELECT r FROM RpcWpBot r WHERE r.id = :id"
   )})
public class RpcWpBot implements Serializable {

   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   @Basic(
      optional = false
   )
   @Column(
      name = "id"
   )
   private Long id;
   @Basic(
      optional = false
   )
   @Lob
   @Column(
      name = "url"
   )
   private String url;
   @Basic(
      optional = false
   )
   @Lob
   @Column(
      name = "rpc"
   )
   private String rpc;


   public RpcWpBot() {}

   public RpcWpBot(Long id) {
      this.id = id;
   }

   public RpcWpBot(Long id, String url, String rpc) {
      this.id = id;
      this.url = url;
      this.rpc = rpc;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getUrl() {
      return this.url;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   public String getRpc() {
      return this.rpc;
   }

   public void setRpc(String rpc) {
      this.rpc = rpc;
   }

   public int hashCode() {
      byte hash = 0;
      int hash1 = hash + (this.id != null?this.id.hashCode():0);
      return hash1;
   }

   public boolean equals(Object object) {
      if(!(object instanceof RpcWpBot)) {
         return false;
      } else {
         RpcWpBot other = (RpcWpBot)object;
         return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
      }
   }

   public String toString() {
      return "xmlrpc.ddos.sql.RpcWpBot[ id=" + this.id + " ]";
   }
}
