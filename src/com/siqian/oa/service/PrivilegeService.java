package com.siqian.oa.service;

import java.util.List;

import com.siqian.oa.base.DaoSupport;
import com.siqian.oa.domain.Privilege;

public interface PrivilegeService extends DaoSupport<Privilege>{

	/**
	 * 查询所有顶级的权限
	 * @return
	 */
	List<Privilege> findTopList();

}
