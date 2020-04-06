package com.store.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.cache.JedisUtil;
import com.store.dao.HeadLineDao;
import com.store.dto.HeadLineExecution;
import com.store.dto.ImageHolder;
import com.store.entity.Area;
import com.store.entity.HeadLine;
import com.store.exceptions.AreaOperationException;
import com.store.service.HeadLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HeadLineServiceImpl implements HeadLineService {
    @Autowired
    private HeadLineDao headLineDao;
    // Adding redis
//    @Autowired
//    private JedisUtil.Keys jedisKeys;
//    @Autowired
//    private JedisUtil.Strings jedisStrings;


    @Override
    public HeadLineExecution addHeadLine(HeadLine headLine, ImageHolder thumbnail) {
        return null;
    }

    @Override
    public HeadLineExecution removeHeadLine(long headLineId) {
        return null;
    }

    @Override
    public HeadLineExecution removeHeadLineList(List<Long> headLineIdList) {
        return null;
    }

    @Override
    public HeadLineExecution modifyHeadLine(HeadLine headLine, ImageHolder thumbnail) {
        return null;
    }

    @Override
    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) {
        return headLineDao.queryHeadLine(headLineCondition);
    }
//    @Override
//    @Transactional
//    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) {
//        String key = HEADLINELISTKEY;
//        List<HeadLine> headLineList = null;
//        ObjectMapper mapper = new ObjectMapper();
//        if (headLineCondition != null && headLineCondition.getEnableStatus() != null) {
//            key = key + "_" + headLineCondition.getEnableStatus();
//        }
//        if (!jedisKeys.exists(key)) {
//            headLineList = headLineDao.queryHeadLine(headLineCondition);
//            String jsonString;
//            try {
//                jsonString = mapper.writeValueAsString(headLineList);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//                throw new AreaOperationException(e.getMessage());
//            }
//            jedisStrings.set(key, jsonString);
//        } else {
//            String jsonString = jedisStrings.get(key);
//            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, HeadLine.class);
//            try {
//                headLineList = mapper.readValue(jsonString, javaType);
//            } catch (IOException e) {
//                e.printStackTrace();
//                throw new AreaOperationException(e.getMessage());
//            }
//        }
//        return headLineDao.queryHeadLine(headLineCondition);
//    }
}
