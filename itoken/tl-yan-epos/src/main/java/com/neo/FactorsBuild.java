package com.neo;

import com.alibaba.fastjson.JSONObject;
import lerrain.tool.formula.Factors;
import lerrain.tool.script.Stack;

import java.util.Map;

public class FactorsBuild {

    public static Factors buildFactors(Object object) {
        Factors factors = new Stack();
        if (object instanceof Factors) {
            factors = (Factors) object;
        } else if (object instanceof Map) {
            ((Stack) factors).setAll((Map) object);
        } else if (object != null) {
            ((Stack) factors).setAll((Map) JSONObject.toJSON(object));
        }
        return factors;
    }


}
