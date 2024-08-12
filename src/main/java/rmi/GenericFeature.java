package rmi;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import external.FL_EVENT_STEM;
import external.FeaturePackage;

public class GenericFeature extends FeaturePackage{
	FeaturePackage Feature;
	/*
	 * Add related Features
	FeaturePackage Pipeling1; 
	 * 
	 */
	
	public FeaturePackage getFeature() {
		return Feature;
	}

	public void setFeature(FeaturePackage feature) {
		Feature = feature;
	}
	
	/*
	 * Because the condition and event are not fixed, so we can not hardcode them in the generic feature
	 */
	public boolean checkCondition(InteractCondition c) {
		return false;
	}
	
	public boolean checkEvent(FL_EVENT_STEM e) {
		return false;
	}
	
	// I propose use java reflection to meet the requirement
	// Because we do not know what PUs are contained in Feature F, so we collect them as a list or hashmap 
	//ArrayList<Method> pulist = new  ArrayList<Method>();
	HashMap<FL_EVENT_STEM, ArrayList<Method>> eventMap = new HashMap<>();
	public synchronized void XEvent(final FL_EVENT_STEM e) {
		if(checkEvent(new FL_EVENT_STEM()) && checkCondition(new InteractCondition())) {
			try {
				//pulist.get(puIndex).invoke(this, e);
				for(Method m : eventMap.get(e)) {
					// Check condition here for each method? 
					m.invoke(this, e);
				}
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	
	class InteractCondition {
		
	}
	
	/*
	 * Add some related methods
	 */
	
}
