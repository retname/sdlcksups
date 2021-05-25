package com.ordermanager.project.ups.mapper;

import com.ordermanager.project.ups.domain.ApiLog;

import java.util.List;

public interface ApiLogMapper {
    void insertApiLog(ApiLog apiLog);
    /**
     * 查询系统api操作日志集合
     *
     * @param apiLog 操作日志对象
     * @return 操作日志集合
     */
    List<ApiLog> selectApiLogList(ApiLog apiLog);
}
