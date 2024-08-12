package blackjack.Split;
import external.*;
import ddt.lang.*;
import res.*;
import blackjack.*;import java.io.IOException;import java.io.ObjectInputStream;import java.io.ObjectOutputStream;import java.io.Serializable;
public class
 SplitDomain extends  BlackjackDomain {
public SplitDomain()
{
}
 public Object clone() throws CloneNotSupportedException { return super.clone(); }



 private void writeObject(ObjectOutputStream out) throws IOException {  
 try {
   out.defaultWriteObject();
 } catch (IOException e) {
   e.printStackTrace();
 }
}
private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException { 
     in.defaultReadObject();
}

protected void finalize() {
try {
super.finalize();
} catch(Throwable e) {
// do nothing
}
}

}
/* IntermediateInfo:SplitResource
rO0ABXNyABNqYXZhLnV0aWwuSGFzaHRhYmxlE7sPJSFK5LgDAAJGAApsb2FkRmFjdG9ySQAJdGhyZXNob2xkeHA/QAAAAAAACHcIAAAACwAAAAB4

*/


/* IntermediateInfo:pureResources
rO0ABXNyABNqYXZhLnV0aWwuSGFzaHRhYmxlE7sPJSFK5LgDAAJGAApsb2FkRmFjdG9ySQAJdGhyZXNob2xkeHA/QAAAAAAACHcIAAAACwAAAAB4

*/


/* IntermediateInfo:pureOrders
rO0ABXNyABBqYXZhLnV0aWwuVmVjdG9y2Zd9W4A7rwEDAANJABFjYXBhY2l0eUluY3JlbWVudEkADGVsZW1lbnRDb3VudFsAC2VsZW1lbnREYXRhdAATW0xqYXZhL2xhbmcvT2JqZWN0O3hwAAAAAAAAAAB1cgATW0xqYXZhLmxhbmcuT2JqZWN0O5DOWJ8QcylsAgAAeHAAAAAAeA==

*/


/* IntermediateInfo:DefinedVariables
rO0ABXNyABNqYXZhLnV0aWwuSGFzaHRhYmxlE7sPJSFK5LgDAAJGAApsb2FkRmFjdG9ySQAJdGhyZXNob2xkeHA/QAAAAAAACHcIAAAACwAAAAB4

*/


/* IntermediateInfo:domainInitiationStatement
rO0ABXNyABNqYXZhLnV0aWwuSGFzaHRhYmxlE7sPJSFK5LgDAAJGAApsb2FkRmFjdG9ySQAJdGhyZXNob2xkeHA/QAAAAAAACHcIAAAACwAAAAB4

*/

