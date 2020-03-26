package com.store.service;

import com.store.dto.ImageHolder;
import com.store.dto.ProductExecution;
import com.store.entity.Product;
import com.store.exceptions.ProductOperationException;

import java.io.InputStream;
import java.util.List;

public interface ProductService {
    ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
            throws ProductOperationException;

}
