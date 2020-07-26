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
 * �����û��������������
 * @author dxw
 *
 */
public class UserServlet extends HttpServlet {
	//��־����
	Logger log =  Logger.getLogger(UserServlet.class);
	//����service�����
	UserService service = new UserServiceImp();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			//��ȡ������
			String oper = req.getParameter("oper");
			if("login".equals(oper)){//У���û���¼	
				checkLogin(req,resp);
			}else if("reg".equals(oper)){//�û�ע��
				checkReg(req,resp);
			}else if("update".equals(oper)){//�޸��û���Ϣ
				updateUserInfo(req,resp);
			}else if("out".equals(oper)){//�û��˳�
				out(req,resp);
			}else if("updateP".equals(oper)){//�޸�����
				updatePwd(req,resp);	
			}else if("sel".equals(oper)){//�޸�����
				selUserPwd(req,resp);
			}
			else{
				log.debug("����������ȷ(UserServlet)");
			}
			
	}
	
	//��ѯ�û�����
	private void selUserPwd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String account = req.getParameter("account");
		String pwd = service.selUserPwdService(account);
		resp.getWriter().write(pwd);
		return;
	}
	//�޸��û�����
	private void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String account = req.getParameter("account");
		String pwd = req.getParameter("pwd");
		int index = service.updataUserPwdService(account, pwd);
		if(index > 0){
			resp.getWriter().write("1");
			return;
		}
		
	}
	
	//�˳�
	private void out(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession hs = req.getSession();
		String account = req.getParameter("account");
		//�˳�session����ʧЧ
		hs.invalidate();
		service.updatUserOnlineFlagService(account, "no");
		//�ض��򵽵�¼����
		resp.sendRedirect("/wxchat/login.jsp");
		return;
	}
	//�޸��û���Ϣ
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
	//�û�ע��
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
		if( u == null){//�˺��Ѵ��ڲ��ܱ�ע��
			index = service.regService(user);
		}
		
		if(index>0){
			resp.getWriter().write("1"); //1 ע��ɹ�
			return;
		}else{
			if(u == null){
				resp.getWriter().write("0");//0 ����ע��ʧ�����
			}else{
				resp.getWriter().write("2");//2 �˺��ѱ�ע��
			}
			
			return;
		}
	}
	//У���¼
	private void checkLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		//��ȡ��������
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		
		//����service�㷽��
		User u = service.checkLoginService(account,password);
		
		if(null != u){//��¼�ɹ�
			service.updatUserOnlineFlagService(account, "yes");//�޸ĵ�¼���
			//�Ѳ鵽���û�����ͺ��Ѷ���ŵ�session��
			HttpSession hs = req.getSession();
			hs.setMaxInactiveInterval(3600);//����session��Чʱ�� 1Сʱ
			hs.setAttribute("user", u);
			resp.getWriter().write("1");
			return;
		}else{//��¼ʧ��
			resp.getWriter().write("0");
			return;
		}
	}
}
