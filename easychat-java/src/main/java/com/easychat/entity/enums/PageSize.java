package com.easychat.entity.enums;

/**
 * @ClassName PageSize
 * @Description
 * @Author Siyuan
 * @Date 2024/11/08/20:52
 * @Version 1.0
 */
public enum PageSize {
    SIZE15(15), SIZE20(20), SIZE30(30), SIZE40(40), SIZE50(50);
    int size;

    private PageSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }
}
