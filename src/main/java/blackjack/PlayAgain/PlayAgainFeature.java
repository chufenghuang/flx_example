package blackjack.PlayAgain;
import res.*;
import external.*;
import java.util.*;
import blackjack.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;/**

 PlayAgain**/

import java.io.Serializable;
import rmi.RMIService;
import java.lang.Cloneable;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class PlayAgainFeature
 extends PlayAgainDomain implements Serializable 
{

BasicBJFeature _basicbjfeature;
private boolean activated = true;
Map<String, Boolean> previousState = new HashMap<String, Boolean>();
public EventBuffer<FL_EVENT_STEM> sharedBuffer;

public EventBuffer<FL_EVENT_STEM> getSharedBuffer(){
return this.sharedBuffer;
}
public void activate() { }
public void deactivate() { }
public boolean isActivated() { return activated; }
public FL_EVENT_STEM when_event_variable;

public PlayAgainFeature(  Player  player ,   Dealer  dealer) {
super();
PlayAgainDomain.dealer = dealer;
PlayAgainDomain.player = player;
PlayAgainDomain.deck = deck;
{Map<String, Boolean>currentState= new HashMap<String, Boolean>(previousState);
currentState.put("blackjackdomain.state.equalstoconstant(state.play)",BlackjackDomain.state.equalsToConstant(State.PLAY));
currentState.put("blackjackdomain.state.equalstoconstant(state.deal_card)",BlackjackDomain.state.equalsToConstant(State.DEAL_CARD));
currentState.put("blackjackdomain.state.equalstoconstant(state.end)",BlackjackDomain.state.equalsToConstant(State.END));
currentState.put("blackjackdomain.state.equalstoconstant(state.judge)",BlackjackDomain.state.equalsToConstant(State.JUDGE));
previousState = new HashMap<String, Boolean>(currentState);}
sharedBuffer = new EventBuffer<FL_EVENT_STEM>();
this._basicbjfeature = new BasicBJFeature();
this._basicbjfeature.sharedBuffer = sharedBuffer;
ExecutorService threadExecuter = Executors.newCachedThreadPool();
Consumer consumer = new Consumer(sharedBuffer);
threadExecuter.execute(consumer);
}
class Consumer implements Runnable{
private EventBuffer<FL_EVENT_STEM> buffer;
public Consumer(EventBuffer<FL_EVENT_STEM> buffer){
this.buffer = buffer;
}
public void run() {
while(true){
try{
PlayAgainFeature.this.XEvent((FL_EVENT_STEM)buffer.remove());
}catch(Exception ex){
ex.printStackTrace();
}//end of catch
}//end of while
}//end of run
}//end of Consumer Class
public  PlayAgainFeature(BasicBJFeature _basicbjfeature)
 {
super();
this._basicbjfeature = _basicbjfeature;
}
/***********************************/

 ;

 
         
               
     
        
          
      
        
   
         
      
 
 /**********************************/
public synchronized void playAgain( FL_EVENT_STEM  e)
 throws java.io.IOException{
try { 
if (activated)
{
System . out . println ( "Play again?(y/n)" ) ;
BufferedReader r = new BufferedReader ( new InputStreamReader ( System . in ) ) ;
String res = "" ;
try{ //for resume readLine & 
 res = r . readLine ( ) ;
}
catch( java.io.IOException ex){
if(_playagain_playagain_readline_java_io_ioexception!=null)_playagain_playagain_readline_java_io_ioexception.handle(ex);else throw ex; 
} // automacitally created for resume 
 if ( res . equals ( "y" ) ) {
state = State . INIT ;
sendEvent ( new Init ( ) ) ;
}
else {
System . out . println ( "Game Over!!!" ) ;
state = State . END ;
}
}

} 
finally {}
}


public void sendEvent( FL_EVENT_STEM e ) {
	sharedBuffer.add(e);
}
public synchronized void XEvent(final FL_EVENT_STEM e )
{
this.when_event_variable = e;
this._basicbjfeature.when_event_variable = e;
{Map<String, Boolean>currentState= new HashMap<String, Boolean>(previousState);
currentState.put("blackjackdomain.state.equalstoconstant(state.play)",BlackjackDomain.state.equalsToConstant(State.PLAY));
currentState.put("blackjackdomain.state.equalstoconstant(state.deal_card)",BlackjackDomain.state.equalsToConstant(State.DEAL_CARD));
currentState.put("blackjackdomain.state.equalstoconstant(state.end)",BlackjackDomain.state.equalsToConstant(State.END));
currentState.put("blackjackdomain.state.equalstoconstant(state.judge)",BlackjackDomain.state.equalsToConstant(State.JUDGE));
previousState = new HashMap<String, Boolean>(currentState);}
		if ( e instanceof Hit) {
boolean _basicbjfeaturehitAtPlaynotskip = true;
if (_basicbjfeaturehitAtPlaynotskip == true &&(BlackjackDomain.state.equalsToConstant(State.PLAY))) {
 if((BlackjackDomain.state.equalsToConstant(State.PLAY))) _basicbjfeature.hitAtPlay((Hit) e);

}			}
		else if ( e instanceof Stand) {
boolean _basicbjfeaturestandAtPlaynotskip = true;
if (_basicbjfeaturestandAtPlaynotskip == true &&(BlackjackDomain.state.equalsToConstant(State.PLAY))) {
 if((BlackjackDomain.state.equalsToConstant(State.PLAY))) _basicbjfeature.standAtPlay((Stand) e);

}			}
		else if ( e instanceof Init) {
boolean _basicbjfeatureinitializenotskip = true;
if (_basicbjfeatureinitializenotskip == true &&(BlackjackDomain.state.equalsToConstant(State.INIT))) {
 if((BlackjackDomain.state.equalsToConstant(State.INIT))) _basicbjfeature.initialize((Init) e);

}			}
else{/* no event matched*/}
{Map<String, Boolean>currentState= new HashMap<String, Boolean>(previousState);
currentState.put("blackjackdomain.state.equalstoconstant(state.play)",BlackjackDomain.state.equalsToConstant(State.PLAY));
currentState.put("blackjackdomain.state.equalstoconstant(state.deal_card)",BlackjackDomain.state.equalsToConstant(State.DEAL_CARD));
currentState.put("blackjackdomain.state.equalstoconstant(state.end)",BlackjackDomain.state.equalsToConstant(State.END));
currentState.put("blackjackdomain.state.equalstoconstant(state.judge)",BlackjackDomain.state.equalsToConstant(State.JUDGE));
if(currentState.equals(previousState))return;}
boolean possibleEnter = true; boolean possibleLeave = true;
Map<String, Boolean> temp;
while(true){
boolean noLeaveEvent = true;
boolean noEnterEvent = true;
temp = new HashMap<String, Boolean>(previousState);
temp.put("blackjackdomain.state.equalstoconstant(state.end)",BlackjackDomain.state.equalsToConstant(State.END));
temp.put("blackjackdomain.state.equalstoconstant(state.judge)",BlackjackDomain.state.equalsToConstant(State.JUDGE));
temp.put("blackjackdomain.state.equalstoconstant(state.play)",BlackjackDomain.state.equalsToConstant(State.PLAY));
temp.put("blackjackdomain.state.equalstoconstant(state.deal_card)",BlackjackDomain.state.equalsToConstant(State.DEAL_CARD));
if(possibleLeave){
boolean _basicbjfeaturedealCardToDealernotskip = true;
boolean _basicbjfeatureleaveAtPlaynotskip = true;
if (_basicbjfeaturedealCardToDealernotskip == true && !(BlackjackDomain.state.equalsToConstant(State.DEAL_CARD)) && previousState.get("blackjackdomain.state.equalstoconstant(state.deal_card)")) {
noLeaveEvent = false;
 if(!(BlackjackDomain.state.equalsToConstant(State.DEAL_CARD))) _basicbjfeature.dealCardToDealer(e);

_basicbjfeatureleaveAtPlaynotskip = false;
}if (_basicbjfeatureleaveAtPlaynotskip == true && !(BlackjackDomain.state.equalsToConstant(State.PLAY)) && previousState.get("blackjackdomain.state.equalstoconstant(state.play)")) {
noLeaveEvent = false;
 if(!(BlackjackDomain.state.equalsToConstant(State.PLAY))) _basicbjfeature.leaveAtPlay(e);

}}
if(!noLeaveEvent){
Map<String, Boolean>temp2 = new HashMap<String, Boolean>(previousState);
temp2.put("blackjackdomain.state.equalstoconstant(state.end)",BlackjackDomain.state.equalsToConstant(State.END));
temp2.put("blackjackdomain.state.equalstoconstant(state.judge)",BlackjackDomain.state.equalsToConstant(State.JUDGE));
temp2.put("blackjackdomain.state.equalstoconstant(state.deal_card)",BlackjackDomain.state.equalsToConstant(State.DEAL_CARD));
temp2.put("blackjackdomain.state.equalstoconstant(state.play)",BlackjackDomain.state.equalsToConstant(State.PLAY));
if (!temp.equals(temp2)) {previousState = temp;possibleEnter = true;possibleLeave = true; }else{ possibleLeave = false;}
}
temp = new HashMap<String, Boolean>(previousState);
temp.put("blackjackdomain.state.equalstoconstant(state.end)",BlackjackDomain.state.equalsToConstant(State.END));
temp.put("blackjackdomain.state.equalstoconstant(state.judge)",BlackjackDomain.state.equalsToConstant(State.JUDGE));
temp.put("blackjackdomain.state.equalstoconstant(state.play)",BlackjackDomain.state.equalsToConstant(State.PLAY));
temp.put("blackjackdomain.state.equalstoconstant(state.deal_card)",BlackjackDomain.state.equalsToConstant(State.DEAL_CARD));
if(possibleEnter){
boolean thisplayAgainnotskip = true;
boolean _basicbjfeatureenterJudgenotskip = true;
boolean _basicbjfeatureenterPlaynotskip = true;
boolean _basicbjfeaturedealCardToPlayernotskip = true;
boolean _basicbjfeatureGameOvernotskip = true;
if (thisplayAgainnotskip == true &&(BlackjackDomain.state.equalsToConstant(State.END))&&temp.get("blackjackdomain.state.equalstoconstant(state.end)")&& !previousState.get("blackjackdomain.state.equalstoconstant(state.end)")) {
noEnterEvent = false;
try {// for default
			this._playagain_playagain_readline_java_io_ioexception = null; if((BlackjackDomain.state.equalsToConstant(State.END))) this.playAgain(e);
			}//for default hander
catch(java.io.IOException exception)
{
DefaultExceptionHandler defaultExceptionHandler=new DefaultExceptionHandler();
defaultExceptionHandler.setFeaturePackageName("PlayAgain");
defaultExceptionHandler.setFeatureName("PlayAgain");
defaultExceptionHandler.setProgramUnitName("playAgain");
defaultExceptionHandler.setEventName("enter");
defaultExceptionHandler.setExceptionName("java.io.IOException");
defaultExceptionHandler.printException();
}

_basicbjfeatureenterJudgenotskip = false;
_basicbjfeatureenterPlaynotskip = false;
_basicbjfeaturedealCardToPlayernotskip = false;
_basicbjfeatureGameOvernotskip = false;
}if (_basicbjfeatureenterJudgenotskip == true &&(BlackjackDomain.state.equalsToConstant(State.JUDGE))&&temp.get("blackjackdomain.state.equalstoconstant(state.judge)")&& !previousState.get("blackjackdomain.state.equalstoconstant(state.judge)")) {
noEnterEvent = false;
 if((BlackjackDomain.state.equalsToConstant(State.JUDGE))) _basicbjfeature.enterJudge(e);

_basicbjfeatureenterPlaynotskip = false;
_basicbjfeaturedealCardToPlayernotskip = false;
_basicbjfeatureGameOvernotskip = false;
}if (_basicbjfeatureenterPlaynotskip == true &&(BlackjackDomain.state.equalsToConstant(State.PLAY))&&temp.get("blackjackdomain.state.equalstoconstant(state.play)")&& !previousState.get("blackjackdomain.state.equalstoconstant(state.play)")) {
noEnterEvent = false;
 if((BlackjackDomain.state.equalsToConstant(State.PLAY))) _basicbjfeature.enterPlay(e);

_basicbjfeaturedealCardToPlayernotskip = false;
_basicbjfeatureGameOvernotskip = false;
}if (_basicbjfeaturedealCardToPlayernotskip == true &&(BlackjackDomain.state.equalsToConstant(State.DEAL_CARD))&&temp.get("blackjackdomain.state.equalstoconstant(state.deal_card)")&& !previousState.get("blackjackdomain.state.equalstoconstant(state.deal_card)")) {
noEnterEvent = false;
 if((BlackjackDomain.state.equalsToConstant(State.DEAL_CARD))) _basicbjfeature.dealCardToPlayer(e);

_basicbjfeatureGameOvernotskip = false;
}if (_basicbjfeatureGameOvernotskip == true &&(BlackjackDomain.state.equalsToConstant(State.END))&&temp.get("blackjackdomain.state.equalstoconstant(state.end)")&& !previousState.get("blackjackdomain.state.equalstoconstant(state.end)")) {
noEnterEvent = false;
 if((BlackjackDomain.state.equalsToConstant(State.END))) _basicbjfeature.GameOver(e);

}}
if(!noEnterEvent){
Map<String, Boolean>temp2 = new HashMap<String, Boolean>(previousState);
temp2.put("blackjackdomain.state.equalstoconstant(state.end)",BlackjackDomain.state.equalsToConstant(State.END));
temp2.put("blackjackdomain.state.equalstoconstant(state.judge)",BlackjackDomain.state.equalsToConstant(State.JUDGE));
temp2.put("blackjackdomain.state.equalstoconstant(state.deal_card)",BlackjackDomain.state.equalsToConstant(State.DEAL_CARD));
temp2.put("blackjackdomain.state.equalstoconstant(state.play)",BlackjackDomain.state.equalsToConstant(State.PLAY));
previousState = temp;if (!temp.equals(temp2)) {possibleEnter = true;possibleLeave = true; }else{ possibleEnter = false;}
}
if(noLeaveEvent && noEnterEvent) break;
}
}

public Object clone() throws CloneNotSupportedException {
return super.clone();
}

public IResumeHandler _playagain_playagain_readline_java_io_ioexception;

protected void finalize() {
try {
super.finalize();
} catch(Throwable e) {
// do nothing
}
}

}
/* IntermediateInfo:FeatureInteractionTable
rO0ABXNyACZjb3JlLnRhYmxlLkZlYXR1cmVJbnRlcmFjdGlvblRhYmxlSW1wbKRW9OnaNkzmAgAF
WgAQaXNGZWF0dXJlTmFtZVNldEwAEmRlZmF1bHRQcmVSZWxhdGlvbnQAKkxjb3JlL2NvbXBpbGVy
ZXNvdXJjZXMvUHJlY2VkZW5jZVJlbGF0aW9uO0wAB2RvbWFpbnN0AA9MamF2YS91dGlsL1NldDtM
AApldmVudE5vZGVzdAAoTGNvcmUvY29tcGlsZXJlc291cmNlcy9FdmVudE5vZGVNYXBJbXBsO0wA
C2ZlYXR1cmVOYW1ldAASTGphdmEvbGFuZy9TdHJpbmc7eHABc3IAKWNvcmUuY29kZWdlbmVyYXRv
ci5QcmVjZWRlbmNlUmVsYXRpb25JbXBspX7kJRXyFGoCAAFMAAlwcmVPcmRlcnN0ABNMamF2YS91
dGlsL0hhc2hTZXQ7eHIAJmNvcmUuY29kZWdlbmVyYXRvci5QYXJ0aWFsT3JkZXJpbmdUcmVl2diA
5Z4HBGACAAFMAAVub2Rlc3QAFUxqYXZhL3V0aWwvSGFzaHRhYmxlO3hwc3IAE2phdmEudXRpbC5I
YXNodGFibGUTuw8lIUrkuAMAAkYACmxvYWRGYWN0b3JJAAl0aHJlc2hvbGR4cD9AAAAAAAAIdwgA
AAALAAAAAnQAB0Jhc2ljQkpzcgAXY29yZS5jb2RlZ2VuZXJhdG9yLk5vZGU57IiELEUMtAIABUkA
FGRpcmVjdFByZWRlY2Vzc29yTnVtWgAKd2FzVmlzaXRlZEwAD2RpcmVjdFN1Y2NOb2Rlc3EAfgAJ
TAAFbGFiZWxxAH4ABEwAC3BhcmVudE5vZGVzcQB+AAd4cAAAAAEAc3EAfgALP0AAAAAAAAh3CAAA
AAsAAAAAeHEAfgANc3IAEWphdmEudXRpbC5IYXNoU2V0ukSFlZa4tzQDAAB4cHcMAAAAED9AAAAA
AAABc3EAfgAOAAAAAABzcQB+AAs/QAAAAAAACHcIAAAACwAAAAFxAH4AD3QAEnByaW9yaXR5UHJl
Y2VkZW5jZXh0AAlQbGF5QWdhaW5zcQB+ABF3DAAAABA/QAAAAAAAAHh4cQB+ABZxAH4AE3hzcQB+
ABF3DAAAABA/QAAAAAAAAXNyABNqYXZhLnV0aWwuQXJyYXlMaXN0eIHSHZnHYZ0DAAFJAARzaXpl
eHAAAAACdwQAAAACcQB+ABZxAH4ADXh4c3IAF2phdmEudXRpbC5MaW5rZWRIYXNoU2V02GzXWpXd
Kh4CAAB4cQB+ABF3DAAAABA/QAAAAAAAAXQACVBsYXlBZ2FpbnhzcgAmY29yZS5jb21waWxlcmVz
b3VyY2VzLkV2ZW50Tm9kZU1hcEltcGxoO63QT4+YggIAAUwAA21hcHQAD0xqYXZhL3V0aWwvTWFw
O3hwc3EAfgALP0AAAAAAAAh3CAAAAAsAAAABdAAFZW50ZXJzcgAqY29yZS5jb21waWxlcmVzb3Vy
Y2VzLlF1YWxpZmllck5vZGVNYXBJbXBs6vPcfcurpLUCAAFMAANtYXBxAH4AH3hwc3EAfgALP0AA
AAAAAAh3CAAAAAsAAAABdAADYWxsc3IAKmNvcmUuY29tcGlsZXJlc291cmNlcy5Db25kaXRpb25O
b2RlTWFwSW1wbHdsFiq+55RpAgABTAADbWFwdAAoTGNvcmUvY29tcGlsZXJlc291cmNlcy9PcmRl
cmVkSGFzaHRhYmxlO3hwc3IAJmNvcmUuY29tcGlsZXJlc291cmNlcy5PcmRlcmVkSGFzaHRhYmxl
eGSg3tJkITACAAFMAAxrZXlPcmRlckxpc3R0ABZMamF2YS91dGlsL0xpbmtlZExpc3Q7eHEAfgAL
P0AAAAAAAAh3CAAAAAsAAAABdAA0QmxhY2tqYWNrRG9tYWluLnN0YXRlLmVxdWFsc1RvQ29uc3Rh
bnQoU3RhdGUgIC4gRU5EKXNyACVjb3JlLmNvbXBpbGVyZXNvdXJjZXMuRml0U3ViZW50cnlUcmVl
Y5X89AsBiasCAANMAAhjaGlsZHJlbnQAEExqYXZhL3V0aWwvTGlzdDtMABFkZWZhdWx0UHJlY2Vk
ZW5jZXEAfgABTAADdmFsdAAjTGNvcmUvY29tcGlsZXJlc291cmNlcy9GaXRTdWJlbnRyeTt4cHNy
ABRqYXZhLnV0aWwuTGlua2VkTGlzdAwpU11KYIgiAwAAeHB3BAAAAAFzcQB+AC5zcQB+ADJ3BAAA
AAB4c3EAfgAGc3EAfgALP0AAAAAAAAh3CAAAAAsAAAAAeHNxAH4AEXcMAAAAED9AAAAAAAAAeHNy
ACdjb3JlLmNvbXBpbGVyZXNvdXJjZXMuRml0UHVTdWJlbnRyeUltcGzJ6iM7PuYTmgIAAUwAA21h
cHEAfgAfeHIAJWNvcmUuY29tcGlsZXJlc291cmNlcy5GaXRTdWJlbnRyeUltcGzqOTCcd/Uh5wIA
B0kAD2NvbnRhaW5XaGVuRWxzZUwAEGZlYXR1cmVOYW1lRW50cnlxAH4ABEwAC3ByZVJlbGF0aW9u
cQB+AAFMABBwcm9ncmFtQm9keUVudHJ5cQB+AARMABBwcm9ncmFtQ29uZGl0aW9ucQB+AARMABRw
cm9ncmFtVW5pdE5hbWVFbnRyeXEAfgAETAANd2hlbkNvbmRpdGlvbnQAH0xjb3JlL2xhbmcvQ29u
ZGl0aW9uRXhwcmVzc2lvbjt4cP////9xAH4AHXNxAH4ABnNxAH4ACz9AAAAAAAAIdwgAAAALAAAA
AHhzcQB+ABF3DAAAABA/QAAAAAAAAHh0AXcgICBTeXN0ZW0gIC4gb3V0ICAuIHByaW50bG4gKCJQ
bGF5IGFnYWluPyh5L24pIikgIDsgICAgIEJ1ZmZlcmVkUmVhZGVyICByID0gIG5ldyBCdWZmZXJl
ZFJlYWRlciAgICggbmV3IElucHV0U3RyZWFtUmVhZGVyICAgKFN5c3RlbSAgLiBpbikgICkgICAg
OyAgICAgU3RyaW5nICByZXMgPSAiIiAgOyAgIHJlcyAgPSByICAuIHJlYWRMaW5lICgpIDsgaWYg
KCByZXMgIC4gZXF1YWxzICgieSIpICkgeyAgICBzdGF0ZSAgPSBTdGF0ZSAgLiBJTklUIDsgICBz
ZW5kRXZlbnQgKCBuZXcgSW5pdCAgICgpICApICA7IH0gIGVsc2UgeyAgICBTeXN0ZW0gIC4gb3V0
ICAuIHByaW50bG4gKCJHYW1lIE92ZXIhISEiKSAgOyAgIHN0YXRlICA9IFN0YXRlICAuIEVORCA7
IH1xAH4ALXQACXBsYXlBZ2FpbnNyAB1jb3JlLmxhbmcuQ29uZGl0aW9uRXhwcmVzc2lvboiK4hse
vVSUAgAEWgAIbmVnYXRpdmVMAAVjaGlsZHEAfgA7TAAEbmV4dHEAfgA7TAADc3RycQB+AAR4cABw
cHQABHRydWVzcgARamF2YS51dGlsLkhhc2hNYXAFB9rBwxZg0QMAAkYACmxvYWRGYWN0b3JJAAl0
aHJlc2hvbGR4cD9AAAAAAAAAdwgAAAAQAAAAAHh4c3EAfgAGc3EAfgALP0AAAAAAAAh3CAAAAAsA
AAAAeHNxAH4AEXcMAAAAED9AAAAAAAAAeHB4c3EAfgAydwQAAAABcQB+AC14eHhxAH4AHQ==

*/


/* IntermediateInfo:MergedFeatureInteractionTable
rO0ABXNyACZjb3JlLnRhYmxlLkZlYXR1cmVJbnRlcmFjdGlvblRhYmxlSW1wbKRW9OnaNkzmAgAF
WgAQaXNGZWF0dXJlTmFtZVNldEwAEmRlZmF1bHRQcmVSZWxhdGlvbnQAKkxjb3JlL2NvbXBpbGVy
ZXNvdXJjZXMvUHJlY2VkZW5jZVJlbGF0aW9uO0wAB2RvbWFpbnN0AA9MamF2YS91dGlsL1NldDtM
AApldmVudE5vZGVzdAAoTGNvcmUvY29tcGlsZXJlc291cmNlcy9FdmVudE5vZGVNYXBJbXBsO0wA
C2ZlYXR1cmVOYW1ldAASTGphdmEvbGFuZy9TdHJpbmc7eHABc3IAKWNvcmUuY29kZWdlbmVyYXRv
ci5QcmVjZWRlbmNlUmVsYXRpb25JbXBspX7kJRXyFGoCAAFMAAlwcmVPcmRlcnN0ABNMamF2YS91
dGlsL0hhc2hTZXQ7eHIAJmNvcmUuY29kZWdlbmVyYXRvci5QYXJ0aWFsT3JkZXJpbmdUcmVl2diA
5Z4HBGACAAFMAAVub2Rlc3QAFUxqYXZhL3V0aWwvSGFzaHRhYmxlO3hwc3IAE2phdmEudXRpbC5I
YXNodGFibGUTuw8lIUrkuAMAAkYACmxvYWRGYWN0b3JJAAl0aHJlc2hvbGR4cD9AAAAAAAAIdwgA
AAALAAAAAnQAB0Jhc2ljQkpzcgAXY29yZS5jb2RlZ2VuZXJhdG9yLk5vZGU57IiELEUMtAIABUkA
FGRpcmVjdFByZWRlY2Vzc29yTnVtWgAKd2FzVmlzaXRlZEwAD2RpcmVjdFN1Y2NOb2Rlc3EAfgAJ
TAAFbGFiZWxxAH4ABEwAC3BhcmVudE5vZGVzcQB+AAd4cAAAAAEAc3EAfgALP0AAAAAAAAh3CAAA
AAsAAAAAeHEAfgANc3IAEWphdmEudXRpbC5IYXNoU2V0ukSFlZa4tzQDAAB4cHcMAAAAED9AAAAA
AAABc3EAfgAOAAAAAABzcQB+AAs/QAAAAAAACHcIAAAACwAAAAFxAH4AD3QAEnByaW9yaXR5UHJl
Y2VkZW5jZXh0AAlQbGF5QWdhaW5zcQB+ABF3DAAAABA/QAAAAAAAAHh4cQB+ABZxAH4AE3hzcQB+
ABF3DAAAABA/QAAAAAAAAXNyABNqYXZhLnV0aWwuQXJyYXlMaXN0eIHSHZnHYZ0DAAFJAARzaXpl
eHAAAAACdwQAAAACcQB+ABZxAH4ADXh4c3IAF2phdmEudXRpbC5MaW5rZWRIYXNoU2V02GzXWpXd
Kh4CAAB4cQB+ABF3DAAAABA/QAAAAAAAAXQACVBsYXlBZ2FpbnhzcgAmY29yZS5jb21waWxlcmVz
b3VyY2VzLkV2ZW50Tm9kZU1hcEltcGxoO63QT4+YggIAAUwAA21hcHQAD0xqYXZhL3V0aWwvTWFw
O3hwc3EAfgALP0AAAAAAAAh3CAAAAAsAAAAFdAANYmxhY2tqYWNrLkhpdHNyACpjb3JlLmNvbXBp
bGVyZXNvdXJjZXMuUXVhbGlmaWVyTm9kZU1hcEltcGzq89x9y6uktQIAAUwAA21hcHEAfgAfeHBz
cQB+AAs/QAAAAAAACHcIAAAACwAAAAF0AANhbGxzcgAqY29yZS5jb21waWxlcmVzb3VyY2VzLkNv
bmRpdGlvbk5vZGVNYXBJbXBsd2wWKr7nlGkCAAFMAANtYXB0AChMY29yZS9jb21waWxlcmVzb3Vy
Y2VzL09yZGVyZWRIYXNodGFibGU7eHBzcgAmY29yZS5jb21waWxlcmVzb3VyY2VzLk9yZGVyZWRI
YXNodGFibGV4ZKDe0mQhMAIAAUwADGtleU9yZGVyTGlzdHQAFkxqYXZhL3V0aWwvTGlua2VkTGlz
dDt4cQB+AAs/QAAAAAAACHcIAAAACwAAAAF0AD1CYXNpY0JKLkJsYWNramFja0RvbWFpbi5zdGF0
ZS5lcXVhbHNUb0NvbnN0YW50KFN0YXRlICAuIFBMQVkpc3IAJWNvcmUuY29tcGlsZXJlc291cmNl
cy5GaXRTdWJlbnRyeVRyZWVjlfz0CwGJqwIAA0wACGNoaWxkcmVudAAQTGphdmEvdXRpbC9MaXN0
O0wAEWRlZmF1bHRQcmVjZWRlbmNlcQB+AAFMAAN2YWx0ACNMY29yZS9jb21waWxlcmVzb3VyY2Vz
L0ZpdFN1YmVudHJ5O3hwc3IAFGphdmEudXRpbC5MaW5rZWRMaXN0DClTXUpgiCIDAAB4cHcEAAAA
AXNxAH4ALnNxAH4AMncEAAAAAHhzcQB+AAZzcQB+AAs/QAAAAAAAAncIAAAAAwAAAAB4c3EAfgAR
dwwAAAABP0AAAAAAAAB4c3IAJ2NvcmUuY29tcGlsZXJlc291cmNlcy5GaXRQdVN1YmVudHJ5SW1w
bMnqIzs+5hOaAgABTAADbWFwcQB+AB94cgAlY29yZS5jb21waWxlcmVzb3VyY2VzLkZpdFN1YmVu
dHJ5SW1wbOo5MJx39SHnAgAHSQAPY29udGFpbldoZW5FbHNlTAAQZmVhdHVyZU5hbWVFbnRyeXEA
fgAETAALcHJlUmVsYXRpb25xAH4AAUwAEHByb2dyYW1Cb2R5RW50cnlxAH4ABEwAEHByb2dyYW1D
b25kaXRpb25xAH4ABEwAFHByb2dyYW1Vbml0TmFtZUVudHJ5cQB+AARMAA13aGVuQ29uZGl0aW9u
dAAfTGNvcmUvbGFuZy9Db25kaXRpb25FeHByZXNzaW9uO3hw/////3QAB0Jhc2ljQkpzcQB+AAZz
cQB+AAs/QAAAAAAAAncIAAAAAwAAAAB4c3EAfgARdwwAAAABP0AAAAAAAAB4dACpICAgcGxheWVy
ICAuIGRlbGl2ZXJDYXJkIChkZWNrICAuIGRlYWxDYXJkRmFjZVVwICgpKSAgOyBpZiAoIHBsYXll
ciAgLiBnZXRDYXJkc1N1bSAoKSAgPCAyMSApIHsgICAgcGxheWVyICAuIGFza0hpdE9yU3RhbmQg
KCkgIDsgfSAgZWxzZSB7ICAgIHN0YXRlICA9IFN0YXRlICAuIEpVREdFIDsgfXQANUJsYWNramFj
a0RvbWFpbi5zdGF0ZS5lcXVhbHNUb0NvbnN0YW50KFN0YXRlICAuIFBMQVkpdAAJaGl0QXRQbGF5
c3IAHWNvcmUubGFuZy5Db25kaXRpb25FeHByZXNzaW9uiIriGx69VJQCAARaAAhuZWdhdGl2ZUwA
BWNoaWxkcQB+ADtMAARuZXh0cQB+ADtMAANzdHJxAH4ABHhwAHBwdAAEdHJ1ZXNyABFqYXZhLnV0
aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAB3
CAAAABAAAAAAeHhzcQB+AAZzcQB+AAs/QAAAAAAAAncIAAAAAwAAAAB4c3EAfgARdwwAAAABP0AA
AAAAAAB4cHhzcQB+ADJ3BAAAAAFxAH4ALXh4dAAPYmxhY2tqYWNrLlN0YW5kc3EAfgAjc3EAfgAL
P0AAAAAAAAh3CAAAAAsAAAABcQB+ACZzcQB+ACdzcQB+ACo/QAAAAAAACHcIAAAACwAAAAF0AD1C
YXNpY0JKLkJsYWNramFja0RvbWFpbi5zdGF0ZS5lcXVhbHNUb0NvbnN0YW50KFN0YXRlICAuIFBM
QVkpc3EAfgAuc3EAfgAydwQAAAABc3EAfgAuc3EAfgAydwQAAAAAeHNxAH4ABnNxAH4ACz9AAAAA
AAACdwgAAAADAAAAAHhzcQB+ABF3DAAAAAE/QAAAAAAAAHhzcQB+ADn/////cQB+AD1zcQB+AAZz
cQB+AAs/QAAAAAAAAncIAAAAAwAAAAB4c3EAfgARdwwAAAABP0AAAAAAAAB4dAAcICAgc3RhdGUg
ID0gU3RhdGUgIC4gSlVER0UgO3QANUJsYWNramFja0RvbWFpbi5zdGF0ZS5lcXVhbHNUb0NvbnN0
YW50KFN0YXRlICAuIFBMQVkpdAALc3RhbmRBdFBsYXlzcQB+AEQAcHBxAH4ARnNxAH4ARz9AAAAA
AAAAdwgAAAAQAAAAAHh4c3EAfgAGc3EAfgALP0AAAAAAAAJ3CAAAAAMAAAAAeHNxAH4AEXcMAAAA
AT9AAAAAAAAAeHB4c3EAfgAydwQAAAABcQB+AFJ4eHQABWxlYXZlc3EAfgAjc3EAfgALP0AAAAAA
AAh3CAAAAAsAAAABcQB+ACZzcQB+ACdzcQB+ACo/QAAAAAAACHcIAAAACwAAAAJ0AD1CYXNpY0JK
LkJsYWNramFja0RvbWFpbi5zdGF0ZS5lcXVhbHNUb0NvbnN0YW50KFN0YXRlICAuIFBMQVkpc3EA
fgAuc3EAfgAydwQAAAABc3EAfgAuc3EAfgAydwQAAAAAeHNxAH4ABnNxAH4ACz9AAAAAAAACdwgA
AAADAAAAAHhzcQB+ABF3DAAAAAE/QAAAAAAAAHhzcQB+ADn/////cQB+AD1zcQB+AAZzcQB+AAs/
QAAAAAAAAncIAAAAAwAAAAB4c3EAfgARdwwAAAABP0AAAAAAAAB4dACJICAgZGVhbGVyICAuIG9w
ZW5GYWNlRG93bkNhcmRzICgpICA7IHdoaWxlICggZGVhbGVyICAuIGdldENhcmRzU3VtICgpICA8
IDE2ICkgeyAgICBkZWFsZXIgIC4gZGVsaXZlckNhcmQgKGRlY2sgIC4gZGVhbENhcmRGYWNlVXAg
KCkpICA7IH10ADVCbGFja2phY2tEb21haW4uc3RhdGUuZXF1YWxzVG9Db25zdGFudChTdGF0ZSAg
LiBQTEFZKXQAC2xlYXZlQXRQbGF5c3EAfgBEAHBwcQB+AEZzcQB+AEc/QAAAAAAAAHcIAAAAEAAA
AAB4eHNxAH4ABnNxAH4ACz9AAAAAAAACdwgAAAADAAAAAHhzcQB+ABF3DAAAAAE/QAAAAAAAAHhw
dABCQmFzaWNCSi5CbGFja2phY2tEb21haW4uc3RhdGUuZXF1YWxzVG9Db25zdGFudChTdGF0ZSAg
LiBERUFMX0NBUkQpc3EAfgAuc3EAfgAydwQAAAABc3EAfgAuc3EAfgAydwQAAAAAeHNxAH4ABnNx
AH4ACz9AAAAAAAACdwgAAAADAAAAAHhzcQB+ABF3DAAAAAE/QAAAAAAAAHhzcQB+ADn/////cQB+
AD1zcQB+AAZzcQB+AAs/QAAAAAAAAncIAAAAAwAAAAB4c3EAfgARdwwAAAABP0AAAAAAAAB4dABw
ICAgZGVhbGVyICAuIGRlbGl2ZXJDYXJkIChkZWNrICAuIGRlYWxDYXJkRmFjZVVwICgpKSAgOyAg
IGRlYWxlciAgLiBkZWxpdmVyQ2FyZCAoZGVjayAgLiBkZWFsQ2FyZEZhY2VEb3duICgpKSAgO3QA
OkJsYWNramFja0RvbWFpbi5zdGF0ZS5lcXVhbHNUb0NvbnN0YW50KFN0YXRlICAuIERFQUxfQ0FS
RCl0ABBkZWFsQ2FyZFRvRGVhbGVyc3EAfgBEAHBwcQB+AEZzcQB+AEc/QAAAAAAAAHcIAAAAEAAA
AAB4eHNxAH4ABnNxAH4ACz9AAAAAAAACdwgAAAADAAAAAHhzcQB+ABF3DAAAAAE/QAAAAAAAAHhw
eHNxAH4AMncEAAAAAnEAfgBscQB+AIB4eHQADmJsYWNramFjay5Jbml0c3EAfgAjc3EAfgALP0AA
AAAAAAh3CAAAAAsAAAABcQB+ACZzcQB+ACdzcQB+ACo/QAAAAAAACHcIAAAACwAAAAF0AD1CYXNp
Y0JKLkJsYWNramFja0RvbWFpbi5zdGF0ZS5lcXVhbHNUb0NvbnN0YW50KFN0YXRlICAuIElOSVQp
c3EAfgAuc3EAfgAydwQAAAABc3EAfgAuc3EAfgAydwQAAAAAeHNxAH4ABnNxAH4ACz9AAAAAAAAC
dwgAAAADAAAAAHhzcQB+ABF3DAAAAAE/QAAAAAAAAHhzcQB+ADn/////cQB+AD1zcQB+AAZzcQB+
AAs/QAAAAAAAAncIAAAAAwAAAAB4c3EAfgARdwwAAAABP0AAAAAAAAB4dAB9ICAgcGxheWVyICAu
IHJlc2V0ICgpICA7ICAgZGVhbGVyICAuIHJlc2V0ICgpICA7ICAgU3lzdGVtICAuIG91dCAgLiBw
cmludGxuICgiZ2FtZSBzdGFydCIpICA7ICAgc3RhdGUgID0gU3RhdGUgIC4gREVBTF9DQVJEIDt0
ADVCbGFja2phY2tEb21haW4uc3RhdGUuZXF1YWxzVG9Db25zdGFudChTdGF0ZSAgLiBJTklUKXQA
CmluaXRpYWxpemVzcQB+AEQAcHBxAH4ARnNxAH4ARz9AAAAAAAAAdwgAAAAQAAAAAHh4c3EAfgAG
c3EAfgALP0AAAAAAAAJ3CAAAAAMAAAAAeHNxAH4AEXcMAAAAAT9AAAAAAAAAeHB4c3EAfgAydwQA
AAABcQB+AJp4eHQABWVudGVyc3EAfgAjc3EAfgALP0AAAAAAAAh3CAAAAAsAAAABdAADYWxsc3EA
fgAnc3EAfgAqP0AAAAAAAAh3CAAAAAsAAAAFdAA8QmFzaWNCSi5CbGFja2phY2tEb21haW4uc3Rh
dGUuZXF1YWxzVG9Db25zdGFudChTdGF0ZSAgLiBFTkQpc3EAfgAuc3EAfgAydwQAAAABc3EAfgAu
c3EAfgAydwQAAAAAeHNxAH4ABnNxAH4ACz9AAAAAAAACdwgAAAADAAAAAHhzcQB+ABF3DAAAAAE/
QAAAAAAAAHhzcQB+ADn/////cQB+AD1zcQB+AAZzcQB+AAs/QAAAAAAAAncIAAAAAwAAAAB4c3EA
fgARdwwAAAABP0AAAAAAAAB4dAAeICAgcGxheWVyICAuIHNheUdhbWVPdmVyICgpICA7dAA0Qmxh
Y2tqYWNrRG9tYWluLnN0YXRlLmVxdWFsc1RvQ29uc3RhbnQoU3RhdGUgIC4gRU5EKXQACEdhbWVP
dmVyc3EAfgBEAHBwcQB+AEZzcQB+AEc/QAAAAAAAAHcIAAAAEAAAAAB4eHNxAH4ABnNxAH4ACz9A
AAAAAAACdwgAAAADAAAAAHhzcQB+ABF3DAAAAAE/QAAAAAAAAHhwdAA+QmFzaWNCSi5CbGFja2ph
Y2tEb21haW4uc3RhdGUuZXF1YWxzVG9Db25zdGFudChTdGF0ZSAgLiBKVURHRSlzcQB+AC5zcQB+
ADJ3BAAAAAFzcQB+AC5zcQB+ADJ3BAAAAAB4c3EAfgAGc3EAfgALP0AAAAAAAAJ3CAAAAAMAAAAA
eHNxAH4AEXcMAAAAAT9AAAAAAAAAeHNxAH4AOf////9xAH4APXNxAH4ABnNxAH4ACz9AAAAAAAAC
dwgAAAADAAAAAHhzcQB+ABF3DAAAAAE/QAAAAAAAAHh0ApIgaWYgKCBwbGF5ZXIgIC4gaXNCSiAo
KSApIHsgIGlmICggZGVhbGVyICAuIGlzQkogKCkgKSB7ICAgIHBsYXllciAgLiBoYXNEcmF3biAo
KSAgOyB9ICBlbHNlIHsgICAgcGxheWVyICAuIGhhc1dvbiAoKSAgOyB9IH0gIGVsc2UgaWYgKCBk
ZWFsZXIgIC4gaXNCSiAoKSApIHsgICAgcGxheWVyICAuIGhhc0xvc3QgKCkgIDsgfSAgZWxzZSB7
ICAgIGludCBwU3VtID0gMCAgOyAgIGludCBkU3VtID0gMCAgOyAgIHBTdW0gID0gcGxheWVyICAu
IGdldENhcmRzU3VtICgpIDsgICBkU3VtICA9IGRlYWxlciAgLiBnZXRDYXJkc1N1bSAoKSA7IGlm
ICggcFN1bSAgPiAyMSApIHsgIGlmICggZFN1bSAgPiAyMSApIHsgICAgcGxheWVyICAuIGhhc0Ry
YXduICgpICA7IH0gIGVsc2UgeyAgICBwbGF5ZXIgIC4gaGFzTG9zdCAoKSAgOyB9IH0gIGVsc2Ug
eyAgaWYgKCBwU3VtICA+IGRTdW0gKSB7ICAgIHBsYXllciAgLiBoYXNXb24gKCkgIDsgfSAgZWxz
ZSBpZiAoIHBTdW0gID09IGRTdW0gKSB7ICAgIHBsYXllciAgLiBoYXNEcmF3biAoKSAgOyB9ICBl
bHNlIHsgIGlmICggZFN1bSAgPiAyMSApIHsgICAgcGxheWVyICAuIGhhc1dvbiAoKSAgOyB9ICBl
bHNlIHsgICAgcGxheWVyICAuIGhhc0xvc3QgKCkgIDsgfSB9IH0gfSAgIHN0YXRlICA9IFN0YXRl
ICAuIEVORCA7dAA2QmxhY2tqYWNrRG9tYWluLnN0YXRlLmVxdWFsc1RvQ29uc3RhbnQoU3RhdGUg
IC4gSlVER0UpdAAKZW50ZXJKdWRnZXNxAH4ARABwcHEAfgBGc3EAfgBHP0AAAAAAAAB3CAAAABAA
AAAAeHhzcQB+AAZzcQB+AAs/QAAAAAAAAncIAAAAAwAAAAB4c3EAfgARdwwAAAABP0AAAAAAAAB4
cHQAPUJhc2ljQkouQmxhY2tqYWNrRG9tYWluLnN0YXRlLmVxdWFsc1RvQ29uc3RhbnQoU3RhdGUg
IC4gUExBWSlzcQB+AC5zcQB+ADJ3BAAAAAFzcQB+AC5zcQB+ADJ3BAAAAAB4c3EAfgAGc3EAfgAL
P0AAAAAAAAJ3CAAAAAMAAAAAeHNxAH4AEXcMAAAAAT9AAAAAAAAAeHNxAH4AOf////9xAH4APXNx
AH4ABnNxAH4ACz9AAAAAAAACdwgAAAADAAAAAHhzcQB+ABF3DAAAAAE/QAAAAAAAAHh0ACAgICBw
bGF5ZXIgIC4gYXNrSGl0T3JTdGFuZCAoKSAgO3QANUJsYWNramFja0RvbWFpbi5zdGF0ZS5lcXVh
bHNUb0NvbnN0YW50KFN0YXRlICAuIFBMQVkpdAAJZW50ZXJQbGF5c3EAfgBEAHBwcQB+AEZzcQB+
AEc/QAAAAAAAAHcIAAAAEAAAAAB4eHNxAH4ABnNxAH4ACz9AAAAAAAACdwgAAAADAAAAAHhzcQB+
ABF3DAAAAAE/QAAAAAAAAHhwdABCQmFzaWNCSi5CbGFja2phY2tEb21haW4uc3RhdGUuZXF1YWxz
VG9Db25zdGFudChTdGF0ZSAgLiBERUFMX0NBUkQpc3EAfgAuc3EAfgAydwQAAAABc3EAfgAuc3EA
fgAydwQAAAAAeHNxAH4ABnNxAH4ACz9AAAAAAAACdwgAAAADAAAAAHhzcQB+ABF3DAAAAAE/QAAA
AAAAAHhzcQB+ADn/////cQB+AD1zcQB+AAZzcQB+AAs/QAAAAAAAAncIAAAAAwAAAAB4c3EAfgAR
dwwAAAABP0AAAAAAAAB4dACJICAgcGxheWVyICAuIGRlbGl2ZXJDYXJkIChkZWNrICAuIGRlYWxD
YXJkRmFjZVVwICgpKSAgOyAgIHBsYXllciAgLiBkZWxpdmVyQ2FyZCAoZGVjayAgLiBkZWFsQ2Fy
ZEZhY2VVcCAoKSkgIDsgICBzdGF0ZSAgPSBTdGF0ZSAgLiBQTEFZIDt0ADpCbGFja2phY2tEb21h
aW4uc3RhdGUuZXF1YWxzVG9Db25zdGFudChTdGF0ZSAgLiBERUFMX0NBUkQpdAAQZGVhbENhcmRU
b1BsYXllcnNxAH4ARABwcHEAfgBGc3EAfgBHP0AAAAAAAAB3CAAAABAAAAAAeHhzcQB+AAZzcQB+
AAs/QAAAAAAAAncIAAAAAwAAAAB4c3EAfgARdwwAAAABP0AAAAAAAAB4cHQAPlBsYXlBZ2Fpbi5C
bGFja2phY2tEb21haW4uc3RhdGUuZXF1YWxzVG9Db25zdGFudChTdGF0ZSAgLiBFTkQpc3EAfgAu
c3EAfgAydwQAAAABc3EAfgAuc3EAfgAydwQAAAAAeHNxAH4ABnNxAH4ACz9AAAAAAAAIdwgAAAAL
AAAAAHhzcQB+ABF3DAAAABA/QAAAAAAAAHhzcQB+ADn/////cQB+AB1zcQB+AAZzcQB+AAs/QAAA
AAAACHcIAAAACwAAAAB4c3EAfgARdwwAAAAQP0AAAAAAAAB4dAF3ICAgU3lzdGVtICAuIG91dCAg
LiBwcmludGxuICgiUGxheSBhZ2Fpbj8oeS9uKSIpICA7ICAgICBCdWZmZXJlZFJlYWRlciAgciA9
ICBuZXcgQnVmZmVyZWRSZWFkZXIgICAoIG5ldyBJbnB1dFN0cmVhbVJlYWRlciAgIChTeXN0ZW0g
IC4gaW4pICApICAgIDsgICAgIFN0cmluZyAgcmVzID0gIiIgIDsgICByZXMgID0gciAgLiByZWFk
TGluZSAoKSA7IGlmICggcmVzICAuIGVxdWFscyAoInkiKSApIHsgICAgc3RhdGUgID0gU3RhdGUg
IC4gSU5JVCA7ICAgc2VuZEV2ZW50ICggbmV3IEluaXQgICAoKSAgKSAgOyB9ICBlbHNlIHsgICAg
U3lzdGVtICAuIG91dCAgLiBwcmludGxuICgiR2FtZSBPdmVyISEhIikgIDsgICBzdGF0ZSAgPSBT
dGF0ZSAgLiBFTkQgOyB9dAA0QmxhY2tqYWNrRG9tYWluLnN0YXRlLmVxdWFsc1RvQ29uc3RhbnQo
U3RhdGUgIC4gRU5EKXQACXBsYXlBZ2FpbnNxAH4ARABwcHQABHRydWVzcQB+AEc/QAAAAAAAAHcI
AAAAEAAAAAB4eHNxAH4ABnNxAH4ACz9AAAAAAAAIdwgAAAALAAAAAHhzcQB+ABF3DAAAABA/QAAA
AAAAAHhweHNxAH4AMncEAAAABXEAfgEFcQB+ALVxAH4AyXEAfgDdcQB+APF4eHhxAH4AHQ==

*/


/* IntermediateInfo:domains
rO0ABXNyABdqYXZhLnV0aWwuTGlua2VkSGFzaFNldNhs11qV3SoeAgAAeHIAEWphdmEudXRpbC5I
YXNoU2V0ukSFlZa4tzQDAAB4cHcMAAAAED9AAAAAAAABdAAJUGxheUFnYWlueA==

*/


/* IntermediateInfo:isExecutable
rO0ABXNyABFqYXZhLmxhbmcuQm9vbGVhbs0gcoDVnPruAgABWgAFdmFsdWV4cAE=

*/


/* IntermediateInfo:proceedToPuhandler
rO0ABXNyABNqYXZhLnV0aWwuQXJyYXlMaXN0eIHSHZnHYZ0DAAFJAARzaXpleHAAAAABdwQAAAAB
c3IAJ2NvcmUuZGF0YXN0cnVjdHVyZS5Qcm9jZWVkVG9IYW5kbGVySW5mb/NJGznJidC0AgAJTAAJ
Y29uZGl0aW9udAASTGphdmEvbGFuZy9TdHJpbmc7TAAPZXZlbnRJZGVudGlmaWVycQB+AANMAAll
dmVudFR5cGVxAH4AA0wAD2ZlYXR1cmVJbnN0YW5jZXEAfgADTAALZmVhdHVyZU5hbWVxAH4AA0wA
CXByb2NlZWRUb3EAfgADTAARcHJvY2VlZFRvSW5zdGFuY2VxAH4AA0wABnB1TmFtZXEAfgADTAAH
cHVUb0FkZHEAfgADeHB0ADRCbGFja2phY2tEb21haW4uc3RhdGUuZXF1YWxzVG9Db25zdGFudChT
dGF0ZSAgLiBFTkQpdAABZXQABWVudGVydAARX3BsYXlhZ2FpbmZlYXR1cmV0AAlQbGF5QWdhaW5w
cHQACXBsYXlBZ2FpbnB4

*/


/* IntermediateInfo:anchorFeature
rO0ABXQAB0Jhc2ljQko=

*/

