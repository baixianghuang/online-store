package com.store.dao;

import com.store.BaseTest;
import com.store.entity.ProductCategory;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Ensure the order of execution is testAXXX, testBXXX...
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void testCDeleteProductCategory() {
        long shopId = 2L;
        List<ProductCategory> list = productCategoryDao.queryProductCategoryList(shopId);
        for (ProductCategory p : list) {
            if (p.getProductCategoryName().equals("pCat2")) {
                int effectedNum = productCategoryDao.deleteProductCategory(p.getProductCategoryId(), p.getShopId());
                System.out.println(effectedNum);
            }
        }
    }

//    @Test
    public void testABatchInsertProductCategory() {
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
    public void testBQueryProductCategoryList() throws Exception{
        long shopId = 2L;
        List<ProductCategory> list = productCategoryDao.queryProductCategoryList(shopId);
        System.out.println(list.size());
        System.out.println(list.get(0).getProductCategoryName());
    }
}
