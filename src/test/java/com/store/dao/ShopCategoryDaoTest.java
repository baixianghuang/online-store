package com.store.dao;

import com.store.BaseTest;
import com.store.entity.Area;
import com.store.entity.ShopCategory;
import com.store.util.ImageUtil;
import com.store.util.PathUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ShopCategoryDaoTest extends BaseTest {
    @Autowired
    private ShopCategoryDao shopCategoryDao;

//    @Test
    public void testQueryShopCategory() {
        List<ShopCategory> list = shopCategoryDao.queryShopCategory(new ShopCategory());

        System.out.println(list.size());
        System.out.println(list.get(0).getShopCategoryName());
    }

    @Test
    public void testAddShopCategory() throws IOException {
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setCreateTime(new Date());
        shopCategory.setLastEditTime(new Date());
        shopCategory.setPriority(2);
        shopCategory.setShopCategoryName("Used keyboard");
        shopCategory.setShopCategoryDesc("Second-hand keyboard");
        String filePath = "G:\\shopCat1.png";
        MultipartFile multipartFile = ImageUtil.path2MultipartFile(filePath);
        String dest = PathUtil.getShopCategoryImagePath();
        System.out.println(dest);
        String generateHeadImg = ImageUtil.generateShopCategoryImg(multipartFile, dest);
        shopCategory.setShopCategoryImg(generateHeadImg);
        ShopCategory parentShopCategory = new ShopCategory();
        parentShopCategory.setShopCategoryId(10L);
        shopCategory.setParent(parentShopCategory);
        int effectNum = shopCategoryDao.insertShopCategory(shopCategory);
        System.out.println("effectNum:" + effectNum);
    }
}
