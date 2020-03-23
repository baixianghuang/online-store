package com.store.dao;

import com.store.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {
    int insertShop(Shop shop);
    int updateShop(Shop shop);
    Shop queryByShopId(long shopId);

    /**
     * Query shop by shopName, enableStatus, and owner
     * Note that when there are more than 1 parameter, use @Param
     * @param shopCondition
     * @param rowIndex from which row
     * @param pageSize the number of rows
     * @return
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex,
                             @Param("pageSize") int pageSize);

    int queryShopCount(@Param("shopCondition") Shop shopCondition);
}
