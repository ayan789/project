package com.document;


import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created by yangzhilei on 2017/10/16.
 * K V model
 */
public class MEntry implements Serializable {

    public String value;
    public String name;

    public MEntry() {

    }

    public MEntry(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public static MEntry obtain(String value, String name) {
        return new MEntry(value, name);
    }

    public static MEntry fromJsonString(String json) {
        if (json == null) {
            return null;
        }
        return JSON.toJavaObject(JSON.parseObject(json), MEntry.class);
    }

    public static String getValue(String json) {

        return fromJsonString(json).value;
    }

    public static String toJsonString(MEntry mEntry) {
        if (mEntry == null) {
            return null;
        }
        return JSON.toJSONString(mEntry);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
