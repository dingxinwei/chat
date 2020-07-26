package com.wx.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.wx.pojo.Friends;
import com.wx.pojo.User;
import com.wx.service.UserService;
import com.wx.service.impl.UserServiceImp;
/**
 * �����û��Ժ��Ѳ���
 * @author dxw
 *
 */
public class FriendServlet extends HttpServlet {
	//��־����
	Logger log =  Logger.getLogger(FriendServlet.class);
	//����service�����
	UserService service = new UserServiceImp();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String oper = req.getParameter("oper");//��ȡ������
		 if("sel".equals(oper)){//�����û�
			selUser(req,resp);
		}else if("add".equals(oper)){//��Ӻ���
			addFriend(req,resp);
		}else if("updnote".equals(oper)){//�޸ı�ע
			updateNote(req,resp);
		}else if("del".equals(oper)){//ɾ������
			deleteFriend(req,resp);
		}else if("selByName".equals(oper)){//��ѯָ���ǳƵĺ���
			selFriendsByNickname(req,resp);
		}else{
			log.debug("����������ȷ(FriendServlet)");
		}
	}
	//��ѯָ���ǳƵĺ���
	private void selFriendsByNickname(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		String name = req.getParameter("name");
		String account = req.getParameter("account");
		Friends friends = service.selAllFriendsByNicknameService(name, account);
		if(friends != null && friends.getFriends() != null){
			resp.getWriter().write(new Gson().toJson(friends.getFriends()));
			return;
		}
		
	}
	//ɾ������
	private void deleteFriend(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String account = req.getParameter("account");
		String faccount = req.getParameter("faccount");
		System.out.println(account+":"+faccount);
		int index = service.deleteFriendService(account, faccount);
		if(index > 0){
			resp.getWriter().write("1");
		}else{
			resp.getWriter().write("0");
		}
		
	}
	//�޸ı�ע
	private void updateNote(HttpServletRequest req, HttpServletResponse resp) {
		String note = req.getParameter("notes");
		String faccount = req.getParameter("faccount");
		String account = req.getParameter("account");
		service.updateFriendNotesService(note,faccount,account);
		
	}
	//��Ӻ���
	private void addFriend(HttpServletRequest req, HttpServletResponse resp) {
		String account = req.getParameter("account");
		String fAccount = req.getParameter("faccount");
		service.addFriendService(account,fAccount);
	}
	//�����û�
	private void selUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String account = req.getParameter("account");
		User user = service.selUserService(account);
		resp.getWriter().write(new Gson().toJson(user));
	}
}
