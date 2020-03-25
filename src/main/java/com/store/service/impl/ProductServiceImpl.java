package com.store.service.impl;

import com.store.dao.ProductDao;
import com.store.dto.ProductCategoryExecution;
import com.store.dto.ProductExecution;
import com.store.entity.Product;
import com.store.enums.ProductCategoryStateEnum;
import com.store.enums.ProductStateEnum;
import com.store.exceptions.ProductCategoryOperationException;
import com.store.exceptions.ProductOperationException;
import com.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    public ProductExecution addProduct(Product product) throws ProductOperationException {
        try {
            int effectedNum = productDao.insertProduct(product);
            ProductCategoryExecution pe = new ProductCategoryExecution();
            if (effectedNum > 0) {
                return new ProductExecution(ProductStateEnum.SUCCESS);
            } else {
                throw new ProductCategoryOperationException("Error in addProduct()");
            }
        } catch (Exception e) {
            throw new ProductCategoryOperationException("Error in addProduct(): " + e.getMessage());
        }
    }
}
