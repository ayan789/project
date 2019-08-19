package com.neo;

import com.alibaba.fastjson.JSONObject;
import lerrain.tool.formula.Formula;
import lerrain.tool.formula.Value;
import lerrain.tool.script.Stack;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Data
public class Page {

    String pageId;

    boolean preCheck = false;

    List<Field> fields;

    Locale locale;

    public <T> T buildModel(T t) {

        return t;
    }

    public Page copy() {
        Page page = new Page();
        page.pageId = pageId;
        page.preCheck = preCheck;
        page.fields = fields;
        return page;
    }

    public void addField(Field field) {
        if (fields == null) {
            fields = new ArrayList<>();
        }
        fields.add(field);
    }

    public static Page filter(Page page, JSONObject param) {
        Stack stack = new Stack();
        stack.setAll(param);
        List<Field> result = new ArrayList<>();
        List<Field> fields = page.fields;
        for (int i = 0; fields != null && i < fields.size(); i++) {
            Field field = fields.get(i);
            Formula condition = field.getVisible();
            if (condition == null || Value.booleanOf(condition, stack)) {
                result.add(field);
            }
        }
        page.setFields(fields);
        return page;
    }

}
