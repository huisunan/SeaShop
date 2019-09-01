package com.rsdtm.supermarket.api.oms;

import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.OmsReturnReason;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

@Api(value = "关于订单退货原因",description = "关于订单退货原因的增删改查")
public interface ReturnReasonControllerApi {

    @ApiOperation("添加订单退货申请")
    ResponseEntity<Void> addReturnReason(OmsReturnReason omsReturnReason);

    @ApiOperation("删除订单退货申请，根据ID")
    @ApiImplicitParam(name = "id",value = "要删除订单退货申请的ID",required = true)
    ResponseEntity<Void> deleteReturnReasonById(Integer id);

    @ApiOperation("修改订订单退货申请")
    ResponseEntity<Void> updateReturnReason(OmsReturnReason omsReturnReason);

    @ApiOperation("根据ID查询订单退货申请")
    ResponseEntity<OmsReturnReason> getReturnReasonById(Integer id);

    @ApiOperation("分页订单退货申请")
    ResponseEntity<PageResult<OmsReturnReason>> getReturnReasonByPage(int page, int rows, OmsReturnReason omsReturnReason);
}
