package com.siqian.oa.view.action;

import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.siqian.oa.base.BaseAction;
import com.siqian.oa.domain.Privilege;
import com.siqian.oa.domain.Role;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{

	private static final long serialVersionUID = -5881943717234263295L;
	
	private Long[] privilegeIds;

//	private Long id;
//	private String name;
//	private String description;
	
	private Role model = new Role();
	
	public Role getModel() {
		
		return model;
	}

	
	/* 列表*/
	public String list() throws Exception{
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "list";
	}
	
	/*删除 */
	public String delete() throws Exception{
		roleService.delete(model.getId());
		return "toList";
	}
	
	/*添加页面*/
	public String addUI() throws Exception{
		return "saveUI";
	}
	
	/*添加*/
	public String add() throws Exception{
//		封装到对象中
//		Role role = new Role();
//		role.setName(model.getName());
//		role.setDescription(model.getDescription());
//		//保存到数据库中
//		roleService.save(role);
		
		roleService.save(model);
		return "toList";
	}
	
	/*修改页面*/
	public String editUI() throws Exception{
		//准备回显的数据
		Role role = roleService.getById(model.getId());
		
		ActionContext.getContext().getValueStack().push(role);

		return "saveUI";
	}
	
	/*修改*/
	public String edit() throws Exception{
		Role role = roleService.getById(model.getId());
		
		role.setName(model.getName());
		role.setDescription(model.getDescription());
		
		roleService.update(role);
		return "toList";
	}
	
	
	/*设置权限页面*/
	public String setPrivilegeUI() throws Exception{
		//准备回显的数据
		Role role = roleService.getById(model.getId());
		
		ActionContext.getContext().getValueStack().push(role);
		
		if(role.getPrivileges() != null){
			privilegeIds = new Long[role.getPrivileges().size()];
			int index = 0;
			for(Privilege priv : role.getPrivileges()) {
				privilegeIds[index++] = priv.getId();
			}
			
		}
		
		//准备数据privilegeList
		List<Privilege> privilegeList = privilegeService.findAll();
		ActionContext.getContext().put("privilegeList", privilegeList);
		
		return "setPrivilegeUI";
		
	}
	
	/*设置权限*/
	public String setPrivilege() throws Exception{
		Role role = roleService.getById(model.getId());
		
		List<Privilege> privilegeList = privilegeService.getByIds(privilegeIds);
		role.setPrivileges(new HashSet<Privilege>(privilegeList));
		
		roleService.update(role);
		return "toList";
	}


	
	
	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}



	
	
	
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//	
//	
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
	
}
