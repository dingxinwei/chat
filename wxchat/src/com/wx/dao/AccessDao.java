package com.wx.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.wx.pojo.Friends;
import com.wx.pojo.MsgCache;
import com.wx.pojo.QChat;
import com.wx.pojo.User;
/**
 * dao层提供对外访问的类
 * @author Administrator
 *
 */
public class AccessDao {
	/**
	 * 
	 * @param account 账号
	 * @param pwd 密码
	 * @return 用户对象
	 */
	public User checkLoginDao(String account,String pwd){
		//创建连接
		SqlSession session = DbTools.createSession();
		//获取接口实现
		UserDao dao = session.getMapper(UserDao.class);
		User user = dao.checkLoginDao(account,pwd);
		DbTools.closeSession(session);
		return user;
	}
	/**
	 * 
	 * @param u 封装用户信息的对象
	 * @return 返回影响的行数
	 */
	public int regDao(User u){
		//创建连接
		SqlSession session = DbTools.createSession();
		//获取接口实现
		UserDao dao = session.getMapper(UserDao.class);
		int index = dao.regDao(u);
		DbTools.closeSession(session);
		return index;
	}
	/**
	 * 修改密码
	 * @param account
	 * @return
	 */
	public String selUserPwdDao(String account){
		//创建连接
		SqlSession session = DbTools.createSession();
		//获取接口实现
		UserDao dao = session.getMapper(UserDao.class);
		String pwd = dao.selUserPwdDao(account);
		DbTools.closeSession(session);
		return pwd;
	}
	/**
	 * 查询用户密码
	 * @param account
	 * @param pwd
	 * @return
	 */
	public int updataUserPwdDao(String account,String pwd){
		//创建连接
		SqlSession session = DbTools.createSession();
		//获取接口实现
		UserDao dao = session.getMapper(UserDao.class);
		int index = dao.updataUserPwdDao(account, pwd);
		DbTools.closeSession(session);
		return index;
	}
	/**
	 * 修改用户信息
	 * @param value
	 * @param account
	 * @param name
	 * @return
	 */
	public int updateUserInfoDao(Object value, Integer account, String name) {
		//创建连接
		SqlSession session = DbTools.createSession();
		//获取接口实现
		UserDao dao = session.getMapper(UserDao.class);
		int index = dao.updateUserInfoDao(value, account, name);
		DbTools.closeSession(session);
		return index;
	}
	/**
	 * 查找用户
	 * @param account
	 * @return
	 */
	public User selUserDao(String account) {
		//创建连接
		SqlSession session = DbTools.createSession();
		//获取接口实现
		FriendDao dao = session.getMapper(FriendDao.class);
		User u  = dao.selUserDao(account);
		DbTools.closeSession(session);
		return u;
	}
	/**
	 * 添加好友
	 * @param account 自己的账号
	 * @param fAccount 好友的账号
	 * @return 影响行数
	 */
	public int addFriendDao(String account, String fAccount) {
		//创建连接
		SqlSession session = DbTools.createSession();
		//获取接口实现
		FriendDao dao = session.getMapper(FriendDao.class);
		int index = dao.addFriendDao(account,fAccount);
		DbTools.closeSession(session);
		return index;
	}
	/**
	 * 查询用户的所有好友
	 * @param account 用户账号
	 * @return 返回一个封装好友信息的对象
	 */
	public Friends selAllFriendsDao(String account){
		//创建连接
		SqlSession session = DbTools.createSession();
		//获取接口实现
		FriendDao dao = session.getMapper(FriendDao.class);
		Friends friends = dao.selAllFriendsDao(account);
		DbTools.closeSession(session);
		return friends;
	}
	/**
	 * 修改好友备注
	 * @param map
	 */
	public void updateFriendNoteDao(Map<String, String> map) {
		
		//创建连接
		SqlSession session = DbTools.createSession();
		//获取接口实现
		FriendDao dao = session.getMapper(FriendDao.class);
		dao.updateFriendNoteDao(map);
		DbTools.closeSession(session);
	}
	/**
	 * 删除好友
	 * @param account
	 * @param faccount
	 * @return
	 */
	public int deleteFriendDao(String account,String faccount){
		//创建连接
		SqlSession session = DbTools.createSession();
		//获取接口实现
		FriendDao dao = session.getMapper(FriendDao.class);
		int index = dao.deleteFriendDao(account, faccount);
		DbTools.closeSession(session);
		return index;
	}
	/**
	 * 创建群聊
	 * @param qname
	 * @param qaccount
	 * @param qflag
	 * @return
	 */
	public int createQChatDao(String qname,String qaccount,Integer qflag){
		//创建连接
		SqlSession session = DbTools.createSession();
		//获取接口实现
		QChatDao dao = session.getMapper(QChatDao.class);
		int index = dao.createQChatDao(qname, qaccount, qflag);
		DbTools.closeSession(session);
		return index;
		
	}
	/**
	 * 查询群聊
	 * @param account
	 * @return
	 */
	public List<QChat> selAllQByAccountDao(String account){
		//创建连接
		SqlSession session = DbTools.createSession();
		//获取接口实现
		QChatDao dao = session.getMapper(QChatDao.class);
		List<QChat> qList = dao.selAllQByAccountDao(account);
		DbTools.closeSession(session);
		return qList;
	}
	/**
	 * 加入群聊
	 * @return
	 */
	public int insertQChatDao(String qName,String qAccount,Integer qflag){
		//创建连接
		SqlSession session = DbTools.createSession();
		//获取接口实现
		QChatDao dao = session.getMapper(QChatDao.class);
		int index = dao.insertQChatDao(qName, qAccount, qflag);
		DbTools.closeSession(session);
		return index;
	}
	/**
	 * 查询所有的群成员
	 * @param qName
	 * @return
	 */
	public List<QChat> selAllQAccountByQName(String qName){
		//创建连接
		SqlSession session = DbTools.createSession();
		//获取接口实现
		FriendDao dao = session.getMapper(FriendDao.class);
		List<QChat> qList = dao.selAllQAccountByQName(qName);
		DbTools.closeSession(session);
		return qList;
	}
	/**
	 * 修改群名
	 * @param oldQname
	 * @param newQname
	 * @return
	 */
	public int updataQNameDao(String oldQname,String newQname){
		//创建连接
		SqlSession session = DbTools.createSession();
		//获取接口实现
		QChatDao dao = session.getMapper(QChatDao.class);
		int index = dao.updataQNameDao(oldQname, newQname);
		DbTools.closeSession(session);
		return index;
		
	}
	/**
	 * 修改群公告
	 * @param note
	 * @param Qname
	 * @return
	 */
	public int updateQNoteDao(String note,String qName){
		//创建连接
		SqlSession session = DbTools.createSession();
		//获取接口实现
		QChatDao dao = session.getMapper(QChatDao.class);
		int index = dao.updateQNoteDao(note, qName);
		DbTools.closeSession(session);
		return index;
	}
	/**
	 * 修改群昵称
	 * @param map
	 * @return
	 */
	public int updateQNicknameDao(Map<String,String> map){
		//创建连接
		SqlSession session = DbTools.createSession();
		//获取接口实现
		QChatDao dao = session.getMapper(QChatDao.class);
		int index = dao.updateQNicknameDao(map);
		DbTools.closeSession(session);
		return index;
	}
	/**
	 * 退出群聊
	 * @param qAccount
	 * @param qName
	 * @return
	 */
	public int deleteQDao(String qAccount,String qName){
		//创建连接
		SqlSession session = DbTools.createSession();
		//获取接口实现
		QChatDao dao = session.getMapper(QChatDao.class);
		int index = dao.deleteQDao(qAccount, qName);
		DbTools.closeSession(session);
		return index;
	}
	/**
	 * 查询用户的好友 按昵称查询
	 * @param name
	 * @return
	 */
	public Friends selAllFriendsByNicknameDao(String name,String account){
		//创建连接
		SqlSession session = DbTools.createSession();
		//获取接口实现
		FriendDao dao = session.getMapper(FriendDao.class);
		Friends friends = dao.selAllFriendsByNicknameDao(name,account);
		DbTools.closeSession(session);
		return friends;
	}
	/**
	 * 查询群聊中的所有用户
	 * @param qname
	 * @return
	 */
	public List<QChat>  selAllQChatByQNameDao(String qname){
		//创建连接
		SqlSession session = DbTools.createSession();
		//获取接口实现
		QChatDao dao = session.getMapper(QChatDao.class);
		List<QChat> list = dao.selAllQChatByQNameDao(qname);
		DbTools.closeSession(session);
		return list;
	}
	/**
	 * 查询指定昵称的群聊用户
	 * @param qname
	 * @param nickname
	 * @return
	 */
	public QChat selQChatUserInfoByNicknameDao(String qname,String nickname){
		//创建连接
		SqlSession session = DbTools.createSession();
		//获取接口实现
		QChatDao dao = session.getMapper(QChatDao.class);
		QChat qchat = dao.selQChatUserInfoByNicknameDao(qname, nickname);
		DbTools.closeSession(session);
		return qchat;
	}
	/**
	 * 修改登录标志
	 * @param account
	 * @param flag
	 * @return
	 */
	public int updatUserOnlineFlagDao(String account,String flag){
		//创建连接
		SqlSession session = DbTools.createSession();
		//获取接口实现
		UserDao dao = session.getMapper(UserDao.class);
		int index = dao.updatUserOnlineFlagDao(account, flag);
		DbTools.closeSession(session);
		return index;
	}
	/**
	 * 存储离线消息
	 * @param msg
	 * @return
	 */
	public int insertMsgDao(MsgCache msg){
		//创建连接
		SqlSession session = DbTools.createSession();
		//获取接口实现
		MsgCacheDao dao = session.getMapper(MsgCacheDao.class);
		int index = dao.insertMsgDao(msg);
		DbTools.closeSession(session);
		return index;
	}
	/**
	 * 删除离线消息
	 * @param rAccount
	 * @return
	 */
	public int deleteMsgDao(String rAccount){
		//创建连接
		SqlSession session = DbTools.createSession();
		//获取接口实现
		MsgCacheDao dao = session.getMapper(MsgCacheDao.class);
		int index = dao.deleteMsgDao(rAccount);
		DbTools.closeSession(session);
		return index;

	}
	/**
	 * 查询指定用户的所有缓存消息
	 * @param rAccount
	 * @return
	 */
	public List<MsgCache> selectMsgDao(String rAccount){
		//创建连接
		SqlSession session = DbTools.createSession();
		//获取接口实现
		MsgCacheDao dao = session.getMapper(MsgCacheDao.class);
		List<MsgCache> list = dao.selectAllMsgDao(rAccount);
		DbTools.closeSession(session);
		return list;
	}
}
