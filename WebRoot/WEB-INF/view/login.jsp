<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include.inc.jsp"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	<!DOCTYPE html>
	<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>南京华宸仓库进销存管理系统</title>
		<link rel="stylesheet" href="${basePath}static/css/web.css">
		<link rel="stylesheet" type="text/css" href="${basePath}static/easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${basePath}static/easyui/themes/icon.css">
		<script type="text/javascript" src="${basePath}static/easyui/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}static/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${basePath}static/easyui/locale/easyui-lang-zh_CN.js"></script>
		<style type="text/css">
		.loginFormIpt1,.loginFormIpt1Onfocus{position:absolute; z-index:1; width:77px; height:38px; background:url(/images/input_bg2.png) no-repeat;}
		.loginTdIpt{ width:250px; height:24px; color:#fff; left:0; line-height:24px; background-color:transparent; font-size:16px;}
		#mainWrapper {width:325px;padding-top:150px;}
		input,img{vertical-align:middle;}
		</style>
		<script>
			$(function(){
				$('#username').focus();
				ShowMessage();
			});
			
			function ShowMessage(){
				var message;
				message="${msg}";
				if(message.length >0){
					$.messager.alert("南京华宸仓库进销存管理系统",message);
				}
			}
			
			function sub(){
				var username = $("#username").val();
			    var password = $("#password").val();
			    var captcha_key = $("#captcha_key").val();
			    
			    if(username == "") {
			        alert("请输入用户名！");
			        $("#username").focus();
			        return false;
			    }
			    if(password == "") {
			        alert("请输入密码！");
			        $("#password").focus();
			        return false;
			    }
			    if(captcha_key == "" || captcha_key == '验证码') {
			        alert("请输入验证码！");
			        $("#captcha_key").focus();
			        return false;
			    }
				$('#ff').submit();
			}
			
			jQuery(document).ready(function(){
				$("#captcha").click(function(){
					$(this).attr("src", "${basePath}Captcha.jpg?time=" + new Date());
					return false;
				});
				
				$("#captcha").click();
			});
		</script>
	</head>
	
	<body style=" background-image:url(${basePath}static/images/login_bg_default2.jpg); background-position: center top;background-repeat: no-repeat; background-color: #00416b;">
		
		<!-- <marquee direction="right" ><strong style="font-family: cursive;font-size: 100px; color: graytext; ">南京华宸仓库进销存管理系统</strong></marquee> -->
		
		<form id="ff" action="${basePath}login" method="post">
			<div id="mainWrapper" >
				<div class="easyui-panel" title="登陆系统"  style="padding:15px 25px;height:215px;width:400px;" 
					 data-options="iconCls:'icon-forward',collapsible:false,minimizable:false,maximizable:false,closable:false">
			        <table>
			            	<tr>
			            		<td>用户名：</td>
			            		<td>
			            			<input type="text" id="username" name="username" value="SH00101"  class="easyui-textbox" required="true" />
			            		</td>
			            	</tr>
			            	<tr>
			            		<td>密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
			            		<td><input type="password" id="password" name="password" value="hive1234" class="easyui-textbox" required="true" /></td>
			            	</tr>
			            	<tr>
			            		<td>验证码：</td>
			            	    <td>
			            	    <input name="captcha_key" class="easyui-textbox" id="captcha_key" type="text"  value=""  tabindex="3"/>
			                        <span><img src="${basePath}Captcha.jpg" alt="点击刷新验证码" width="75" height="30" id="captcha"/></span>
			                     </td>
			            	</tr>
							<tr>
								<td colspan="2">
									<div style="padding:5px 75px">
							            <button class="button white medium" onClick="sub();">登录</button>
							        </div>
								</td>
							</tr>
			            </table>
			    </div>	    
			</div>
		</form>
	</body>
	</html>