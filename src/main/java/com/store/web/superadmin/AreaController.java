package com.store.web.superadmin;

import com.store.entity.Area;
import com.store.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/superadmin")
public class AreaController {
    Logger logger = LoggerFactory.getLogger(AreaController.class);

    @Autowired
    private AreaService areaService;

    // Note that all values in RequestMapping are set to lowercase (to facilitate url writing)
    @RequestMapping(value = "/listarea", method = RequestMethod.GET)
    @ResponseBody  // print the json result to the web page
    public Map<String, Object> listArea() {
        logger.info("***** start *****");
        long startTime = System.currentTimeMillis();
        Map<String, Object> modelMap = new HashMap<>();
        List<Area> list = new ArrayList<>();
        try {
            list = areaService.getAreaList();
            modelMap.put("rows", list);
            modelMap.put("total", list.size());
        } catch (Exception e) {
            e.printStackTrace();;
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }

        logger.error("test error!");
        long endTime = System.currentTimeMillis();
        logger.debug("Cost time:[{}ms]", endTime - startTime);
        logger.info("***** end *****");

        return modelMap;
    }
}
