package com.example.tlyanepos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tprogress {
    private String progressId;
    private String label;
    private String stepKey;
    private String parentId;
    private String title;
    private String group;
    private int sort;
    private String status;
}
