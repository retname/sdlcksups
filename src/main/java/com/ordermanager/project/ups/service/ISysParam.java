package com.ordermanager.project.ups.service;

import com.ordermanager.project.ups.domain.SysParam;

import java.util.List;

/**
 * 参数表
 */
public interface ISysParam {




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
