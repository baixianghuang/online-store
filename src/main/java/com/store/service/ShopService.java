package com.store.service;

import com.store.dto.ShopExecution;
import com.store.entity.Shop;
import com.store.exceptions.ShopOperationException;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;
}
