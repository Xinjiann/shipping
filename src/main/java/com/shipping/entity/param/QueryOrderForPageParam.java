package com.shipping.entity.param;

import com.shipping.common.lang.PageParam;
import lombok.Data;

@Data
public class QueryOrderForPageParam {

    /**
     * openid
     */
    private String openid;

    /**
     * 订单状态
     */
    private Integer status;

    /**
     * 分页部件
     */
    private PageParam pageParam;
}
