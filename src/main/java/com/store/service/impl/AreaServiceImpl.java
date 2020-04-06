package com.store.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.cache.JedisUtil;
import com.store.dao.AreaDao;
import com.store.entity.Area;
import com.store.exceptions.AreaOperationException;
import com.store.service.AreaService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;
//    // Adding redis
//    @Autowired
//    private JedisUtil.Keys jedisKeys;
//    @Autowired
//    private JedisUtil.Strings jedisStrings;

    private static String AREALISTKEY = "arealist";

    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }
//    @Override
//    @Transactional
//    public List<Area> getAreaList() {
//        String key = AREALISTKEY;
//        List<Area> areaList = null;
//        ObjectMapper mapper = new ObjectMapper();
//        if (!jedisKeys.exists(key)) {
//            areaList = areaDao.queryArea();
//            String jsonString;
//            try {
//                jsonString = mapper.writeValueAsString(areaList);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//                throw new AreaOperationException(e.getMessage());
//            }
//            jedisStrings.set(key, jsonString);
//        } else {
//            String jsonString = jedisStrings.get(key);
//            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Area.class);
//            try {
//                areaList = mapper.readValue(jsonString, javaType);
//            } catch (IOException e) {
//                e.printStackTrace();
//                throw new AreaOperationException(e.getMessage());
//            }
//        }
//        return areaDao.queryArea();
//    }
}
