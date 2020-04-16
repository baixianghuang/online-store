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
import com.store.enums.ShopCategoryStateEnum;
import com.store.exceptions.ShopCategoryOperationException;
import com.store.exceptions.ShopOperationException;
import com.store.service.ShopCategoryService;
import com.store.util.ImageUtil;
import com.store.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
        // 空值判断
        if (shopCategory != null) {
            // 设定默认值
            shopCategory.setCreateTime(new Date());
            shopCategory.setLastEditTime(new Date());
            if (thumbnail != null) {
                // 若上传有图片流，则进行存储操作，并给shopCategory实体类设置上相对路径
                addThumbnail(shopCategory, thumbnail);
            }
            try {
                // 往数据库添加店铺类别信息
                int effectedNum = shopCategoryDao.insertShopCategory(shopCategory);
                if (effectedNum > 0) {
                    return new ShopCategoryExecution(ShopCategoryStateEnum.SUCCESS, shopCategory);
                } else {
                    return new ShopCategoryExecution(ShopCategoryStateEnum.INNER_ERROR);
                }
            } catch (Exception e) {
                throw new ShopCategoryOperationException("添加店铺类别信息失败:" + e.toString());
            }
        } else {
            return new ShopCategoryExecution(ShopCategoryStateEnum.EMPTY);
        }
    }

    @Override
    public ShopCategoryExecution modifyShopCategory(ShopCategory shopCategory, ImageHolder thumbnail) {
        // 空值判断，主要判断shopCategoryId不为空
        if (shopCategory.getShopCategoryId() != null && shopCategory.getShopCategoryId() > 0) {
            // 设定默认值
            shopCategory.setLastEditTime(new Date());
            if (thumbnail != null) {
                // 若上传的图片不为空，则先获取之前的图片路径
                ShopCategory tempShopCategory = shopCategoryDao.queryShopCategoryById(shopCategory.getShopCategoryId());
                if (tempShopCategory.getShopCategoryImg() != null) {
                    // 若之前图片不为空，则先移除之前的图片
                    ImageUtil.deleteFileOrPath(tempShopCategory.getShopCategoryImg());
                }
                // 存储新的图片
                addThumbnail(shopCategory, thumbnail);
            }
            try {
                // 更新数据库信息
                int effectedNum = shopCategoryDao.updateShopCategory(shopCategory);
                if (effectedNum > 0) {
                    return new ShopCategoryExecution(ShopCategoryStateEnum.SUCCESS, shopCategory);
                } else {
                    return new ShopCategoryExecution(ShopCategoryStateEnum.INNER_ERROR);
                }
            } catch (Exception e) {
                throw new ShopCategoryOperationException("更新店铺类别信息失败:" + e.toString());
            }
        } else {
            return new ShopCategoryExecution(ShopCategoryStateEnum.EMPTY);
        }
    }

    private void addThumbnail(ShopCategory shopCategory, ImageHolder thumbnail) {
        String dest = PathUtil.getShopCategoryImagePath();
        String thumbnailAddr = ImageUtil.generateNormalImage(thumbnail, dest);
        shopCategory.setShopCategoryImg(thumbnailAddr);
    }

    @Override
    public ShopCategory getShopCategoryById(Long shopCategoryId) {
        return shopCategoryDao.queryShopCategoryById(shopCategoryId);
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
