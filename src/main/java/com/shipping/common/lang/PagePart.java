package com.shipping.common.lang;

import lombok.Data;

@Data
public class PagePart {

    /**
     * 每页数据量
     */
    private long size;

    /**
     * 当前页数
     */
    private long current;

}
