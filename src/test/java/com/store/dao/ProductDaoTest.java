package com.store.dao;

import com.store.BaseTest;
import com.store.entity.Product;
import com.store.entity.ProductCategory;
import com.store.entity.ProductImg;
import com.store.entity.Shop;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductDaoTest extends BaseTest {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;

    //    @Test
    public void testUpdateProduct(){
        Shop shop = new Shop();
        shop.setShopId(2L);
        ProductCategory pc1 = new ProductCategory();
        pc1.setProductCategoryId(1L);
        Product product = new Product();
        product.setProductId(4L);
        product.setShop(shop);
        product.setProductCategory(pc1);
        product.setProductName("updated name");
        int effectedNum = productDao.updateProduct(product);
        System.out.println(effectedNum);
    }

//    @Test
    public void testQueryProductById(){
        Product product = productDao.queryProductById(4L);
        System.out.println(product.getProductName());
    }

//    @Test
    public void testQueryProductList(){
        Product productCondition = new Product();
        List<Product> list = productDao.queryProductList(productCondition, 0, 3);
        System.out.println(list.size());
        System.out.println(productDao.queryProductCount(productCondition));
    }

    @Test
    public void testUpdateProductCategory(){
        int effectedNum = productDao.updateProductCategoryToNull(1L);
        System.out.println(effectedNum);
    }

}
