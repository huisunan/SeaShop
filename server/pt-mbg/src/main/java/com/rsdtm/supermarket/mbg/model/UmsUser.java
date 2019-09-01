package com.rsdtm.supermarket.mbg.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

@ApiModel(value = "用户表")
@Table(name = "ums_user")
@Data
public class UmsUser implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "帐户名也是登录账户")
    private String name;

    @ApiModelProperty(value = "账户是否可用:0->不可用,1->可用")
    private Byte enable;

    @ApiModelProperty(value = "手机号，可用用于登录")
    private String phone;

    @ApiModelProperty(value = "所属员工id")
    private Integer staffId;

    private static final long serialVersionUID = 1L;
}