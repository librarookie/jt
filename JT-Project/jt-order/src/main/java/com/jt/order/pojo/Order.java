package com.jt.order.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @Author chao
 * @Date 2019/2/25 - 15:22
 */
@TableName("tb_order")
@Data
@Accessors
public class Order extends BasePojo {

    @TableField(exist=false)	//入库操作忽略该字段
    private OrderShipping orderShipping;

    //封装订单商品信息  一对多
    @TableField(exist=false)	//入库操作忽略该字段
    private List<OrderItem> orderItems;

    @TableId
    private String orderId;
    private String payment;
    private Integer paymentType;
    private String postFee;
    private Integer status;
    private Date created;
    private Date updated;
    private Date paymentTime;
    private Date consignTime;
    private Date endTime;
    private Date closeTime;
    private String shippingName;
    private String shippingCode;
    private Long userId;
    private String buyerMessage;
    private String buyerNick;
    private Integer buyerRate;
}
