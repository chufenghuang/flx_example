package blackjack;
import res.*;
import external.*;
import java.util.*;import java.rmi.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import rmi.RMIService;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class 
 BasicBJFeature extends 
 BlackjackDomain implements Serializable, Cloneable  {

private boolean activated = true;
public FL_EVENT_STEM when_event_variable;
Map<String, Boolean> previousState = new HashMap<String, Boolean>();
public EventBuffer<FL_EVENT_STEM> sharedBuffer;
public EventBuffer<FL_EVENT_STEM> getSharedBuffer(){
return this.sharedBuffer;
}
 public Object clone() throws CloneNotSupportedException { return super.clone(); }
public void activate() { }
public void deactivate() { }
public boolean isActivated() { return activated; }

public  BasicBJFeature()
{
super();
{Map<String, Boolean>currentState= new HashMap<String, Boolean>(previousState);
currentState.put("blackjackdomain.state.equalstoconstant(state.play)",BlackjackDomain.state.equalsToConstant(State.PLAY));
currentState.put("blackjackdomain.state.equalstoconstant(state.deal_card)",BlackjackDomain.state.equalsToConstant(State.DEAL_CARD));
currentState.put("blackjackdomain.state.equalstoconstant(state.end)",BlackjackDomain.state.equalsToConstant(State.END));
currentState.put("blackjackdomain.state.equalstoconstant(state.judge)",BlackjackDomain.state.equalsToConstant(State.JUDGE));
previousState = new HashMap<String, Boolean>(currentState);}
}
public  BasicBJFeature(  Player  player ,   Dealer  dealer)
{
super();
BlackjackDomain.state = State  . INIT;
BlackjackDomain.player = player;
BlackjackDomain.dealer = dealer;
BlackjackDomain.deck = deck;
{Map<String, Boolean>currentState= new HashMap<String, Boolean>(previousState);
currentState.put("blackjackdomain.state.equalstoconstant(state.play)",BlackjackDomain.state.equalsToConstant(State.PLAY));
currentState.put("blackjackdomain.state.equalstoconstant(state.deal_card)",BlackjackDomain.state.equalsToConstant(State.DEAL_CARD));
currentState.put("blackjackdomain.state.equalstoconstant(state.end)",BlackjackDomain.state.equalsToConstant(State.END));
currentState.put("blackjackdomain.state.equalstoconstant(state.judge)",BlackjackDomain.state.equalsToConstant(State.JUDGE));
previousState = new HashMap<String, Boolean>(currentState);}
sharedBuffer = new EventBuffer<FL_EVENT_STEM>();
ExecutorService threadExecuter = Executors.newCachedThreadPool();
class Consumer implements Runnable{
private EventBuffer<FL_EVENT_STEM> buffer;
public Consumer(EventBuffer<FL_EVENT_STEM> buffer){
this.buffer = buffer;
}
public void run() {
while(true){
try{
BasicBJFeature.this.XEvent(buffer.remove());
}catch(Exception ex){
ex.printStackTrace();
}//end of catch
}//end of while
}//end of run
}//end of Consumer Class
Consumer consumer = new Consumer(sharedBuffer);
threadExecuter.execute(consumer);
}

 
      
      
         
      
 /**********************************/
public synchronized void initialize(Init e)
{
try { 
if (activated)
{
player . reset ( ) ;
dealer . reset ( ) ;
System . out . println ( "game start" ) ;
state = State . DEAL_CARD ;
}

} 
finally {}
}


 
           
           
      
 /**********************************/
public synchronized void dealCardToPlayer( FL_EVENT_STEM  e)
{
try { 
if (activated)
{
player . deliverCard ( deck . dealCardFaceUp ( ) ) ;
player . deliverCard ( deck . dealCardFaceUp ( ) ) ;
state = State . PLAY ;
}

} 
finally {}
}


 
           
           
 /**********************************/
public synchronized void dealCardToDealer( FL_EVENT_STEM  e)
{
try { 
if (activated)
{
dealer . deliverCard ( deck . dealCardFaceUp ( ) ) ;
dealer . deliverCard ( deck . dealCardFaceDown ( ) ) ;
}

} 
finally {}
}


 
      
 /**********************************/
public synchronized void enterPlay( FL_EVENT_STEM  e)
{
try { 
if (activated)
{
player . askHitOrStand ( ) ;
}

} 
finally {}
}


 
           
           
      
   
      
 
 /**********************************/
public synchronized void hitAtPlay(Hit e)
{
try { 
if (activated)
{
player . deliverCard ( deck . dealCardFaceUp ( ) ) ;
if ( player . getCardsSum ( ) < 21 ) {
player . askHitOrStand ( ) ;
}
else {
state = State . JUDGE ;
}
}

} 
finally {}
}


 
      
 /**********************************/
public synchronized void standAtPlay(Stand e)
{
try { 
if (activated)
{
state = State . JUDGE ;
}

} 
finally {}
}


 
      
           
           
 
 /**********************************/
public synchronized void leaveAtPlay( FL_EVENT_STEM  e)
{
try { 
if (activated)
{
dealer . openFaceDownCards ( ) ;
while ( dealer . getCardsSum ( ) < 16 ) {
dealer . deliverCard ( deck . dealCardFaceUp ( ) ) ;
}
}

} 
finally {}
}


 
         
         
      
   
      
 
           
      
   
     
     
        
        
       
       
      
   
      
 
   
       
      
         
      
   
       
      
   
      
 
 
 
 
      
 /**********************************/
public synchronized void enterJudge( FL_EVENT_STEM  e)
{
try { 
if (activated)
{
if ( player . isBJ ( ) ) {
if ( dealer . isBJ ( ) ) {
player . hasDrawn ( ) ;
}
else {
player . hasWon ( ) ;
}
}
else if ( dealer . isBJ ( ) ) {
player . hasLost ( ) ;
}
else {
int pSum = 0 ;
int dSum = 0 ;
pSum = player . getCardsSum ( ) ;
dSum = dealer . getCardsSum ( ) ;
if ( pSum > 21 ) {
if ( dSum > 21 ) {
player . hasDrawn ( ) ;
}
else {
player . hasLost ( ) ;
}
}
else {
if ( pSum > dSum ) {
player . hasWon ( ) ;
}
else if ( pSum == dSum ) {
player . hasDrawn ( ) ;
}
else {
if ( dSum > 21 ) {
player . hasWon ( ) ;
}
else {
player . hasLost ( ) ;
}
}
}
}
state = State . END ;
}

} 
finally {}
}


 
      
 /**********************************/
public synchronized void GameOver( FL_EVENT_STEM  e)
{
try { 
if (activated)
{
player . sayGameOver ( ) ;
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
{Map<String, Boolean>currentState= new HashMap<String, Boolean>(previousState);
currentState.put("blackjackdomain.state.equalstoconstant(state.play)",BlackjackDomain.state.equalsToConstant(State.PLAY));
currentState.put("blackjackdomain.state.equalstoconstant(state.deal_card)",BlackjackDomain.state.equalsToConstant(State.DEAL_CARD));
currentState.put("blackjackdomain.state.equalstoconstant(state.end)",BlackjackDomain.state.equalsToConstant(State.END));
currentState.put("blackjackdomain.state.equalstoconstant(state.judge)",BlackjackDomain.state.equalsToConstant(State.JUDGE));
previousState = new HashMap<String, Boolean>(currentState);}
		if ( e instanceof Hit) {
boolean thishitAtPlaynotskip = true;
if (thishitAtPlaynotskip == true &&(BlackjackDomain.state.equalsToConstant(State.PLAY))) {
 if((BlackjackDomain.state.equalsToConstant(State.PLAY))) this.hitAtPlay((Hit) e);

}			}
		else if ( e instanceof Stand) {
boolean thisstandAtPlaynotskip = true;
if (thisstandAtPlaynotskip == true &&(BlackjackDomain.state.equalsToConstant(State.PLAY))) {
 if((BlackjackDomain.state.equalsToConstant(State.PLAY))) this.standAtPlay((Stand) e);

}			}
		else if ( e instanceof Init) {
boolean thisinitializenotskip = true;
if (thisinitializenotskip == true &&(BlackjackDomain.state.equalsToConstant(State.INIT))) {
 if((BlackjackDomain.state.equalsToConstant(State.INIT))) this.initialize((Init) e);

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
boolean thisdealCardToDealernotskip = true;
boolean thisleaveAtPlaynotskip = true;
if (thisdealCardToDealernotskip == true && !(BlackjackDomain.state.equalsToConstant(State.DEAL_CARD)) && previousState.get("blackjackdomain.state.equalstoconstant(state.deal_card)")) {
noLeaveEvent = false;
 if(!(BlackjackDomain.state.equalsToConstant(State.DEAL_CARD))) this.dealCardToDealer(e);

thisleaveAtPlaynotskip = false;
}if (thisleaveAtPlaynotskip == true && !(BlackjackDomain.state.equalsToConstant(State.PLAY)) && previousState.get("blackjackdomain.state.equalstoconstant(state.play)")) {
noLeaveEvent = false;
 if(!(BlackjackDomain.state.equalsToConstant(State.PLAY))) this.leaveAtPlay(e);

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
boolean thisenterPlaynotskip = true;
boolean thisenterJudgenotskip = true;
boolean thisdealCardToPlayernotskip = true;
boolean thisGameOvernotskip = true;
if (thisenterPlaynotskip == true &&(BlackjackDomain.state.equalsToConstant(State.PLAY))&&temp.get("blackjackdomain.state.equalstoconstant(state.play)")&& !previousState.get("blackjackdomain.state.equalstoconstant(state.play)")) {
noEnterEvent = false;
 if((BlackjackDomain.state.equalsToConstant(State.PLAY))) this.enterPlay(e);

thisenterJudgenotskip = false;
thisdealCardToPlayernotskip = false;
thisGameOvernotskip = false;
}if (thisenterJudgenotskip == true &&(BlackjackDomain.state.equalsToConstant(State.JUDGE))&&temp.get("blackjackdomain.state.equalstoconstant(state.judge)")&& !previousState.get("blackjackdomain.state.equalstoconstant(state.judge)")) {
noEnterEvent = false;
 if((BlackjackDomain.state.equalsToConstant(State.JUDGE))) this.enterJudge(e);

thisdealCardToPlayernotskip = false;
thisGameOvernotskip = false;
}if (thisdealCardToPlayernotskip == true &&(BlackjackDomain.state.equalsToConstant(State.DEAL_CARD))&&temp.get("blackjackdomain.state.equalstoconstant(state.deal_card)")&& !previousState.get("blackjackdomain.state.equalstoconstant(state.deal_card)")) {
noEnterEvent = false;
 if((BlackjackDomain.state.equalsToConstant(State.DEAL_CARD))) this.dealCardToPlayer(e);

thisGameOvernotskip = false;
}if (thisGameOvernotskip == true &&(BlackjackDomain.state.equalsToConstant(State.END))&&temp.get("blackjackdomain.state.equalstoconstant(state.end)")&& !previousState.get("blackjackdomain.state.equalstoconstant(state.end)")) {
noEnterEvent = false;
 if((BlackjackDomain.state.equalsToConstant(State.END))) this.GameOver(e);

}}
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
rO0ABXNyACZjb3JlLnRhYmxlLkZlYXR1cmVJbnRlcmFjdGlvblRhYmxlSW1wbKRW9OnaNkzmAgAF
WgAQaXNGZWF0dXJlTmFtZVNldEwAEmRlZmF1bHRQcmVSZWxhdGlvbnQAKkxjb3JlL2NvbXBpbGVy
ZXNvdXJjZXMvUHJlY2VkZW5jZVJlbGF0aW9uO0wAB2RvbWFpbnN0AA9MamF2YS91dGlsL1NldDtM
AApldmVudE5vZGVzdAAoTGNvcmUvY29tcGlsZXJlc291cmNlcy9FdmVudE5vZGVNYXBJbXBsO0wA
C2ZlYXR1cmVOYW1ldAASTGphdmEvbGFuZy9TdHJpbmc7eHABc3IAKWNvcmUuY29kZWdlbmVyYXRv
ci5QcmVjZWRlbmNlUmVsYXRpb25JbXBspX7kJRXyFGoCAAFMAAlwcmVPcmRlcnN0ABNMamF2YS91
dGlsL0hhc2hTZXQ7eHIAJmNvcmUuY29kZWdlbmVyYXRvci5QYXJ0aWFsT3JkZXJpbmdUcmVl2diA
5Z4HBGACAAFMAAVub2Rlc3QAFUxqYXZhL3V0aWwvSGFzaHRhYmxlO3hwc3IAE2phdmEudXRpbC5I
YXNodGFibGUTuw8lIUrkuAMAAkYACmxvYWRGYWN0b3JJAAl0aHJlc2hvbGR4cD9AAAAAAAAIdwgA
AAALAAAAAHhzcgARamF2YS51dGlsLkhhc2hTZXS6RIWVlri3NAMAAHhwdwwAAAAQP0AAAAAAAAB4
c3IAF2phdmEudXRpbC5MaW5rZWRIYXNoU2V02GzXWpXdKh4CAAB4cQB+AA13DAAAABA/QAAAAAAA
AXQACUJsYWNramFja3hzcgAmY29yZS5jb21waWxlcmVzb3VyY2VzLkV2ZW50Tm9kZU1hcEltcGxo
O63QT4+YggIAAUwAA21hcHQAD0xqYXZhL3V0aWwvTWFwO3hwc3EAfgALP0AAAAAAAAh3CAAAAAsA
AAAFdAANYmxhY2tqYWNrLkhpdHNyACpjb3JlLmNvbXBpbGVyZXNvdXJjZXMuUXVhbGlmaWVyTm9k
ZU1hcEltcGzq89x9y6uktQIAAUwAA21hcHEAfgATeHBzcQB+AAs/QAAAAAAACHcIAAAACwAAAAF0
AANhbGxzcgAqY29yZS5jb21waWxlcmVzb3VyY2VzLkNvbmRpdGlvbk5vZGVNYXBJbXBsd2wWKr7n
lGkCAAFMAANtYXB0AChMY29yZS9jb21waWxlcmVzb3VyY2VzL09yZGVyZWRIYXNodGFibGU7eHBz
cgAmY29yZS5jb21waWxlcmVzb3VyY2VzLk9yZGVyZWRIYXNodGFibGV4ZKDe0mQhMAIAAUwADGtl
eU9yZGVyTGlzdHQAFkxqYXZhL3V0aWwvTGlua2VkTGlzdDt4cQB+AAs/QAAAAAAACHcIAAAACwAA
AAF0ADVCbGFja2phY2tEb21haW4uc3RhdGUuZXF1YWxzVG9Db25zdGFudChTdGF0ZSAgLiBQTEFZ
KXNyACVjb3JlLmNvbXBpbGVyZXNvdXJjZXMuRml0U3ViZW50cnlUcmVlY5X89AsBiasCAANMAAhj
aGlsZHJlbnQAEExqYXZhL3V0aWwvTGlzdDtMABFkZWZhdWx0UHJlY2VkZW5jZXEAfgABTAADdmFs
dAAjTGNvcmUvY29tcGlsZXJlc291cmNlcy9GaXRTdWJlbnRyeTt4cHNyABRqYXZhLnV0aWwuTGlu
a2VkTGlzdAwpU11KYIgiAwAAeHB3BAAAAAFzcQB+ACJzcQB+ACZ3BAAAAAB4c3EAfgAGc3EAfgAL
P0AAAAAAAAh3CAAAAAsAAAAAeHNxAH4ADXcMAAAAED9AAAAAAAAAeHNyACdjb3JlLmNvbXBpbGVy
ZXNvdXJjZXMuRml0UHVTdWJlbnRyeUltcGzJ6iM7PuYTmgIAAUwAA21hcHEAfgATeHIAJWNvcmUu
Y29tcGlsZXJlc291cmNlcy5GaXRTdWJlbnRyeUltcGzqOTCcd/Uh5wIAB0kAD2NvbnRhaW5XaGVu
RWxzZUwAEGZlYXR1cmVOYW1lRW50cnlxAH4ABEwAC3ByZVJlbGF0aW9ucQB+AAFMABBwcm9ncmFt
Qm9keUVudHJ5cQB+AARMABBwcm9ncmFtQ29uZGl0aW9ucQB+AARMABRwcm9ncmFtVW5pdE5hbWVF
bnRyeXEAfgAETAANd2hlbkNvbmRpdGlvbnQAH0xjb3JlL2xhbmcvQ29uZGl0aW9uRXhwcmVzc2lv
bjt4cP////90AAdCYXNpY0JKc3EAfgAGc3EAfgALP0AAAAAAAAh3CAAAAAsAAAAAeHNxAH4ADXcM
AAAAED9AAAAAAAAAeHQAqSAgIHBsYXllciAgLiBkZWxpdmVyQ2FyZCAoZGVjayAgLiBkZWFsQ2Fy
ZEZhY2VVcCAoKSkgIDsgaWYgKCBwbGF5ZXIgIC4gZ2V0Q2FyZHNTdW0gKCkgIDwgMjEgKSB7ICAg
IHBsYXllciAgLiBhc2tIaXRPclN0YW5kICgpICA7IH0gIGVsc2UgeyAgICBzdGF0ZSAgPSBTdGF0
ZSAgLiBKVURHRSA7IH1xAH4AIXQACWhpdEF0UGxheXNyAB1jb3JlLmxhbmcuQ29uZGl0aW9uRXhw
cmVzc2lvboiK4hsevVSUAgAEWgAIbmVnYXRpdmVMAAVjaGlsZHEAfgAvTAAEbmV4dHEAfgAvTAAD
c3RycQB+AAR4cABwcHQABHRydWVzcgARamF2YS51dGlsLkhhc2hNYXAFB9rBwxZg0QMAAkYACmxv
YWRGYWN0b3JJAAl0aHJlc2hvbGR4cD9AAAAAAAAAdwgAAAAQAAAAAHh4c3EAfgAGc3EAfgALP0AA
AAAAAAh3CAAAAAsAAAAAeHNxAH4ADXcMAAAAED9AAAAAAAAAeHB4c3EAfgAmdwQAAAABcQB+ACF4
eHQAD2JsYWNramFjay5TdGFuZHNxAH4AF3NxAH4ACz9AAAAAAAAIdwgAAAALAAAAAXEAfgAac3EA
fgAbc3EAfgAeP0AAAAAAAAh3CAAAAAsAAAABdAA1QmxhY2tqYWNrRG9tYWluLnN0YXRlLmVxdWFs
c1RvQ29uc3RhbnQoU3RhdGUgIC4gUExBWSlzcQB+ACJzcQB+ACZ3BAAAAAFzcQB+ACJzcQB+ACZ3
BAAAAAB4c3EAfgAGc3EAfgALP0AAAAAAAAh3CAAAAAsAAAAAeHNxAH4ADXcMAAAAED9AAAAAAAAA
eHNxAH4ALf////9xAH4AMXNxAH4ABnNxAH4ACz9AAAAAAAAIdwgAAAALAAAAAHhzcQB+AA13DAAA
ABA/QAAAAAAAAHh0ABwgICBzdGF0ZSAgPSBTdGF0ZSAgLiBKVURHRSA7cQB+AEV0AAtzdGFuZEF0
UGxheXNxAH4ANwBwcHEAfgA5c3EAfgA6P0AAAAAAAAB3CAAAABAAAAAAeHhzcQB+AAZzcQB+AAs/
QAAAAAAACHcIAAAACwAAAAB4c3EAfgANdwwAAAAQP0AAAAAAAAB4cHhzcQB+ACZ3BAAAAAFxAH4A
RXh4dAAFbGVhdmVzcQB+ABdzcQB+AAs/QAAAAAAACHcIAAAACwAAAAFxAH4AGnNxAH4AG3NxAH4A
Hj9AAAAAAAAIdwgAAAALAAAAAnQAOkJsYWNramFja0RvbWFpbi5zdGF0ZS5lcXVhbHNUb0NvbnN0
YW50KFN0YXRlICAuIERFQUxfQ0FSRClzcQB+ACJzcQB+ACZ3BAAAAAFzcQB+ACJzcQB+ACZ3BAAA
AAB4c3EAfgAGc3EAfgALP0AAAAAAAAh3CAAAAAsAAAAAeHNxAH4ADXcMAAAAED9AAAAAAAAAeHNx
AH4ALf////9xAH4AMXNxAH4ABnNxAH4ACz9AAAAAAAAIdwgAAAALAAAAAHhzcQB+AA13DAAAABA/
QAAAAAAAAHh0AHAgICBkZWFsZXIgIC4gZGVsaXZlckNhcmQgKGRlY2sgIC4gZGVhbENhcmRGYWNl
VXAgKCkpICA7ICAgZGVhbGVyICAuIGRlbGl2ZXJDYXJkIChkZWNrICAuIGRlYWxDYXJkRmFjZURv
d24gKCkpICA7cQB+AF50ABBkZWFsQ2FyZFRvRGVhbGVyc3EAfgA3AHBwcQB+ADlzcQB+ADo/QAAA
AAAAAHcIAAAAEAAAAAB4eHNxAH4ABnNxAH4ACz9AAAAAAAAIdwgAAAALAAAAAHhzcQB+AA13DAAA
ABA/QAAAAAAAAHhwdAA1QmxhY2tqYWNrRG9tYWluLnN0YXRlLmVxdWFsc1RvQ29uc3RhbnQoU3Rh
dGUgIC4gUExBWSlzcQB+ACJzcQB+ACZ3BAAAAAFzcQB+ACJzcQB+ACZ3BAAAAAB4c3EAfgAGc3EA
fgALP0AAAAAAAAh3CAAAAAsAAAAAeHNxAH4ADXcMAAAAED9AAAAAAAAAeHNxAH4ALf////9xAH4A
MXNxAH4ABnNxAH4ACz9AAAAAAAAIdwgAAAALAAAAAHhzcQB+AA13DAAAABA/QAAAAAAAAHh0AIkg
ICBkZWFsZXIgIC4gb3BlbkZhY2VEb3duQ2FyZHMgKCkgIDsgd2hpbGUgKCBkZWFsZXIgIC4gZ2V0
Q2FyZHNTdW0gKCkgIDwgMTYgKSB7ICAgIGRlYWxlciAgLiBkZWxpdmVyQ2FyZCAoZGVjayAgLiBk
ZWFsQ2FyZEZhY2VVcCAoKSkgIDsgfXEAfgBxdAALbGVhdmVBdFBsYXlzcQB+ADcAcHBxAH4AOXNx
AH4AOj9AAAAAAAAAdwgAAAAQAAAAAHh4c3EAfgAGc3EAfgALP0AAAAAAAAh3CAAAAAsAAAAAeHNx
AH4ADXcMAAAAED9AAAAAAAAAeHB4c3EAfgAmdwQAAAACcQB+AF5xAH4AcXh4dAAOYmxhY2tqYWNr
LkluaXRzcQB+ABdzcQB+AAs/QAAAAAAACHcIAAAACwAAAAFxAH4AGnNxAH4AG3NxAH4AHj9AAAAA
AAAIdwgAAAALAAAAAXQANUJsYWNramFja0RvbWFpbi5zdGF0ZS5lcXVhbHNUb0NvbnN0YW50KFN0
YXRlICAuIElOSVQpc3EAfgAic3EAfgAmdwQAAAABc3EAfgAic3EAfgAmdwQAAAAAeHNxAH4ABnNx
AH4ACz9AAAAAAAAIdwgAAAALAAAAAHhzcQB+AA13DAAAABA/QAAAAAAAAHhzcQB+AC3/////cQB+
ADFzcQB+AAZzcQB+AAs/QAAAAAAACHcIAAAACwAAAAB4c3EAfgANdwwAAAAQP0AAAAAAAAB4dAB9
ICAgcGxheWVyICAuIHJlc2V0ICgpICA7ICAgZGVhbGVyICAuIHJlc2V0ICgpICA7ICAgU3lzdGVt
ICAuIG91dCAgLiBwcmludGxuICgiZ2FtZSBzdGFydCIpICA7ICAgc3RhdGUgID0gU3RhdGUgIC4g
REVBTF9DQVJEIDtxAH4AinQACmluaXRpYWxpemVzcQB+ADcAcHBxAH4AOXNxAH4AOj9AAAAAAAAA
dwgAAAAQAAAAAHh4c3EAfgAGc3EAfgALP0AAAAAAAAh3CAAAAAsAAAAAeHNxAH4ADXcMAAAAED9A
AAAAAAAAeHB4c3EAfgAmdwQAAAABcQB+AIp4eHQABWVudGVyc3EAfgAXc3EAfgALP0AAAAAAAAh3
CAAAAAsAAAABcQB+ABpzcQB+ABtzcQB+AB4/QAAAAAAACHcIAAAACwAAAAR0ADpCbGFja2phY2tE
b21haW4uc3RhdGUuZXF1YWxzVG9Db25zdGFudChTdGF0ZSAgLiBERUFMX0NBUkQpc3EAfgAic3EA
fgAmdwQAAAABc3EAfgAic3EAfgAmdwQAAAAAeHNxAH4ABnNxAH4ACz9AAAAAAAAIdwgAAAALAAAA
AHhzcQB+AA13DAAAABA/QAAAAAAAAHhzcQB+AC3/////cQB+ADFzcQB+AAZzcQB+AAs/QAAAAAAA
CHcIAAAACwAAAAB4c3EAfgANdwwAAAAQP0AAAAAAAAB4dACJICAgcGxheWVyICAuIGRlbGl2ZXJD
YXJkIChkZWNrICAuIGRlYWxDYXJkRmFjZVVwICgpKSAgOyAgIHBsYXllciAgLiBkZWxpdmVyQ2Fy
ZCAoZGVjayAgLiBkZWFsQ2FyZEZhY2VVcCAoKSkgIDsgICBzdGF0ZSAgPSBTdGF0ZSAgLiBQTEFZ
IDtxAH4Ao3QAEGRlYWxDYXJkVG9QbGF5ZXJzcQB+ADcAcHBxAH4AOXNxAH4AOj9AAAAAAAAAdwgA
AAAQAAAAAHh4c3EAfgAGc3EAfgALP0AAAAAAAAh3CAAAAAsAAAAAeHNxAH4ADXcMAAAAED9AAAAA
AAAAeHB0ADVCbGFja2phY2tEb21haW4uc3RhdGUuZXF1YWxzVG9Db25zdGFudChTdGF0ZSAgLiBQ
TEFZKXNxAH4AInNxAH4AJncEAAAAAXNxAH4AInNxAH4AJncEAAAAAHhzcQB+AAZzcQB+AAs/QAAA
AAAACHcIAAAACwAAAAB4c3EAfgANdwwAAAAQP0AAAAAAAAB4c3EAfgAt/////3EAfgAxc3EAfgAG
c3EAfgALP0AAAAAAAAh3CAAAAAsAAAAAeHNxAH4ADXcMAAAAED9AAAAAAAAAeHQAICAgIHBsYXll
ciAgLiBhc2tIaXRPclN0YW5kICgpICA7cQB+ALZ0AAllbnRlclBsYXlzcQB+ADcAcHBxAH4AOXNx
AH4AOj9AAAAAAAAAdwgAAAAQAAAAAHh4c3EAfgAGc3EAfgALP0AAAAAAAAh3CAAAAAsAAAAAeHNx
AH4ADXcMAAAAED9AAAAAAAAAeHB0ADZCbGFja2phY2tEb21haW4uc3RhdGUuZXF1YWxzVG9Db25z
dGFudChTdGF0ZSAgLiBKVURHRSlzcQB+ACJzcQB+ACZ3BAAAAAFzcQB+ACJzcQB+ACZ3BAAAAAB4
c3EAfgAGc3EAfgALP0AAAAAAAAh3CAAAAAsAAAAAeHNxAH4ADXcMAAAAED9AAAAAAAAAeHNxAH4A
Lf////9xAH4AMXNxAH4ABnNxAH4ACz9AAAAAAAAIdwgAAAALAAAAAHhzcQB+AA13DAAAABA/QAAA
AAAAAHh0ApIgaWYgKCBwbGF5ZXIgIC4gaXNCSiAoKSApIHsgIGlmICggZGVhbGVyICAuIGlzQkog
KCkgKSB7ICAgIHBsYXllciAgLiBoYXNEcmF3biAoKSAgOyB9ICBlbHNlIHsgICAgcGxheWVyICAu
IGhhc1dvbiAoKSAgOyB9IH0gIGVsc2UgaWYgKCBkZWFsZXIgIC4gaXNCSiAoKSApIHsgICAgcGxh
eWVyICAuIGhhc0xvc3QgKCkgIDsgfSAgZWxzZSB7ICAgIGludCBwU3VtID0gMCAgOyAgIGludCBk
U3VtID0gMCAgOyAgIHBTdW0gID0gcGxheWVyICAuIGdldENhcmRzU3VtICgpIDsgICBkU3VtICA9
IGRlYWxlciAgLiBnZXRDYXJkc1N1bSAoKSA7IGlmICggcFN1bSAgPiAyMSApIHsgIGlmICggZFN1
bSAgPiAyMSApIHsgICAgcGxheWVyICAuIGhhc0RyYXduICgpICA7IH0gIGVsc2UgeyAgICBwbGF5
ZXIgIC4gaGFzTG9zdCAoKSAgOyB9IH0gIGVsc2UgeyAgaWYgKCBwU3VtICA+IGRTdW0gKSB7ICAg
IHBsYXllciAgLiBoYXNXb24gKCkgIDsgfSAgZWxzZSBpZiAoIHBTdW0gID09IGRTdW0gKSB7ICAg
IHBsYXllciAgLiBoYXNEcmF3biAoKSAgOyB9ICBlbHNlIHsgIGlmICggZFN1bSAgPiAyMSApIHsg
ICAgcGxheWVyICAuIGhhc1dvbiAoKSAgOyB9ICBlbHNlIHsgICAgcGxheWVyICAuIGhhc0xvc3Qg
KCkgIDsgfSB9IH0gfSAgIHN0YXRlICA9IFN0YXRlICAuIEVORCA7cQB+AMl0AAplbnRlckp1ZGdl
c3EAfgA3AHBwcQB+ADlzcQB+ADo/QAAAAAAAAHcIAAAAEAAAAAB4eHNxAH4ABnNxAH4ACz9AAAAA
AAAIdwgAAAALAAAAAHhzcQB+AA13DAAAABA/QAAAAAAAAHhwdAA0QmxhY2tqYWNrRG9tYWluLnN0
YXRlLmVxdWFsc1RvQ29uc3RhbnQoU3RhdGUgIC4gRU5EKXNxAH4AInNxAH4AJncEAAAAAXNxAH4A
InNxAH4AJncEAAAAAHhzcQB+AAZzcQB+AAs/QAAAAAAACHcIAAAACwAAAAB4c3EAfgANdwwAAAAQ
P0AAAAAAAAB4c3EAfgAt/////3EAfgAxc3EAfgAGc3EAfgALP0AAAAAAAAh3CAAAAAsAAAAAeHNx
AH4ADXcMAAAAED9AAAAAAAAAeHQAHiAgIHBsYXllciAgLiBzYXlHYW1lT3ZlciAoKSAgO3EAfgDc
dAAIR2FtZU92ZXJzcQB+ADcAcHBxAH4AOXNxAH4AOj9AAAAAAAAAdwgAAAAQAAAAAHh4c3EAfgAG
c3EAfgALP0AAAAAAAAh3CAAAAAsAAAAAeHNxAH4ADXcMAAAAED9AAAAAAAAAeHB4c3EAfgAmdwQA
AAAEcQB+AKNxAH4AtnEAfgDJcQB+ANx4eHhxAH4AMQ==

*/


/* IntermediateInfo:MergedInteractionTable
rO0ABXNyACZjb3JlLnRhYmxlLkZlYXR1cmVJbnRlcmFjdGlvblRhYmxlSW1wbKRW9OnaNkzmAgAF
WgAQaXNGZWF0dXJlTmFtZVNldEwAEmRlZmF1bHRQcmVSZWxhdGlvbnQAKkxjb3JlL2NvbXBpbGVy
ZXNvdXJjZXMvUHJlY2VkZW5jZVJlbGF0aW9uO0wAB2RvbWFpbnN0AA9MamF2YS91dGlsL1NldDtM
AApldmVudE5vZGVzdAAoTGNvcmUvY29tcGlsZXJlc291cmNlcy9FdmVudE5vZGVNYXBJbXBsO0wA
C2ZlYXR1cmVOYW1ldAASTGphdmEvbGFuZy9TdHJpbmc7eHABc3IAKWNvcmUuY29kZWdlbmVyYXRv
ci5QcmVjZWRlbmNlUmVsYXRpb25JbXBspX7kJRXyFGoCAAFMAAlwcmVPcmRlcnN0ABNMamF2YS91
dGlsL0hhc2hTZXQ7eHIAJmNvcmUuY29kZWdlbmVyYXRvci5QYXJ0aWFsT3JkZXJpbmdUcmVl2diA
5Z4HBGACAAFMAAVub2Rlc3QAFUxqYXZhL3V0aWwvSGFzaHRhYmxlO3hwc3IAE2phdmEudXRpbC5I
YXNodGFibGUTuw8lIUrkuAMAAkYACmxvYWRGYWN0b3JJAAl0aHJlc2hvbGR4cD9AAAAAAAAIdwgA
AAALAAAAAHhzcgARamF2YS51dGlsLkhhc2hTZXS6RIWVlri3NAMAAHhwdwwAAAAQP0AAAAAAAAB4
c3IAF2phdmEudXRpbC5MaW5rZWRIYXNoU2V02GzXWpXdKh4CAAB4cQB+AA13DAAAABA/QAAAAAAA
AXQACUJsYWNramFja3hzcgAmY29yZS5jb21waWxlcmVzb3VyY2VzLkV2ZW50Tm9kZU1hcEltcGxo
O63QT4+YggIAAUwAA21hcHQAD0xqYXZhL3V0aWwvTWFwO3hwc3EAfgALP0AAAAAAAAh3CAAAAAsA
AAAFdAANYmxhY2tqYWNrLkhpdHNyACpjb3JlLmNvbXBpbGVyZXNvdXJjZXMuUXVhbGlmaWVyTm9k
ZU1hcEltcGzq89x9y6uktQIAAUwAA21hcHEAfgATeHBzcQB+AAs/QAAAAAAACHcIAAAACwAAAAF0
AANhbGxzcgAqY29yZS5jb21waWxlcmVzb3VyY2VzLkNvbmRpdGlvbk5vZGVNYXBJbXBsd2wWKr7n
lGkCAAFMAANtYXB0AChMY29yZS9jb21waWxlcmVzb3VyY2VzL09yZGVyZWRIYXNodGFibGU7eHBz
cgAmY29yZS5jb21waWxlcmVzb3VyY2VzLk9yZGVyZWRIYXNodGFibGV4ZKDe0mQhMAIAAUwADGtl
eU9yZGVyTGlzdHQAFkxqYXZhL3V0aWwvTGlua2VkTGlzdDt4cQB+AAs/QAAAAAAACHcIAAAACwAA
AAF0AD1CYXNpY0JKLkJsYWNramFja0RvbWFpbi5zdGF0ZS5lcXVhbHNUb0NvbnN0YW50KFN0YXRl
ICAuIFBMQVkpc3IAJWNvcmUuY29tcGlsZXJlc291cmNlcy5GaXRTdWJlbnRyeVRyZWVjlfz0CwGJ
qwIAA0wACGNoaWxkcmVudAAQTGphdmEvdXRpbC9MaXN0O0wAEWRlZmF1bHRQcmVjZWRlbmNlcQB+
AAFMAAN2YWx0ACNMY29yZS9jb21waWxlcmVzb3VyY2VzL0ZpdFN1YmVudHJ5O3hwc3IAFGphdmEu
dXRpbC5MaW5rZWRMaXN0DClTXUpgiCIDAAB4cHcEAAAAAXNxAH4AInNxAH4AJncEAAAAAHhzcQB+
AAZzcQB+AAs/QAAAAAAACHcIAAAACwAAAAB4c3EAfgANdwwAAAAQP0AAAAAAAAB4c3IAJ2NvcmUu
Y29tcGlsZXJlc291cmNlcy5GaXRQdVN1YmVudHJ5SW1wbMnqIzs+5hOaAgABTAADbWFwcQB+ABN4
cgAlY29yZS5jb21waWxlcmVzb3VyY2VzLkZpdFN1YmVudHJ5SW1wbOo5MJx39SHnAgAHSQAPY29u
dGFpbldoZW5FbHNlTAAQZmVhdHVyZU5hbWVFbnRyeXEAfgAETAALcHJlUmVsYXRpb25xAH4AAUwA
EHByb2dyYW1Cb2R5RW50cnlxAH4ABEwAEHByb2dyYW1Db25kaXRpb25xAH4ABEwAFHByb2dyYW1V
bml0TmFtZUVudHJ5cQB+AARMAA13aGVuQ29uZGl0aW9udAAfTGNvcmUvbGFuZy9Db25kaXRpb25F
eHByZXNzaW9uO3hw/////3QAB0Jhc2ljQkpzcQB+AAZzcQB+AAs/QAAAAAAACHcIAAAACwAAAAB4
c3EAfgANdwwAAAAQP0AAAAAAAAB4dACpICAgcGxheWVyICAuIGRlbGl2ZXJDYXJkIChkZWNrICAu
IGRlYWxDYXJkRmFjZVVwICgpKSAgOyBpZiAoIHBsYXllciAgLiBnZXRDYXJkc1N1bSAoKSAgPCAy
MSApIHsgICAgcGxheWVyICAuIGFza0hpdE9yU3RhbmQgKCkgIDsgfSAgZWxzZSB7ICAgIHN0YXRl
ICA9IFN0YXRlICAuIEpVREdFIDsgfXQANUJsYWNramFja0RvbWFpbi5zdGF0ZS5lcXVhbHNUb0Nv
bnN0YW50KFN0YXRlICAuIFBMQVkpdAAJaGl0QXRQbGF5c3IAHWNvcmUubGFuZy5Db25kaXRpb25F
eHByZXNzaW9uiIriGx69VJQCAARaAAhuZWdhdGl2ZUwABWNoaWxkcQB+AC9MAARuZXh0cQB+AC9M
AANzdHJxAH4ABHhwAHBwdAAEdHJ1ZXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAK
bG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAB3CAAAABAAAAAAeHhzcQB+AAZzcQB+AAs/
QAAAAAAACHcIAAAACwAAAAB4c3EAfgANdwwAAAAQP0AAAAAAAAB4cHhzcQB+ACZ3BAAAAAFxAH4A
IXh4dAAPYmxhY2tqYWNrLlN0YW5kc3EAfgAXc3EAfgALP0AAAAAAAAh3CAAAAAsAAAABcQB+ABpz
cQB+ABtzcQB+AB4/QAAAAAAACHcIAAAACwAAAAF0AD1CYXNpY0JKLkJsYWNramFja0RvbWFpbi5z
dGF0ZS5lcXVhbHNUb0NvbnN0YW50KFN0YXRlICAuIFBMQVkpc3EAfgAic3EAfgAmdwQAAAABc3EA
fgAic3EAfgAmdwQAAAAAeHNxAH4ABnNxAH4ACz9AAAAAAAAIdwgAAAALAAAAAHhzcQB+AA13DAAA
ABA/QAAAAAAAAHhzcQB+AC3/////cQB+ADFzcQB+AAZzcQB+AAs/QAAAAAAACHcIAAAACwAAAAB4
c3EAfgANdwwAAAAQP0AAAAAAAAB4dAAcICAgc3RhdGUgID0gU3RhdGUgIC4gSlVER0UgO3QANUJs
YWNramFja0RvbWFpbi5zdGF0ZS5lcXVhbHNUb0NvbnN0YW50KFN0YXRlICAuIFBMQVkpdAALc3Rh
bmRBdFBsYXlzcQB+ADgAcHBxAH4AOnNxAH4AOz9AAAAAAAAAdwgAAAAQAAAAAHh4c3EAfgAGc3EA
fgALP0AAAAAAAAh3CAAAAAsAAAAAeHNxAH4ADXcMAAAAED9AAAAAAAAAeHB4c3EAfgAmdwQAAAAB
cQB+AEZ4eHQABWxlYXZlc3EAfgAXc3EAfgALP0AAAAAAAAh3CAAAAAsAAAABcQB+ABpzcQB+ABtz
cQB+AB4/QAAAAAAACHcIAAAACwAAAAJ0AD1CYXNpY0JKLkJsYWNramFja0RvbWFpbi5zdGF0ZS5l
cXVhbHNUb0NvbnN0YW50KFN0YXRlICAuIFBMQVkpc3EAfgAic3EAfgAmdwQAAAABc3EAfgAic3EA
fgAmdwQAAAAAeHNxAH4ABnNxAH4ACz9AAAAAAAAIdwgAAAALAAAAAHhzcQB+AA13DAAAABA/QAAA
AAAAAHhzcQB+AC3/////cQB+ADFzcQB+AAZzcQB+AAs/QAAAAAAACHcIAAAACwAAAAB4c3EAfgAN
dwwAAAAQP0AAAAAAAAB4dACJICAgZGVhbGVyICAuIG9wZW5GYWNlRG93bkNhcmRzICgpICA7IHdo
aWxlICggZGVhbGVyICAuIGdldENhcmRzU3VtICgpICA8IDE2ICkgeyAgICBkZWFsZXIgIC4gZGVs
aXZlckNhcmQgKGRlY2sgIC4gZGVhbENhcmRGYWNlVXAgKCkpICA7IH10ADVCbGFja2phY2tEb21h
aW4uc3RhdGUuZXF1YWxzVG9Db25zdGFudChTdGF0ZSAgLiBQTEFZKXQAC2xlYXZlQXRQbGF5c3EA
fgA4AHBwcQB+ADpzcQB+ADs/QAAAAAAAAHcIAAAAEAAAAAB4eHNxAH4ABnNxAH4ACz9AAAAAAAAI
dwgAAAALAAAAAHhzcQB+AA13DAAAABA/QAAAAAAAAHhwdABCQmFzaWNCSi5CbGFja2phY2tEb21h
aW4uc3RhdGUuZXF1YWxzVG9Db25zdGFudChTdGF0ZSAgLiBERUFMX0NBUkQpc3EAfgAic3EAfgAm
dwQAAAABc3EAfgAic3EAfgAmdwQAAAAAeHNxAH4ABnNxAH4ACz9AAAAAAAAIdwgAAAALAAAAAHhz
cQB+AA13DAAAABA/QAAAAAAAAHhzcQB+AC3/////cQB+ADFzcQB+AAZzcQB+AAs/QAAAAAAACHcI
AAAACwAAAAB4c3EAfgANdwwAAAAQP0AAAAAAAAB4dABwICAgZGVhbGVyICAuIGRlbGl2ZXJDYXJk
IChkZWNrICAuIGRlYWxDYXJkRmFjZVVwICgpKSAgOyAgIGRlYWxlciAgLiBkZWxpdmVyQ2FyZCAo
ZGVjayAgLiBkZWFsQ2FyZEZhY2VEb3duICgpKSAgO3QAOkJsYWNramFja0RvbWFpbi5zdGF0ZS5l
cXVhbHNUb0NvbnN0YW50KFN0YXRlICAuIERFQUxfQ0FSRCl0ABBkZWFsQ2FyZFRvRGVhbGVyc3EA
fgA4AHBwcQB+ADpzcQB+ADs/QAAAAAAAAHcIAAAAEAAAAAB4eHNxAH4ABnNxAH4ACz9AAAAAAAAI
dwgAAAALAAAAAHhzcQB+AA13DAAAABA/QAAAAAAAAHhweHNxAH4AJncEAAAAAnEAfgB0cQB+AGB4
eHQADmJsYWNramFjay5Jbml0c3EAfgAXc3EAfgALP0AAAAAAAAh3CAAAAAsAAAABcQB+ABpzcQB+
ABtzcQB+AB4/QAAAAAAACHcIAAAACwAAAAF0AD1CYXNpY0JKLkJsYWNramFja0RvbWFpbi5zdGF0
ZS5lcXVhbHNUb0NvbnN0YW50KFN0YXRlICAuIElOSVQpc3EAfgAic3EAfgAmdwQAAAABc3EAfgAi
c3EAfgAmdwQAAAAAeHNxAH4ABnNxAH4ACz9AAAAAAAAIdwgAAAALAAAAAHhzcQB+AA13DAAAABA/
QAAAAAAAAHhzcQB+AC3/////cQB+ADFzcQB+AAZzcQB+AAs/QAAAAAAACHcIAAAACwAAAAB4c3EA
fgANdwwAAAAQP0AAAAAAAAB4dAB9ICAgcGxheWVyICAuIHJlc2V0ICgpICA7ICAgZGVhbGVyICAu
IHJlc2V0ICgpICA7ICAgU3lzdGVtICAuIG91dCAgLiBwcmludGxuICgiZ2FtZSBzdGFydCIpICA7
ICAgc3RhdGUgID0gU3RhdGUgIC4gREVBTF9DQVJEIDt0ADVCbGFja2phY2tEb21haW4uc3RhdGUu
ZXF1YWxzVG9Db25zdGFudChTdGF0ZSAgLiBJTklUKXQACmluaXRpYWxpemVzcQB+ADgAcHBxAH4A
OnNxAH4AOz9AAAAAAAAAdwgAAAAQAAAAAHh4c3EAfgAGc3EAfgALP0AAAAAAAAh3CAAAAAsAAAAA
eHNxAH4ADXcMAAAAED9AAAAAAAAAeHB4c3EAfgAmdwQAAAABcQB+AI54eHQABWVudGVyc3EAfgAX
c3EAfgALP0AAAAAAAAh3CAAAAAsAAAABcQB+ABpzcQB+ABtzcQB+AB4/QAAAAAAACHcIAAAACwAA
AAR0ADxCYXNpY0JKLkJsYWNramFja0RvbWFpbi5zdGF0ZS5lcXVhbHNUb0NvbnN0YW50KFN0YXRl
ICAuIEVORClzcQB+ACJzcQB+ACZ3BAAAAAFzcQB+ACJzcQB+ACZ3BAAAAAB4c3EAfgAGc3EAfgAL
P0AAAAAAAAh3CAAAAAsAAAAAeHNxAH4ADXcMAAAAED9AAAAAAAAAeHNxAH4ALf////9xAH4AMXNx
AH4ABnNxAH4ACz9AAAAAAAAIdwgAAAALAAAAAHhzcQB+AA13DAAAABA/QAAAAAAAAHh0AB4gICBw
bGF5ZXIgIC4gc2F5R2FtZU92ZXIgKCkgIDt0ADRCbGFja2phY2tEb21haW4uc3RhdGUuZXF1YWxz
VG9Db25zdGFudChTdGF0ZSAgLiBFTkQpdAAIR2FtZU92ZXJzcQB+ADgAcHBxAH4AOnNxAH4AOz9A
AAAAAAAAdwgAAAAQAAAAAHh4c3EAfgAGc3EAfgALP0AAAAAAAAh3CAAAAAsAAAAAeHNxAH4ADXcM
AAAAED9AAAAAAAAAeHB0AD5CYXNpY0JKLkJsYWNramFja0RvbWFpbi5zdGF0ZS5lcXVhbHNUb0Nv
bnN0YW50KFN0YXRlICAuIEpVREdFKXNxAH4AInNxAH4AJncEAAAAAXNxAH4AInNxAH4AJncEAAAA
AHhzcQB+AAZzcQB+AAs/QAAAAAAACHcIAAAACwAAAAB4c3EAfgANdwwAAAAQP0AAAAAAAAB4c3EA
fgAt/////3EAfgAxc3EAfgAGc3EAfgALP0AAAAAAAAh3CAAAAAsAAAAAeHNxAH4ADXcMAAAAED9A
AAAAAAAAeHQCkiBpZiAoIHBsYXllciAgLiBpc0JKICgpICkgeyAgaWYgKCBkZWFsZXIgIC4gaXNC
SiAoKSApIHsgICAgcGxheWVyICAuIGhhc0RyYXduICgpICA7IH0gIGVsc2UgeyAgICBwbGF5ZXIg
IC4gaGFzV29uICgpICA7IH0gfSAgZWxzZSBpZiAoIGRlYWxlciAgLiBpc0JKICgpICkgeyAgICBw
bGF5ZXIgIC4gaGFzTG9zdCAoKSAgOyB9ICBlbHNlIHsgICAgaW50IHBTdW0gPSAwICA7ICAgaW50
IGRTdW0gPSAwICA7ICAgcFN1bSAgPSBwbGF5ZXIgIC4gZ2V0Q2FyZHNTdW0gKCkgOyAgIGRTdW0g
ID0gZGVhbGVyICAuIGdldENhcmRzU3VtICgpIDsgaWYgKCBwU3VtICA+IDIxICkgeyAgaWYgKCBk
U3VtICA+IDIxICkgeyAgICBwbGF5ZXIgIC4gaGFzRHJhd24gKCkgIDsgfSAgZWxzZSB7ICAgIHBs
YXllciAgLiBoYXNMb3N0ICgpICA7IH0gfSAgZWxzZSB7ICBpZiAoIHBTdW0gID4gZFN1bSApIHsg
ICAgcGxheWVyICAuIGhhc1dvbiAoKSAgOyB9ICBlbHNlIGlmICggcFN1bSAgPT0gZFN1bSApIHsg
ICAgcGxheWVyICAuIGhhc0RyYXduICgpICA7IH0gIGVsc2UgeyAgaWYgKCBkU3VtICA+IDIxICkg
eyAgICBwbGF5ZXIgIC4gaGFzV29uICgpICA7IH0gIGVsc2UgeyAgICBwbGF5ZXIgIC4gaGFzTG9z
dCAoKSAgOyB9IH0gfSB9ICAgc3RhdGUgID0gU3RhdGUgIC4gRU5EIDt0ADZCbGFja2phY2tEb21h
aW4uc3RhdGUuZXF1YWxzVG9Db25zdGFudChTdGF0ZSAgLiBKVURHRSl0AAplbnRlckp1ZGdlc3EA
fgA4AHBwcQB+ADpzcQB+ADs/QAAAAAAAAHcIAAAAEAAAAAB4eHNxAH4ABnNxAH4ACz9AAAAAAAAI
dwgAAAALAAAAAHhzcQB+AA13DAAAABA/QAAAAAAAAHhwdAA9QmFzaWNCSi5CbGFja2phY2tEb21h
aW4uc3RhdGUuZXF1YWxzVG9Db25zdGFudChTdGF0ZSAgLiBQTEFZKXNxAH4AInNxAH4AJncEAAAA
AXNxAH4AInNxAH4AJncEAAAAAHhzcQB+AAZzcQB+AAs/QAAAAAAACHcIAAAACwAAAAB4c3EAfgAN
dwwAAAAQP0AAAAAAAAB4c3EAfgAt/////3EAfgAxc3EAfgAGc3EAfgALP0AAAAAAAAh3CAAAAAsA
AAAAeHNxAH4ADXcMAAAAED9AAAAAAAAAeHQAICAgIHBsYXllciAgLiBhc2tIaXRPclN0YW5kICgp
ICA7dAA1QmxhY2tqYWNrRG9tYWluLnN0YXRlLmVxdWFsc1RvQ29uc3RhbnQoU3RhdGUgIC4gUExB
WSl0AAllbnRlclBsYXlzcQB+ADgAcHBxAH4AOnNxAH4AOz9AAAAAAAAAdwgAAAAQAAAAAHh4c3EA
fgAGc3EAfgALP0AAAAAAAAh3CAAAAAsAAAAAeHNxAH4ADXcMAAAAED9AAAAAAAAAeHB0AEJCYXNp
Y0JKLkJsYWNramFja0RvbWFpbi5zdGF0ZS5lcXVhbHNUb0NvbnN0YW50KFN0YXRlICAuIERFQUxf
Q0FSRClzcQB+ACJzcQB+ACZ3BAAAAAFzcQB+ACJzcQB+ACZ3BAAAAAB4c3EAfgAGc3EAfgALP0AA
AAAAAAh3CAAAAAsAAAAAeHNxAH4ADXcMAAAAED9AAAAAAAAAeHNxAH4ALf////9xAH4AMXNxAH4A
BnNxAH4ACz9AAAAAAAAIdwgAAAALAAAAAHhzcQB+AA13DAAAABA/QAAAAAAAAHh0AIkgICBwbGF5
ZXIgIC4gZGVsaXZlckNhcmQgKGRlY2sgIC4gZGVhbENhcmRGYWNlVXAgKCkpICA7ICAgcGxheWVy
ICAuIGRlbGl2ZXJDYXJkIChkZWNrICAuIGRlYWxDYXJkRmFjZVVwICgpKSAgOyAgIHN0YXRlICA9
IFN0YXRlICAuIFBMQVkgO3QAOkJsYWNramFja0RvbWFpbi5zdGF0ZS5lcXVhbHNUb0NvbnN0YW50
KFN0YXRlICAuIERFQUxfQ0FSRCl0ABBkZWFsQ2FyZFRvUGxheWVyc3EAfgA4AHBwcQB+ADpzcQB+
ADs/QAAAAAAAAHcIAAAAEAAAAAB4eHNxAH4ABnNxAH4ACz9AAAAAAAAIdwgAAAALAAAAAHhzcQB+
AA13DAAAABA/QAAAAAAAAHhweHNxAH4AJncEAAAABHEAfgDkcQB+ALxxAH4A0HEAfgCoeHh4cQB+
ADE=

*/


/* IntermediateInfo:domains
rO0ABXNyABdqYXZhLnV0aWwuTGlua2VkSGFzaFNldNhs11qV3SoeAgAAeHIAEWphdmEudXRpbC5I
YXNoU2V0ukSFlZa4tzQDAAB4cHcMAAAAED9AAAAAAAABdAAJQmxhY2tqYWNreA==

*/


/* IntermediateInfo:proceedToPuhandler
rO0ABXNyABNqYXZhLnV0aWwuQXJyYXlMaXN0eIHSHZnHYZ0DAAFJAARzaXpleHAAAAAJdwQAAAAJ
c3IAJ2NvcmUuZGF0YXN0cnVjdHVyZS5Qcm9jZWVkVG9IYW5kbGVySW5mb/NJGznJidC0AgAJTAAJ
Y29uZGl0aW9udAASTGphdmEvbGFuZy9TdHJpbmc7TAAPZXZlbnRJZGVudGlmaWVycQB+AANMAAll
dmVudFR5cGVxAH4AA0wAD2ZlYXR1cmVJbnN0YW5jZXEAfgADTAALZmVhdHVyZU5hbWVxAH4AA0wA
CXByb2NlZWRUb3EAfgADTAARcHJvY2VlZFRvSW5zdGFuY2VxAH4AA0wABnB1TmFtZXEAfgADTAAH
cHVUb0FkZHEAfgADeHB0ADVCbGFja2phY2tEb21haW4uc3RhdGUuZXF1YWxzVG9Db25zdGFudChT
dGF0ZSAgLiBJTklUKXQAAWV0AA5ibGFja2phY2suSW5pdHQAD19iYXNpY2JqZmVhdHVyZXQAB0Jh
c2ljQkpwcHQACmluaXRpYWxpemVwc3EAfgACdAA6QmxhY2tqYWNrRG9tYWluLnN0YXRlLmVxdWFs
c1RvQ29uc3RhbnQoU3RhdGUgIC4gREVBTF9DQVJEKXEAfgAGdAAFZW50ZXJ0AA9fYmFzaWNiamZl
YXR1cmVxAH4ACXBwdAAQZGVhbENhcmRUb1BsYXllcnBzcQB+AAJ0ADpCbGFja2phY2tEb21haW4u
c3RhdGUuZXF1YWxzVG9Db25zdGFudChTdGF0ZSAgLiBERUFMX0NBUkQpcQB+AAZ0AAVsZWF2ZXQA
D19iYXNpY2JqZmVhdHVyZXEAfgAJcHB0ABBkZWFsQ2FyZFRvRGVhbGVycHNxAH4AAnQANUJsYWNr
amFja0RvbWFpbi5zdGF0ZS5lcXVhbHNUb0NvbnN0YW50KFN0YXRlICAuIFBMQVkpcQB+AAZ0AAVl
bnRlcnQAD19iYXNpY2JqZmVhdHVyZXEAfgAJcHB0AAllbnRlclBsYXlwc3EAfgACdAA1QmxhY2tq
YWNrRG9tYWluLnN0YXRlLmVxdWFsc1RvQ29uc3RhbnQoU3RhdGUgIC4gUExBWSlxAH4ABnQADWJs
YWNramFjay5IaXR0AA9fYmFzaWNiamZlYXR1cmVxAH4ACXBwdAAJaGl0QXRQbGF5cHNxAH4AAnQA
NUJsYWNramFja0RvbWFpbi5zdGF0ZS5lcXVhbHNUb0NvbnN0YW50KFN0YXRlICAuIFBMQVkpcQB+
AAZ0AA9ibGFja2phY2suU3RhbmR0AA9fYmFzaWNiamZlYXR1cmVxAH4ACXBwdAALc3RhbmRBdFBs
YXlwc3EAfgACdAA1QmxhY2tqYWNrRG9tYWluLnN0YXRlLmVxdWFsc1RvQ29uc3RhbnQoU3RhdGUg
IC4gUExBWSlxAH4ABnQABWxlYXZldAAPX2Jhc2ljYmpmZWF0dXJlcQB+AAlwcHQAC2xlYXZlQXRQ
bGF5cHNxAH4AAnQANkJsYWNramFja0RvbWFpbi5zdGF0ZS5lcXVhbHNUb0NvbnN0YW50KFN0YXRl
ICAuIEpVREdFKXEAfgAGdAAFZW50ZXJ0AA9fYmFzaWNiamZlYXR1cmVxAH4ACXBwdAAKZW50ZXJK
dWRnZXBzcQB+AAJ0ADRCbGFja2phY2tEb21haW4uc3RhdGUuZXF1YWxzVG9Db25zdGFudChTdGF0
ZSAgLiBFTkQpcQB+AAZ0AAVlbnRlcnQAD19iYXNpY2JqZmVhdHVyZXEAfgAJcHB0AAhHYW1lT3Zl
cnB4

*/

