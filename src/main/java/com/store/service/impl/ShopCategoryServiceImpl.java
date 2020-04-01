package com.store.service.impl;

import com.store.dao.ShopCategoryDao;
import com.store.dto.ImageHolder;
import com.store.dto.ShopCategoryExecution;
import com.store.entity.ShopCategory;
import com.store.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

    @Autowired
    ShopCategoryDao shopCategoryDao;

    @Override
    public ShopCategoryExecution addShopCategory(ShopCategory shopCategory, ImageHolder thumbnail) {
        return null;
    }

    @Override
    public ShopCategoryExecution modifyShopCategory(ShopCategory shopCategory, ImageHolder thumbnail) {
        return null;
    }

    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        return shopCategoryDao.queryShopCategory(shopCategoryCondition);
    }

    @Override
    public ShopCategory getShopCategoryById(Long shopCategoryId) {
        return null;
    }
}
