package com.siqian.oa.test;

import org.junit.Test;

import com.siqian.oa.dao.RoleDao;
import com.siqian.oa.dao.UserDao;
import com.siqian.oa.dao.impl.RoleDaoImpl;
import com.siqian.oa.dao.impl.UserDaoImpl;

public class BaseDaoTest {
	
	@Test
	public void testSave(){
		UserDao userDao = new UserDaoImpl();
		RoleDao roleDao = new RoleDaoImpl();
	}

}
