package com.document;

import com.neo.InsureFactors;
import com.neo.MDocument;
import com.neo.SInsure;

public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        SInsure reqInsure = new SInsure();
        reqInsure.setInsureId("15602418731066516719590296462043");
        test.loadDocument(reqInsure);
    }

    public void loadDocument(SInsure reqInsure){
        String insureId = reqInsure.getInsureId();
        DocumentManager documentManager = new DocumentManagerImpl();
        MDocument mDocument = documentManager.loadDocument(insureId);
    }

}
