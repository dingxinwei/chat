package com.wx.websocket;
import java.io.IOException;
import java.util.Vector;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.wx.pojo.Friends;
import com.wx.service.UserService;
import com.wx.service.impl.UserServiceImp;
/**
 * ����
 * @author dxw
 *
 */
@ServerEndpoint("/chat")
public class ChatServer {
	private  UserService service = new UserServiceImp();
	//�洢��ǰ�Ự����
	private Session session;
	//�洢��ǰ�ͻ��˵��˺�
	private String account;
	//�洢���пͻ���
	public static Vector<ChatServer> allChannl = new Vector<ChatServer>();
	@OnOpen
	public void onOpen(Session session){
		this.session = session;
		String queryParam = session.getQueryString();
		String value[] = queryParam.split("=");
		this.account = value[1];
		allChannl.add(this);
		System.out.println("һ�������ӵ������"+value[1]);
		//�������������û�Ϊ����
		service.updatUserOnlineFlagService(this.account, "yes");
		
	}
	@OnMessage
	public void onMessage(String message,Session session){
		int index = message.indexOf(":");
		
		String faccount = message.substring(1, index);
		String msg = message.substring(index+1, message.length());
		String lmsg = this.account + ":" + msg;
		System.out.println(msg);
		if(message.startsWith("@%")){//�����ļ�����Ϣ 
			faccount =  message.substring(2, index);
			lmsg = "%" + this.account + ":" + msg;
		}
		
		if(message.startsWith("@")){//������Ϣ
			for(ChatServer client:allChannl){
				if(client.account.equals(faccount)){
					try {
						client.session.getBasicRemote().sendText(lmsg);	
					} catch (IOException e) {
						System.out.println("������Ϣת��ʧ��");
					}		
				}
			}
		}else{//������Ϣ
			for(ChatServer client:allChannl){
				if(client.account.equals(faccount)){
					try {
						client.session.getBasicRemote().sendText(msg);	
					} catch (IOException e) {
						System.out.println("����ת��ʧ�ܣ�");
					}		
				}
			}
		}
		
		
	} 
	@OnClose
	public void onClose(){
		
		allChannl.remove(this);
		System.out.println("һ���ͻ��˶Ͽ����ӣ�");
		//�Ͽ����������û�Ϊ������
		service.updatUserOnlineFlagService(this.account, "no");
	}
}
