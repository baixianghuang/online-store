package com.store.dao;

import com.store.BaseTest;
import com.store.entity.ProductImg;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductImagDaoTest extends BaseTest {

    @Autowired
    private ProductImgDao productImgDao;

    @Test
    public void testABatchInsertProductImg(){
        ProductImg img1 = new ProductImg();
        img1.setImgAddr("img1");
        img1.setImgDesc("desc img1");
        img1.setPriority(1);
        img1.setCreateTime(new Date());
        img1.setProductId(4L);
        ProductImg img2 = new ProductImg();
        img2.setImgAddr("img2");
        img2.setImgDesc("desc img2");
        img2.setPriority(1);
        img2.setCreateTime(new Date());
        img2.setProductId(4L);
        List<ProductImg> productImgList = new ArrayList<>();
        productImgList.add(img1);
        productImgList.add(img2);
        int effectedNum = productImgDao.batchInsertProductImg(productImgList);
        System.out.println(effectedNum);
    }
}
