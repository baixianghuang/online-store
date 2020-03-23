package com.store.dao;

import com.store.BaseTest;
import com.store.entity.Area;
import com.store.entity.PersonInfo;
import com.store.entity.Shop;
import com.store.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class ShopDaoTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;

    @Test
    public void testQueryShopList() {
        Shop shopCondition = new Shop();
        PersonInfo owner = new PersonInfo();
        owner.setUserId(15L);
        shopCondition.setOwner(owner);
        List<Shop> shopList = shopDao.queryShopList(shopCondition,0,5);
        System.out.println(shopList.size());
        System.out.println(shopList.get(0).getShopName());
        System.out.println(shopDao.queryShopCount(shopCondition));
    }

//    @Test
    public void testQueryByShopId() {
        Shop shop = shopDao.queryByShopId(2L);
        System.out.println(shop.getShopName());
    }

//    @Test
    public void testInsertShop() {
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
        shop.setShopName("testName");
        shop.setShopDesc("testDesc");
        shop.setShopAddr("testAdrr");
        shop.setPhone("testPhone");
        shop.setShopImg("testImg");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("under review");
        int effectNum = shopDao.insertShop(shop);
        System.out.println(effectNum);
    }

//    @Test
    public void testUpdateShop() {
        Shop shop = new Shop();
        shop.setShopId(2L);
        shop.setShopDesc("updateDesc");
        shop.setShopAddr("updateAdrr");
        shop.setLastEditTime(new Date());
        int effectNum = shopDao.updateShop(shop);
        System.out.println(effectNum);
    }

}
