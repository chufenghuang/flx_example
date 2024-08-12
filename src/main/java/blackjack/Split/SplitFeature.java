package blackjack.Split;
import res.*;
import external.*;
import java.util.*;
import blackjack.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import external.*;
import domainType.*;/**

 Split**/

import java.io.Serializable;
import rmi.RMIService;
import java.lang.Cloneable;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class SplitFeature
 extends SplitDomain implements Serializable 
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

public SplitFeature(SplitPlayer player ,   Dealer  dealer) {
super();
SplitDomain.deck = deck;
SplitDomain.dealer = dealer;
SplitDomain.player = player;
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
SplitFeature.this.XEvent((FL_EVENT_STEM)buffer.remove());
}catch(Exception ex){
ex.printStackTrace();
}//end of catch
}//end of while
}//end of run
}//end of Consumer Class
public  SplitFeature(BasicBJFeature _basicbjfeature)
 {
super();
this._basicbjfeature = _basicbjfeature;
}
/***********************************/

 ;

 
      
 public boolean get_spEnterPlay_0_condition(){
return (((SplitPlayer)player).splittable());
}
/**********************************/
public synchronized void spEnterPlay_0( FL_EVENT_STEM  e)
{
try { 
if (activated)
{
(( SplitPlayer ) player ) . askHitStandOrSplit ( ) ;
}

} 
finally {}
}


 
      
           
         
      
 
  
      
 
 /**********************************/
public synchronized void split(Split e)
{
try { 
if (activated)
{
(( SplitPlayer ) player ) . addHand ( ) ;
(( SplitPlayer ) player ) . deliverCard ( deck . dealCardFaceUp ( ) ) ;
if ( (( SplitPlayer ) player ) . splittable ( ) ) {
(( SplitPlayer ) player ) . askHitStandOrSplit ( ) ;
}
else {
(( SplitPlayer ) player ) . askHitOrStand ( ) ;
}
}

} 
finally {}
}


 
               
      
         
      
      
 
 /**********************************/
public synchronized void spLeavePlay( FL_EVENT_STEM  e)
{
try { 
if (activated)
{
System . out . println ( "Check if the player has more hands:" + (( SplitPlayer ) player ) . hasMoreHands ( ) ) ;
(( SplitPlayer ) player ) . minusHand ( ) ;
if ( (( SplitPlayer ) player ) . hasMoreHands ( ) ) {
(( SplitPlayer ) player ) . getNextHandToPlay ( ) ;
state = State . PLAY ;
}
}

} 
finally {}
}


 
           
 
  
               
      
 
 /**********************************/
public synchronized void splitEnterJudge( FL_EVENT_STEM  e)
{
try { 
if (activated)
{
if ( 0 == (( SplitPlayer ) player ) . getCurrentHand ( ) ) {
}
else {
System . out . println ( "Judge split game hand " + (( SplitPlayer ) player ) . getCurrentHand ( ) ) ;
(( SplitPlayer ) player ) . swapHand ( ) ;
}
}

} 
finally {}
}


 
           
      
 
           
      
 
          
      
 
 /**********************************/
public synchronized void splitLeaveJudge( FL_EVENT_STEM  e)
{
try { 
if (activated)
{
if ( 0 == (( SplitPlayer ) player ) . getCurrentHand ( ) ) {
state = State . END ;
}
else if ( ! (( SplitPlayer ) player ) . isLastHand ( ) ) {
state = State . JUDGE ;
}
else if ( (( SplitPlayer ) player ) . isLastHand ( ) ) {
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
		else if ( e instanceof Split) {
boolean thissplitnotskip = true;
if (thissplitnotskip == true &&(BlackjackDomain.state.equalsToConstant(State.PLAY))) {
 if((BlackjackDomain.state.equalsToConstant(State.PLAY))) this.split((Split) e);

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
boolean thisspLeavePlaynotskip = true;
boolean thissplitLeaveJudgenotskip = true;
boolean _basicbjfeaturedealCardToDealernotskip = true;
boolean _basicbjfeatureleaveAtPlaynotskip = true;
if (thisspLeavePlaynotskip == true && !(BlackjackDomain.state.equalsToConstant(State.PLAY)) && previousState.get("blackjackdomain.state.equalstoconstant(state.play)")) {
noLeaveEvent = false;
 if(!(BlackjackDomain.state.equalsToConstant(State.PLAY))) this.spLeavePlay(e);

thissplitLeaveJudgenotskip = false;
}if (thissplitLeaveJudgenotskip == true && !(BlackjackDomain.state.equalsToConstant(State.JUDGE)) && previousState.get("blackjackdomain.state.equalstoconstant(state.judge)")) {
noLeaveEvent = false;
 if(!(BlackjackDomain.state.equalsToConstant(State.JUDGE))) this.splitLeaveJudge(e);

}if (_basicbjfeaturedealCardToDealernotskip == true && !(BlackjackDomain.state.equalsToConstant(State.DEAL_CARD)) && previousState.get("blackjackdomain.state.equalstoconstant(state.deal_card)")) {
noLeaveEvent = false;
 if(!(BlackjackDomain.state.equalsToConstant(State.DEAL_CARD))) _basicbjfeature.dealCardToDealer(e);

_basicbjfeatureleaveAtPlaynotskip = false;
}if (_basicbjfeatureleaveAtPlaynotskip == true && !(BlackjackDomain.state.equalsToConstant(State.PLAY)) && previousState.get("blackjackdomain.state.equalstoconstant(state.play)")) {
noLeaveEvent = false;
 if(!(BlackjackDomain.state.equalsToConstant(State.PLAY))) _basicbjfeature.leaveAtPlay(e);

}}
if(!noLeaveEvent){
Map<String, Boolean>temp2 = new HashMap<String, Boolean>(previousState);
temp2.put("blackjackdomain.state.equalstoconstant(state.judge)",BlackjackDomain.state.equalsToConstant(State.JUDGE));
temp2.put("blackjackdomain.state.equalstoconstant(state.end)",BlackjackDomain.state.equalsToConstant(State.END));
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
if ((BlackjackDomain.state.equalsToConstant(State.PLAY))&& !previousState.get("blackjackdomain.state.equalstoconstant(state.play)")&& temp.get("blackjackdomain.state.equalstoconstant(state.play)") && (this.get_spEnterPlay_0_condition())) {
boolean thissplitEnterJudgenotskip = true;
boolean thisspEnterPlay_0notskip = true;
boolean thisspEnterPlay_0WhenCondtionResult = this.get_spEnterPlay_0_condition();
boolean _basicbjfeatureenterJudgenotskip = true;
boolean _basicbjfeatureenterPlaynotskip = true;
boolean _basicbjfeaturedealCardToPlayernotskip = true;
boolean _basicbjfeatureGameOvernotskip = true;
if (thissplitEnterJudgenotskip == true &&(BlackjackDomain.state.equalsToConstant(State.JUDGE))&& temp.get("blackjackdomain.state.equalstoconstant(state.judge)")&&!previousState.get("blackjackdomain.state.equalstoconstant(state.judge)")) {
noEnterEvent = false;
 if((BlackjackDomain.state.equalsToConstant(State.JUDGE))) this.splitEnterJudge(e);

thisspEnterPlay_0notskip = false;
_basicbjfeatureenterJudgenotskip = false;
_basicbjfeatureenterPlaynotskip = false;
_basicbjfeaturedealCardToPlayernotskip = false;
_basicbjfeatureGameOvernotskip = false;
}if (thisspEnterPlay_0WhenCondtionResult &&thisspEnterPlay_0notskip == true &&(BlackjackDomain.state.equalsToConstant(State.PLAY))&& temp.get("blackjackdomain.state.equalstoconstant(state.play)")&&!previousState.get("blackjackdomain.state.equalstoconstant(state.play)")) {
noEnterEvent = false;
 if((BlackjackDomain.state.equalsToConstant(State.PLAY))) this.spEnterPlay_0(e);

_basicbjfeatureenterJudgenotskip = false;
_basicbjfeatureenterPlaynotskip = false;
_basicbjfeaturedealCardToPlayernotskip = false;
_basicbjfeatureGameOvernotskip = false;
}if (_basicbjfeatureenterJudgenotskip == true &&(BlackjackDomain.state.equalsToConstant(State.JUDGE))&& temp.get("blackjackdomain.state.equalstoconstant(state.judge)")&&!previousState.get("blackjackdomain.state.equalstoconstant(state.judge)")) {
noEnterEvent = false;
 if((BlackjackDomain.state.equalsToConstant(State.JUDGE))) _basicbjfeature.enterJudge(e);

_basicbjfeatureenterPlaynotskip = false;
_basicbjfeaturedealCardToPlayernotskip = false;
_basicbjfeatureGameOvernotskip = false;
}if (_basicbjfeatureenterPlaynotskip == true &&(BlackjackDomain.state.equalsToConstant(State.PLAY))&& temp.get("blackjackdomain.state.equalstoconstant(state.play)")&&!previousState.get("blackjackdomain.state.equalstoconstant(state.play)")) {
noEnterEvent = false;
 if((BlackjackDomain.state.equalsToConstant(State.PLAY))) _basicbjfeature.enterPlay(e);

_basicbjfeaturedealCardToPlayernotskip = false;
_basicbjfeatureGameOvernotskip = false;
}if (_basicbjfeaturedealCardToPlayernotskip == true &&(BlackjackDomain.state.equalsToConstant(State.DEAL_CARD))&& temp.get("blackjackdomain.state.equalstoconstant(state.deal_card)")&&!previousState.get("blackjackdomain.state.equalstoconstant(state.deal_card)")) {
noEnterEvent = false;
 if((BlackjackDomain.state.equalsToConstant(State.DEAL_CARD))) _basicbjfeature.dealCardToPlayer(e);

_basicbjfeatureGameOvernotskip = false;
}if (_basicbjfeatureGameOvernotskip == true &&(BlackjackDomain.state.equalsToConstant(State.END))&& temp.get("blackjackdomain.state.equalstoconstant(state.end)")&&!previousState.get("blackjackdomain.state.equalstoconstant(state.end)")) {
noEnterEvent = false;
 if((BlackjackDomain.state.equalsToConstant(State.END))) _basicbjfeature.GameOver(e);

}}
else {
boolean thissplitEnterJudgenotskip = true;
boolean thisspEnterPlay_0notskip = true;
boolean _basicbjfeatureenterJudgenotskip = true;
boolean _basicbjfeatureenterPlaynotskip = true;
boolean _basicbjfeaturedealCardToPlayernotskip = true;
boolean _basicbjfeatureGameOvernotskip = true;
if (thissplitEnterJudgenotskip == true &&(BlackjackDomain.state.equalsToConstant(State.JUDGE))&&temp.get("blackjackdomain.state.equalstoconstant(state.judge)")&& !previousState.get("blackjackdomain.state.equalstoconstant(state.judge)")) {
noEnterEvent = false;
 if((BlackjackDomain.state.equalsToConstant(State.JUDGE))) this.splitEnterJudge(e);

thisspEnterPlay_0notskip = false;
}if ((this.get_spEnterPlay_0_condition()) &&thisspEnterPlay_0notskip == true &&(BlackjackDomain.state.equalsToConstant(State.PLAY))&&temp.get("blackjackdomain.state.equalstoconstant(state.play)")&& !previousState.get("blackjackdomain.state.equalstoconstant(state.play)")) {
noEnterEvent = false;
 if((BlackjackDomain.state.equalsToConstant(State.PLAY))) this.spEnterPlay_0(e);

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
}
if(!noEnterEvent){
Map<String, Boolean>temp2 = new HashMap<String, Boolean>(previousState);
temp2.put("blackjackdomain.state.equalstoconstant(state.judge)",BlackjackDomain.state.equalsToConstant(State.JUDGE));
temp2.put("blackjackdomain.state.equalstoconstant(state.end)",BlackjackDomain.state.equalsToConstant(State.END));
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


protected void finalize() {
try {
super.finalize();
} catch(Throwable e) {
// do nothing
}
}

}
/* IntermediateInfo:FeatureInteractionTable
rO0ABXNyACZjb3JlLnRhYmxlLkZlYXR1cmVJbnRlcmFjdGlvblRhYmxlSW1wbKRW9OnaNkzmAgAFWgAQaXNGZWF0dXJlTmFtZVNldEwAEmRlZmF1bHRQcmVSZWxhdGlvbnQAKkxjb3JlL2NvbXBpbGVyZXNvdXJjZXMvUHJlY2VkZW5jZVJlbGF0aW9uO0wAB2RvbWFpbnN0AA9MamF2YS91dGlsL1NldDtMAApldmVudE5vZGVzdAAoTGNvcmUvY29tcGlsZXJlc291cmNlcy9FdmVudE5vZGVNYXBJbXBsO0wAC2ZlYXR1cmVOYW1ldAASTGphdmEvbGFuZy9TdHJpbmc7eHABc3IAKWNvcmUuY29kZWdlbmVyYXRvci5QcmVjZWRlbmNlUmVsYXRpb25JbXBspX7kJRXyFGoCAAFMAAlwcmVPcmRlcnN0ABNMamF2YS91dGlsL0hhc2hTZXQ7eHIAJmNvcmUuY29kZWdlbmVyYXRvci5QYXJ0aWFsT3JkZXJpbmdUcmVl2diA5Z4HBGACAAFMAAVub2Rlc3QAFUxqYXZhL3V0aWwvSGFzaHRhYmxlO3hwc3IAE2phdmEudXRpbC5IYXNodGFibGUTuw8lIUrkuAMAAkYACmxvYWRGYWN0b3JJAAl0aHJlc2hvbGR4cD9AAAAAAAAIdwgAAAALAAAAAnQAB0Jhc2ljQkpzcgAXY29yZS5jb2RlZ2VuZXJhdG9yLk5vZGU57IiELEUMtAIABUkAFGRpcmVjdFByZWRlY2Vzc29yTnVtWgAKd2FzVmlzaXRlZEwAD2RpcmVjdFN1Y2NOb2Rlc3EAfgAJTAAFbGFiZWxxAH4ABEwAC3BhcmVudE5vZGVzcQB+AAd4cAAAAAEAc3EAfgALP0AAAAAAAAh3CAAAAAsAAAAAeHEAfgANc3IAEWphdmEudXRpbC5IYXNoU2V0ukSFlZa4tzQDAAB4cHcMAAAAED9AAAAAAAABc3EAfgAOAAAAAABzcQB+AAs/QAAAAAAACHcIAAAACwAAAAFxAH4AD3QAEnN0cmFpZ2h0UHJlY2VkZW5jZXh0AAVTcGxpdHNxAH4AEXcMAAAAED9AAAAAAAAAeHhxAH4AFnEAfgATeHNxAH4AEXcMAAAAED9AAAAAAAABc3IAE2phdmEudXRpbC5BcnJheUxpc3R4gdIdmcdhnQMAAUkABHNpemV4cAAAAAJ3BAAAAAJxAH4AFnEAfgANeHhzcgAXamF2YS51dGlsLkxpbmtlZEhhc2hTZXTYbNdald0qHgIAAHhxAH4AEXcMAAAAED9AAAAAAAABdAAFU3BsaXR4c3IAJmNvcmUuY29tcGlsZXJlc291cmNlcy5FdmVudE5vZGVNYXBJbXBsaDut0E+PmIICAAFMAANtYXB0AA9MamF2YS91dGlsL01hcDt4cHNxAH4ACz9AAAAAAAAIdwgAAAALAAAAA3QABWxlYXZlc3IAKmNvcmUuY29tcGlsZXJlc291cmNlcy5RdWFsaWZpZXJOb2RlTWFwSW1wbOrz3H3Lq6S1AgABTAADbWFwcQB+AB94cHNxAH4ACz9AAAAAAAAIdwgAAAALAAAAAXQAA2FsbHNyACpjb3JlLmNvbXBpbGVyZXNvdXJjZXMuQ29uZGl0aW9uTm9kZU1hcEltcGx3bBYqvueUaQIAAUwAA21hcHQAKExjb3JlL2NvbXBpbGVyZXNvdXJjZXMvT3JkZXJlZEhhc2h0YWJsZTt4cHNyACZjb3JlLmNvbXBpbGVyZXNvdXJjZXMuT3JkZXJlZEhhc2h0YWJsZXhkoN7SZCEwAgABTAAMa2V5T3JkZXJMaXN0dAAWTGphdmEvdXRpbC9MaW5rZWRMaXN0O3hxAH4ACz9AAAAAAAAIdwgAAAALAAAAAnQANUJsYWNramFja0RvbWFpbi5zdGF0ZS5lcXVhbHNUb0NvbnN0YW50KFN0YXRlICAuIFBMQVkpc3IAJWNvcmUuY29tcGlsZXJlc291cmNlcy5GaXRTdWJlbnRyeVRyZWVjlfz0CwGJqwIAA0wACGNoaWxkcmVudAAQTGphdmEvdXRpbC9MaXN0O0wAEWRlZmF1bHRQcmVjZWRlbmNlcQB+AAFMAAN2YWx0ACNMY29yZS9jb21waWxlcmVzb3VyY2VzL0ZpdFN1YmVudHJ5O3hwc3IAFGphdmEudXRpbC5MaW5rZWRMaXN0DClTXUpgiCIDAAB4cHcEAAAAAXNxAH4ALnNxAH4AMncEAAAAAHhzcQB+AAZzcQB+AAs/QAAAAAAACHcIAAAACwAAAAB4c3EAfgARdwwAAAAQP0AAAAAAAAB4c3IAJ2NvcmUuY29tcGlsZXJlc291cmNlcy5GaXRQdVN1YmVudHJ5SW1wbMnqIzs+5hOaAgABTAADbWFwcQB+AB94cgAlY29yZS5jb21waWxlcmVzb3VyY2VzLkZpdFN1YmVudHJ5SW1wbOo5MJx39SHnAgAHSQAPY29udGFpbldoZW5FbHNlTAAQZmVhdHVyZU5hbWVFbnRyeXEAfgAETAALcHJlUmVsYXRpb25xAH4AAUwAEHByb2dyYW1Cb2R5RW50cnlxAH4ABEwAEHByb2dyYW1Db25kaXRpb25xAH4ABEwAFHByb2dyYW1Vbml0TmFtZUVudHJ5cQB+AARMAA13aGVuQ29uZGl0aW9udAAfTGNvcmUvbGFuZy9Db25kaXRpb25FeHByZXNzaW9uO3hw/////3EAfgAdc3EAfgAGc3EAfgALP0AAAAAAAAh3CAAAAAsAAAAAeHNxAH4AEXcMAAAAED9AAAAAAAAAeHQA5SAgIFN5c3RlbSAgLiBvdXQgIC4gcHJpbnRsbiAoIkNoZWNrIGlmIHRoZSBwbGF5ZXIgaGFzIG1vcmUgaGFuZHM6IiAgKyBwbGF5ZXIgIC4gaGFzTW9yZUhhbmRzICgpKSAgOyAgIHBsYXllciAgLiBtaW51c0hhbmQgKCkgIDsgaWYgKCBwbGF5ZXIgIC4gaGFzTW9yZUhhbmRzICgpICkgeyAgICBwbGF5ZXIgIC4gZ2V0TmV4dEhhbmRUb1BsYXkgKCkgIDsgICBzdGF0ZSAgPSBTdGF0ZSAgLiBQTEFZIDsgfSBxAH4ALXQAC3NwTGVhdmVQbGF5c3IAHWNvcmUubGFuZy5Db25kaXRpb25FeHByZXNzaW9uiIriGx69VJQCAARaAAhuZWdhdGl2ZUwABWNoaWxkcQB+ADtMAARuZXh0cQB+ADtMAANzdHJxAH4ABHhwAHBwdAAEdHJ1ZXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAB3CAAAABAAAAAAeHhzcQB+AAZzcQB+AAs/QAAAAAAACHcIAAAACwAAAAB4c3EAfgARdwwAAAAQP0AAAAAAAAB4cHQANkJsYWNramFja0RvbWFpbi5zdGF0ZS5lcXVhbHNUb0NvbnN0YW50KFN0YXRlICAuIEpVREdFKXNxAH4ALnNxAH4AMncEAAAAAXNxAH4ALnNxAH4AMncEAAAAAHhzcQB+AAZzcQB+AAs/QAAAAAAACHcIAAAACwAAAAB4c3EAfgARdwwAAAAQP0AAAAAAAAB4c3EAfgA5/////3EAfgAdc3EAfgAGc3EAfgALP0AAAAAAAAh3CAAAAAsAAAAAeHNxAH4AEXcMAAAAED9AAAAAAAAAeHQA1iBpZiAoIDAgID09IHBsYXllciAgLiBnZXRDdXJyZW50SGFuZCAoKSApIHsgICAgc3RhdGUgID0gU3RhdGUgIC4gRU5EIDsgfSAgZWxzZSBpZiAoICAhIHBsYXllciAgLiBpc0xhc3RIYW5kICgpICkgeyAgICBzdGF0ZSAgPSBTdGF0ZSAgLiBKVURHRSA7IH0gIGVsc2UgaWYgKCBwbGF5ZXIgIC4gaXNMYXN0SGFuZCAoKSApIHsgICAgc3RhdGUgID0gU3RhdGUgIC4gRU5EIDsgfSBxAH4ASnQAD3NwbGl0TGVhdmVKdWRnZXNxAH4AQgBwcHEAfgBEc3EAfgBFP0AAAAAAAAB3CAAAABAAAAAAeHhzcQB+AAZzcQB+AAs/QAAAAAAACHcIAAAACwAAAAB4c3EAfgARdwwAAAAQP0AAAAAAAAB4cHhzcQB+ADJ3BAAAAAJxAH4ALXEAfgBKeHh0AAtzcGxpdC5TcGxpdHNxAH4AI3NxAH4ACz9AAAAAAAAIdwgAAAALAAAAAXEAfgAmc3EAfgAnc3EAfgAqP0AAAAAAAAh3CAAAAAsAAAABdAA1QmxhY2tqYWNrRG9tYWluLnN0YXRlLmVxdWFsc1RvQ29uc3RhbnQoU3RhdGUgIC4gUExBWSlzcQB+AC5zcQB+ADJ3BAAAAAFzcQB+AC5zcQB+ADJ3BAAAAAB4c3EAfgAGc3EAfgALP0AAAAAAAAh3CAAAAAsAAAAAeHNxAH4AEXcMAAAAED9AAAAAAAAAeHNxAH4AOf////9xAH4AHXNxAH4ABnNxAH4ACz9AAAAAAAAIdwgAAAALAAAAAHhzcQB+ABF3DAAAABA/QAAAAAAAAHh0AMUgICBwbGF5ZXIgIC4gYWRkSGFuZCAoKSAgOyAgIHBsYXllciAgLiBkZWxpdmVyQ2FyZCAoZGVjayAgLiBkZWFsQ2FyZEZhY2VVcCAoKSkgIDsgaWYgKCBwbGF5ZXIgIC4gc3BsaXR0YWJsZSAoKSApIHsgICAgcGxheWVyICAuIGFza0hpdFN0YW5kT3JTcGxpdCAoKSAgOyB9ICBlbHNlIHsgICAgcGxheWVyICAuIGFza0hpdE9yU3RhbmQgKCkgIDsgfXEAfgBjdAAFc3BsaXRzcQB+AEIAcHBxAH4ARHNxAH4ART9AAAAAAAAAdwgAAAAQAAAAAHh4c3EAfgAGc3EAfgALP0AAAAAAAAh3CAAAAAsAAAAAeHNxAH4AEXcMAAAAED9AAAAAAAAAeHB4c3EAfgAydwQAAAABcQB+AGN4eHQABWVudGVyc3EAfgAjc3EAfgALP0AAAAAAAAh3CAAAAAsAAAABcQB+ACZzcQB+ACdzcQB+ACo/QAAAAAAACHcIAAAACwAAAAJ0ADVCbGFja2phY2tEb21haW4uc3RhdGUuZXF1YWxzVG9Db25zdGFudChTdGF0ZSAgLiBQTEFZKXNxAH4ALnNxAH4AMncEAAAAAXNxAH4ALnNxAH4AMncEAAAAAHhzcQB+AAZzcQB+AAs/QAAAAAAACHcIAAAACwAAAAB4c3EAfgARdwwAAAAQP0AAAAAAAAB4c3EAfgA6/////3EAfgAdc3EAfgAGc3EAfgALP0AAAAAAAAh3CAAAAAsAAAACdAAHQmFzaWNCSnNxAH4ADgAAAAEAc3EAfgALP0AAAAAAAAh3CAAAAAsAAAAAeHEAfgCHc3EAfgARdwwAAAAQP0AAAAAAAAFzcQB+AA4AAAAAAHNxAH4ACz9AAAAAAAAIdwgAAAALAAAAAXEAfgCIdAAScHJpb3JpdHlQcmVjZWRlbmNleHQABVNwbGl0c3EAfgARdwwAAAAQP0AAAAAAAAB4eHEAfgCOcQB+AIt4c3EAfgARdwwAAAAQP0AAAAAAAAB4dAApeyAgICBwbGF5ZXIgIC4gYXNrSGl0U3RhbmRPclNwbGl0ICgpICA7IH1xAH4AfHQADXNwRW50ZXJQbGF5XzBzcQB+AEIAcHB0ACIoKFNwbGl0UGxheWVyKXBsYXllcikuc3BsaXR0YWJsZSgpeHNxAH4ABnNxAH4ACz9AAAAAAAAIdwgAAAALAAAAAHhzcQB+ABF3DAAAABA/QAAAAAAAAHhwdAA2QmxhY2tqYWNrRG9tYWluLnN0YXRlLmVxdWFsc1RvQ29uc3RhbnQoU3RhdGUgIC4gSlVER0Upc3EAfgAuc3EAfgAydwQAAAABc3EAfgAuc3EAfgAydwQAAAAAeHNxAH4ABnNxAH4ACz9AAAAAAAAIdwgAAAALAAAAAHhzcQB+ABF3DAAAABA/QAAAAAAAAHhzcQB+ADn/////cQB+AB1zcQB+AAZzcQB+AAs/QAAAAAAACHcIAAAACwAAAAB4c3EAfgARdwwAAAAQP0AAAAAAAAB4dACsIGlmICggMCAgPT0gcGxheWVyICAuIGdldEN1cnJlbnRIYW5kICgpICkgeyAgfSAgZWxzZSB7ICAgIFN5c3RlbSAgLiBvdXQgIC4gcHJpbnRsbiAoIkp1ZGdlIHNwbGl0IGdhbWUgaGFuZCAiICArIHBsYXllciAgLiBnZXRDdXJyZW50SGFuZCAoKSkgIDsgICBwbGF5ZXIgIC4gc3dhcEhhbmQgKCkgIDsgfXEAfgCYdAAPc3BsaXRFbnRlckp1ZGdlc3EAfgBCAHBwcQB+AERzcQB+AEU/QAAAAAAAAHcIAAAAEAAAAAB4eHNxAH4ABnNxAH4ACz9AAAAAAAAIdwgAAAALAAAAAHhzcQB+ABF3DAAAABA/QAAAAAAAAHhweHNxAH4AMncEAAAAAnEAfgB8cQB+AJh4eHhxAH4AHQ==

*/


/* IntermediateInfo:MergedFeatureInteractionTable
rO0ABXNyACZjb3JlLnRhYmxlLkZlYXR1cmVJbnRlcmFjdGlvblRhYmxlSW1wbKRW9OnaNkzmAgAFWgAQaXNGZWF0dXJlTmFtZVNldEwAEmRlZmF1bHRQcmVSZWxhdGlvbnQAKkxjb3JlL2NvbXBpbGVyZXNvdXJjZXMvUHJlY2VkZW5jZVJlbGF0aW9uO0wAB2RvbWFpbnN0AA9MamF2YS91dGlsL1NldDtMAApldmVudE5vZGVzdAAoTGNvcmUvY29tcGlsZXJlc291cmNlcy9FdmVudE5vZGVNYXBJbXBsO0wAC2ZlYXR1cmVOYW1ldAASTGphdmEvbGFuZy9TdHJpbmc7eHABc3IAKWNvcmUuY29kZWdlbmVyYXRvci5QcmVjZWRlbmNlUmVsYXRpb25JbXBspX7kJRXyFGoCAAFMAAlwcmVPcmRlcnN0ABNMamF2YS91dGlsL0hhc2hTZXQ7eHIAJmNvcmUuY29kZWdlbmVyYXRvci5QYXJ0aWFsT3JkZXJpbmdUcmVl2diA5Z4HBGACAAFMAAVub2Rlc3QAFUxqYXZhL3V0aWwvSGFzaHRhYmxlO3hwc3IAE2phdmEudXRpbC5IYXNodGFibGUTuw8lIUrkuAMAAkYACmxvYWRGYWN0b3JJAAl0aHJlc2hvbGR4cD9AAAAAAAAIdwgAAAALAAAAAnQAB0Jhc2ljQkpzcgAXY29yZS5jb2RlZ2VuZXJhdG9yLk5vZGU57IiELEUMtAIABUkAFGRpcmVjdFByZWRlY2Vzc29yTnVtWgAKd2FzVmlzaXRlZEwAD2RpcmVjdFN1Y2NOb2Rlc3EAfgAJTAAFbGFiZWxxAH4ABEwAC3BhcmVudE5vZGVzcQB+AAd4cAAAAAEAc3EAfgALP0AAAAAAAAh3CAAAAAsAAAAAeHEAfgANc3IAEWphdmEudXRpbC5IYXNoU2V0ukSFlZa4tzQDAAB4cHcMAAAAED9AAAAAAAABc3EAfgAOAAAAAABzcQB+AAs/QAAAAAAACHcIAAAACwAAAAFxAH4AD3QAEnN0cmFpZ2h0UHJlY2VkZW5jZXh0AAVTcGxpdHNxAH4AEXcMAAAAED9AAAAAAAAAeHhxAH4AFnEAfgATeHNxAH4AEXcMAAAAED9AAAAAAAABc3IAE2phdmEudXRpbC5BcnJheUxpc3R4gdIdmcdhnQMAAUkABHNpemV4cAAAAAJ3BAAAAAJxAH4AFnEAfgANeHhzcgAXamF2YS51dGlsLkxpbmtlZEhhc2hTZXTYbNdald0qHgIAAHhxAH4AEXcMAAAAED9AAAAAAAABdAAFU3BsaXR4c3IAJmNvcmUuY29tcGlsZXJlc291cmNlcy5FdmVudE5vZGVNYXBJbXBsaDut0E+PmIICAAFMAANtYXB0AA9MamF2YS91dGlsL01hcDt4cHNxAH4ACz9AAAAAAAAIdwgAAAALAAAABnQADWJsYWNramFjay5IaXRzcgAqY29yZS5jb21waWxlcmVzb3VyY2VzLlF1YWxpZmllck5vZGVNYXBJbXBs6vPcfcurpLUCAAFMAANtYXBxAH4AH3hwc3EAfgALP0AAAAAAAAh3CAAAAAsAAAABdAADYWxsc3IAKmNvcmUuY29tcGlsZXJlc291cmNlcy5Db25kaXRpb25Ob2RlTWFwSW1wbHdsFiq+55RpAgABTAADbWFwdAAoTGNvcmUvY29tcGlsZXJlc291cmNlcy9PcmRlcmVkSGFzaHRhYmxlO3hwc3IAJmNvcmUuY29tcGlsZXJlc291cmNlcy5PcmRlcmVkSGFzaHRhYmxleGSg3tJkITACAAFMAAxrZXlPcmRlckxpc3R0ABZMamF2YS91dGlsL0xpbmtlZExpc3Q7eHEAfgALP0AAAAAAAAh3CAAAAAsAAAABdAA9QmFzaWNCSi5CbGFja2phY2tEb21haW4uc3RhdGUuZXF1YWxzVG9Db25zdGFudChTdGF0ZSAgLiBQTEFZKXNyACVjb3JlLmNvbXBpbGVyZXNvdXJjZXMuRml0U3ViZW50cnlUcmVlY5X89AsBiasCAANMAAhjaGlsZHJlbnQAEExqYXZhL3V0aWwvTGlzdDtMABFkZWZhdWx0UHJlY2VkZW5jZXEAfgABTAADdmFsdAAjTGNvcmUvY29tcGlsZXJlc291cmNlcy9GaXRTdWJlbnRyeTt4cHNyABRqYXZhLnV0aWwuTGlua2VkTGlzdAwpU11KYIgiAwAAeHB3BAAAAAFzcQB+AC5zcQB+ADJ3BAAAAAB4c3EAfgAGc3EAfgALP0AAAAAAAAJ3CAAAAAMAAAAAeHNxAH4AEXcMAAAAAT9AAAAAAAAAeHNyACdjb3JlLmNvbXBpbGVyZXNvdXJjZXMuRml0UHVTdWJlbnRyeUltcGzJ6iM7PuYTmgIAAUwAA21hcHEAfgAfeHIAJWNvcmUuY29tcGlsZXJlc291cmNlcy5GaXRTdWJlbnRyeUltcGzqOTCcd/Uh5wIAB0kAD2NvbnRhaW5XaGVuRWxzZUwAEGZlYXR1cmVOYW1lRW50cnlxAH4ABEwAC3ByZVJlbGF0aW9ucQB+AAFMABBwcm9ncmFtQm9keUVudHJ5cQB+AARMABBwcm9ncmFtQ29uZGl0aW9ucQB+AARMABRwcm9ncmFtVW5pdE5hbWVFbnRyeXEAfgAETAANd2hlbkNvbmRpdGlvbnQAH0xjb3JlL2xhbmcvQ29uZGl0aW9uRXhwcmVzc2lvbjt4cP////90AAdCYXNpY0JKc3EAfgAGc3EAfgALP0AAAAAAAAJ3CAAAAAMAAAAAeHNxAH4AEXcMAAAAAT9AAAAAAAAAeHQAqSAgIHBsYXllciAgLiBkZWxpdmVyQ2FyZCAoZGVjayAgLiBkZWFsQ2FyZEZhY2VVcCAoKSkgIDsgaWYgKCBwbGF5ZXIgIC4gZ2V0Q2FyZHNTdW0gKCkgIDwgMjEgKSB7ICAgIHBsYXllciAgLiBhc2tIaXRPclN0YW5kICgpICA7IH0gIGVsc2UgeyAgICBzdGF0ZSAgPSBTdGF0ZSAgLiBKVURHRSA7IH10ADVCbGFja2phY2tEb21haW4uc3RhdGUuZXF1YWxzVG9Db25zdGFudChTdGF0ZSAgLiBQTEFZKXQACWhpdEF0UGxheXNyAB1jb3JlLmxhbmcuQ29uZGl0aW9uRXhwcmVzc2lvboiK4hsevVSUAgAEWgAIbmVnYXRpdmVMAAVjaGlsZHEAfgA7TAAEbmV4dHEAfgA7TAADc3RycQB+AAR4cABwcHQABHRydWVzcgARamF2YS51dGlsLkhhc2hNYXAFB9rBwxZg0QMAAkYACmxvYWRGYWN0b3JJAAl0aHJlc2hvbGR4cD9AAAAAAAAAdwgAAAAQAAAAAHh4c3EAfgAGc3EAfgALP0AAAAAAAAJ3CAAAAAMAAAAAeHNxAH4AEXcMAAAAAT9AAAAAAAAAeHB4c3EAfgAydwQAAAABcQB+AC14eHQAD2JsYWNramFjay5TdGFuZHNxAH4AI3NxAH4ACz9AAAAAAAAIdwgAAAALAAAAAXEAfgAmc3EAfgAn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

*/


/* IntermediateInfo:domains
rO0ABXNyABdqYXZhLnV0aWwuTGlua2VkSGFzaFNldNhs11qV3SoeAgAAeHIAEWphdmEudXRpbC5IYXNoU2V0ukSFlZa4tzQDAAB4cHcMAAAAED9AAAAAAAABdAAFU3BsaXR4

*/


/* IntermediateInfo:isExecutable
rO0ABXNyABFqYXZhLmxhbmcuQm9vbGVhbs0gcoDVnPruAgABWgAFdmFsdWV4cAE=

*/


/* IntermediateInfo:proceedToPuhandler
rO0ABXNyABNqYXZhLnV0aWwuQXJyYXlMaXN0eIHSHZnHYZ0DAAFJAARzaXpleHAAAAAFdwQAAAAFc3IAJ2NvcmUuZGF0YXN0cnVjdHVyZS5Qcm9jZWVkVG9IYW5kbGVySW5mb/NJGznJidC0AgAJTAAJY29uZGl0aW9udAASTGphdmEvbGFuZy9TdHJpbmc7TAAPZXZlbnRJZGVudGlmaWVycQB+AANMAAlldmVudFR5cGVxAH4AA0wAD2ZlYXR1cmVJbnN0YW5jZXEAfgADTAALZmVhdHVyZU5hbWVxAH4AA0wACXByb2NlZWRUb3EAfgADTAARcHJvY2VlZFRvSW5zdGFuY2VxAH4AA0wABnB1TmFtZXEAfgADTAAHcHVUb0FkZHEAfgADeHB0ADVCbGFja2phY2tEb21haW4uc3RhdGUuZXF1YWxzVG9Db25zdGFudChTdGF0ZSAgLiBQTEFZKXQAAWV0AAVlbnRlcnQADV9zcGxpdGZlYXR1cmV0AAVTcGxpdHBwdAANc3BFbnRlclBsYXlfMHBzcQB+AAJ0ADVCbGFja2phY2tEb21haW4uc3RhdGUuZXF1YWxzVG9Db25zdGFudChTdGF0ZSAgLiBQTEFZKXEAfgAGdAALc3BsaXQuU3BsaXR0AA1fc3BsaXRmZWF0dXJlcQB+AAlwcHQABXNwbGl0cHNxAH4AAnQANUJsYWNramFja0RvbWFpbi5zdGF0ZS5lcXVhbHNUb0NvbnN0YW50KFN0YXRlICAuIFBMQVkpcQB+AAZ0AAVsZWF2ZXQADV9zcGxpdGZlYXR1cmVxAH4ACXBwdAALc3BMZWF2ZVBsYXlwc3EAfgACdAA2QmxhY2tqYWNrRG9tYWluLnN0YXRlLmVxdWFsc1RvQ29uc3RhbnQoU3RhdGUgIC4gSlVER0UpcQB+AAZ0AAVlbnRlcnQADV9zcGxpdGZlYXR1cmVxAH4ACXBwdAAPc3BsaXRFbnRlckp1ZGdlcHNxAH4AAnQANkJsYWNramFja0RvbWFpbi5zdGF0ZS5lcXVhbHNUb0NvbnN0YW50KFN0YXRlICAuIEpVREdFKXEAfgAGdAAFbGVhdmV0AA1fc3BsaXRmZWF0dXJlcQB+AAlwcHQAD3NwbGl0TGVhdmVKdWRnZXB4

*/


/* IntermediateInfo:anchorFeature
rO0ABXQAB0Jhc2ljQko=

*/

