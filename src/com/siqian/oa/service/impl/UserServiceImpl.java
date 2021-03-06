package com.siqian.oa.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siqian.oa.dao.impl.DaoSupportImpl;
import com.siqian.oa.domain.User;
import com.siqian.oa.service.UserService;

@Service
@Transactional
public class UserServiceImpl extends DaoSupportImpl<User> implements UserService{

	public User findByLoginNameAndPassword(String loginName, String password) {
		//使用密码的MD5摘要进行对比
		String md5Digest = DigestUtils.md5Hex(password);
		
		return (User) getSession().createQuery("FROM User u WHERE u.loginName=? AND u.password=?")//
				.setParameter(0, loginName)//
				.setParameter(1, md5Digest)//
				.uniqueResult();
	}

}
