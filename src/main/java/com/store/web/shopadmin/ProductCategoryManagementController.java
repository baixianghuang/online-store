package com.store.web.shopadmin;

import com.store.dto.Result;
import com.store.entity.ProductCategory;
import com.store.entity.Shop;
import com.store.enums.ProductCategoryStateEnum;
import com.store.service.ProductCategoryService;
import com.store.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("shopadmin")
public class ProductCategoryManagementController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "/getproductcategorylist", method = RequestMethod.GET)
    @ResponseBody
    private Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request) {
//        //TODO remove this after log module is finished
//        Shop shop = new Shop();
//        shop.setShopId(2L);
//        request.getSession().setAttribute("currentShop", shop);
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        if (currentShop != null && currentShop.getShopId() > 0) {
            List<ProductCategory> productCategoryList = productCategoryService.
                    getProductCategoryList(currentShop.getShopId());
            return new Result<List<ProductCategory>> (true, productCategoryList);
        } else {
            ProductCategoryStateEnum ps = ProductCategoryStateEnum.INNER_ERROR;
            return new Result<List<ProductCategory>> (false, ps.getState(), ps.getStateInfo());
        }
    }
}
