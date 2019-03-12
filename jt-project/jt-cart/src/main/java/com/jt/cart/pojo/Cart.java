package com.jt.cart.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author chao
 * @Date 2019/2/22 - 10:33
 */
@Data
@Accessors
@TableName("tb_cart")
public class Cart extends BasePojo {

    @TableId(type = IdType.AUTO)
    private Long    id;
    private Long    userId;
    private Long    itemId;
    private String  itemTitle;
    private String  itemImage;
    private Long    itemPrice;
    private Integer num;
}
