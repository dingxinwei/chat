package com.wx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

























import com.wx.dao.AccessDao;
import com.wx.pojo.Friends;
import com.wx.pojo.MsgCache;
import com.wx.pojo.QChat;
import com.wx.pojo.User;
import com.wx.service.UserService;
import com.wx.servlet.UserServlet;

public class UserServiceImp implements UserService {
	//dao�����
	AccessDao mapper = new AccessDao();
	//��־����
	Logger log =  Logger.getLogger(UserServlet.class);
	/**
	 * �û���¼
	 */
	@Override
	public User checkLoginService(String account,String pwd) {
		User user = mapper.checkLoginDao(account,pwd);
		if(null!=user){//��¼�ɹ���ӡ��־
			log.debug(account+"��¼�ɹ�");
		}
		return user;
	}
	/**
	 * �û�ע��
	 */
	@Override
	public int regService(User u) {
		int index = mapper.regDao(u);
		if(index>0){//ע��ɹ���ӡ��־
			log.debug(u.getAccount()+"ע��ɹ�");
		}
		return index;
	}
	/**
	 * �޸��û���Ϣ
	 */
	@Override
	public void updateUserInfoService(Object value, Integer account, String name) {
		
		int index = mapper.updateUserInfoDao(value,account,name);
		if(index > 0){
			log.debug(account+"�޸���"+name);
		}
		
	}
	/**
	 * �����û�
	 */
	@Override
	public User selUserService(String account) {
		User u = mapper.selUserDao(account);
		return u;
	}
	/**
	 * ��Ӻ���
	 */
	@Override
	public void addFriendService(String account, String fAccount) {
		int index = mapper.addFriendDao(account,fAccount);
		
	}
	/**
	 * �������к���
	 */
	@Override
	public Friends selAllFriendsService(String account) {
		Friends friends = mapper.selAllFriendsDao(account);
		return friends;
	}
	/**
	 * �޸ĺ��ѱ�ע
	 */
	@Override
	public void updateFriendNotesService(String note, String faccount, String account) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("note", note);
		map.put("faccount", faccount);
		map.put("account", account);
		mapper.updateFriendNoteDao(map);
		
	}
	/**
	 * ɾ������
	 */
	@Override
	public int deleteFriendService(String account, String faccount) {
		int index = mapper.deleteFriendDao(account, faccount);
		if(index >0){
			log.debug(account + "ɾ������" + faccount + "�ɹ�");
		}else{
			log.debug(account + "ɾ������" + faccount + "ʧ��");
		}
		return index;
	}
	@Override
	public int createQChatService(String qname, String qaccount, Integer qflag) {
		int index = mapper.createQChatDao(qname, qaccount, qflag);
		if(index > 0){
			log.debug(qaccount + "����Ⱥ��");
		}
		return index;
	}
	/**
	 * ��ѯ�û����������Ⱥ��
	 */
	@Override
	public List<QChat> selAllQByAccountService(String account) {
		List<QChat> qList = mapper.selAllQByAccountDao(account);
		return qList;
	}
	/**
	 * ����Ⱥ��
	 */
	@Override
	public int insertQChatService(String qName, String qAccount, Integer qflag) {
		int index = mapper.insertQChatDao(qName, qAccount, qflag);
		return index;
	}
	/**
	 * �޸�Ⱥ��
	 */
	@Override
	public int updateQNameService(String oldQname, String newQname) {
		int index = mapper.updataQNameDao(oldQname, newQname);
		return index;
	}
	/**
	 * �޸�Ⱥ����
	 */
	@Override
	public int updateQNoteService(String note, String qName) {
		int index = mapper.updateQNoteDao(note, qName);
		return index;
	}
	/**
	 * �޸�Ⱥ�ǳ�
	 */
	@Override
	public int updateQNicknameService(String qNickname, String qName,
			String qAccount) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("nickname", qNickname);
		map.put("qname", qName);
		map.put("account", qAccount);
		return mapper.updateQNicknameDao(map);
		
	}
	/**
	 * �˳�Ⱥ��
	 */
	@Override
	public int deleteQService(String qAccount, String qName) {
		
		return mapper.deleteQDao(qAccount, qName);
	}
	/**
	 * ��ѯָ���ǳƺ���
	 */
	@Override
	public Friends selAllFriendsByNicknameService(String name, String account) {
		if(name.contains("%")){//���������ַ�
			name = name.replaceAll("%", "");
		}else if(name.contains("_")){
			name = name.replaceAll("_", "");
		}
		name = "%" + name + "%";
		return mapper.selAllFriendsByNicknameDao(name, account);
	}
	
	
	/**
	 * �޸�����
	 */
	@Override
	public int updataUserPwdService(String account, String pwd) {
		int index = mapper.updataUserPwdDao(account, pwd);
		if(index > 0){
			log.debug(account + "�޸�������");
		}
		return index;
	}
	/**
	 * ��ѯ�û�����
	 */
	@Override
	public String selUserPwdService(String account) {
		
		return mapper.selUserPwdDao(account);
	}
	/**
	 * ��ѯȺ���е������û�
	 */
	@Override
	public List<QChat> selAllQChatByQNameService(String qname) {
		
		return mapper.selAllQChatByQNameDao(qname);
	}
	/**
	 * ��ѯȺ����ָ���ǳƵ��û�
	 */
	@Override
	public QChat selQChatUserInfoByNicknameService(String qname, String nickname) {
		if(nickname.contains("%")){//���������ַ�
			nickname = nickname.replaceAll("%", "");
		}else if(nickname.contains("_")){
			nickname = nickname.replaceAll("_", "");
		}
		nickname = "%" + nickname + "%";
		return mapper.selQChatUserInfoByNicknameDao(qname, nickname);
	}
	/**
	 * �޸ĵ�¼��־
	 */
	@Override
	public int updatUserOnlineFlagService(String account, String flag) {
		
		return mapper.updatUserOnlineFlagDao(account, flag);
	}
	/**
	 * �洢������Ϣ
	 */
	@Override
	public int insertMsgService(MsgCache msg) {
		
		return mapper.insertMsgDao(msg);
	}
	/**
	 * ɾ��������Ϣ
	 */
	@Override
	public int deleteMsgService(String rAccount) {
		return mapper.deleteMsgDao(rAccount);
	}
	/**
	 * ��ѯ������Ϣ
	 */
	@Override
	public List<MsgCache> selectMsgService(String rAccount) {
		
		return mapper.selectMsgDao(rAccount);
	}

}
