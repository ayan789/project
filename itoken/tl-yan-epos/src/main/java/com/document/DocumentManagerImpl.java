package com.document;

import com.neo.InsureFactors;
import com.neo.InsureFactorsBuild;
import com.neo.MDocument;

public class DocumentManagerImpl implements DocumentManager {
    @Override
    public MDocument loadDocument(String insureId) {
        InsureFactorsBuild insureFactorsBuild = new InsureFactorsBuild();
        InsureFactors insureFactors = insureFactorsBuild.buildInsureFactors(insureId);

        return null;
    }
}
