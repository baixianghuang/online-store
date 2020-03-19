package com.store.service;

import com.store.BaseTest;
import com.store.dto.ShopExecution;
import com.store.entity.Area;
import com.store.entity.PersonInfo;
import com.store.entity.Shop;
import com.store.entity.ShopCategory;
import com.store.enums.ShopStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Test
    public void testAddShop() throws FileNotFoundException {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(15L);
        area.setAreaId(3);
        shopCategory.setShopCategoryId(12L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("testName3");
        shop.setShopDesc("testDesc2");
        shop.setShopAddr("testAdrr2");
        shop.setPhone("testPhone2");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("under review");
        File shopImg = new File("F:\\QQ\\873253190\\FileRecv\\test1.jpg");
        ShopExecution se = shopService.addShop(shop, new FileInputStream(shopImg), shopImg.getName());
    }

}
