package com.neo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by zhangjunwen on 2017/10/20
 *
 * @author zhangjunwen
 * @date 2017/10/20
 */
@Data
@NoArgsConstructor
public class DItem implements Cloneable {


    private String type;
    private String key;
    /**
     * 名称
     */
    private String name;
    /**
     * 代码
     */
    private String code;
    /**
     * 对应commonKey
     */
    private String commonKey;
    private String isChild;

    /**
     * 是否常用
     */
    private String isHot;


    private List<DItem> items;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
