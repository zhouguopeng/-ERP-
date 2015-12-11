package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



import com.model.Organ;
import com.model.Role;
import com.model.User;



@Controller
@RequestMapping(value = "/users")
public class UserController {
	
	private static final String USER = "main/user";
	
	@RequestMapping(value = "/info", method = {RequestMethod.GET})
	public String timeout() {
		
		return USER;
	}
	@RequestMapping(value = "/combobox")
	@ResponseBody
	public String initCombobox(HttpServletRequest request) throws Exception {
		String type = request.getParameter("type");
		ObjectMapper o = new ObjectMapper();
		if("organ".equals(type)){
			// TODO:查询单位列表
			List<Organ> organList = new ArrayList<Organ>();
			Organ temp1= new Organ();
			Organ temp2 = new Organ();
			Organ temp3 = new Organ();
			temp1.setOrganId(0);
			temp1.setOrganName("---请选择");
			temp2.setOrganId(1);
			temp2.setOrganName("铁路七局");
			temp3.setOrganId(2);
			temp3.setOrganName("信息部");
			organList.add(temp1);
			organList.add(temp2);
			organList.add(temp3);
			return o.writeValueAsString(organList);
			
		}else if("role".equals(type)){
			// TODO:查询角色列表
			List<Role> roleList = new ArrayList<Role>();
			Role temp1= new Role();
			Role temp2 = new Role();
			Role temp3 = new Role();
			Role temp4 = new Role();
			temp1.setRoleId(0);
			temp1.setRoleName("---请选择");
			temp2.setRoleId(1);
			temp2.setRoleName("超级管理员");
			temp3.setRoleId(2);
			temp3.setRoleName("设备管理员");
			temp4.setRoleId(3);
			temp4.setRoleName("一般工作人员");
			roleList.add(temp1);
			roleList.add(temp2);
			roleList.add(temp3);
			roleList.add(temp4);
			return o.writeValueAsString(roleList);
		}else{
			return null;
		}
		
	}
	
	@RequestMapping(value = "/findAll",method={RequestMethod.POST})
	@ResponseBody
	public String findAll(User searchUser,HttpServletRequest request,HttpServletResponse response)throws Exception{
		int page=Integer.parseInt(request.getParameter("page")== null ? "1":request.getParameter("page"));
		int rows=Integer.parseInt(request.getParameter("rows")== null ? "20":request.getParameter("rows"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("startPage", (page-1)*rows);
		pageMap.put("endPage", rows);
	/*	List<Users> u=usersService.getAll(pageMap,searchUser);
		int total=usersService.countUsers();*/
		List<User> userList = new ArrayList<User>();
		int i = 5;
		int total = 0;
		do{
			User user = new User();
			user.setUserId(1000+i);
			user.setUserName("user" +i);
			user.setPassword("123456");
			
			Organ organ = new Organ();
			if( i%2 ==0){
				organ.setOrganId(1);;
				organ.setOrganName("铁路七局");
			}else{
				organ.setOrganId(2);
				organ.setOrganName("信息部");
			}
			user.setOrgan(organ);
			user.setOrganId(user.getOrgan().getOrganId());
			user.setOrganName(user.getOrgan().getOrganName());
			
			Role role = new Role();
			if(i%3 == 0){
				role.setRoleId(1);
				role.setRoleName("超级管理员");
			}else if(i%2 == 0){
				role.setRoleId(2);
				role.setRoleName("设备管理员");
			}else{
				role.setRoleId(3);
				role.setRoleName("一般工作人员");
			}
			user.setRole(role);
			user.setRoleId(user.getRole().getRoleId());
			user.setRoleName(user.getRole().getRoleName());
			userList.add(user);
			
			i--;
			
		}while(i > 0);
		// 过滤信息
		Iterator<User> iterator = userList.iterator();
		List<User> result = new ArrayList<User>();
		while(iterator.hasNext()){
			User temp = iterator.next();
			boolean flag = true;
			if(searchUser.getOrganId() != 0 && searchUser.getOrganId() != temp.getOrganId()){
				flag = false;
			}
			if(searchUser.getRoleId() != 0 && searchUser.getRoleId() != temp.getRoleId()){
				flag = false;
			}
			if(!StringUtils.isEmpty(searchUser.getUserName())&& !temp.getUserName().contains(searchUser.getUserName())){
				flag = false;
			}
			if(flag){
				total++;
				result.add(temp);
			}
		}
		map.put("rows", result);
		map.put("total",total);

		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);
	}
	

	/**
	 * 添加员工
	 * @param user
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="addUsers",method={RequestMethod.POST})
	@ResponseBody
	public String addUsers(User user,Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) throws Exception{
		int res = 0;
		// Ajax传递多值到对象中时，SpringMVC自动生成的map中会存放两个值，对象以及对象验证的结果值
		map.clear();
		ObjectMapper mapper = new ObjectMapper();
		// TODO: 保存数据
		res =1;
		// int res=usersService.addUsers(user);
		// System.out.println(res);
		if(res > 0){
			map.put("type", "Success");
			map.put("msg", "保存成功!!!");
		}else{
			map.put("type", "Error");
			map.put("msg", "保存失败 !!!");
		}
		return mapper.writeValueAsString(map);
	}
	
	@RequestMapping(value="updateUsers",method={RequestMethod.POST})
	@ResponseBody
	public String updateUsers(User user,Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) throws Exception{
		int id=Integer.parseInt(request.getParameter("id"));
		map.clear();
		user.setUserId(id);
		// TODO:验证用户是否存在
		// TODO:判定修改记录是否是最新
		// TODO:保存数据和操作日志
		
		map.put("type", "Success");
		map.put("msg", "update user success !!!");
	
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(map);
	}
	@RequestMapping(value="deleteUsers")
	public void deleteUsers(HttpServletRequest request,HttpServletResponse response) throws Exception{
		/*int id=Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		int res=usersService.deleteUsers(id);*/
		Map<String, Object> map=new HashMap<String, Object>();
		/*if(res>0){
			map.put("success", true);
		}else{*/
			map.put("success", false);
			map.put("errorMsg", "update user fail !!!");
		/*}*/
			ObjectMapper o = new ObjectMapper();
			String str=o.writeValueAsString(map);
		response.getWriter().write(str);
	}
}
