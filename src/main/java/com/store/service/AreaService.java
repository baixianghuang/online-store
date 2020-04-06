package com.store.service;

import com.store.entity.Area;

import java.util.List;

public interface AreaService {
    public static final String AREALISTKEY = "arealist";  // redis

    List<Area> getAreaList();
}
