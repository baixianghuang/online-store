package com.store.service;

import com.store.BaseTest;
import com.store.dao.ProductDao;
import com.store.dto.ImageHolder;
import com.store.dto.ProductExecution;
import com.store.entity.Product;
import com.store.entity.ProductCategory;
import com.store.entity.Shop;
import com.store.enums.ProductStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductServiceTest extends BaseTest {

    @Autowired
    private ProductService productService;

//    @Test
    public void testAddProduct() throws FileNotFoundException {
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(2L);
        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryId(2L);
        product.setShop(shop);
        product.setProductCategory(pc);
        product.setProductName("tName1");
        product.setPriority(20);
        product.setProductDesc("tDesc1");
        product.setCreateTime(new Date());
        product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
        File thumbnailFile = new File("F:\\QQ\\873253190\\FileRecv\\test1.jpg");
        InputStream is = new FileInputStream(thumbnailFile);
        ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);
        File productImg1 = new File("F:\\QQ\\873253190\\FileRecv\\test1.jpg");
        InputStream is1 = new FileInputStream(productImg1);
        File productImg2 = new File("F:\\QQ\\873253190\\FileRecv\\test1.jpg");
        InputStream is2 = new FileInputStream(productImg2);
        List<ImageHolder> productImgList = new ArrayList<>();
        productImgList.add(new ImageHolder(productImg1.getName(), is1));
        productImgList.add(new ImageHolder(productImg2.getName(), is2));
        ProductExecution pe = productService.addProduct(product, thumbnail, productImgList);
        System.out.println(pe.getStateInfo());
    }

    @Test
    public void testUpdateProduct() throws FileNotFoundException {
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(2L);
        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryId(2L);
        product.setProductId(4L);
        product.setShop(shop);
        product.setProductCategory(pc);
        product.setProductName("modified tName1");
        product.setProductDesc("modified tDesc1");
        File thumbnailFile = new File("F:\\QQ\\873253190\\FileRecv\\test1.jpg");
        InputStream is = new FileInputStream(thumbnailFile);
        ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);
        File productImg1 = new File("F:\\QQ\\873253190\\FileRecv\\test1.jpg");
        InputStream is1 = new FileInputStream(productImg1);
        File productImg2 = new File("F:\\QQ\\873253190\\FileRecv\\test1.jpg");
        InputStream is2 = new FileInputStream(productImg2);
        List<ImageHolder> productImgList = new ArrayList<>();
        productImgList.add(new ImageHolder(productImg1.getName(), is1));
        productImgList.add(new ImageHolder(productImg2.getName(), is2));
        ProductExecution pe = productService.modifyProduct(product, thumbnail, productImgList);
        System.out.println(pe.getStateInfo());
    }
}
