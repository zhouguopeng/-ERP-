package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.Rights;
import com.soft863.dolphin.util.AjaxObject;

/**
 * 
 * @ProjectName: [zwh] 
 * @Package: [com.zwh.controller.IndexController.java]  
 * @ClassName: [IndexController] 
 * @Description: [TODO]
 * @Author [Administrator]
 *
 */
@Controller
@RequestMapping("/main/index")
public class IndexController {
	//@Autowired
	//private UserService<User> userService;
	//@Autowired
	//private LogService<LogSys> logService;
	
	private static final String INDEX = "main/index";
	private static final String UPDATE_PASSWORD = "main/updatePwd";
	
	@RequestMapping(value="", method={RequestMethod.GET, RequestMethod.POST})
	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//给客户端发送cookie
		try{
			/*User user = userService.get(request.getSession().getAttribute(ConstantStr.LOGIN_ID).toString());
			if(user!=null&&user.getUsername()!=null){
				Cookie cookie = new Cookie("ramsUserName", URLEncoder.encode(user.getUsername(),"UTF-8"));
				cookie.setMaxAge(3600*24);
				cookie.setPath("/");
				response.addCookie(cookie);
			}*/
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return INDEX;
	}
	
	@RequestMapping(value="searchMenus", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody String searchMenus(){
		JSONObject json = new JSONObject();	
		Subject subject = getCurrentUser();
		try{
			List<Rights> list = new ArrayList<Rights>();
			for(int i=0;i<3;i++){
				Rights r = new Rights();
				r.setRightId(i);
				r.setParentId(0);
				r.setLevel("01");
				if(i==0){
					r.setRightName("系统管理");
				} else if(i==1){
					r.setRightName("物理设备管理");
				} else if(i==2){
					r.setRightName("虚拟设备管理");
				}
				list.add(r);
			}
			
			/*for(int i = 0; i < list.size(); i++){
				Map<String,Object> m = (Map)list.get(i);
				Iterator<Map.Entry<String,Object>> it = m.entrySet().iterator();
				while (it.hasNext()) {  
			        Map.Entry<String,Object> entry = (Map.Entry<String,Object>)it.next();   
			        if(entry.getValue()==null){
			        	m.put(entry.getKey(), "");
			        }
				}
			}*/
			StringBuffer sb = new StringBuffer("{\"menus\": [");
			for(int i = 0; i < list.size(); i ++){
				Rights r = list.get(i);
	        		sb.append("{\"menuid\":\"");  
	                sb.append(r.getRightId());  
	                sb.append("\",\"icon\":\"");  
	                sb.append(r.getIcon());  
	                sb.append("\",\"menuname\":\"");  
	                sb.append(r.getRightName());  
	                sb.append("\",\"menus\":");  
	                sb.append(this.getUIStr(r.getRightId(), "02"));  
	                sb.append("");  
	                sb.append("},");  
			}
			
			sb.append("]}");
			Map<String,Object> map = new HashMap<String,Object>();
			json = JSONObject.fromObject(map);
			json.put("retvalue", 0);
			json.put("rows", sb.toString());
		} catch(Exception e){
			json.put("retvalue", -1);
			json.put("msgcode","-1");
			json.put("msgname",e.toString());
			json.put("ordercode", "");
			e.printStackTrace();
		} 
		return json.toString();
	}
	
	private String getUIStr(Integer compId, String layerId) throws Exception{
		Subject subject = getCurrentUser();
		//获得界面数据
		//List list = this.indexService.getComponents(compId, "04");
		List<Rights> list = new ArrayList<Rights>();
		for(int i=4;i<7;i++){
			Rights r = new Rights();
			r.setRightId(i);
			r.setParentId(compId);
			r.setLevel("02");
			r.setIcon("icon-nav");
			r.setUrl("users/info");
			if(i==4){
				r.setRightName("用户管理");
			} else if(i==5){
				r.setRightName("角色管理");
			} else if(i==6){
				r.setRightName("部门管理");
			}
			list.add(r);
		}
		if(list.size() == 0) 
			return "";
		
		StringBuffer sb = new StringBuffer("[");
		for(int i = 0; i < list.size(); i ++){
			Rights r = list.get(i);
        	//if(subject.hasRole(r.getModel())){
        		sb.append("{\"menuid\":\"");  
                sb.append(r.getRightId());  
                sb.append("\",\"menuname\":\"");  
                sb.append(r.getRightName());  
                sb.append("\",\"icon\":\"");  
                sb.append(r.getIcon());  
                sb.append("\",\"url\":\"");  
                sb.append(r.getUrl());  
                sb.append("\"");  
                sb.append("},");  
			//}
		}
		sb.append("]");
		return sb.toString();
	}
	
	@RequestMapping(value="/error", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody String error(HttpServletRequest request) {
		return AjaxObject.newError("权限不足！").setCallbackType("").toString();
	}
	
	/**
	 * 
	 * @Title: preUpdatePassword
	 * @Description: 跳转到密码修改页面
	 * @return String
	 * @Author Administrator
	 */
	@RequestMapping(value="/toUpdatePwd", method=RequestMethod.GET)
	public String preUpdatePassword() {
		return UPDATE_PASSWORD;
	}
	
	/**
	 * 
	 * @Title: updatePassword
	 * @Description: 修改密码
	 * @param plainPassword
	 * @param newPassword
	 * @throws Exception
	 * @return String
	 * @Author Administrator
	 */
	@RequestMapping(value="/updatePwd", method=RequestMethod.POST)
	public @ResponseBody String updatePassword(String plainPassword, String newPassword) throws Exception {
		/*plainPassword = new Md5Hash(plainPassword.trim()).toString();
		Long longinId = this.getCurUserId();
		User userTmp = userService.get(longinId);
		if(!(plainPassword.equals(userTmp.getPassword()))){
			return AjaxObject.newError("原始密码错误，请重新输入").toString();
		}
		userTmp.setPassword(new Md5Hash(newPassword.trim()).toString());
		this.addAttributesUpdateUser(userTmp);
		userService.update(userTmp);*/
		return AjaxObject.newOk("修改成功").toString();
	}
	
	/**
	 * 
	 * @Title: logoutVerify
	 * @Description: 安全退出
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return void
	 * @Author Administrator
	 */
	@RequestMapping(value="/logoutVerify", method=RequestMethod.POST)
	public void logoutVerify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*User user = userService.get(this.getCurUserId());
		if(user!=null){
			userService.updateSelective(user);
			LogSys log = new LogSys();
			logService.insertLog(log);
		}*/
		response.getWriter().print("success");
	}
	
	/**
	 * 
	 * @Title: addAttributesUpdate
	 * @Description: 更新用户前更新共通属性
	 * @param _user
	 * @return void
	 * @Author Administrator
	 */
/*	private void addAttributesUpdateUser(User _user) {
		Long longinId = this.getCurUserId();
		Date date = new Date();
		_user.setUpdEac(_user.getUpdEac() + 1);
		_user.setUpdYmdhms(date);
		_user.setUpdId(longinId);
	}*/
	
	/**
	 * 
	 * @Title: getCurUserId
	 * @Description: 获取当前登录用户id
	 * @return
	 * @return Long
	 * @Author Administrator
	 */
	/*private Long getCurUserId(){
		Subject currentUser = SecurityUtils.getSubject();
		return (Long) currentUser.getSession().getAttribute(ConstantStr.LOGIN_ID);
	}*/
	
	private Subject getCurrentUser() {
		Subject currentUser = SecurityUtils.getSubject();
		return currentUser;
	}
}
