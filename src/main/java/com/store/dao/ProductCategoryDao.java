package com.store.dao;

import com.store.entity.ProductCategory;
import com.store.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryDao {
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);
    List<ProductCategory> queryProductCategoryList(long shopId);
}
