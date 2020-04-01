package com.store.service.impl;

import com.store.dao.HeadLineDao;
import com.store.dto.HeadLineExecution;
import com.store.dto.ImageHolder;
import com.store.entity.HeadLine;
import com.store.service.HeadLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeadLineServiceImpl implements HeadLineService {
    @Autowired
    private HeadLineDao headLineDao;

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
}
