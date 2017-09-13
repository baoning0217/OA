package com.siqian.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siqian.oa.dao.impl.DaoSupportImpl;
import com.siqian.oa.domain.Privilege;
import com.siqian.oa.service.PrivilegeService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class PrivilegeServiceImpl extends DaoSupportImpl<Privilege> implements PrivilegeService{

	public List<Privilege> findTopList() {
		return getSession().createQuery("FROM Privilege p WHERE p.parent IS NULL").list();
	}

}
