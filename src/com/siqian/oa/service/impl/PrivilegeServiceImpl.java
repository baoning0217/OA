package com.siqian.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siqian.oa.dao.impl.DaoSupportImpl;
import com.siqian.oa.domain.Privilege;
import com.siqian.oa.service.PrivilegeService;

@Service
@Transactional
public class PrivilegeServiceImpl extends DaoSupportImpl<Privilege> implements PrivilegeService{

}
