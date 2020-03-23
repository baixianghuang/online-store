package com.store.service;

import com.store.dto.ProductCategoryExecution;
import com.store.entity.ProductCategory;
import com.store.entity.ShopCategory;
import com.store.exceptions.ProductCategoryOperationException;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> getProductCategoryList(long shopId);
    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
            throws ProductCategoryOperationException;
}
