package com.ccic.salesapp.performance.domain.vo.dto;

import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.List;

@Getter
public enum MessageStatusEnum {
    UN_READ("0", "unRead", "未读"),
    READ("1", "read", "已读"),
    DELETE("2", "delete", "删除"),
    ALL("3", "all", "全部");

    private String key;
    private String type;
    private String desc;

    MessageStatusEnum(String key, String type, String desc) {
        this.type = type;
        this.key = key;
        this.desc = desc;
    }

    public static List<String> getkeys() {
            List<String> keys = Lists.newArrayList();
            for (MessageStatusEnum messageDetailTypeEnum : MessageStatusEnum.values()) {
                if (!messageDetailTypeEnum.key.equals(MessageStatusEnum.DELETE.getKey())) {
                    keys.add(messageDetailTypeEnum.key);
                }
            }
        return keys;
    }
}
