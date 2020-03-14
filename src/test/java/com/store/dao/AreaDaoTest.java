package com.store.dao;

import com.store.BaseTest;
import com.store.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AreaDaoTest extends BaseTest {
    @Autowired
    private AreaDao areaDao;

    @Test
    public void test() {
        List<Area> list = areaDao.queryArea();
        System.out.println(list.size());
    }
}
