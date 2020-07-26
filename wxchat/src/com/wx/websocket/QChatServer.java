package com.wx.websocket;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.wx.dao.AccessDao;
import com.wx.pojo.QChat;
/**
 * Ⱥ��
 * @author Administrator
 *
 */
@ServerEndpoint("/qchat")
public class QChatServer {
	private static AccessDao dao = new AccessDao();
	//�洢��ǰ�Ự����
	private Session session;
	//�洢��ǰ�ͻ��˵��˺�
	private String account;
	//�洢���пͻ���
	public static Vector<QChatServer> allChannl = new Vector<QChatServer>();
	@OnOpen
	public void onOpen(Session session){
		this.session = session;
		String queryParam = session.getQueryString();
		String value[] = queryParam.split("=");
		this.account = value[1];
		allChannl.add(this);
		System.out.println("һ�������ӵ������");
	}
	@OnMessage
	public void onMessage(String message,Session session){
		int index = message.indexOf(";");
		String qName = message.substring(1, index);
		message = message.substring(1,message.length());
		List<QChat> qList = dao.selAllQAccountByQName(qName);
		for(QChatServer client:allChannl){
			if(this != client){
				for(QChat qchat:qList){
					if(qchat.getqAccount().equals(client.account)){
						try {
							client.session.getBasicRemote().sendText(message);
						} catch (IOException e) {
							e.printStackTrace();
						}	
					}
				}
			}
		}
	} 
	@OnClose
	public void onClose(){
		allChannl.remove(this);
		System.out.println("һ���ͻ��˶Ͽ����ӣ�");
	}
}
