package com.rsdtm.supermarket.oms.service;

import com.rsdtm.supermarket.common.enums.ExceptionEnum;
import com.rsdtm.supermarket.common.exception.PtException;
import com.rsdtm.supermarket.mbg.model.OmsOrderItem;
import com.rsdtm.supermarket.mbg.query.OmsOrderItemParamVo;
import com.rsdtm.supermarket.mbg.vo.ProductVo;
import com.rsdtm.supermarket.mbg.vo.VipProductVo;
import com.rsdtm.supermarket.oms.client.BmsClient;
import com.rsdtm.supermarket.oms.dao.OrderDao;
import com.rsdtm.supermarket.oms.dao.OrderItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    @Autowired
    OrderItemDao orderItemDao;

    @Autowired
    OrderDao orderDao;

    @Autowired
    BmsClient bmsClient;

    public int addOrderItem(OmsOrderItemParamVo omsOrderItem) {
        if (omsOrderItem.getOrderId() == null)
            throw new PtException(ExceptionEnum.ORDER_ID_NOT_NULL);
        if (omsOrderItem.getOrderSn() == null)
            throw new PtException(ExceptionEnum.ORDER_SN_NOT_NULL);
        if (omsOrderItem.getProductId() == null)
            throw new PtException(ExceptionEnum.PRODUCT_ID_NOT_NULL);
        if (omsOrderItem.getQuantity() == null)
            throw new PtException(ExceptionEnum.PRODUCT_QUANTITY_NOT_NULL);
        if (!orderDao.existsWithPrimaryKey(omsOrderItem.getOrderId()))
            throw new PtException(ExceptionEnum.ORDER_NOT_EXIST);




        ProductVo product = bmsClient.getProductById(omsOrderItem.getProductId()).getBody();
        if (product == null)
            throw new PtException(ExceptionEnum.PRODUCT_NOT_EXIST);

        //设置原价
        omsOrderItem.setProductPrice(product.getPrice());

        //设置会员价格
        VipProductVo body = bmsClient.getVipProductVoByProductIdAndUserId(omsOrderItem.getProductId(), omsOrderItem.getUserId()).getBody();
        if (body!=null)
            omsOrderItem.setProductPrice(body.getPrice());
        else
            omsOrderItem.setProductPrice(product.getPrice());



        //是否已经存在
        OmsOrderItem temp = orderItemDao.getByOidAndPid(omsOrderItem.getProductId(), omsOrderItem.getProductId());
        return 0;
    }
}
