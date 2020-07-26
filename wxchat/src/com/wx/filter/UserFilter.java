package com.wx.filter;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wx.pojo.User;
import com.wx.service.UserService;
import com.wx.service.impl.UserServiceImp;
/**
 *���ù����� ͳһ���ñ����ʽ �����ļ�
 */
public class UserFilter implements Filter{

	@Override
	public void destroy() {
		UserService service = new UserServiceImp();
		service.updatUserOnlineFlagService("", "no");//�������ر����������û�������
		//��������ļ�
		File file = new File("E:\\apache-tomcat-7.0.56-windows-x64\\apache-tomcat-7.0.56\\webapps\\wxchat\\chatFile");
		File[] files = file.listFiles();
		 for(File f:files){
			 f.delete();
		 }
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	
		
	}

}
