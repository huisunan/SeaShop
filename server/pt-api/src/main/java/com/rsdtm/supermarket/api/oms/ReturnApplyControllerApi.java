package com.rsdtm.supermarket.api.oms;

import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.OmsReturnApply;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

@Api(value = "关于订单退货申请",description = "关于订单退货申请的增删改查")
public interface ReturnApplyControllerApi {
    @ApiOperation("添加订单退货申请")
    ResponseEntity<Void> addReturnApply(OmsReturnApply omsReturnApply);

    @ApiOperation("删除订单退货申请，根据ID")
    @ApiImplicitParam(name = "id",value = "要删除订单退货申请的ID",required = true)
    ResponseEntity<Void> deleteReturnApplyById(Integer id);

    @ApiOperation("修改订订单退货申请")
    ResponseEntity<Void> updateReturnApply(OmsReturnApply omsReturnApply);

    @ApiOperation("根据ID查询订单退货申请")
    ResponseEntity<OmsReturnApply> getReturnApplyById(Integer id);

    @ApiOperation("分页订单退货申请")
    ResponseEntity<PageResult<OmsReturnApply>> getReturnApplyByPage(int page, int rows, OmsReturnApply omsReturnApply);
}
