package com.neo;

import lerrain.tool.formula.Formula;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Field {

    static final String TYPE_INPUT = "input";
    static final String TYPE_SELECT = "select";

    String uniqueID;

    String type;

    String subType;//小类

    String label;

    String placeHolder;

    int maxLength = -1;

    List<FieldRule> rules;

    Formula value;

    List<FieldOption> options;

    Formula visible;

    boolean necessary = true;

    /**
     * 是否可编辑
     */
    Formula disabled;

    /**
     * 是否显示
     */
    Formula show;

    /**
     * 字典项
     */
    Formula dictKey;

    /**
     * 扩展Attr
     */
    Map<String, String> attr;

    /**
     * 扩展FieldDecorator
     */
    Map<String, String> fieldDecorator;

    public void putAttr(String key, String value) {
        if (attr == null) {
            attr = new HashMap<>();
        }
        attr.put(key, value);
    }

    public void putFieldDecorator(String key, String value) {
        if (fieldDecorator == null) {
            fieldDecorator = new HashMap<>();
        }
        fieldDecorator.put(key, value);
    }

}
