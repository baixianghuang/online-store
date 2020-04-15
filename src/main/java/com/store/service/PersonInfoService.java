package com.store.service;

import com.store.dto.PersonInfoExecution;
import com.store.entity.PersonInfo;

public interface PersonInfoService {

	PersonInfo queryInfoByUserId(long userId);

	PersonInfoExecution insertPersonInfo(PersonInfo user);

	PersonInfoExecution updatePersonInfo(PersonInfo user);
}
