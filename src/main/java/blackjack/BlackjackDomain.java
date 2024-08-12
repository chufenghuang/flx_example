package blackjack;
import external.*;
import ddt.lang.*;
import res.*;import java.io.IOException;import java.io.ObjectInputStream;import java.io.ObjectOutputStream;import java.io.Serializable;
public class
 BlackjackDomain extends FeaturePackage {

 public static DTenum state ;public BlackjackDomain()
{
 state = State . INIT ;}
 public Object clone() throws CloneNotSupportedException { return super.clone(); }



 public static Player player;
 public static Dealer dealer;
 public static Deck deck = new Deck(52);
 private void writeObject(ObjectOutputStream out) throws IOException {  
 try {
   out.defaultWriteObject();
out.writeObject( deck);
out.writeObject( dealer);
out.writeObject( player);
 } catch (IOException e) {
   e.printStackTrace();
 }
}
private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException { 
     in.defaultReadObject();
this.deck = (  Deck ) in.readObject(); 
this.dealer = (  Dealer ) in.readObject(); 
this.player = (  Player ) in.readObject(); 
}

protected void finalize() {
try {
super.finalize();
} catch(Throwable e) {
// do nothing
}
}

}
/* IntermediateInfo:BlackjackResource
rO0ABXNyABNqYXZhLnV0aWwuSGFzaHRhYmxlE7sPJSFK5LgDAAJGAApsb2FkRmFjdG9ySQAJdGhy
ZXNob2xkeHA/QAAAAAAACHcIAAAACwAAAAB4

*/


/* IntermediateInfo:BlackjackPath
rO0ABXQACGZpbGVQYXRo

*/


/* IntermediateInfo:pureResources
rO0ABXNyABNqYXZhLnV0aWwuSGFzaHRhYmxlE7sPJSFK5LgDAAJGAApsb2FkRmFjdG9ySQAJdGhy
ZXNob2xkeHA/QAAAAAAACHcIAAAACwAAAAN0AARkZWNrdAAHICBEZWNrIHQABmRlYWxlcnQACSAg
RGVhbGVyIHQABnBsYXllcnQACSAgUGxheWVyIHg=

*/


/* IntermediateInfo:pureOrders
rO0ABXNyABBqYXZhLnV0aWwuVmVjdG9y2Zd9W4A7rwEDAANJABFjYXBhY2l0eUluY3JlbWVudEkA
DGVsZW1lbnRDb3VudFsAC2VsZW1lbnREYXRhdAATW0xqYXZhL2xhbmcvT2JqZWN0O3hwAAAAAAAA
AAJ1cgATW0xqYXZhLmxhbmcuT2JqZWN0O5DOWJ8QcylsAgAAeHAAAAACdAAGcGxheWVydAAGZGVh
bGVyeA==

*/


/* IntermediateInfo:DefinedVariables
rO0ABXNyABNqYXZhLnV0aWwuSGFzaHRhYmxlE7sPJSFK5LgDAAJGAApsb2FkRmFjdG9ySQAJdGhy
ZXNob2xkeHA/QAAAAAAACHcIAAAACwAAAAF0ABVCbGFja2phY2tEb21haW4uc3RhdGV0AAkgIERU
ZW51bSB4

*/


/* IntermediateInfo:domainInitiationStatement
rO0ABXNyABNqYXZhLnV0aWwuSGFzaHRhYmxlE7sPJSFK5LgDAAJGAApsb2FkRmFjdG9ySQAJdGhy
ZXNob2xkeHA/QAAAAAAACHcIAAAACwAAAAF0ABVCbGFja2phY2tEb21haW4uc3RhdGV0ABAgPSBT
dGF0ZSAgLiBJTklUeA==

*/

