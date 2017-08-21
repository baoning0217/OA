package com.siqian.oa.test;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;


@Controller
@Scope("prototype")
public class TestAction extends ActionSupport{

	private static final long serialVersionUID = -6091436693971649778L;

	public String execute() throws Exception {
		
		return "success";
	}

}
