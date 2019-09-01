package com.rsdtm.supermarket.api.bms;

import com.rsdtm.supermarket.mbg.model.BmsVip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(value = "会员管理",description = "会员管理,一套增删改查")
public interface VipControllerApi {

    @ApiOperation("添加会员")
    ResponseEntity<Void> addVip(BmsVip bmsVip);

    @ApiOperation("根据ID删除会员")
    @ApiImplicitParam(name = "id",value = "会员ID",required = true)
    ResponseEntity<Void> deleteVipById(Integer id);

    @ApiOperation("更新会员信息")
    ResponseEntity<Void> updateVip(BmsVip bmsVip);

    @ApiOperation("根据ID查询会员")
    @ApiImplicitParam(name = "id",value = "会员ID",required = true)
    ResponseEntity<BmsVip> queryVipById(Integer id);

    @ApiOperation("查询所有会员")
    ResponseEntity<List<BmsVip>> queryAllVip();
}
