package com.siqian.oa.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.siqian.oa.base.DaoSupport;

@Transactional
@SuppressWarnings("unchecked")
public abstract class DaoSupportImpl<T> implements DaoSupport<T>{

	@Resource
	private SessionFactory sessionFactory;
	private Class<T> clazz;
	
	public DaoSupportImpl(){
		//使用反射技术得到T的真实类型
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();//获取当前new的 对象的 泛型的 类 类型
		this.clazz = (Class<T>)pt.getActualTypeArguments()[0];//获取第一个类型参数的真实类型
		System.out.println("clazz--------->"+clazz);
		
	}
	
	
	/**
	 * 获取当前可用的session
	 * @return
	 */
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
		
	}
	
	public void save(T entity) {
		getSession().save(entity);
		
	}
	
	public void update(T entity) {
		getSession().update(entity);
		
	}

	public void delete(Long id) {
		Object obj = getById(id);
		if(obj != null){
			getSession().delete(obj);
		}
		
	}

	public T getById(Long id) {
		if(id == null){
			return null;
		}else{

			return (T)getSession().get(clazz,id);
		}	
	}

	
	public List<T> getByIds(Long[] ids) {
		if(ids == null || ids.length == 0){
			return Collections.EMPTY_LIST;
		}else{
			return getSession().createQuery("FROM "+ clazz.getSimpleName() +" where id IN (:ids)").setParameterList("ids", ids).list();
		}
	
	}

	public List<T> findAll() {
	
		return getSession().createQuery("FROM " + clazz.getSimpleName()).list();
	}



	

}
