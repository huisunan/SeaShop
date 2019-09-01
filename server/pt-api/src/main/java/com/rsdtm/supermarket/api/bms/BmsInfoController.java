package com.rsdtm.supermarket.api.bms;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

@Api(value = "URL操作",description = "模块基础信息获取")
public interface BmsInfoController {
    @ApiOperation("获取BMS所有URL")
    ResponseEntity<Void> getAllUrl();
}
