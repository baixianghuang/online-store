package com.store.dao;

import com.store.BaseTest;
import com.store.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void testBatchInsertProductCategory() {
        ProductCategory productCategory1 = new ProductCategory();
        productCategory1.setProductCategoryName("pCat1");
        productCategory1.setPriority(1);
        productCategory1.setCreateTime(new Date());
        productCategory1.setShopId(2L);
        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setProductCategoryName("pCat2");
        productCategory2.setPriority(2);
        productCategory2.setCreateTime(new Date());
        productCategory2.setShopId(2L);
        List<ProductCategory> list = new ArrayList<>();
        list.add(productCategory1);
        list.add(productCategory2);
        int effectedNum = productCategoryDao.batchInsertProductCategory(list);
        System.out.println(effectedNum);
    }

//    @Test
    public void testQueryProductCategoryList() throws Exception{
        long shopId = 2L;
        List<ProductCategory> list = productCategoryDao.queryProductCategoryList(shopId);
        System.out.println(list.size());
    }
}
