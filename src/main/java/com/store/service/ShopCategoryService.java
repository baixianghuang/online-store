package com.store.service;

import com.store.dto.ImageHolder;
import com.store.dto.ShopCategoryExecution;
import com.store.entity.ShopCategory;

import java.util.List;

public interface ShopCategoryService {
    public static final String SHOPCATEGORYLISTKEY = "shopcategorylist";  // redis

    ShopCategoryExecution addShopCategory(ShopCategory shopCategory, ImageHolder thumbnail);

    ShopCategoryExecution modifyShopCategory(ShopCategory shopCategory, ImageHolder thumbnail);

    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);

    ShopCategory getShopCategoryById(Long shopCategoryId);
}
