package com.easychat.entity.enums;

/**
 * @ClassName BeautyAccountStatusEnum
 * @Description
 * @Author Siyuan
 * @Date 2024/11/08/21:26
 * @Version 1.0
 */
public enum BeautyAccountStatusEnum {

    NO_USE(0, "未使用"),
    USEED(1, "已使用");


    private Integer status;
    private String desc;

    BeautyAccountStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static BeautyAccountStatusEnum getByStatus(Integer status) {
        for (BeautyAccountStatusEnum item : BeautyAccountStatusEnum.values()) {
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

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
