package blackjack.PackageIntergration;
import external.*;
import res.*;
import blackjack.*;
import blackjack.Split.*;
import blackjack.Natural.*;
import java.rmi.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class NaturalSplitFeaturePackage
 extends FeaturePackage implements Serializable 
{

BasicBJFeature _basicbjfeature;NaturalFeature _naturalfeature;SplitFeature _splitfeature;
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

public NaturalSplitFeaturePackage(  Player  player_Blackjack ,   Dealer  dealer_Blackjack) {
super();
BlackjackDomain.dealer = dealer_Blackjack;
BlackjackDomain.player = player_Blackjack;
BlackjackDomain.state = State  . INIT;
{Map<String, Boolean>currentState= new HashMap<String, Boolean>(previousState);
currentState.put("blackjackdomain.state.equalstoconstant(state.play)",BlackjackDomain.state.equalsToConstant(State.PLAY));
currentState.put("blackjackdomain.state.equalstoconstant(state.deal_card)",BlackjackDomain.state.equalsToConstant(State.DEAL_CARD));
currentState.put("blackjackdomain.state.equalstoconstant(state.end)",BlackjackDomain.state.equalsToConstant(State.END));
currentState.put("blackjackdomain.state.equalstoconstant(state.judge)",BlackjackDomain.state.equalsToConstant(State.JUDGE));
previousState = new HashMap<String, Boolean>(currentState);}
sharedBuffer = new EventBuffer<FL_EVENT_STEM>();
this._basicbjfeature = new BasicBJFeature();
this._naturalfeature = new NaturalFeature( this._basicbjfeature );
this._naturalfeature.sharedBuffer = sharedBuffer;
this._basicbjfeature.sharedBuffer = sharedBuffer;
this._splitfeature = new SplitFeature( this._basicbjfeature );
this._splitfeature.sharedBuffer = sharedBuffer;
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
NaturalSplitFeaturePackage.this.XEvent((FL_EVENT_STEM)buffer.remove());
}catch(Exception ex){
ex.printStackTrace();
}//end of catch
}//end of while
}//end of run
}//end of Consumer Class
public NaturalSplitFeaturePackage(NaturalFeature _naturalfeature, SplitFeature _splitfeature)
 {
super();
this._naturalfeature = _naturalfeature;
this._splitfeature = _splitfeature;
}

 
 public void EnterPlay( FL_EVENT_STEM  e)
{
try { 
if (activated)
{
}

} catch (Exception ex) { 
ex.printStackTrace(); } 
}


 
 public void EnterJudge( FL_EVENT_STEM  e)
{
try { 
if (activated)
{
}

} catch (Exception ex) { 
ex.printStackTrace(); } 
}


 
               
   public boolean get_LeavePlay_0_condition(){
return (true);
}
/**********************************/
public synchronized void LeavePlay_0( FL_EVENT_STEM  e)
{
try { 
if (activated)
{
}

} 
finally {}
}


 
 public void LeaveJudge( FL_EVENT_STEM  e)
{
try { 
if (activated)
{
}

} catch (Exception ex) { 
ex.printStackTrace(); } 
}


public void sendEvent( FL_EVENT_STEM e ) {
	sharedBuffer.add(e);
}
public synchronized void XEvent(final FL_EVENT_STEM e )
{
this.when_event_variable = e;
this._naturalfeature.when_event_variable = e;
this._basicbjfeature.when_event_variable = e;
this._splitfeature.when_event_variable = e;
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
boolean _splitfeaturesplitnotskip = true;
if (_splitfeaturesplitnotskip == true &&(BlackjackDomain.state.equalsToConstant(State.PLAY))) {
 if((BlackjackDomain.state.equalsToConstant(State.PLAY))) _splitfeature.split((Split) e);

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
if (!(BlackjackDomain.state.equalsToConstant(State.PLAY)) && previousState.get("blackjackdomain.state.equalstoconstant(state.play)") && (this.get_LeavePlay_0_condition())) {
boolean thisLeaveJudgenotskip = true;
boolean thisLeavePlay_0notskip = true;
boolean thisLeavePlay_0WhenCondtionResult = this.get_LeavePlay_0_condition();
boolean _naturalfeaturenaturalLeavePlay_0notskip = true;
boolean _naturalfeaturenaturalLeavePlay_0WhenCondtionResult = _naturalfeature.get_naturalLeavePlay_0_condition();
boolean _splitfeaturespLeavePlaynotskip = true;
boolean _splitfeaturesplitLeaveJudgenotskip = true;
boolean _basicbjfeaturedealCardToDealernotskip = true;
boolean _basicbjfeatureleaveAtPlaynotskip = true;
if (thisLeaveJudgenotskip == true && !(BlackjackDomain.state.equalsToConstant(State.JUDGE)) && previousState.get("blackjackdomain.state.equalstoconstant(state.judge)")) {
noLeaveEvent = false;
 if(!(BlackjackDomain.state.equalsToConstant(State.JUDGE))) this.LeaveJudge(e);

thisLeavePlay_0notskip = false;
}if (thisLeavePlay_0WhenCondtionResult &&thisLeavePlay_0notskip == true && !(BlackjackDomain.state.equalsToConstant(State.PLAY)) && previousState.get("blackjackdomain.state.equalstoconstant(state.play)")) {
noLeaveEvent = false;
 if(!(BlackjackDomain.state.equalsToConstant(State.PLAY))) this.LeavePlay_0(e);

}if (_naturalfeaturenaturalLeavePlay_0WhenCondtionResult &&_naturalfeaturenaturalLeavePlay_0notskip == true && !(BlackjackDomain.state.equalsToConstant(State.PLAY)) && previousState.get("blackjackdomain.state.equalstoconstant(state.play)")) {
noLeaveEvent = false;
 if(!(BlackjackDomain.state.equalsToConstant(State.PLAY))) _naturalfeature.naturalLeavePlay_0(e);

}if (_splitfeaturespLeavePlaynotskip == true && !(BlackjackDomain.state.equalsToConstant(State.PLAY)) && previousState.get("blackjackdomain.state.equalstoconstant(state.play)")) {
noLeaveEvent = false;
 if(!(BlackjackDomain.state.equalsToConstant(State.PLAY))) _splitfeature.spLeavePlay(e);

_splitfeaturesplitLeaveJudgenotskip = false;
}if (_splitfeaturesplitLeaveJudgenotskip == true && !(BlackjackDomain.state.equalsToConstant(State.JUDGE)) && previousState.get("blackjackdomain.state.equalstoconstant(state.judge)")) {
noLeaveEvent = false;
 if(!(BlackjackDomain.state.equalsToConstant(State.JUDGE))) _splitfeature.splitLeaveJudge(e);

}if (_basicbjfeaturedealCardToDealernotskip == true && !(BlackjackDomain.state.equalsToConstant(State.DEAL_CARD)) && previousState.get("blackjackdomain.state.equalstoconstant(state.deal_card)")) {
noLeaveEvent = false;
 if(!(BlackjackDomain.state.equalsToConstant(State.DEAL_CARD))) _basicbjfeature.dealCardToDealer(e);

_basicbjfeatureleaveAtPlaynotskip = false;
}if (_basicbjfeatureleaveAtPlaynotskip == true && !(BlackjackDomain.state.equalsToConstant(State.PLAY)) && previousState.get("blackjackdomain.state.equalstoconstant(state.play)")) {
noLeaveEvent = false;
 if(!(BlackjackDomain.state.equalsToConstant(State.PLAY))) _basicbjfeature.leaveAtPlay(e);

}}
else {
boolean thisLeaveJudgenotskip = true;
boolean thisLeavePlay_0notskip = true;
boolean _splitfeaturespLeavePlaynotskip = true;
boolean _splitfeaturesplitLeaveJudgenotskip = true;
boolean _naturalfeaturenaturalLeavePlay_0notskip = true;
boolean _basicbjfeaturedealCardToDealernotskip = true;
boolean _basicbjfeatureleaveAtPlaynotskip = true;
if (thisLeaveJudgenotskip == true && !(BlackjackDomain.state.equalsToConstant(State.JUDGE)) && previousState.get("blackjackdomain.state.equalstoconstant(state.judge)")) {
noLeaveEvent = false;
 if(!(BlackjackDomain.state.equalsToConstant(State.JUDGE))) this.LeaveJudge(e);

thisLeavePlay_0notskip = false;
}if ((this.get_LeavePlay_0_condition()) &&thisLeavePlay_0notskip == true && !(BlackjackDomain.state.equalsToConstant(State.PLAY)) && previousState.get("blackjackdomain.state.equalstoconstant(state.play)")) {
noLeaveEvent = false;
 if(!(BlackjackDomain.state.equalsToConstant(State.PLAY))) this.LeavePlay_0(e);

}if (_splitfeaturespLeavePlaynotskip == true && !(BlackjackDomain.state.equalsToConstant(State.PLAY)) && previousState.get("blackjackdomain.state.equalstoconstant(state.play)")) {
noLeaveEvent = false;
 if(!(BlackjackDomain.state.equalsToConstant(State.PLAY))) _splitfeature.spLeavePlay(e);

_splitfeaturesplitLeaveJudgenotskip = false;
_naturalfeaturenaturalLeavePlay_0notskip = false;
}if (_splitfeaturesplitLeaveJudgenotskip == true && !(BlackjackDomain.state.equalsToConstant(State.JUDGE)) && previousState.get("blackjackdomain.state.equalstoconstant(state.judge)")) {
noLeaveEvent = false;
 if(!(BlackjackDomain.state.equalsToConstant(State.JUDGE))) _splitfeature.splitLeaveJudge(e);

_naturalfeaturenaturalLeavePlay_0notskip = false;
}if ((_naturalfeature.get_naturalLeavePlay_0_condition()) &&_naturalfeaturenaturalLeavePlay_0notskip == true && !(BlackjackDomain.state.equalsToConstant(State.PLAY)) && previousState.get("blackjackdomain.state.equalstoconstant(state.play)")) {
noLeaveEvent = false;
 if(!(BlackjackDomain.state.equalsToConstant(State.PLAY))) _naturalfeature.naturalLeavePlay_0(e);

}if (_basicbjfeaturedealCardToDealernotskip == true && !(BlackjackDomain.state.equalsToConstant(State.DEAL_CARD)) && previousState.get("blackjackdomain.state.equalstoconstant(state.deal_card)")) {
noLeaveEvent = false;
 if(!(BlackjackDomain.state.equalsToConstant(State.DEAL_CARD))) _basicbjfeature.dealCardToDealer(e);

_basicbjfeatureleaveAtPlaynotskip = false;
}if (_basicbjfeatureleaveAtPlaynotskip == true && !(BlackjackDomain.state.equalsToConstant(State.PLAY)) && previousState.get("blackjackdomain.state.equalstoconstant(state.play)")) {
noLeaveEvent = false;
 if(!(BlackjackDomain.state.equalsToConstant(State.PLAY))) _basicbjfeature.leaveAtPlay(e);

}}
}
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
if ((BlackjackDomain.state.equalsToConstant(State.PLAY))&& !previousState.get("blackjackdomain.state.equalstoconstant(state.play)")&& temp.get("blackjackdomain.state.equalstoconstant(state.play)") && (_splitfeature.get_spEnterPlay_0_condition())) {
boolean thisEnterJudgenotskip = true;
boolean thisEnterPlaynotskip = true;
boolean _splitfeaturesplitEnterJudgenotskip = true;
boolean _splitfeaturespEnterPlay_0notskip = true;
boolean _splitfeaturespEnterPlay_0WhenCondtionResult = _splitfeature.get_spEnterPlay_0_condition();
boolean _naturalfeaturenaturalEnterPlaynotskip = true;
boolean _basicbjfeatureenterJudgenotskip = true;
boolean _basicbjfeatureenterPlaynotskip = true;
boolean _basicbjfeaturedealCardToPlayernotskip = true;
boolean _basicbjfeatureGameOvernotskip = true;
if (thisEnterJudgenotskip == true &&(BlackjackDomain.state.equalsToConstant(State.JUDGE))&& temp.get("blackjackdomain.state.equalstoconstant(state.judge)")&&!previousState.get("blackjackdomain.state.equalstoconstant(state.judge)")) {
noEnterEvent = false;
 if((BlackjackDomain.state.equalsToConstant(State.JUDGE))) this.EnterJudge(e);

thisEnterPlaynotskip = false;
}if (thisEnterPlaynotskip == true &&(BlackjackDomain.state.equalsToConstant(State.PLAY))&& temp.get("blackjackdomain.state.equalstoconstant(state.play)")&&!previousState.get("blackjackdomain.state.equalstoconstant(state.play)")) {
noEnterEvent = false;
 if((BlackjackDomain.state.equalsToConstant(State.PLAY))) this.EnterPlay(e);

}if (_splitfeaturesplitEnterJudgenotskip == true &&(BlackjackDomain.state.equalsToConstant(State.JUDGE))&& temp.get("blackjackdomain.state.equalstoconstant(state.judge)")&&!previousState.get("blackjackdomain.state.equalstoconstant(state.judge)")) {
noEnterEvent = false;
 if((BlackjackDomain.state.equalsToConstant(State.JUDGE))) _splitfeature.splitEnterJudge(e);

_splitfeaturespEnterPlay_0notskip = false;
_naturalfeaturenaturalEnterPlaynotskip = false;
_basicbjfeatureenterJudgenotskip = false;
_basicbjfeatureenterPlaynotskip = false;
_basicbjfeaturedealCardToPlayernotskip = false;
_basicbjfeatureGameOvernotskip = false;
}if (_splitfeaturespEnterPlay_0WhenCondtionResult &&_splitfeaturespEnterPlay_0notskip == true &&(BlackjackDomain.state.equalsToConstant(State.PLAY))&& temp.get("blackjackdomain.state.equalstoconstant(state.play)")&&!previousState.get("blackjackdomain.state.equalstoconstant(state.play)")) {
noEnterEvent = false;
 if((BlackjackDomain.state.equalsToConstant(State.PLAY))) _splitfeature.spEnterPlay_0(e);

_naturalfeaturenaturalEnterPlaynotskip = false;
_basicbjfeatureenterJudgenotskip = false;
_basicbjfeatureenterPlaynotskip = false;
_basicbjfeaturedealCardToPlayernotskip = false;
_basicbjfeatureGameOvernotskip = false;
}if (_naturalfeaturenaturalEnterPlaynotskip == true &&(BlackjackDomain.state.equalsToConstant(State.PLAY))&& temp.get("blackjackdomain.state.equalstoconstant(state.play)")&&!previousState.get("blackjackdomain.state.equalstoconstant(state.play)")) {
noEnterEvent = false;
 if((BlackjackDomain.state.equalsToConstant(State.PLAY))) _naturalfeature.naturalEnterPlay(e);

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
boolean thisEnterJudgenotskip = true;
boolean thisEnterPlaynotskip = true;
boolean _splitfeaturespEnterPlay_0notskip = true;
boolean _splitfeaturesplitEnterJudgenotskip = true;
boolean _naturalfeaturenaturalEnterPlaynotskip = true;
boolean _basicbjfeatureenterJudgenotskip = true;
boolean _basicbjfeatureenterPlaynotskip = true;
boolean _basicbjfeaturedealCardToPlayernotskip = true;
boolean _basicbjfeatureGameOvernotskip = true;
if (thisEnterJudgenotskip == true &&(BlackjackDomain.state.equalsToConstant(State.JUDGE))&&temp.get("blackjackdomain.state.equalstoconstant(state.judge)")&& !previousState.get("blackjackdomain.state.equalstoconstant(state.judge)")) {
noEnterEvent = false;
 if((BlackjackDomain.state.equalsToConstant(State.JUDGE))) this.EnterJudge(e);

thisEnterPlaynotskip = false;
}if (thisEnterPlaynotskip == true &&(BlackjackDomain.state.equalsToConstant(State.PLAY))&&temp.get("blackjackdomain.state.equalstoconstant(state.play)")&& !previousState.get("blackjackdomain.state.equalstoconstant(state.play)")) {
noEnterEvent = false;
 if((BlackjackDomain.state.equalsToConstant(State.PLAY))) this.EnterPlay(e);

}if ((_splitfeature.get_spEnterPlay_0_condition()) &&_splitfeaturespEnterPlay_0notskip == true &&(BlackjackDomain.state.equalsToConstant(State.PLAY))&&temp.get("blackjackdomain.state.equalstoconstant(state.play)")&& !previousState.get("blackjackdomain.state.equalstoconstant(state.play)")) {
noEnterEvent = false;
 if((BlackjackDomain.state.equalsToConstant(State.PLAY))) _splitfeature.spEnterPlay_0(e);

_splitfeaturesplitEnterJudgenotskip = false;
_naturalfeaturenaturalEnterPlaynotskip = false;
}if (_splitfeaturesplitEnterJudgenotskip == true &&(BlackjackDomain.state.equalsToConstant(State.JUDGE))&&temp.get("blackjackdomain.state.equalstoconstant(state.judge)")&& !previousState.get("blackjackdomain.state.equalstoconstant(state.judge)")) {
noEnterEvent = false;
 if((BlackjackDomain.state.equalsToConstant(State.JUDGE))) _splitfeature.splitEnterJudge(e);

_naturalfeaturenaturalEnterPlaynotskip = false;
}if (_naturalfeaturenaturalEnterPlaynotskip == true &&(BlackjackDomain.state.equalsToConstant(State.PLAY))&&temp.get("blackjackdomain.state.equalstoconstant(state.play)")&& !previousState.get("blackjackdomain.state.equalstoconstant(state.play)")) {
noEnterEvent = false;
 if((BlackjackDomain.state.equalsToConstant(State.PLAY))) _naturalfeature.naturalEnterPlay(e);

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

protected void finalize() {
try {
super.finalize();
} catch(Throwable e) {
// do nothing
}
}
}

/* IntermediateInfo:FeatureInteractionTable
rO0ABXNyACZjb3JlLnRhYmxlLkZlYXR1cmVJbnRlcmFjdGlvblRhYmxlSW1wbKRW9OnaNkzmAgAFWgAQaXNGZWF0dXJlTmFtZVNldEwAEmRlZmF1bHRQcmVSZWxhdGlvbnQAKkxjb3JlL2NvbXBpbGVyZXNvdXJjZXMvUHJlY2VkZW5jZVJlbGF0aW9uO0wAB2RvbWFpbnN0AA9MamF2YS91dGlsL1NldDtMAApldmVudE5vZGVzdAAoTGNvcmUvY29tcGlsZXJlc291cmNlcy9FdmVudE5vZGVNYXBJbXBsO0wAC2ZlYXR1cmVOYW1ldAASTGphdmEvbGFuZy9TdHJpbmc7eHABc3IAKWNvcmUuY29kZWdlbmVyYXRvci5QcmVjZWRlbmNlUmVsYXRpb25JbXBspX7kJRXyFGoCAAFMAAlwcmVPcmRlcnN0ABNMamF2YS91dGlsL0hhc2hTZXQ7eHIAJmNvcmUuY29kZWdlbmVyYXRvci5QYXJ0aWFsT3JkZXJpbmdUcmVl2diA5Z4HBGACAAFMAAVub2Rlc3QAFUxqYXZhL3V0aWwvSGFzaHRhYmxlO3hwc3IAE2phdmEudXRpbC5IYXNodGFibGUTuw8lIUrkuAMAAkYACmxvYWRGYWN0b3JJAAl0aHJlc2hvbGR4cD9AAAAAAAAIdwgAAAALAAAABHQAB05hdHVyYWxzcgAXY29yZS5jb2RlZ2VuZXJhdG9yLk5vZGU57IiELEUMtAIABUkAFGRpcmVjdFByZWRlY2Vzc29yTnVtWgAKd2FzVmlzaXRlZEwAD2RpcmVjdFN1Y2NOb2Rlc3EAfgAJTAAFbGFiZWxxAH4ABEwAC3BhcmVudE5vZGVzcQB+AAd4cAAAAAIAc3EAfgALP0AAAAAAAAh3CAAAAAsAAAABc3EAfgAOAAAAAwBzcQB+AAs/QAAAAAAACHcIAAAACwAAAAB4dAAHQmFzaWNCSnNyABFqYXZhLnV0aWwuSGFzaFNldLpEhZWWuLc0AwAAeHB3DAAAABA/QAAAAAAAA3NxAH4ADgAAAAAAc3EAfgALP0AAAAAAAAh3CAAAAAsAAAADcQB+ABF0ABJzdHJhaWdodFByZWNlZGVuY2VxAH4AD3EAfgAYc3EAfgAOAAAAAQBzcQB+AAs/QAAAAAAACHcIAAAACwAAAAJxAH4AEXQAEnN0cmFpZ2h0UHJlY2VkZW5jZXEAfgAPdAAScHJpb3JpdHlQcmVjZWRlbmNleHQABVNwbGl0c3EAfgAUdwwAAAAQP0AAAAAAAAFxAH4AFnhxAH4AGHh0AAxOYXR1cmFsU3BsaXRzcQB+ABR3DAAAABA/QAAAAAAAAHhxAH4AD3EAfgAZeHQAEnN0cmFpZ2h0UHJlY2VkZW5jZXhxAH4ADXNxAH4AFHcMAAAAED9AAAAAAAACcQB+ABZxAH4AGXhxAH4AH3EAfgAWcQB+ABNxAH4AEXEAfgAdcQB+ABl4c3EAfgAUdwwAAAAQP0AAAAAAAAB4c3IAF2phdmEudXRpbC5MaW5rZWRIYXNoU2V02GzXWpXdKh4CAAB4cQB+ABR3DAAAABA/QAAAAAAAAnQACUJsYWNramFja3QABVNwbGl0eHNyACZjb3JlLmNvbXBpbGVyZXNvdXJjZXMuRXZlbnROb2RlTWFwSW1wbGg7rdBPj5iCAgABTAADbWFwdAAPTGphdmEvdXRpbC9NYXA7eHBzcQB+AAs/QAAAAAAACHcIAAAACwAAAAJ0AAVsZWF2ZXNyACpjb3JlLmNvbXBpbGVyZXNvdXJjZXMuUXVhbGlmaWVyTm9kZU1hcEltcGzq89x9y6uktQIAAUwAA21hcHEAfgApeHBzcQB+AAs/QAAAAAAACHcIAAAACwAAAAF0AANhbGxzcgAqY29yZS5jb21waWxlcmVzb3VyY2VzLkNvbmRpdGlvbk5vZGVNYXBJbXBsd2wWKr7nlGkCAAFMAANtYXB0AChMY29yZS9jb21waWxlcmVzb3VyY2VzL09yZGVyZWRIYXNodGFibGU7eHBzcgAmY29yZS5jb21waWxlcmVzb3VyY2VzLk9yZGVyZWRIYXNodGFibGV4ZKDe0mQhMAIAAUwADGtleU9yZGVyTGlzdHQAFkxqYXZhL3V0aWwvTGlua2VkTGlzdDt4cQB+AAs/QAAAAAAACHcIAAAACwAAAAJ0ADVCbGFja2phY2tEb21haW4uc3RhdGUuZXF1YWxzVG9Db25zdGFudChTdGF0ZSAgLiBQTEFZKXNyACVjb3JlLmNvbXBpbGVyZXNvdXJjZXMuRml0U3ViZW50cnlUcmVlY5X89AsBiasCAANMAAhjaGlsZHJlbnQAEExqYXZhL3V0aWwvTGlzdDtMABFkZWZh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

*/


/* IntermediateInfo:MergedFeatureInteractionTable
rO0ABXNyACZjb3JlLnRhYmxlLkZlYXR1cmVJbnRlcmFjdGlvblRhYmxlSW1wbKRW9OnaNkzmAgAFWgAQaXNGZWF0dXJlTmFtZVNldEwAEmRlZmF1bHRQcmVSZWxhdGlvbnQAKkxjb3JlL2NvbXBpbGVyZXNvdXJjZXMvUHJlY2VkZW5jZVJlbGF0aW9uO0wAB2RvbWFpbnN0AA9MamF2YS91dGlsL1NldDtMAApldmVudE5vZGVzdAAoTGNvcmUvY29tcGlsZXJlc291cmNlcy9FdmVudE5vZGVNYXBJbXBsO0wAC2ZlYXR1cmVOYW1ldAASTGphdmEvbGFuZy9TdHJpbmc7eHABc3IAKWNvcmUuY29kZWdlbmVyYXRvci5QcmVjZWRlbmNlUmVsYXRpb25JbXBspX7kJRXyFGoCAAFMAAlwcmVPcmRlcnN0ABNMamF2YS91dGlsL0hhc2hTZXQ7eHIAJmNvcmUuY29kZWdlbmVyYXRvci5QYXJ0aWFsT3JkZXJpbmdUcmVl2diA5Z4HBGACAAFMAAVub2Rlc3QAFUxqYXZhL3V0aWwvSGFzaHRhYmxlO3hwc3IAE2phdmEudXRpbC5IYXNodGFibGUTuw8lIUrkuAMAAkYACmxvYWRGYWN0b3JJAAl0aHJlc2hvbGR4cD9AAAAAAAAIdwgAAAALAAAABHQAB05hdHVyYWxzcgAXY29yZS5jb2RlZ2VuZXJhdG9yLk5vZGU57IiELEUMtAIABUkAFGRpcmVjdFByZWRlY2Vzc29yTnVtWgAKd2FzVmlzaXRlZEwAD2RpcmVjdFN1Y2NOb2Rlc3EAfgAJTAAFbGFiZWxxAH4ABEwAC3BhcmVudE5vZGVzcQB+AAd4cAAAAAIAc3EAfgALP0AAAAAAAAh3CAAAAAsAAAABc3EAfgAOAAAAAwBzcQB+AAs/QAAAAAAACHcIAAAACwAAAAB4dAAHQmFzaWNCSnNyABFqYXZhLnV0aWwuSGFzaFNldLpEhZWWuLc0AwAAeHB3DAAAABA/QAAAAAAAA3NxAH4ADgAAAAAAc3EAfgALP0AAAAAAAAh3CAAAAAsAAAADcQB+ABF0ABJzdHJhaWdodFByZWNlZGVuY2VxAH4AD3EAfgAYc3EAfgAOAAAAAQBzcQB+AAs/QAAAAAAACHcIAAAACwAAAAJxAH4AEXQAEnN0cmFpZ2h0UHJlY2VkZW5jZXEAfgAPdAAScHJpb3JpdHlQcmVjZWRlbmNleHQABVNwbGl0c3EAfgAUdwwAAAAQP0AAAAAAAAFxAH4AFnhxAH4AGHh0AAxOYXR1cmFsU3BsaXRzcQB+ABR3DAAAABA/QAAAAAAAAHhxAH4AD3EAfgAZeHQAEnN0cmFpZ2h0UHJlY2VkZW5jZXhxAH4ADXNxAH4AFHcMAAAAED9AAAAAAAACcQB+ABZxAH4AGXhxAH4AH3EAfgAWcQB+ABNxAH4AEXEAfgAdcQB+ABl4c3EAfgAUdwwAAAAQP0AAAAAAAAB4c3IAF2phdmEudXRpbC5MaW5rZWRIYXNoU2V02GzXWpXdKh4CAAB4cQB+ABR3DAAAABA/QAAAAAAAAnQACUJsYWNramFja3QABVNwbGl0eHNyACZjb3JlLmNvbXBpbGVyZXNvdXJjZXMuRXZlbnROb2RlTWFwSW1wbGg7rdBPj5iCAgABTAADbWFwdAAPTGphdmEvdXRpbC9NYXA7eHBzcQB+AAs/QAAAAAAACHcIAAAACwAAAAZ0AA1ibGFja2phY2suSGl0c3IAKmNvcmUuY29tcGlsZXJlc291cmNlcy5RdWFsaWZpZXJOb2RlTWFwSW1wbOrz3H3Lq6S1AgABTAADbWFwcQB+ACl4cHNxAH4ACz9AAAAAAAAIdwgAAAALAAAAAXQAA2FsbHNyACpjb3JlLmNvbXBpbGVyZXNvdXJjZXMuQ29uZGl0aW9uTm9kZU1hcEltcGx3bBYqvueUaQIAAUwAA21hcHQAKExjb3JlL2NvbXBpbGVyZXNvdXJjZXMvT3JkZXJlZEhhc2h0YWJsZTt4cHNyACZjb3JlLmNvbXBpbGVyZXNvdXJjZXMuT3JkZXJlZEhhc2h0YWJsZXhkoN7SZCEwAgABTAAMa2V5T3JkZXJMaXN0dAAWTGphdmEvdXRpbC9MaW5rZWRMaXN0O3hxAH4ACz9AAAAAAAAIdwgAAAALAAAAAXQAPUJhc2ljQkouQmxhY2tqYWNrRG9tYWluLnN0YXRlLmVxdWFsc1RvQ29uc3RhbnQoU3RhdGUgIC4gUExBWSlzcgAlY29yZS5jb21waWxlcmVzb3VyY2VzLkZpdFN1YmVudHJ5VHJlZWOV/PQLAYmrAgADTAAIY2hpbGRyZW50ABBMamF2YS91dGlsL0xpc3Q7TAARZGVmYXVsdFByZWNlZGVuY2VxAH4AAUwAA3ZhbHQAI0xjb3JlL2NvbXBpbGVyZXNvdXJjZXMvRml0U3ViZW50cnk7eHBzcgAUamF2YS51dGlsLkxpbmtlZExpc3QMKVNdSmCIIgMAAHhwdwQAAAABc3EAfgA4c3EAfgA8dwQAAAAAeHNxAH4ABnNxAH4ACz9AAAAAAAACdwgAAAADAAAAAHhzcQB+ABR3DAAAAAE/QAAAAAAAAHhzcgAnY29yZS5jb21waWxlcmVzb3VyY2VzLkZpdFB1U3ViZW50cnlJbXBsyeojOz7mE5oCAAFMAANtYXBxAH4AKXhyACVjb3JlLmNvbXBpbGVyZXNvdXJjZXMuRml0U3ViZW50cnlJbXBs6jkwnHf1IecCAAdJAA9jb250YWluV2hlbkVsc2VMABBmZWF0dXJlTmFtZUVudHJ5cQB+AARMAAtwcmVSZWxhdGlvbnEAfgABTAAQcHJvZ3JhbUJvZHlFbnRyeXEAfgAETAAQcHJvZ3JhbUNvbmRpdGlvbnEAfgAETAAUcHJvZ3JhbVVuaXROYW1lRW50cnlxAH4ABEwADXdoZW5Db25kaXRpb250AB9MY29yZS9sYW5nL0NvbmRpdGlvbkV4cHJlc3Npb247eHD/////dAAHQmFzaWNCSnNxAH4ABnNxAH4ACz9AAAAAAAACdwgAAAADAAAAAHhzcQB+ABR3DAAAAAE/QAAAAAAAAHh0AKkgICBwbGF5ZXIgIC4gZGVsaXZlckNhcmQgKGRlY2sgIC4gZGVhbENhcmRGYWNlVXAgKCkpICA7IGlmICggcGxheWVyICAuIGdldENhcmRzU3VtICgpICA8IDIxICkgeyAgICBwbGF5ZXIgIC4gYXNrSGl0T3JTdGFuZCAoKSAgOyB9ICBlbHNlIHsgICAgc3RhdGUgID0gU3RhdGUgIC4gSlVER0UgOyB9dAA1QmxhY2tqYWNrRG9tYWluLnN0YXRlLmVxdWFsc1RvQ29uc3RhbnQoU3RhdGUgIC4gUExBWSl0AAloaXRBdFBsYXlzcgAdY29yZS5sYW5nLkNvbmRpdGlvbkV4cHJlc3Npb26IiuIbHr1UlAIABFoACG5lZ2F0aXZlTAAFY2hpbGRxAH4ARUwABG5leHRxAH4ARUwAA3N0cnEAfgAEeHAA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

*/


/* IntermediateInfo:domains
rO0ABXNyABdqYXZhLnV0aWwuTGlua2VkSGFzaFNldNhs11qV3SoeAgAAeHIAEWphdmEudXRpbC5IYXNoU2V0ukSFlZa4tzQDAAB4cHcMAAAAED9AAAAAAAACdAAJQmxhY2tqYWNrdAAFU3BsaXR4

*/


/* IntermediateInfo:proceedToPuhandler
rO0ABXNyABNqYXZhLnV0aWwuQXJyYXlMaXN0eIHSHZnHYZ0DAAFJAARzaXpleHAAAAAEdwQAAAAEc3IAJ2NvcmUuZGF0YXN0cnVjdHVyZS5Qcm9jZWVkVG9IYW5kbGVySW5mb/NJGznJidC0AgAJTAAJY29uZGl0aW9udAASTGphdmEvbGFuZy9TdHJpbmc7TAAPZXZlbnRJZGVudGlmaWVycQB+AANMAAlldmVudFR5cGVxAH4AA0wAD2ZlYXR1cmVJbnN0YW5jZXEAfgADTAALZmVhdHVyZU5hbWVxAH4AA0wACXByb2NlZWRUb3EAfgADTAARcHJvY2VlZFRvSW5zdGFuY2VxAH4AA0wABnB1TmFtZXEAfgADTAAHcHVUb0FkZHEAfgADeHB0AClzdGF0ZSAgLiBlcXVhbHNUb0NvbnN0YW50IChTdGF0ZSAgLiBQTEFZKXQAAWV0AAVlbnRlcnQAG19uYXR1cmFsc3BsaXRmZWF0dXJlcGFja2FnZXQADE5hdHVyYWxTcGxpdHBwdAAJRW50ZXJQbGF5cHNxAH4AAnQAKnN0YXRlICAuIGVxdWFsc1RvQ29uc3RhbnQgKFN0YXRlICAuIEpVREdFKXEAfgAGdAAFZW50ZXJ0ABtfbmF0dXJhbHNwbGl0ZmVhdHVyZXBhY2thZ2VxAH4ACXBwdAAKRW50ZXJKdWRnZXBzcQB+AAJ0AClzdGF0ZSAgLiBlcXVhbHNUb0NvbnN0YW50IChTdGF0ZSAgLiBQTEFZKXEAfgAGdAAFbGVhdmV0ABtfbmF0dXJhbHNwbGl0ZmVhdHVyZXBhY2thZ2VxAH4ACXBwdAALTGVhdmVQbGF5XzBwc3EAfgACdAAqc3RhdGUgIC4gZXF1YWxzVG9Db25zdGFudCAoU3RhdGUgIC4gSlVER0UpcQB+AAZ0AAVsZWF2ZXQAG19uYXR1cmFsc3BsaXRmZWF0dXJlcGFja2FnZXEAfgAJcHB0AApMZWF2ZUp1ZGdlcHg=

*/

