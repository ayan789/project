package com.neo;

import lerrain.tool.formula.Formula;
import lerrain.tool.formula.FormulaUtil;
import lombok.Data;

@Data
public class FieldRule {

    boolean required = true;

    String message;

    String patternKey;

    Formula condition;

    public static FieldRule obtain(boolean required, String message, String patternKey, String condition) {
        FieldRule fieldRule = new FieldRule();
        fieldRule.required = required;
        fieldRule.message = message;
        fieldRule.patternKey = patternKey;
        fieldRule.condition = FormulaUtil.formulaOf(condition);
        return fieldRule;
    }
}
