package com.store.service;

import com.store.dto.ShopExecution;
import com.store.entity.Shop;

import java.io.File;

public interface ShopService {
    ShopExecution addShop(Shop shop, File shopImg);
}
