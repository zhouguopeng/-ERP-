<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/view/include.inc.jsp"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>南京华宸仓库进销存管理系统</title>
     <link href="${basePath}static/css/default.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="${basePath}static/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${basePath}static/easyui/themes/icon.css">
	<script type="text/javascript" src="${basePath}static/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="${basePath}static/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${basePath}static/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${basePath}static/js/XiuCai.index.js"></script>
	
    <script type="text/javascript">
    var _menus = undefined;
    $(function (){    
    	initMenus();
    });
    
    function initMenus(){
    	 $.post("${basePath}main/index/searchMenus","",function(result){
          	if (result.retvalue == 0){
          		_menus = result.rows;
          		InitLeftMenu();
              } else {
              	$.messager.alert('Info',result.errorMsg,'error');
              }
          },'json');
    }
        //设置登录窗口
        function openPwd() {
            $('#w').window({
                title: '修改密码',
                width: 300,
                modal: true,
                shadow: true,
                closed: true,
                height: 160,
                resizable:false
            });
        }
        //关闭登录窗口
        function closePwd() {
            $('#w').window('close');
        }
        //修改密码
        function serverLogin() {
            var $newpass = $('#txtNewPass');
            var $rePass = $('#txtRePass');

            if ($newpass.val() == '') {
                msgShow('系统提示', '请输入密码！', 'warning');
                return false;
            }
            if ($rePass.val() == '') {
                msgShow('系统提示', '请在一次输入密码！', 'warning');
                return false;
            }

            if ($newpass.val() != $rePass.val()) {
                msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
                return false;
            }

            $.post('/ajax/editpassword.ashx?newpass=' + $newpass.val(), function(msg) {
                msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
                $newpass.val('');
                $rePass.val('');
                close();
            })
            
        }

        $(function() {
            openPwd();
            $('#editpass').click(function() {
                $('#w').window('open');
            });
            $('#btnEp').click(function() {
                serverLogin();
            })
			$('#btnCancel').click(function(){closePwd();})
            $('#loginOut').click(function() {
                $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {
                    if (r) {
                        $('#logoutForm').submit();
                    }
                });
            })
        });
		
		

    </script>
  </head>
  
  <body class="easyui-layout" style="overflow-y: hidden"  fit="true" scroll="no">
<noscript>
	<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
	    <img src="${basePath}static/images/noscript.gif" alt='抱歉，请开启脚本支持！' />
	</div></noscript>
	
	<div id="loading-mask" style="position:absolute;top:0px; left:0px; width:100%; height:100%; background:#D2E0F2; z-index:20000">
	<div id="pageloading" style="position:absolute; top:50%; left:50%; margin:-120px 0px 0px -120px; text-align:center;  border:2px solid #8DB2E3; width:200px; height:40px;  font-size:14px;padding:10px; font-weight:bold; background:#fff; color:#15428B;"> 
	    <img src="${basePath}static/images/loading.gif" align="absmiddle" /> 正在加载中,请稍候...</div>
	</div>

    <div region="north"  border="false" style="overflow: hidden; height: 50px;
        background: url(${basePath}static/images/LOGO.jpg) #7f99be repeat-x center 50%;
         font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; height:50px; padding-right:30px;" >欢迎 ${userName} <a href="#" id="editpass" class="easyui-linkbutton" iconCls="icon-edit" ><label style="color: black;">修改密码</label></a> <a href="#" class="easyui-linkbutton" iconCls="icon-clear" id="loginOut" ><label style="color: black;">安全退出</label></a></span>
        <span style="padding-left:500px; font-size: 30px; "> 南京华宸仓库进销存管理系统</span>
    </div>
<!--     #E0ECFF D2E0F2-->
    <div region="south"  style="height:24px; background: #D2E0F2;OVERFLOW-Y:hidden;" >
        <div class="footer">Copyright 南京华宸仓库进销存管理系统  http://www.futongware.com Email:sales@futongware.com Tel:010-80549070</div>
    </div>
    <div region="west" split="true"  title="进销存系统导航菜单" style="width:180px;" id="west">
		<div id="nav"></div>
    </div>
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			<div title="欢迎使用" style="padding:20px;overflow:hidden; color:red;" >
				<form id="logoutForm" action="logoutemplogin.action" method="post"></form>
				<%-- <shiro:hasAnyRoles name="HrUserInfo,HrUserJC,HrUserKH,HrUserPX,HrUserMT,HrUserHT,HrUser,HrUserZG,HrUserDL,HrUserQT,HrUserGZ,AppNotice"> --%>
				欢迎使用本平台
				<%-- </shiro:hasAnyRoles> --%>
			</div>
		</div>
    </div>
    
    
    <!--修改密码窗口-->
    <div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 150px; padding: 5px; background: #fafafa;">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
                <table cellpadding=3>
                    <tr>
                        <td>新密码：</td>
                        <td><input id="txtNewPass" type="password" class="txt01" /></td>
                    </tr>
                    <tr>
                        <td>确认密码：</td>
                        <td><input id="txtRePass" type="password" class="txt01" /></td>
                    </tr>
                </table>
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >确定</a> 
                <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
            </div>
        </div>
    </div>

	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="close">关闭</div>
		<div id="closeall">全部关闭</div>
		<div id="closeother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="closeright">当前页右侧全部关闭</div>
		<div id="closeleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="exit">退出</div>
	</div>


</body>
</html>
