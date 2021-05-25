package com.ordermanager.project.ups.service;

import com.ordermanager.project.ups.domain.ApiLog;
import com.ordermanager.project.ups.domain.CreateOrder;
import com.ordermanager.project.ups.vo.OrderDto;
import com.ordermanager.project.ups.vo.PrintLabelModel;
import com.ordermanager.project.ups.vo.Result;

import java.util.List;

public interface ICreateOrder {


    CreateOrder getByOrderNumber(String number);


    /**
     * 下单 接口
     * @param orderDto
     * @return
     */
    Result batchOrder(OrderDto orderDto);

    /**
     * 查询系统api操作日志集合
     *
     * @param apiLog 操作日志对象
     * @return 操作日志集合
     */
    public List<ApiLog> selectApiLogList(ApiLog apiLog);


    /**
     * 查询订单集合
     *
     * @param createOrder 订单对象
     * @return 订单集合
     */
    public List<CreateOrder> selectCreateOrderList(CreateOrder createOrder);


    /**
     * 获取面单接口
     * @param pm
     * @return
     */
    Result printLabel(PrintLabelModel pm);

    /**
     * 价格试算接口
     * @param orderDto
     * @return
     */
    Result calPrice(OrderDto orderDto);
}
