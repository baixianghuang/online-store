package com.store.service.impl;

import com.store.dao.ProductCategoryDao;
import com.store.dto.ProductCategoryExecution;
import com.store.entity.ProductCategory;
import com.store.entity.ShopCategory;
import com.store.exceptions.ProductCategoryOperationException;
import com.store.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    ProductCategoryDao productCategoryDao;

    @Override
    public List<ProductCategory> getProductCategoryList(long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }

    @Override
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException {
        int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
        ProductCategoryExecution pe = new ProductCategoryExecution();
        if (effectedNum > 0) {
            pe.setProductCategoryList(productCategoryList);
        } else {
            pe.setStateInfo();
        }

        return ;
    }
}
