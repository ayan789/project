package com.ccic.salessapp.common.utils;

import net.sf.json.JSONException;
import net.sf.json.util.PropertySetStrategy;

public class PropertyStrategyWrapper extends PropertySetStrategy {
	private PropertySetStrategy original; 
	public PropertyStrategyWrapper(PropertySetStrategy original) {
	        this.original = original;
	 }
	@Override
	public void setProperty(Object o1, String string, Object o2) throws JSONException {
		try {
            original.setProperty(o1, string, o2);
        } catch (Exception ex) {           
        }
	}
}
