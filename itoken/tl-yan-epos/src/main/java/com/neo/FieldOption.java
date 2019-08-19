package com.neo;

import lerrain.tool.formula.Formula;
import lombok.Data;

@Data
public class FieldOption {

    String key;
    String value;
    Formula valueFormula;
    Formula keyFormula;
    Formula visible;
    boolean selected = false;

    public static FieldOption obtain(String key, String value) {
        FieldOption fieldOption = new FieldOption();
        fieldOption.key = key;
        fieldOption.value = value;
        return fieldOption;
    }
}
