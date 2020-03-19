package com.store.dao;

import com.store.BaseTest;
import com.store.entity.Area;
import com.store.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShopCategoryDaoTest extends BaseTest {
    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Test
    public void test() {
        List<ShopCategory> list = shopCategoryDao.queryShopCategory(new ShopCategory());
        System.out.println(list.size());
    }
}
