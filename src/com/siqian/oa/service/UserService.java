package com.siqian.oa.service;

import com.siqian.oa.base.DaoSupport;
import com.siqian.oa.domain.User;

public interface UserService extends DaoSupport<User>{

	/**
	 * 根据登录名与密码进行查询用户
	 * @param loginName
	 * @param password	明文密码
	 * @return
	 */
	User findByLoginNameAndPassword(String loginName, String password);

}
