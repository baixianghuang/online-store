package com.store.service;

import java.io.IOException;
import java.util.List;

import com.store.dto.HeadLineExecution;
import com.store.dto.ImageHolder;
import com.store.entity.HeadLine;

public interface HeadLineService {
	public static final String HEADLINELISTKEY = "headlinelist";  // redis

	List<HeadLine> getHeadLineList(HeadLine headLineCondition);

	HeadLineExecution addHeadLine(HeadLine headLine, ImageHolder thumbnail);

	HeadLineExecution modifyHeadLine(HeadLine headLine, ImageHolder thumbnail);

	HeadLineExecution removeHeadLine(long headLineId);

	HeadLineExecution removeHeadLineList(List<Long> headLineIdList);
}
