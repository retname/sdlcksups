package com.ordermanager.project.ups.mapper;

import com.ordermanager.project.ups.domain.CreateOrder;

import java.util.List;
import java.util.Map;


public interface CreateOrderMapper {


    CreateOrder getByOrderNumber(String number);


    int insertCreateOrder(CreateOrder createOrder);

    /**
     * 查询订单集合
     *
     * @param createOrder 订单对象
     * @return 订单集合
     */
     List<CreateOrder> selectCreateOrderList(CreateOrder createOrder);


    /**
     * 查询 第三方订单号，订单号，渠道号，png/pdf地址
     * @param
     * @param orderNumbers 订单号列表
     * @return
     */
     List<Map<String, Object>> getByFormatAndOrderNum(List<String> orderNumbers);

    /**
     * 修改订单的渠道号和面单地址
     * @param orderNo
     * @param trackingNo
     * @param pdfUrl
     */
    void updateByOrderNo(String orderNo, String trackingNo, String pdfUrl,String pdfAddress,String pngAddress,String format);


    /**
     * 获取渠道号为空的订单 重新去获取
     * @return
     */
    List<CreateOrder> getIsEmptyTrackingNum(String responseStatus);

}
