package com.siqian.oa.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.siqian.oa.service.DepartmentService;
import com.siqian.oa.service.PrivilegeService;
import com.siqian.oa.service.RoleService;
import com.siqian.oa.service.UserService;

public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T>{

	//------------------ModelDriven的支持-------------------------
	private static final long serialVersionUID = 5284932704514513045L;

	protected T model;
	
	
	
	
	public BaseAction(){
		try {
		//通过反射获得model的真实类型
		ParameterizedType pt = (ParameterizedType)this.getClass().getGenericSuperclass();
		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
		//通过反射创建model的真实类型
		
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	public T getModel() {

		return model;
	}
	
	
	//-----------------Service实例的声明------------------
	@Resource
	protected DepartmentService departmentService;
	@Resource
	protected RoleService roleService;
	@Resource
	protected UserService userService;
	@Resource
	protected PrivilegeService privilegeService;
	

}
