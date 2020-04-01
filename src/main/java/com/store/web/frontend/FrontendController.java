package com.store.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/frontend")
public class FrontendController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    private String index() {
        return "frontend/index";
    }

    @RequestMapping(value = "/shopdetail", method = RequestMethod.GET)
    private String showShopDetail() {
        return "frontend/shopdetail";
    }

    @RequestMapping(value = "/productdetail", method = RequestMethod.GET)
    private String showProductDetail() {
        return "frontend/productdetail";
    }

    @RequestMapping(value = "/shoplist", method = RequestMethod.GET)
    private String showShopList() {
        return "frontend/shoplist";
    }

    @RequestMapping(value = "/pointrecord", method = RequestMethod.GET)
    private String showPointRecord() {
        return "frontend/pointrecord";
    }

    @RequestMapping(value = "/myawarddetail", method = RequestMethod.GET)
    private String showMyAwardDetail() {
        return "frontend/myawarddetail";
    }

    @RequestMapping(value = "/myrecord", method = RequestMethod.GET)
    private String showMyRecord() {
        return "frontend/myrecord";
    }

    @RequestMapping(value = "/mypoint", method = RequestMethod.GET)
    private String showMyPoint() {
        return "frontend/mypoint";
    }
}
