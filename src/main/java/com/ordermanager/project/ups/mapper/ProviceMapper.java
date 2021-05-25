package com.ordermanager.project.ups.mapper;

import com.ordermanager.project.ups.domain.Provice;

public interface ProviceMapper {


    Provice getByStateName(String stateName);
}
