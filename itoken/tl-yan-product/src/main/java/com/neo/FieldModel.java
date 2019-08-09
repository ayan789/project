package com.neo;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import lerrain.tool.formula.Factors;
import lerrain.tool.formula.Formula;
import lerrain.tool.formula.Value;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FieldModel {

    String uniqueID;
    String label;
    String initialValue;
    String type;
    JSONObject fieldDecorator;
    JSONObject attr;

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Rule {
        boolean required;
        String message;
        String pattern;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Option {
        String key;
        String value;

        static Option obtain(String key, String value) {
            Option option = new Option();
            option.key = key;
            option.value = value;
            return option;
        }
    }

    public static List<FieldModel> toFieldModelList(List<Field> fields, VMPageRequest pageRequest, Factors factors) {
        List<FieldModel> fieldModels = new ArrayList<>();
        if (fields != null) {
            for (Field field : fields) {
                Formula visible = field.getVisible();
                if (visible == null || Value.booleanOf(visible, factors)) {
                    fieldModels.add(toFieldModel(field, factors));
                }
            }
        }
        return fieldModels;
    }

    public static FieldModel toFieldModel(Field field, Factors factors) {
        FieldModel fieldModel = new FieldModel();
        fieldModel.uniqueID = field.getUniqueID();
        fieldModel.type = field.getType();
        fieldModel.label = field.getLabel();
        if (field.getValue() != null)
            fieldModel.initialValue = Value.stringOf(field.getValue(), factors);
        fieldModel.fieldDecorator = toFieldDecorator(field, factors);
        fieldModel.attr = toAttr(field, factors);
        return fieldModel;
    }

    static JSONObject toAttr(Field field, Factors factors) {
        JSONObject attr = new JSONObject();
        attr.put("type", field.getSubType());
        attr.put("placeholder", field.getPlaceHolder());
        attr.put("maxLength", field.getMaxLength());
        attr.put("options", toOptionList(field, factors));
        if (field.getDisabled() != null) {
            attr.put("disabled", Value.booleanOf(field.getDisabled(), factors));
        }
        if (field.getShow() != null) {
            attr.put("show", Value.booleanOf(field.getShow(), factors));
        }
        if (field.getAttr() != null) {
            attr.putAll(field.getAttr());
        }
        return attr;
    }

    static JSONObject toFieldDecorator(Field field, Factors factors) {
        JSONObject fieldDecorator = new JSONObject();
        fieldDecorator.put("key", field.getUniqueID());
        fieldDecorator.put("rules", toRuleList(field.getRules(), factors));
        if (field.getFieldDecorator() != null) {
            fieldDecorator.putAll(field.getFieldDecorator());
        }
        return fieldDecorator;
    }

    static List<Option> toOptionList(Field field, Factors factors) {
        List<FieldOption> fieldOptions = field.getOptions();
        List<Option> options = new ArrayList<>();
        if (fieldOptions != null) {
            for (FieldOption fieldOption : fieldOptions) {
                Formula visible = fieldOption.getVisible();
                if (visible == null || Value.booleanOf(visible, factors)) {
                    options.add(toOption(fieldOption, factors));
                }
            }
        }

        String dictKey = null;
        if (field.getDictKey() != null) {
            dictKey = Value.stringOf(field.getDictKey(), factors);
        }

        if (StringTool.isNotNull(dictKey)) {
            DItem dItem = DictUtils.getDict(dictKey);
            List<DItem> list = dItem.getItems();
            if (list != null) {
                for (DItem item : list) {
                    options.add(Option.obtain(item.getName(), item.getCode()));
                }
            }
        }

        return options;
    }


    static Option toOption(FieldOption fieldOption, Factors factors) {
        Option option = new Option();
        option.key = fieldOption.getKey();
        option.value = fieldOption.getValue();
        if (fieldOption.getValueFormula() != null) {
            option.value = Value.stringOf(fieldOption.getValueFormula(), factors);
        }
        if (fieldOption.getKeyFormula() != null) {
            option.key = Value.stringOf(fieldOption.getKeyFormula(), factors);
        }
        return option;
    }

    static List<Rule> toRuleList(List<FieldRule> fieldRules, Factors factors) {
        List<Rule> rules = new ArrayList<>();
        if (fieldRules != null) {
            for (FieldRule fieldRule : fieldRules) {
                Formula condition = fieldRule.getCondition();
                if (condition == null || Value.booleanOf(condition, factors)) {
                    rules.add(toRule(fieldRule));
                }
            }
        }
        return rules;
    }

    static Rule toRule(FieldRule fieldRule) {
        Rule rule = new Rule();
        rule.required = fieldRule.isRequired();
        rule.message = fieldRule.getMessage();
        rule.pattern = fieldRule.getPatternKey();
        return rule;
    }

}
