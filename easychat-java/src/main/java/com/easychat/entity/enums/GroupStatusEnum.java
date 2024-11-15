package com.easychat.entity.enums;

/**
 * @ClassName GroupStatusEnum
 * @Description 避免硬编码
 * @Author Siyuan
 * @Date 2024/11/15/21:39
 * @Version 1.0
 */
public enum GroupStatusEnum {
    NORMAL(1, "正常"),
    DISSOLUTION(0, "解散");

    private Integer status;

    private String desc;

    GroupStatusEnum(Integer status, String desc) {
        this.status = status;
        this.status = status;
    }

    public static GroupStatusEnum getByStatus(Integer status) {
        for (GroupStatusEnum item : GroupStatusEnum.values()) {
            if (item.getStatus().equals(status)) {
                return item;
            }
        }
        return null;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
