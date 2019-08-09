package com.neo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GroupDocumentDefine {

    public GroupDocumentDefine(String groupName,String code, boolean expand, List<DocumentDefine> documentDefineList){
        this.groupName = groupName;
        this.code = code;
        this.expand = expand;
        this.documentDefineList = documentDefineList;
    }

    private String groupName;
    private String code;
    private boolean expand;
    private List<DocumentDefine> documentDefineList;
}