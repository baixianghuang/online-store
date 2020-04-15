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
import com.store.enums.HeadLineStateEnum;
import com.store.exceptions.AreaOperationException;
import com.store.exceptions.HeadLineOperationException;
import com.store.service.HeadLineService;
import com.store.util.ImageUtil;
import com.store.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
    @Transactional
    public HeadLineExecution addHeadLine(HeadLine headLine, ImageHolder thumbnail) {
        if (headLine != null) {
            headLine.setCreateTime(new Date());
            headLine.setLastEditTime(new Date());
            if (thumbnail != null) {
                addThumbnail(headLine, thumbnail);
            }
            try {
                int effectedNum = headLineDao.insertHeadLine(headLine);
                if (effectedNum > 0) {
                    return new HeadLineExecution(HeadLineStateEnum.SUCCESS, headLine);
                } else {
                    return new HeadLineExecution(HeadLineStateEnum.INNER_ERROR);
                }
            } catch (Exception e) {
                throw new HeadLineOperationException("Headline operation failed:" + e.toString());
            }
        } else {
            return new HeadLineExecution(HeadLineStateEnum.EMPTY);
        }
    }

    @Override
    @Transactional
    public HeadLineExecution removeHeadLine(long headLineId) {
        if (headLineId > 0) {
            try {
                HeadLine tempHeadLine = headLineDao.queryHeadLineById(headLineId);
                if (tempHeadLine.getLineImg() != null) {
                    ImageUtil.deleteFileOrPath(tempHeadLine.getLineImg());
                }
                int effectedNum = headLineDao.deleteHeadLine(headLineId);
                if (effectedNum > 0) {
                    return new HeadLineExecution(HeadLineStateEnum.SUCCESS);
                } else {
                    return new HeadLineExecution(HeadLineStateEnum.INNER_ERROR);
                }
            } catch (Exception e) {
                throw new HeadLineOperationException("removeHeadLine failed: " + e.toString());
            }
        } else {
            return new HeadLineExecution(HeadLineStateEnum.EMPTY);
        }
    }

    @Override
    @Transactional
    public HeadLineExecution removeHeadLineList(List<Long> headLineIdList) {
        if (headLineIdList != null && headLineIdList.size() > 0) {
            try {
                List<HeadLine> headLineList = headLineDao.queryHeadLineByIds(headLineIdList);
                for (HeadLine headLine : headLineList) {
                    if (headLine.getLineImg() != null) {
                        ImageUtil.deleteFileOrPath(headLine.getLineImg());
                    }
                }
                int effectedNum = headLineDao.batchDeleteHeadLine(headLineIdList);
                if (effectedNum > 0) {
                    return new HeadLineExecution(HeadLineStateEnum.SUCCESS);
                } else {
                    return new HeadLineExecution(HeadLineStateEnum.INNER_ERROR);
                }
            } catch (Exception e) {
                throw new HeadLineOperationException("removeHeadLineList failed. " + e.toString());
            }
        } else {
            return new HeadLineExecution(HeadLineStateEnum.EMPTY);
        }
    }

    @Override
    @Transactional
    public HeadLineExecution modifyHeadLine(HeadLine headLine, ImageHolder thumbnail) {
        if (headLine.getLineId() != null && headLine.getLineId() > 0) {
            headLine.setLastEditTime(new Date());
            if (thumbnail != null) {
                HeadLine tempHeadLine = headLineDao.queryHeadLineById(headLine.getLineId());
                if (tempHeadLine.getLineImg() != null) {
                    ImageUtil.deleteFileOrPath(tempHeadLine.getLineImg());
                }
                addThumbnail(headLine, thumbnail);
            }
            try {
                int effectedNum = headLineDao.updateHeadLine(headLine);
                if (effectedNum > 0) {
                    return new HeadLineExecution(HeadLineStateEnum.SUCCESS, headLine);
                } else {
                    return new HeadLineExecution(HeadLineStateEnum.INNER_ERROR);
                }
            } catch (Exception e) {
                throw new HeadLineOperationException("updateHeadLine failed: " + e.toString());
            }
        } else {
            return new HeadLineExecution(HeadLineStateEnum.EMPTY);
        }
    }

    private void addThumbnail(HeadLine headLine, ImageHolder thumbnail) {
        String dest = PathUtil.getHeadLineImagePath();
        String thumbnailAddr = ImageUtil.generateNormalImage(thumbnail, dest);
        headLine.setLineImg(thumbnailAddr);
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
