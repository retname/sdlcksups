package com.ordermanager.project.ups.service;


import com.ordermanager.project.ups.domain.Provice;
import com.ordermanager.project.ups.mapper.ProviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviceImpl {


    @Autowired(required = false)
    private ProviceMapper proviceMapper;


    public Provice getByStateName(String stateName){
        return  proviceMapper.getByStateName(stateName);
    }


}
