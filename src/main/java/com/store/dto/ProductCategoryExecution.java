package com.store.dto;

import com.store.entity.ProductCategory;
import com.store.entity.ProductCategory;
import com.store.enums.ProductCategoryStateEnum;
import com.store.enums.ProductCategoryStateEnum;

import java.util.List;

public class ProductCategoryExecution {
    private int state;
    private String stateInfo;
    private int count;
    private ProductCategory shop;
    private List<ProductCategory> productCategoryList;

    public ProductCategoryExecution() {
    }

    /**
     * When the operation failed, this constructor is invoked
     * @param productCategoryStateEnum
     */
    public ProductCategoryExecution(ProductCategoryStateEnum productCategoryStateEnum) {
        this.state = productCategoryStateEnum.getState();
        this.stateInfo = productCategoryStateEnum.getStateInfo();
    }

//    public ProductCategoryExecution(ProductCategoryStateEnum shopStateEnum, ProductCategory productCategory) {
//        this.state = shopStateEnum.getState();
//        this.stateInfo = shopStateEnum.getStateInfo();
//        this.shop = productCategory;
//    }

    public ProductCategoryExecution(ProductCategoryStateEnum shopStateEnum, List<ProductCategory> productCategoryList) {
        this.state = shopStateEnum.getState();
        this.stateInfo = shopStateEnum.getStateInfo();
        this.productCategoryList = productCategoryList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ProductCategory getProductCategory() {
        return shop;
    }

    public void setProductCategory(ProductCategory shop) {
        this.shop = shop;
    }

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }
}
