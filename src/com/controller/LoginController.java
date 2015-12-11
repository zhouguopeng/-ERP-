package com.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soft863.dolphin.shiro.IncorrectCaptchaException;
import com.soft863.dolphin.util.AjaxObject;

/**
 * HomeController主页面
 * 显示系统主页
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	
	private static final String LOGIN_PAGE = "login";
	private static final String LOGIN_DIALOG = "main/loginDialog";
	private static final String REG = "main/register";
	private static final String FPW = "main/fpw";
	

	@RequestMapping(method = RequestMethod.GET)
	public String login(Map<String,Object> map, HttpServletRequest request) throws UnsupportedEncodingException {
		Cookie[] cookies = request.getCookies(); 
		for (int i = 0; cookies != null && i < cookies.length; i++) { 
			if("ramsUserName".equals(cookies[i].getName())){
				map.put("usernameRmas", URLDecoder.decode(cookies[i].getValue(),"UTF-8"));
			}
		} 
		return LOGIN_PAGE;
	}
	
	@RequestMapping(method = {RequestMethod.GET}, params = "ajax=true")
	public @ResponseBody
	String loginDialog2AJAX() {
		return loginDialog();
	}

	@RequestMapping(method = {RequestMethod.GET}, headers = "X-Requested-With=XMLHttpRequest")
	public @ResponseBody
	String loginDialog() {
		return AjaxObject.newTimeout("会话超时，请重新登录。").toString();
	}

	@RequestMapping(value = "/timeout", method = {RequestMethod.GET})
	public String timeout() {
		return LOGIN_DIALOG;
	}

	@RequestMapping(value = "/timeout/success", method = {RequestMethod.GET})
	public @ResponseBody
	String timeoutSuccess() throws Exception {
		return AjaxObject.newOk("登录成功。").toString();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String username,
			Map<String, Object> map, HttpServletRequest request) {

		String msg = parseException(request);
		
		map.put("msg", msg);
		map.put("usernameRmas", username);
		return LOGIN_PAGE;
	}

	@RequestMapping(method = {RequestMethod.POST}, headers = "x-requested-with=XMLHttpRequest")
	public @ResponseBody
	String failDialog(HttpServletRequest request) {
		String msg = parseException(request);

		AjaxObject ajaxObject = new AjaxObject(msg);
		ajaxObject.setStatusCode(AjaxObject.STATUS_CODE_FAILURE);
		ajaxObject.setCallbackType("");

		return ajaxObject.toString();
	}
	
	@RequestMapping(value="/toReg", method=RequestMethod.GET)
	public String toReg() {
		return REG;
	}
	
	@RequestMapping(value="/toFPW", method=RequestMethod.GET)
	public String toFPW() {
		return FPW;
	}

	private String parseException(HttpServletRequest request) {
		String errorString = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		Class<?> error = null;
		try {
			if (errorString != null) {
				error = Class.forName(errorString);
			}
		} catch (ClassNotFoundException e) {
			//LOG.error(Exceptions.getStackTraceAsString(e));
		} 
		
		String msg = "其他错误！";
		if (error != null) {
			if (error.equals(UnknownAccountException.class))
				msg = "未知帐号错误！";
			else if (error.equals(IncorrectCredentialsException.class))
				msg = "密码错误！";
			else if (error.equals(IncorrectCaptchaException.class))
				msg = "验证码错误！";
			else if (error.equals(AuthenticationException.class))
				msg = "用户名错误！";//认证失败！
			else if (error.equals(DisabledAccountException.class))
				msg = "因非法信息被系统禁用，请联系管理员或者重新注册新账号使用";
		}
		return "登录失败，" + msg;
	}

}
