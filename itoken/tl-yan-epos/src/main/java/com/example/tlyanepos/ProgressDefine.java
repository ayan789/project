package com.example.tlyanepos;


import lerrain.tool.formula.Formula;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Data
public class ProgressDefine {

    Locale locale;
    String progressId;
    List<Step> steps;

    public void addStep(Step step) {
        if (steps == null) {
            steps = new ArrayList<>();
        }
        steps.add(step);
    }

    @Data
    public static class Step {

        String parentStepKey;

        String title;

        String label;

        String stepKey;

        List<Step> children;

        Formula visible;

        public boolean isGroup() {
            return parentStepKey == null && hasChild();
        }

        boolean hasChild() {
            return children != null && !children.isEmpty();
        }

        public void addChild(Step step) {
            if (children == null) {
                children = new ArrayList<>();
            }
            children.add(step);
        }
    }
}
