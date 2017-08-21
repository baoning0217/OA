package com.siqian.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.siqian.oa.base.BaseAction;
import com.siqian.oa.domain.Department;
import com.siqian.oa.util.DepartmentUtils;

@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department>{

	private static final long serialVersionUID = -3404199978514546931L;
	
	private Long parentId;
	
	public Long getParentId() {
		return parentId;
	}


	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	

	/* 列表 */
	public String list() throws Exception {
		List<Department> departmentList = departmentService.findAll();
		if(parentId == null){
			departmentList = departmentService.findTopList();//顶级部门列表
		}else{
			departmentList = departmentService.findChildren(parentId);//子部门列表
			
			Department parent = departmentService.getById(parentId);
			ActionContext.getContext().put("parent", parent);
		}
		
		ActionContext.getContext().put("departmentList", departmentList);
		
		return "list";
	}
	
	
	/* 删除 */
	public String delete() throws Exception {
		departmentService.delete(model.getId());
		return "toList";
	}
	
	
	/* 添加页面  */
	public String addUI() throws Exception {
		//准备departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		
		return "saveUI";
	}
	
	
	/* 添加  */
	public String add() throws Exception {
		//关联上级部门
		Department parent = departmentService.getById(parentId);
		model.setParent(parent);
		//保存
		departmentService.save(model);
		return "toList";
	}
	
	
	/* 修改页面  */
	public String editUI() throws Exception {
		//准备departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		//回显数据
		Department department = departmentService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(department);
		
		if(department.getParent() != null){
			parentId = department.getParent().getId();
		}
		
		return "saveUI";
	}
	
	
	/* 修改  */
	public String edit() throws Exception {
		// 1.从数据库中取出原对象
		Department department = departmentService.getById(model.getId());
		// 2.设置要修改的属性
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		department.setParent(departmentService.getById(parentId));//设置所属的上级部门
		// 3.更新到数据库中
		departmentService.update(department);
		
		return "toList";
	}


	
	

}
