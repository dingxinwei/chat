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
 * �����Ⱥ�ĵĲ���
 * @author dxw
 *
 */
public class QChatServlet extends HttpServlet {
	//��־����
	Logger log =  Logger.getLogger(UserServlet.class);
	//����service�����
	UserService service = new UserServiceImp();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//��ȡ������
		String oper = req.getParameter("oper");
		 	if("createq".equals(oper)){//����Ⱥ��
				createQChat(req,resp);
			}else if("join".equals(oper)){//����Ⱥ��
				insertQChat(req,resp);
			}else if("updateQName".equals(oper)){//�޸�Ⱥ��
				updateQName(req,resp);
			}else if("updateQNote".equals(oper)){//�޸�Ⱥ����
				updateQNote(req,resp);
			}else if("updateNickname".equals(oper)){//�޸�Ⱥ�ǳ�	
				updateNickname(req,resp);
			}else if("outQ".equals(oper)){//�˳�Ⱥ��
				deleteQ(req,resp);
			}else if("selAllQ".equals(oper)){//��ѯȺ���е������û�
				selAllUserInQChatByQName(req,resp);
			}else if("searchQmem".equals(oper)){//��ѯȺ���е�ָ���ǳƵ��û�
				selQChatUserInfoByNickname(req,resp);
			}else{
				log.debug("����������ȷ(QChatServlet)");
			}
	}
	//��ѯȺ���е�ָ���ǳƵ��û�
	private void selQChatUserInfoByNickname(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		String qname = req.getParameter("qname");
		String nickname = req.getParameter("nickname");
		QChat qchat = service.selQChatUserInfoByNicknameService(qname, nickname);
		resp.getWriter().write(new Gson().toJson(qchat));
		return;
	}
	//��ѯȺ���е������û�
	private void selAllUserInQChatByQName(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		String qname = req.getParameter("qname");
		List<QChat> list = service.selAllQChatByQNameService(qname);
		resp.getWriter().write(new Gson().toJson(list));
		return;
	}
	//�˳�Ⱥ�� 
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
	//�޸�Ⱥ�ǳ�
	private void updateNickname(HttpServletRequest req, HttpServletResponse resp) {
		String qNickname = req.getParameter("nickname");
		String qName = req.getParameter("qName");
		String qAccount = req.getParameter("account");
		System.out.println(qNickname+":"+qName+":"+qAccount);
		service.updateQNicknameService(qNickname, qName, qAccount);
		
	}
	//�޸�Ⱥ����
	private void updateQNote(HttpServletRequest req, HttpServletResponse resp) {
		String note = req.getParameter("note");	
		String qName = req.getParameter("qName");
		System.out.println(note+":"+qName);
		service.updateQNoteService(note, qName);
	}
	//�޸�Ⱥ��
	private void updateQName(HttpServletRequest req, HttpServletResponse resp) {
		String oldName = req.getParameter("oldName");
		String newName = req.getParameter("newName");
		
		service.updateQNameService(oldName, newName);
	}

	//����Ⱥ��
	private void insertQChat(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String qName = req.getParameter("qname");
		String qAccount = req.getParameter("qaccount");
		
		int index = service.insertQChatService(qName, qAccount, 1);
		if(index > 0){
			resp.getWriter().write("1");
			return;
		}
		
	}
	//����Ⱥ��
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
