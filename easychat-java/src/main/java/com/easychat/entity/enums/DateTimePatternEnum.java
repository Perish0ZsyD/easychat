package com.easychat.entity.enums;

/**
 * @ClassName DateTimePatternEnum
 * @Description
 * @Author Siyuan
 * @Date 2024/11/08/20:51
 * @Version 1.0
 */

public enum DateTimePatternEnum {
    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"), YYYY_MM_DD("yyyy-MM-dd"), YYYYMM("yyyyMM");

    private String pattern;

    DateTimePatternEnum(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}