package com.wx.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.wx.pojo.QChat;
import com.wx.service.UserService;
import com.wx.service.impl.UserServiceImp;
/**
 * 处理对群聊的操作
 * @author dxw
 *
 */
public class QChatServlet extends HttpServlet {
	//日志对象
	Logger log =  Logger.getLogger(UserServlet.class);
	//创建service层对象
	UserService service = new UserServiceImp();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取操作符
		String oper = req.getParameter("oper");
		 	if("createq".equals(oper)){//创建群聊
				createQChat(req,resp);
			}else if("join".equals(oper)){//加入群聊
				insertQChat(req,resp);
			}else if("updateQName".equals(oper)){//修改群名
				updateQName(req,resp);
			}else if("updateQNote".equals(oper)){//修改群公告
				updateQNote(req,resp);
			}else if("updateNickname".equals(oper)){//修改群昵称	
				updateNickname(req,resp);
			}else if("outQ".equals(oper)){//退出群聊
				deleteQ(req,resp);
			}else if("selAllQ".equals(oper)){//查询群聊中的所有用户
				selAllUserInQChatByQName(req,resp);
			}else if("searchQmem".equals(oper)){//查询群聊中的指定昵称的用户
				selQChatUserInfoByNickname(req,resp);
			}else{
				log.debug("操作符不正确(QChatServlet)");
			}
	}
	//查询群聊中的指定昵称的用户
	private void selQChatUserInfoByNickname(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		String qname = req.getParameter("qname");
		String nickname = req.getParameter("nickname");
		QChat qchat = service.selQChatUserInfoByNicknameService(qname, nickname);
		resp.getWriter().write(new Gson().toJson(qchat));
		return;
	}
	//查询群聊中的所有用户
	private void selAllUserInQChatByQName(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		String qname = req.getParameter("qname");
		List<QChat> list = service.selAllQChatByQNameService(qname);
		resp.getWriter().write(new Gson().toJson(list));
		return;
	}
	//退出群聊 
	private void deleteQ(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String qName = req.getParameter("qName");
		String qAccount = req.getParameter("account");
		System.out.println(qName+":"+qAccount);
		int index = service.deleteQService(qAccount, qName);
		if(index > 0){
			resp.getWriter().write("1");
			return;
		}
	}
	//修改群昵称
	private void updateNickname(HttpServletRequest req, HttpServletResponse resp) {
		String qNickname = req.getParameter("nickname");
		String qName = req.getParameter("qName");
		String qAccount = req.getParameter("account");
		System.out.println(qNickname+":"+qName+":"+qAccount);
		service.updateQNicknameService(qNickname, qName, qAccount);
		
	}
	//修改群公告
	private void updateQNote(HttpServletRequest req, HttpServletResponse resp) {
		String note = req.getParameter("note");	
		String qName = req.getParameter("qName");
		System.out.println(note+":"+qName);
		service.updateQNoteService(note, qName);
	}
	//修改群名
	private void updateQName(HttpServletRequest req, HttpServletResponse resp) {
		String oldName = req.getParameter("oldName");
		String newName = req.getParameter("newName");
		
		service.updateQNameService(oldName, newName);
	}

	//加入群聊
	private void insertQChat(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String qName = req.getParameter("qname");
		String qAccount = req.getParameter("qaccount");
		
		int index = service.insertQChatService(qName, qAccount, 1);
		if(index > 0){
			resp.getWriter().write("1");
			return;
		}
		
	}
	//创建群聊
	private void createQChat(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String qname = req.getParameter("qname");
		String qaccount = req.getParameter("qaccount");
		System.out.println(qname+":"+qaccount);
		int index = service.createQChatService(qname, qaccount, 0);
		if(index > 0){
			resp.getWriter().write("1");
			return;
		}
		
	}

}
