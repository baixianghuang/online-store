package com.store.service;

import com.store.dto.ImageHolder;
import com.store.dto.ShopExecution;
import com.store.entity.Shop;
import com.store.exceptions.ShopOperationException;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;
    ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;
    Shop getByShopId(long shopId);
    ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);
}
