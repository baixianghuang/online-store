package com.store.dao;

import com.store.entity.LocalAuth;
import org.apache.ibatis.annotations.Param;

import java.util.Date;


public interface LocalAuthDao {

	LocalAuth queryLocalByUsernameAndPwd(@Param("username") String username, @Param("password") String password);

	LocalAuth queryLocalByUsername(String username);

	LocalAuth queryLocalByLocalAuthId(long localAuthId);

	int insertLocalAuth(LocalAuth localAuth);

	int updateLocalAuth(@Param("username") String username, @Param("password") String password,
                        @Param("newPassword") String newPassword, @Param("lastEditTime") Date lastEditTime);

}
