package com.example.tlyanepos;



import java.util.ArrayList;

public class EUTreeNode {
    private long id;
    private long pid;
    private String text;
    private String state;


    public EUTreeNode() {
        super();
    }

    public EUTreeNode(long id, String text, String state) {
        super();
        this.id = id;
        this.text = text;
        this.state = state;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }




}
