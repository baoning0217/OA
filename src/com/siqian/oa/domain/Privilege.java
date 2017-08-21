package com.siqian.oa.domain;

import java.util.HashSet;
import java.util.Set;

public class Privilege {
	
	private Long id;
	private String url;
	private String name;
	private Set<Role> roles = new HashSet<Role>();
	private Privilege parent;
	private Set<Privilege> children = new HashSet<Privilege>();
	
	
	public Privilege() {
		
	}
	
	
	public Privilege(String name,String url,  Privilege parent) {
		super();
		this.url = url;
		this.name = name;
		this.parent = parent;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public Privilege getParent() {
		return parent;
	}
	public void setParent(Privilege parent) {
		this.parent = parent;
	}
	public Set<Privilege> getchildren() {
		return children;
	}
	public void setchildren(Set<Privilege> children) {
		this.children = children;
	}
	
	
	
	

}
