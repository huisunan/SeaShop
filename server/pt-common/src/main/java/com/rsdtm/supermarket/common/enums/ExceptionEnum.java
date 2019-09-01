package com.rsdtm.supermarket.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum  ExceptionEnum {
    SUPPLIER_NOT_EXIST(404,"供应商不存在"),
    SUPPLIER_ID_NOT_NULL(400,"供应商ID不能为空"),
    CATEGORY_NOT_EXIST(404,"分类不存在"),
    CATEGORY_ID_NOT_NULL(400,"供应商ID不能为空"),
    PRODUCT_NOT_EXIST(404,"商品不存在"),
    PRODUCT_ID_NOT_NULL(400,"商品ID不能为空"),
    PRODUCT_SUPPLIER_ID_NOT_NULL(400,"商品供应商关系ID不能为空"),
    PRODUCT_SUPPLIER_NOT_EXIST(404,"商品供应商关系不存在"),
    VIP_NOT_EXIST(404,"会员不存在"),
    VIP_ID_NOT_NULL(400,"会员ID不能为空"),
    USER_NOT_EXIST(404,"用户不存在"),
    USER_ID_NOT_NULL(400,"用户ID不能为空"),
    VIP_PRODUCT_PRICE_NOT_NULL(400,"会员价格不能为空"),
    VIP_PRODUCT_NOT_EXIST(404,"会员价格不存在"),
    VIP_PRODUCT_ID_NOT_NULL(400,"会员价格ID不能为空"),
    VIP_PRODUCT_IS_EXIST(400,"会员价格已经存在"),
    STAFF_CLASS_NOT_EXIST(404,"员工分类不存在"),
    STAFF_CLASS_ID_NOT_NULL(400, "员工分类ID不能为空"),
    STAFF_JOB_NOT_EXIST(404,"员工职务不存在"),
    STAFF_JOB_ID_NOT_NULL(400, "员工职务ID不能为空"),
    STAFF_NOT_EXIST(404,"员工不存在"),
    STAFF_ID_NOT_NULL(400, "员工职务ID不能为空"),
    USER_NAME_NOT_NULL(400, "用户名不能为空"),
    USER_PASSWORD_NOT_NULL(400, "用户密码不能为空"),
    USER_NAME_EXIST(400, "用户名已存在"),
    USER_PASSWORD_WRONG(400, "用户密码错误"),
    ROLE_NOT_EXIST(404,"角色不存在"),
    ROLE_ID_NOT_NULL(400,"角色ID不能为空"),
    USER_ROLE_IS_EXIST(400,"用户角色已存在"),
    USER_ROLE_IS_NOT_EXIST(404,"用户角色不存在"),
    ROLE_NAME_NOT_NULL(400,"角色名不能为空"),
    ROLE_ZH_NAME_NOT_NULL(400,"角色中文名不能为空"),
    MENU_NOT_EXIST(404,"资源菜单不存在"),
    MENU_ID_NOT_NULL(400,"资源菜单ID不能为空"),
    ROLE_MENU_NOT_EXIST(404,"角色资源菜单不存在"),
    ROLE_MENU_IS_EXIST(400,"角色资源菜单已存在"),
    ORDER_NOT_EXIST(404,"订单不存在"),
    ORDER_ID_NOT_NULL(400,"订单ID不能为空"),
    ORDER_SN_NOT_NULL(400,"订单编号不能为空"),
    PRODUCT_QUANTITY_NOT_NULL(400,"商品数量不能为空"),
    ;
    int code;
    String msg;

}
