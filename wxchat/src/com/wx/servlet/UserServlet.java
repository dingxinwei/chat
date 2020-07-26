package com.wx.servlet;


import java.util.List;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.wx.pojo.Friends;
import com.wx.pojo.QChat;
import com.wx.pojo.User;
import com.wx.service.UserService;
import com.wx.service.impl.UserServiceImp;
/**
 * 处理用户操作自身的请求
 * @author dxw
 *
 */
public class UserServlet extends HttpServlet {
	//日志对象
	Logger log =  Logger.getLogger(UserServlet.class);
	//创建service层对象
	UserService service = new UserServiceImp();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			//获取操作符
			String oper = req.getParameter("oper");
			if("login".equals(oper)){//校验用户登录	
				checkLogin(req,resp);
			}else if("reg".equals(oper)){//用户注册
				checkReg(req,resp);
			}else if("update".equals(oper)){//修改用户信息
				updateUserInfo(req,resp);
			}else if("out".equals(oper)){//用户退出
				out(req,resp);
			}else if("updateP".equals(oper)){//修改密码
				updatePwd(req,resp);	
			}else if("sel".equals(oper)){//修改密码
				selUserPwd(req,resp);
			}
			else{
				log.debug("操作符不正确(UserServlet)");
			}
			
	}
	
	//查询用户密码
	private void selUserPwd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String account = req.getParameter("account");
		String pwd = service.selUserPwdService(account);
		resp.getWriter().write(pwd);
		return;
	}
	//修改用户密码
	private void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String account = req.getParameter("account");
		String pwd = req.getParameter("pwd");
		int index = service.updataUserPwdService(account, pwd);
		if(index > 0){
			resp.getWriter().write("1");
			return;
		}
		
	}
	
	//退出
	private void out(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession hs = req.getSession();
		String account = req.getParameter("account");
		//退出session设置失效
		hs.invalidate();
		service.updatUserOnlineFlagService(account, "no");
		//重定向到登录界面
		resp.sendRedirect("/wxchat/login.jsp");
		return;
	}
	//修改用户信息
	private void updateUserInfo(HttpServletRequest req, HttpServletResponse resp) {
		Integer account = req.getParameter("account")!=null?Integer.parseInt(req.getParameter("account")):null;
		String nickname = req.getParameter("nickname");
		Integer age = req.getParameter("age")!=null?Integer.parseInt(req.getParameter("age")):null;;
		Integer sex = req.getParameter("sex")!=null?Integer.parseInt(req.getParameter("sex")):null;
		String birth = req.getParameter("birth");
		if(nickname != null){
			service.updateUserInfoService(nickname,account,"nickname");
		}else if(age != null){
			service.updateUserInfoService(age,account,"age");
		}else if(sex != null){
			service.updateUserInfoService(sex,account,"sex");
		}else if(birth != null){
			service.updateUserInfoService(birth,account,"birth");
		}
		
	}
	//用户注册
	private void checkReg(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String account = req.getParameter("account");
		String password = req.getParameter("password");  
		String nickname = req.getParameter("nickname");
		Integer sex = Integer.parseInt(req.getParameter("sex"));
		Integer age = Integer.parseInt(req.getParameter("age"));
		String birth = req.getParameter("birth");
		
		User user = new User(account,password,nickname,age,sex,birth,"","no");
		int index = 0;
		User u  = service.selUserService(account);
		if( u == null){//账号已存在不能被注册
			index = service.regService(user);
		}
		
		if(index>0){
			resp.getWriter().write("1"); //1 注册成功
			return;
		}else{
			if(u == null){
				resp.getWriter().write("0");//0 其他注册失败情况
			}else{
				resp.getWriter().write("2");//2 账号已被注册
			}
			
			return;
		}
	}
	//校验登录
	private void checkLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		//获取请求数据
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		
		//调用service层方法
		User u = service.checkLoginService(account,password);
		
		if(null != u){//登录成功
			service.updatUserOnlineFlagService(account, "yes");//修改登录标记
			//把查到的用户对象和好友对象放到session中
			HttpSession hs = req.getSession();
			hs.setMaxInactiveInterval(3600);//设置session有效时间 1小时
			hs.setAttribute("user", u);
			resp.getWriter().write("1");
			return;
		}else{//登录失败
			resp.getWriter().write("0");
			return;
		}
	}
}
