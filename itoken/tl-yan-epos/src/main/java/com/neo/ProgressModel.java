package com.neo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.neo.Progress.Step;
import lerrain.tool.formula.Factors;
import lerrain.tool.formula.Value;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProgressModel {

    List<StepModel> stepModels;
    String currentStepKey;
    String nextStepKey;
    String prevStepKey;
    String insureType;

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class StepModel {
        String title;
        String label;
        String stepKey;
        boolean isGroup = false;
        List<StepModel> children;

        boolean hasChild() {
            return children == null || children.isEmpty();
        }
    }

    public static ProgressModel toProgressModel(Progress progress, Factors factors) {
        ProgressModel progressModel = new ProgressModel();
        progressModel.stepModels = toStepModelList(progress.getSteps(), factors);
        return progressModel;
    }

    public static List<StepModel> toStepModelList(List<Step> steps, Factors factors) {
        List<StepModel> stepModels = new ArrayList<>();
        if (steps != null) {
            for (Step step : steps) {
                if (step.getVisible() == null || Value.booleanOf(step.getVisible(), factors)) {
                    stepModels.add(toStepModel(step, factors));
                }
            }
        }
        return stepModels;
    }

    static List<StepModel> buildChildSteps(ProgressModel progress) {
        List<StepModel> stepModels = progress.stepModels;
        List<StepModel> result = new ArrayList<>();
        for (int i = 0; i < stepModels.size(); i++) {
            StepModel stepModel = stepModels.get(i);
            if (!stepModel.isGroup()) {
                result.add(stepModel);
            } else {
                result.addAll(stepModel.getChildren());
            }
        }
        return result;
    }

    public static String findPrev(ProgressModel progress, String currentStepKey) {
        List<StepModel> stepModels = buildChildSteps(progress);

        if (stepModels.isEmpty()) {
            return null;
        }
        if (currentStepKey == null) {
            return null;
        }
        for (int i = 0; i < stepModels.size(); i++) {
            if (currentStepKey.equals(stepModels.get(i).stepKey)) {
                return i > 0 ? stepModels.get(i - 1).stepKey : null;
            }
        }
        return null;
    }

    public static String findNextStep(ProgressModel progress, String currentStepKey) {
        List<StepModel> stepModels = buildChildSteps(progress);

        if (stepModels.isEmpty()) {
            return null;
        }
        if (currentStepKey == null) {
            return stepModels.get(0).stepKey;
        }
        for (int i = 0; i < stepModels.size(); i++) {
            if (currentStepKey.equals(stepModels.get(i).stepKey)) {
                return i < stepModels.size() - 1 ? stepModels.get(i + 1).stepKey : null;
            }
        }
        return null;
    }

    static StepModel toStepModel(Step step, Factors factors) {
        StepModel stepModel = new StepModel();
        stepModel.label = step.getLabel();
        stepModel.stepKey = step.getStepKey();
        stepModel.title = step.getTitle();
        stepModel.isGroup = step.isGroup();

        if (step.getChildren() != null && step.getChildren().size() > 0) {
            stepModel.setChildren(toStepModelList(step.getChildren(), factors));
        }

        return stepModel;
    }
}
