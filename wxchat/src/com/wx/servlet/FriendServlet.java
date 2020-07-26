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
 * 处理用户对好友操作
 * @author dxw
 *
 */
public class FriendServlet extends HttpServlet {
	//日志对象
	Logger log =  Logger.getLogger(FriendServlet.class);
	//创建service层对象
	UserService service = new UserServiceImp();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String oper = req.getParameter("oper");//获取操作符
		 if("sel".equals(oper)){//查找用户
			selUser(req,resp);
		}else if("add".equals(oper)){//添加好友
			addFriend(req,resp);
		}else if("updnote".equals(oper)){//修改备注
			updateNote(req,resp);
		}else if("del".equals(oper)){//删除好友
			deleteFriend(req,resp);
		}else if("selByName".equals(oper)){//查询指定昵称的好友
			selFriendsByNickname(req,resp);
		}else{
			log.debug("操作符不正确(FriendServlet)");
		}
	}
	//查询指定昵称的好友
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
	//删除好友
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
	//修改备注
	private void updateNote(HttpServletRequest req, HttpServletResponse resp) {
		String note = req.getParameter("notes");
		String faccount = req.getParameter("faccount");
		String account = req.getParameter("account");
		service.updateFriendNotesService(note,faccount,account);
		
	}
	//添加好友
	private void addFriend(HttpServletRequest req, HttpServletResponse resp) {
		String account = req.getParameter("account");
		String fAccount = req.getParameter("faccount");
		service.addFriendService(account,fAccount);
	}
	//查找用户
	private void selUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String account = req.getParameter("account");
		User user = service.selUserService(account);
		resp.getWriter().write(new Gson().toJson(user));
	}
}
