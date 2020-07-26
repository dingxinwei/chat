package com.wx.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.wx.pojo.Friends;
import com.wx.pojo.MsgCache;
import com.wx.pojo.QChat;
import com.wx.pojo.User;
/**
 * dao���ṩ������ʵ���
 * @author Administrator
 *
 */
public class AccessDao {
	/**
	 * 
	 * @param account �˺�
	 * @param pwd ����
	 * @return �û�����
	 */
	public User checkLoginDao(String account,String pwd){
		//��������
		SqlSession session = DbTools.createSession();
		//��ȡ�ӿ�ʵ��
		UserDao dao = session.getMapper(UserDao.class);
		User user = dao.checkLoginDao(account,pwd);
		DbTools.closeSession(session);
		return user;
	}
	/**
	 * 
	 * @param u ��װ�û���Ϣ�Ķ���
	 * @return ����Ӱ�������
	 */
	public int regDao(User u){
		//��������
		SqlSession session = DbTools.createSession();
		//��ȡ�ӿ�ʵ��
		UserDao dao = session.getMapper(UserDao.class);
		int index = dao.regDao(u);
		DbTools.closeSession(session);
		return index;
	}
	/**
	 * �޸�����
	 * @param account
	 * @return
	 */
	public String selUserPwdDao(String account){
		//��������
		SqlSession session = DbTools.createSession();
		//��ȡ�ӿ�ʵ��
		UserDao dao = session.getMapper(UserDao.class);
		String pwd = dao.selUserPwdDao(account);
		DbTools.closeSession(session);
		return pwd;
	}
	/**
	 * ��ѯ�û�����
	 * @param account
	 * @param pwd
	 * @return
	 */
	public int updataUserPwdDao(String account,String pwd){
		//��������
		SqlSession session = DbTools.createSession();
		//��ȡ�ӿ�ʵ��
		UserDao dao = session.getMapper(UserDao.class);
		int index = dao.updataUserPwdDao(account, pwd);
		DbTools.closeSession(session);
		return index;
	}
	/**
	 * �޸��û���Ϣ
	 * @param value
	 * @param account
	 * @param name
	 * @return
	 */
	public int updateUserInfoDao(Object value, Integer account, String name) {
		//��������
		SqlSession session = DbTools.createSession();
		//��ȡ�ӿ�ʵ��
		UserDao dao = session.getMapper(UserDao.class);
		int index = dao.updateUserInfoDao(value, account, name);
		DbTools.closeSession(session);
		return index;
	}
	/**
	 * �����û�
	 * @param account
	 * @return
	 */
	public User selUserDao(String account) {
		//��������
		SqlSession session = DbTools.createSession();
		//��ȡ�ӿ�ʵ��
		FriendDao dao = session.getMapper(FriendDao.class);
		User u  = dao.selUserDao(account);
		DbTools.closeSession(session);
		return u;
	}
	/**
	 * ��Ӻ���
	 * @param account �Լ����˺�
	 * @param fAccount ���ѵ��˺�
	 * @return Ӱ������
	 */
	public int addFriendDao(String account, String fAccount) {
		//��������
		SqlSession session = DbTools.createSession();
		//��ȡ�ӿ�ʵ��
		FriendDao dao = session.getMapper(FriendDao.class);
		int index = dao.addFriendDao(account,fAccount);
		DbTools.closeSession(session);
		return index;
	}
	/**
	 * ��ѯ�û������к���
	 * @param account �û��˺�
	 * @return ����һ����װ������Ϣ�Ķ���
	 */
	public Friends selAllFriendsDao(String account){
		//��������
		SqlSession session = DbTools.createSession();
		//��ȡ�ӿ�ʵ��
		FriendDao dao = session.getMapper(FriendDao.class);
		Friends friends = dao.selAllFriendsDao(account);
		DbTools.closeSession(session);
		return friends;
	}
	/**
	 * �޸ĺ��ѱ�ע
	 * @param map
	 */
	public void updateFriendNoteDao(Map<String, String> map) {
		
		//��������
		SqlSession session = DbTools.createSession();
		//��ȡ�ӿ�ʵ��
		FriendDao dao = session.getMapper(FriendDao.class);
		dao.updateFriendNoteDao(map);
		DbTools.closeSession(session);
	}
	/**
	 * ɾ������
	 * @param account
	 * @param faccount
	 * @return
	 */
	public int deleteFriendDao(String account,String faccount){
		//��������
		SqlSession session = DbTools.createSession();
		//��ȡ�ӿ�ʵ��
		FriendDao dao = session.getMapper(FriendDao.class);
		int index = dao.deleteFriendDao(account, faccount);
		DbTools.closeSession(session);
		return index;
	}
	/**
	 * ����Ⱥ��
	 * @param qname
	 * @param qaccount
	 * @param qflag
	 * @return
	 */
	public int createQChatDao(String qname,String qaccount,Integer qflag){
		//��������
		SqlSession session = DbTools.createSession();
		//��ȡ�ӿ�ʵ��
		QChatDao dao = session.getMapper(QChatDao.class);
		int index = dao.createQChatDao(qname, qaccount, qflag);
		DbTools.closeSession(session);
		return index;
		
	}
	/**
	 * ��ѯȺ��
	 * @param account
	 * @return
	 */
	public List<QChat> selAllQByAccountDao(String account){
		//��������
		SqlSession session = DbTools.createSession();
		//��ȡ�ӿ�ʵ��
		QChatDao dao = session.getMapper(QChatDao.class);
		List<QChat> qList = dao.selAllQByAccountDao(account);
		DbTools.closeSession(session);
		return qList;
	}
	/**
	 * ����Ⱥ��
	 * @return
	 */
	public int insertQChatDao(String qName,String qAccount,Integer qflag){
		//��������
		SqlSession session = DbTools.createSession();
		//��ȡ�ӿ�ʵ��
		QChatDao dao = session.getMapper(QChatDao.class);
		int index = dao.insertQChatDao(qName, qAccount, qflag);
		DbTools.closeSession(session);
		return index;
	}
	/**
	 * ��ѯ���е�Ⱥ��Ա
	 * @param qName
	 * @return
	 */
	public List<QChat> selAllQAccountByQName(String qName){
		//��������
		SqlSession session = DbTools.createSession();
		//��ȡ�ӿ�ʵ��
		FriendDao dao = session.getMapper(FriendDao.class);
		List<QChat> qList = dao.selAllQAccountByQName(qName);
		DbTools.closeSession(session);
		return qList;
	}
	/**
	 * �޸�Ⱥ��
	 * @param oldQname
	 * @param newQname
	 * @return
	 */
	public int updataQNameDao(String oldQname,String newQname){
		//��������
		SqlSession session = DbTools.createSession();
		//��ȡ�ӿ�ʵ��
		QChatDao dao = session.getMapper(QChatDao.class);
		int index = dao.updataQNameDao(oldQname, newQname);
		DbTools.closeSession(session);
		return index;
		
	}
	/**
	 * �޸�Ⱥ����
	 * @param note
	 * @param Qname
	 * @return
	 */
	public int updateQNoteDao(String note,String qName){
		//��������
		SqlSession session = DbTools.createSession();
		//��ȡ�ӿ�ʵ��
		QChatDao dao = session.getMapper(QChatDao.class);
		int index = dao.updateQNoteDao(note, qName);
		DbTools.closeSession(session);
		return index;
	}
	/**
	 * �޸�Ⱥ�ǳ�
	 * @param map
	 * @return
	 */
	public int updateQNicknameDao(Map<String,String> map){
		//��������
		SqlSession session = DbTools.createSession();
		//��ȡ�ӿ�ʵ��
		QChatDao dao = session.getMapper(QChatDao.class);
		int index = dao.updateQNicknameDao(map);
		DbTools.closeSession(session);
		return index;
	}
	/**
	 * �˳�Ⱥ��
	 * @param qAccount
	 * @param qName
	 * @return
	 */
	public int deleteQDao(String qAccount,String qName){
		//��������
		SqlSession session = DbTools.createSession();
		//��ȡ�ӿ�ʵ��
		QChatDao dao = session.getMapper(QChatDao.class);
		int index = dao.deleteQDao(qAccount, qName);
		DbTools.closeSession(session);
		return index;
	}
	/**
	 * ��ѯ�û��ĺ��� ���ǳƲ�ѯ
	 * @param name
	 * @return
	 */
	public Friends selAllFriendsByNicknameDao(String name,String account){
		//��������
		SqlSession session = DbTools.createSession();
		//��ȡ�ӿ�ʵ��
		FriendDao dao = session.getMapper(FriendDao.class);
		Friends friends = dao.selAllFriendsByNicknameDao(name,account);
		DbTools.closeSession(session);
		return friends;
	}
	/**
	 * ��ѯȺ���е������û�
	 * @param qname
	 * @return
	 */
	public List<QChat>  selAllQChatByQNameDao(String qname){
		//��������
		SqlSession session = DbTools.createSession();
		//��ȡ�ӿ�ʵ��
		QChatDao dao = session.getMapper(QChatDao.class);
		List<QChat> list = dao.selAllQChatByQNameDao(qname);
		DbTools.closeSession(session);
		return list;
	}
	/**
	 * ��ѯָ���ǳƵ�Ⱥ���û�
	 * @param qname
	 * @param nickname
	 * @return
	 */
	public QChat selQChatUserInfoByNicknameDao(String qname,String nickname){
		//��������
		SqlSession session = DbTools.createSession();
		//��ȡ�ӿ�ʵ��
		QChatDao dao = session.getMapper(QChatDao.class);
		QChat qchat = dao.selQChatUserInfoByNicknameDao(qname, nickname);
		DbTools.closeSession(session);
		return qchat;
	}
	/**
	 * �޸ĵ�¼��־
	 * @param account
	 * @param flag
	 * @return
	 */
	public int updatUserOnlineFlagDao(String account,String flag){
		//��������
		SqlSession session = DbTools.createSession();
		//��ȡ�ӿ�ʵ��
		UserDao dao = session.getMapper(UserDao.class);
		int index = dao.updatUserOnlineFlagDao(account, flag);
		DbTools.closeSession(session);
		return index;
	}
	/**
	 * �洢������Ϣ
	 * @param msg
	 * @return
	 */
	public int insertMsgDao(MsgCache msg){
		//��������
		SqlSession session = DbTools.createSession();
		//��ȡ�ӿ�ʵ��
		MsgCacheDao dao = session.getMapper(MsgCacheDao.class);
		int index = dao.insertMsgDao(msg);
		DbTools.closeSession(session);
		return index;
	}
	/**
	 * ɾ��������Ϣ
	 * @param rAccount
	 * @return
	 */
	public int deleteMsgDao(String rAccount){
		//��������
		SqlSession session = DbTools.createSession();
		//��ȡ�ӿ�ʵ��
		MsgCacheDao dao = session.getMapper(MsgCacheDao.class);
		int index = dao.deleteMsgDao(rAccount);
		DbTools.closeSession(session);
		return index;

	}
	/**
	 * ��ѯָ���û������л�����Ϣ
	 * @param rAccount
	 * @return
	 */
	public List<MsgCache> selectMsgDao(String rAccount){
		//��������
		SqlSession session = DbTools.createSession();
		//��ȡ�ӿ�ʵ��
		MsgCacheDao dao = session.getMapper(MsgCacheDao.class);
		List<MsgCache> list = dao.selectAllMsgDao(rAccount);
		DbTools.closeSession(session);
		return list;
	}
}
