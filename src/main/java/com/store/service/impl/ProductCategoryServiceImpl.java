package com.store.service.impl;

import com.store.dao.ProductCategoryDao;
import com.store.dto.ProductCategoryExecution;
import com.store.entity.ProductCategory;
import com.store.entity.ShopCategory;
import com.store.enums.ProductCategoryStateEnum;
import com.store.exceptions.ProductCategoryOperationException;
import com.store.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException {
        if (productCategoryList != null && productCategoryList.size() > 0) {
            try {
                int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
                ProductCategoryExecution pe = new ProductCategoryExecution();
                if (effectedNum > 0) {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                } else {
                    throw new ProductCategoryOperationException("Error in batchAddProductCategory()");
                }
            } catch (Exception e) {
                throw new ProductCategoryOperationException("Error in batchAddProductCategory(): "+e.getMessage());
            }
        } else {
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }
    }

    @Override
    @Transactional
    public ProductCategoryExecution deleteProductCategory(long shopCategoryId, long shopId) throws ProductCategoryOperationException {
        // TODO set effected products' shopCategoryId into null
        try {
            int effectedNum = productCategoryDao.deleteProductCategory(shopCategoryId, shopId);
            ProductCategoryExecution pe = new ProductCategoryExecution();
            if (effectedNum > 0) {
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            } else {
                throw new ProductCategoryOperationException("Error in deleteProductCategory()");
            }
        } catch (Exception e) {
            throw new ProductCategoryOperationException("Error in deleteProductCategory(): " + e.getMessage());
        }
    }
}
