package com.wx.service;

import java.util.List;

import com.wx.pojo.Friends;
import com.wx.pojo.MsgCache;
import com.wx.pojo.QChat;
import com.wx.pojo.User;
/**
 * service��ӿ�
 * @author dxw
 *
 */
public interface UserService {
	/**
	 * 
	 * @param account �˺�
	 * @param pwd ����
	 * @return �����û�����
	 */
	
	User checkLoginService(String account,String pwd);
	/**
	 * �û�ע��
	 * @param u
	 * @return
	 */
	int regService(User u);
	/**
	 * ��ѯ�û�����
	 * @param account
	 * @return
	 */
	String selUserPwdService(String account);
	/**
	 * �޸��û�����
	 * @param account
	 * @param pwd
	 * @return
	 */
	int updataUserPwdService(String account,String pwd);
	/**
	 * �޸��û���Ϣ
	 * @param value  �޸ĵ�ֵ
	 * @param account �޸ĵ��˺�
	 * @param name �޸ĵ�����
	 */
	void updateUserInfoService(Object value, Integer account, String name);
	/**
	 * �����û�
	 * @param account ��ѯ�û�
	 * @return ���ز鵽���û���Ϣ
	 */
	User selUserService(String account);
	/**
	 * 
	 * @param account
	 * @param fAccount
	 */
	void addFriendService(String account, String fAccount);
	/**
	 * ��ѯ���к���
	 * @param account
	 * @return
	 */
	
	Friends  selAllFriendsService(String account);
	/**
	 * �޸ĺ��ѱ�ע
	 * @param note ��ע
	 * @param faccount �����˺�
	 * @param account �û��˺�
	 */
	void updateFriendNotesService(String note, String faccount, String account);
	/**
	 * ɾ������
	 * @param account
	 * @param faccount
	 * @return
	 */
	int deleteFriendService(String account,String faccount);
	/**
	 * ����Ⱥ��
	 * @param qname
	 * @param qaccount
	 * @param flag
	 */
	int createQChatService(String qname,String qaccount,Integer flag);
	/**
	 * ��ѯ�û����������Ⱥ��
	 * @param account
	 * @return
	 */
	List<QChat> selAllQByAccountService(String account);
	/**
	 * ����Ⱥ��
	 * @param qName
	 * @param qAccount
	 * @param qflag
	 * @return
	 */
	int insertQChatService(String qName,String qAccount,Integer qflag);
	
	/**
	 * �޸�Ⱥ��
	 */
	
	int updateQNameService(String oldQname,String newQname);
	/**
	 * �޸�Ⱥ����
	 * @param note
	 * @param qName
	 * @return
	 */
	int updateQNoteService(String note,String qName);
	/**
	 * �޸�Ⱥ�ǳ�
	 * @param qNickname
	 * @param qName
	 * @param qAccount
	 * @return
	 */
	int updateQNicknameService(String qNickname,String qName,String qAccount);
	/**
	 * �˳�Ⱥ��
	 * @param qAccount
	 * @param qName
	 * @return
	 */
	int deleteQService(String qAccount,String qName);
	/**
	 * ��ѯָ���ǳƵĺ���
	 * @param account
	 * @return
	 */
	Friends  selAllFriendsByNicknameService(String name,String account);
	/**
	 * ��ѯȺ���е������û�
	 * @param qname
	 * @return
	 */
	List<QChat> selAllQChatByQNameService(String qname);
	
	/**
	 * ��ѯȺ����ָ���ǳƵ��û�
	 * @param qname
	 * @param nickname
	 * @return
	 */
	QChat selQChatUserInfoByNicknameService(String qname,String nickname);
	/**
	 * �޸ĵ�¼��־
	 * @param account
	 * @param flag
	 * @return
	 */
	int  updatUserOnlineFlagService(String account,String flag);
	
	/**
	 * �洢������Ϣ
	 * @param msg
	 * @return
	 */
	int insertMsgService(MsgCache msg);
	/**
	 * ɾ���û�������������Ϣ
	 * @param rAccount
	 * @return
	 */
	int deleteMsgService(String rAccount);
	/**
	 * ��ѯ�û�������������Ϣ
	 * @param rAccount
	 * @return
	 */
	List<MsgCache> selectMsgService(String rAccount);
}	

