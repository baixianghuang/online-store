package com.store.service;

import com.store.BaseTest;
import com.store.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AreaServiceTest extends BaseTest {
    @Autowired
    private AreaService areaService;

    @Test
    public void testGetAreaList() {
        List<Area> list = areaService.getAreaList();
        System.out.println(list);
    }
}
