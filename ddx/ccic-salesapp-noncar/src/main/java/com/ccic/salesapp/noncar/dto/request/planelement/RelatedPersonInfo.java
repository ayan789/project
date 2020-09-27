package com.ccic.salesapp.noncar.dto.request.planelement;
import java.util.ArrayList;
import java.util.List;

import com.ccic.salesapp.noncar.dto.request.noncar.PlanElementGroup;

public class RelatedPersonInfo {

    private List<PlanElementGroup> form = new ArrayList<PlanElementGroup>();
    public void setForm(List<PlanElementGroup> form) {
         this.form = form;
     }
     public List<PlanElementGroup> getForm() {
         return form;
     }

}