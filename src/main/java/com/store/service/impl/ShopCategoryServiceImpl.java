package com.store.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.cache.JedisUtil;
import com.store.dao.ShopCategoryDao;
import com.store.dto.ImageHolder;
import com.store.dto.ShopCategoryExecution;
import com.store.entity.HeadLine;
import com.store.entity.Shop;
import com.store.entity.ShopCategory;
import com.store.exceptions.ShopOperationException;
import com.store.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Autowired
    ShopCategoryDao shopCategoryDao;
    // Adding redis
//    @Autowired
//    private JedisUtil.Keys jedisKeys;
//    @Autowired
//    private JedisUtil.Strings jedisStrings;

    @Override
    public ShopCategoryExecution addShopCategory(ShopCategory shopCategory, ImageHolder thumbnail) {
        return null;
    }

    @Override
    public ShopCategoryExecution modifyShopCategory(ShopCategory shopCategory, ImageHolder thumbnail) {
        return null;
    }

    @Override
    public ShopCategory getShopCategoryById(Long shopCategoryId) {
        return null;
    }

    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        return shopCategoryDao.queryShopCategory(shopCategoryCondition);
    }
//    @Override
//    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
//        String key = SHOPCATEGORYLISTKEY;
//        List<ShopCategory> shopCategoryList = null;
//        ObjectMapper mapper = new ObjectMapper();
//        if (shopCategoryCondition == null) {
//            key = key + "_allfirstlevel";
//        } else if (shopCategoryCondition != null && shopCategoryCondition.getParent() != null &&
//                shopCategoryCondition.getParent().getShopCategoryId() != null) {
//            key = key + "_parent" + shopCategoryCondition.getParent().getShopCategoryId();
//        } else if (shopCategoryCondition != null) {
//            key = key + "_allsecondlevel";
//        }
//        if (!jedisKeys.exists(key)) {
//            shopCategoryList = shopCategoryDao.queryShopCategory(shopCategoryCondition);
//            String jsonString;
//            try {
//                jsonString = mapper.writeValueAsString(shopCategoryList);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//                throw new ShopOperationException(e.getMessage());
//            }
//            jedisStrings.set(key, jsonString);
//        } else {
//            String jsonString = jedisStrings.get(key);
//            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, ShopCategory.class);
//            try {
//                shopCategoryList = mapper.readValue(jsonString, javaType);
//            } catch (IOException e) {
//                e.printStackTrace();
//                throw new ShopOperationException(e.getMessage());
//            }
//        }
//        return shopCategoryDao.queryShopCategory(shopCategoryCondition);
//    }

}
