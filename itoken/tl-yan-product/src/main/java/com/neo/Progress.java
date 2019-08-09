package com.neo;


import lerrain.tool.formula.Formula;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Progress {

    Locale locale;
    String progressId;
    List<Step> steps;

    public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getProgressId() {
		return progressId;
	}

	public void setProgressId(String progressId) {
		this.progressId = progressId;
	}

	public List<Step> getSteps() {
		return steps;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}

	public void addStep(Step step) {
        if (steps == null) {
            steps = new ArrayList<>();
        }
        steps.add(step);
    }

    public static class Step {

        String parentStepKey;

        String title;

        String label;

        String stepKey;

        List<Step> children;

        Formula visible;
        

        public String getParentStepKey() {
			return parentStepKey;
		}

		public void setParentStepKey(String parentStepKey) {
			this.parentStepKey = parentStepKey;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public String getStepKey() {
			return stepKey;
		}

		public void setStepKey(String stepKey) {
			this.stepKey = stepKey;
		}

		public List<Step> getChildren() {
			return children;
		}

		public void setChildren(List<Step> children) {
			this.children = children;
		}

		public Formula getVisible() {
			return visible;
		}

		public void setVisible(Formula visible) {
			this.visible = visible;
		}

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
