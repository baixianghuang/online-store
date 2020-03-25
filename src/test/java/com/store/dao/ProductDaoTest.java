package com.store.dao;

import com.store.BaseTest;
import com.store.entity.Product;
import com.store.entity.ProductCategory;
import com.store.entity.ProductImg;
import com.store.entity.Shop;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductDaoTest extends BaseTest {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;

    @Test
    public void testABatchInsertProductImg(){
        Shop shop = new Shop();
        shop.setShopId(2L);
        ProductCategory pc1 = new ProductCategory();
        pc1.setProductCategoryId(1L);
        Product p1 = new Product();
        p1.setProductName("n1");
        p1.setProductDesc("d1");
        p1.setImgAddr("img1");
        p1.setPriority(1);
        p1.setEnableStatus(1);
        p1.setCreateTime(new Date());
        p1.setLastEditTime(new Date());
        p1.setShop(shop);
        p1.setProductCategory(pc1);
        int effectedNum = productDao.insertProduct(p1);
        System.out.println(effectedNum);
    }
}
