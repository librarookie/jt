package com.jt.sso.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author chao
 * @date 2019/2/21 - 14:07
 */
@Data   //lombok自动添加get/set/constructor
@Accessors  //lombok开启链式结构
@TableName("tb_user")
public class User extends BasePojo {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;
}
