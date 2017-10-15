package com.junfly.water.entity.vo.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: pq
 * @Description:
 * @Date: 2017/5/25 9:19
 */
@ApiModel("系统用户")
@Data
public class SysUserVO implements Serializable {
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;

    private String wxUserId;
}
