package com.rsdtm.supermarket.mbg.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

@ApiModel(value = "会员表")
@Table(name = "bms_vip")
@Data
public class BmsVip implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    @ApiModelProperty(value = "会员等级")
    private Integer level;

    @ApiModelProperty(value = "会员类型")
    private String type;

    private static final long serialVersionUID = 1L;
}