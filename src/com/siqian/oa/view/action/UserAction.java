package com.siqian.oa.view.action;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.siqian.oa.base.BaseAction;
import com.siqian.oa.domain.Department;
import com.siqian.oa.domain.Role;
import com.siqian.oa.domain.User;
import com.siqian.oa.util.DepartmentUtils;


@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{

	private static final long serialVersionUID = 2084104217985511407L;
	
	private Long departmentId;
	private Long[] roleIds;
 	
	
	
	/* 列表  */
	public String list() throws Exception{
		List<User> userList = userService.findAll();
		ActionContext.getContext().put("userList", userList);
		return "list";
	}
	
	/*	添加页面	 */
	public String addUI() throws Exception{
		//准备数据 departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		
		//准备数据 roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		
		return "saveUI";
	}
	
	
	/*	添加	 */
	public String add() throws Exception{
		//封装到对象中，当model是实体类型时，也可以使用model，但是要设置未封装的属性
		//设置所属部门
		model.setDepartment(departmentService.getById(departmentId));
		
		//设置关联的岗位
		List<Role> roleList = roleService.getByIds(roleIds);
		model.setRoles(new HashSet<Role>(roleList));
		
		//设置默认密码1234
		String md5Digest = DigestUtils.md5Hex("1234");
		model.setPassword(md5Digest);
		
		//保存到数据库
		userService.save(model);
		
		return "toList";
	}
	
	
	/*	修改页面	 */
	public String editUI() throws Exception{
		//准备数据 departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
				
		//准备数据 roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		
		//数据回显
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		
		if(user.getDepartment() != null){
			departmentId = user.getDepartment().getId();      
		}
		if(user.getRoles() != null){
			roleIds = new Long[user.getRoles().size()];
			int index = 0;
			for(Role role : user.getRoles()){
				roleIds[index++] = role.getId();
			}	
		}
		return "saveUI";
	}
	
	
	/*	编辑	 */
	public String edit() throws Exception{
		//1.从数据库中取出原对象
		User user = userService.getById(model.getId());
		
		//2.设置要修改的属性
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());
		user.setDescription(model.getDescription());
		
		user.setDepartment(departmentService.getById(departmentId));
		
		List<Role> roleList = roleService.getByIds(roleIds);
		user.setRoles(new HashSet<Role>(roleList));
		
		//3.更新到数据库
		userService.update(user);
		
		return "toList";
	}
	
	
	/*	删除	 */
	public String delete() throws Exception{
		userService.delete(model.getId());
		return "toList";
	}
	
	/*	初始化密码	 */
	public String initPassword() throws Exception{
		User user = userService.getById(model.getId());
		String md5Digest = DigestUtils.md5Hex("1234");
		user.setPassword(md5Digest);
		userService.update(user);
		return "toList";
	}
	
	
	
	/** 登录页面  */
	public String loginUI() throws Exception{
		return "loginUI";
	}
	
	
	/** 登录   */
	public String login() throws Exception{
		User user = userService.findByLoginNameAndPassword(model.getLoginName(),model.getPassword());
		if(user == null) {
			addFieldError("login", "用户名或密码不正确!");
			return "loginUI";
		}else{
			//登录用户
			ActionContext.getContext().getSession().put("user", user);
			return "toIndex";
			
		}	
	}
	
	
	/** 注销    */
	public String logout() throws Exception{
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}
	


	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}
	
	
	


	
	
	
	
	
	

}
