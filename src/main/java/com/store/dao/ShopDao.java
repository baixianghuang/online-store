package com.store.dao;

import com.store.entity.Shop;

public interface ShopDao {
    int insertShop(Shop shop);
    int updateShop(Shop shop);
}
