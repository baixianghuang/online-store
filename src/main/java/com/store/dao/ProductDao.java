package com.store.dao;

import com.store.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {

    int insertProduct(Product product);

    int deleteProduct(@Param("productId") long productId, @Param("shopId") long shopId);

    int updateProduct(Product product);

    /**
     * Before remove product category, set effected products' productCategoryId to null
     * @param productCategoryId
     * @return
     */
    int updateProductCategoryToNull(long productCategoryId);

    /**
     * Using productName, status, shopId, shopCategory to query shop and divide the result according to pageSize
     * @param productCondition
     * @param rowIndex
     * @param pageSize
     * @return
     */
    List<Product> queryProductList(@Param("productCondition") Product productCondition, @Param("rowIndex") int rowIndex,
                                   @Param("pageSize") int pageSize);

    int queryProductCount(@Param("productCondition") Product productCondition);

    Product queryProductById(long productId);
}
