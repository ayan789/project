package com.example.tlyanepos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class TprogressTreeNode {
    private String progressId;
    private String parentId;
    private String stepKey;
    private String status;

    private ArrayList<TprogressTreeNode> children=new ArrayList<TprogressTreeNode>();

    public TprogressTreeNode(String progressId, String parentId, String stepKey, String status) {
        super();
        this.progressId = progressId;
        this.parentId = parentId;
        this.stepKey = stepKey;
        this.status = status;
    }

    public void add(TprogressTreeNode node)
    {
        if("00".equals(node.parentId))
        {
            this.children.add(node);
        }
        else if(node.parentId.equals(this.progressId))
        {
            this.children.add(node);
        }
        else
        {
            for(TprogressTreeNode tmp_node:children)
            {
                tmp_node.add(node);
            }
        }
    }



    public ArrayList<TprogressTreeNode> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<TprogressTreeNode> children) {
        this.children = children;
    }


}
