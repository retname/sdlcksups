package com.ordermanager.project.ups.mapper;

import com.ordermanager.project.ups.domain.SysParam;

import java.util.List;


public interface SysParamMapper {


    /**
     * 通过接口类型获取接口信息
     * @param urlType
     * @return
     */
    SysParam getByUrlType(int urlType);
    /**
     * 查询系统参数集合
     *
     * @return 系统参数集合
     */
    List<SysParam> selectSysParamList();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    SysParam selectSysParamById(int id);

    /**
     * 根据id更新
     * @param sysParam
     */
    int updateSysParamById(SysParam sysParam);
}
