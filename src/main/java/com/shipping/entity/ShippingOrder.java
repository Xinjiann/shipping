package com.shipping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author lixinjian
 * @since 2023-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShippingOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 描述
     */
    private String remark;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 快递单号
     */
    private String trackingNumber;

    /**
     * 地址
     */
    private String address;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建者openid
     */
    private String openid;

    /**
     * 金额
     */
    private BigDecimal price;

    /**
     * 国际单号
     */
    private String orderNumber;

    /**
     * 图片key
     */
    private String image;

    /**
     * 订单状态
     */
    private Integer status;

    /**
     * 删除标志
     */
    private Integer deleted;


}
