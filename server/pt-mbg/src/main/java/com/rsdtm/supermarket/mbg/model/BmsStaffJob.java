package com.rsdtm.supermarket.mbg.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

@ApiModel(value = "员工职务表")
@Table(name = "bms_staff_job")
@Data
public class BmsStaffJob implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    @ApiModelProperty(value = "所属分类")
    private Integer staffClassId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    private static final long serialVersionUID = 1L;
}