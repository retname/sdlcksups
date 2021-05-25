package com.ordermanager.project.ups.service;

import com.ordermanager.project.ups.domain.SysParam;
import com.ordermanager.project.ups.mapper.SysParamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysParamImpl implements ISysParam{



    @Autowired(required = false)
    private SysParamMapper sysParamMapper;

    @Override
    public SysParam getByUrlType(int urlType) {
        return  sysParamMapper.getByUrlType(urlType);
    }

    @Override
    public List<SysParam> selectSysParamList() {
        return sysParamMapper.selectSysParamList();
    }

    @Override
    public SysParam selectSysParamById(int id) {
        return sysParamMapper.selectSysParamById(id);
    }

    public int updateSysParamById(SysParam sysParam) {
       return sysParamMapper.updateSysParamById(sysParam);
    }
}
