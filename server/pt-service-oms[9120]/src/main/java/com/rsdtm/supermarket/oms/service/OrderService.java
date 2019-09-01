package com.rsdtm.supermarket.oms.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rsdtm.supermarket.common.enums.ExceptionEnum;
import com.rsdtm.supermarket.common.exception.PtException;
import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.OmsOrder;
import com.rsdtm.supermarket.mbg.vo.BmsUserVo;
import com.rsdtm.supermarket.mbg.vo.StaffVo;
import com.rsdtm.supermarket.oms.client.BmsClient;
import com.rsdtm.supermarket.oms.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    OrderDao orderDao;

    @Autowired
    BmsClient client;

    @Transactional
    public int addOrder(OmsOrder omsOrder) {
        if (omsOrder.getUserId() == null)
            throw new PtException(ExceptionEnum.USER_ID_NOT_NULL);
        if (omsOrder.getStaffId() == null)
            throw new PtException(ExceptionEnum.STAFF_ID_NOT_NULL);
        BmsUserVo user = client.getUserById(omsOrder.getUserId()).getBody();
        if (user==null)
            throw new PtException(ExceptionEnum.USER_NOT_EXIST);
        StaffVo staff = client.queryStaffById(omsOrder.getStaffId()).getBody();
        if ( staff== null)
            throw new PtException(ExceptionEnum.STAFF_NOT_EXIST);

        omsOrder.setId(null);
        omsOrder.setOrderSn(UUID.randomUUID().toString().replace("-",""));

        omsOrder.setStaffName(staff.getName());
        omsOrder.setUserName(user.getName());

        return this.orderDao.insert(omsOrder);
    }
    @Transactional
    public int deleteOrderById(Integer id) {
        if (!orderDao.existsWithPrimaryKey(id))
            throw new PtException(ExceptionEnum.ORDER_NOT_EXIST);
        return this.orderDao.deleteByPrimaryKey(id);
    }

    @Transactional
    public int updateOrder(OmsOrder omsOrder) {
        if (omsOrder.getId()==null)
            throw new PtException(ExceptionEnum.ORDER_ID_NOT_NULL);
        if (!orderDao.existsWithPrimaryKey(omsOrder.getId()))
            throw new PtException(ExceptionEnum.ORDER_NOT_EXIST);
        return orderDao.updateByPrimaryKeySelective(omsOrder);
    }

    public OmsOrder getOrderById(Integer id) {
        return this.orderDao.selectByPrimaryKey(id);
    }

    public PageResult<OmsOrder> getOrderByPage(int page, int rows, OmsOrder omsOrder) {
        PageHelper.startPage(page,rows);
        Example example = new Example(OmsOrder.class);

        Page<OmsOrder> omsOrders = (Page<OmsOrder>) orderDao.selectByExample(example);

        return PageResult.restPage(omsOrders);
    }
}
