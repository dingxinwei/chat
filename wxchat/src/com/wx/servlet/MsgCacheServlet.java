package com.wx.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.wx.pojo.MsgCache;
import com.wx.service.UserService;
import com.wx.service.impl.UserServiceImp;

public class MsgCacheServlet extends HttpServlet {
	//日志对象
	Logger log =  Logger.getLogger(FriendServlet.class);
	//创建service层对象
	UserService service = new UserServiceImp();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String oper = req.getParameter("oper");
		if("insert".equals(oper)){
			insertMsg(req,resp);
		}else if("delete".equals(oper)){
			deleteMsg(req,resp);
		}else if("select".equals(oper)){
			selectMsg(req,resp);
		}else{
			log.debug("操作符不正确(MsgCacheServlet)");
		}
	}
	//查询离线消息
	private void selectMsg(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String rAccount = req.getParameter("rAccount");
		System.out.println("rAccount"+rAccount);
		List<MsgCache> list = service.selectMsgService(rAccount);
		resp.getWriter().write(new Gson().toJson(list));
		
	}
	//删除离线消息
	private void deleteMsg(HttpServletRequest req, HttpServletResponse resp) {
		String rAccount = req.getParameter("rAccount");
		service.deleteMsgService(rAccount);
		
	}
	//存储离线消息
	private void insertMsg(HttpServletRequest req, HttpServletResponse resp) {
		String sAccount = req.getParameter("sAccount");
		String rAccount = req.getParameter("rAccount");
		String message = req.getParameter("msg");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sAccount+":"+rAccount+":"+message);
		Date sendTime=null;
		try {
			if(req.getParameter("time") != null){
				sendTime = sdf.parse(req.getParameter("time"));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		MsgCache msg = new MsgCache(sAccount,rAccount,message,sendTime);
		service.insertMsgService(msg);
	}
}
