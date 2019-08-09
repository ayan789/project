package com.neo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class VMService {

    
    PagesMgr pagesMgr = new PagesMgr();

    
    ProgressMgr progressMgr = new ProgressMgr();

    public List<FieldModel> loadVMPage(VMPageRequest pageRequest) {

        String pageId = pageRequest.getPageId();
        Object content = pageRequest.getContent();

        //TODO 暂时屏蔽
//        Page page = pagesMgr.getPage(pageId, pageRequest.getLocale());
        Page page = pagesMgr.getPage(pageId, LoginUtil.currentLocale());

        if (page != null) {
            return FieldModel.toFieldModelList(page.getFields(), pageRequest, FactorsBuild.buildFactors(content));
        }

        return new ArrayList<>();
    }

    public ProgressModel loadProgressStep(VMProgressRequest vmProgressRequest) {
        String progressId = vmProgressRequest.getProgressId();
        Object content = vmProgressRequest.getContent();
        String currentStepKey = vmProgressRequest.getCurrentStep();

        //TODO 暂时屏蔽
//        Progress progress = progressMgr.getProgress(progressId, vmProgressRequest.getLocale());
        Progress progress = progressMgr.getProgress(progressId, LoginUtil.currentLocale());

        if (progress != null) {
            ProgressModel progressModel = ProgressModel.toProgressModel(progress, FactorsBuild.buildFactors(content));
            progressModel.setCurrentStepKey(currentStepKey);
            progressModel.setNextStepKey(ProgressModel.findNextStep(progressModel, currentStepKey));
            progressModel.setPrevStepKey(ProgressModel.findPrev(progressModel, currentStepKey));
            return progressModel;
        }

        return null;
    }

}
