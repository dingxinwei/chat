package com.wx.service;

import java.util.List;

import com.wx.pojo.Friends;
import com.wx.pojo.MsgCache;
import com.wx.pojo.QChat;
import com.wx.pojo.User;
/**
 * service层接口
 * @author dxw
 *
 */
public interface UserService {
	/**
	 * 
	 * @param account 账号
	 * @param pwd 密码
	 * @return 返回用户对象
	 */
	
	User checkLoginService(String account,String pwd);
	/**
	 * 用户注册
	 * @param u
	 * @return
	 */
	int regService(User u);
	/**
	 * 查询用户密码
	 * @param account
	 * @return
	 */
	String selUserPwdService(String account);
	/**
	 * 修改用户密码
	 * @param account
	 * @param pwd
	 * @return
	 */
	int updataUserPwdService(String account,String pwd);
	/**
	 * 修改用户信息
	 * @param value  修改的值
	 * @param account 修改的账号
	 * @param name 修改的属性
	 */
	void updateUserInfoService(Object value, Integer account, String name);
	/**
	 * 查找用户
	 * @param account 查询用户
	 * @return 返回查到的用户信息
	 */
	User selUserService(String account);
	/**
	 * 
	 * @param account
	 * @param fAccount
	 */
	void addFriendService(String account, String fAccount);
	/**
	 * 查询所有好友
	 * @param account
	 * @return
	 */
	
	Friends  selAllFriendsService(String account);
	/**
	 * 修改好友备注
	 * @param note 备注
	 * @param faccount 好友账号
	 * @param account 用户账号
	 */
	void updateFriendNotesService(String note, String faccount, String account);
	/**
	 * 删除好友
	 * @param account
	 * @param faccount
	 * @return
	 */
	int deleteFriendService(String account,String faccount);
	/**
	 * 创建群聊
	 * @param qname
	 * @param qaccount
	 * @param flag
	 */
	int createQChatService(String qname,String qaccount,Integer flag);
	/**
	 * 查询用户加入的所有群聊
	 * @param account
	 * @return
	 */
	List<QChat> selAllQByAccountService(String account);
	/**
	 * 加入群聊
	 * @param qName
	 * @param qAccount
	 * @param qflag
	 * @return
	 */
	int insertQChatService(String qName,String qAccount,Integer qflag);
	
	/**
	 * 修改群名
	 */
	
	int updateQNameService(String oldQname,String newQname);
	/**
	 * 修改群公告
	 * @param note
	 * @param qName
	 * @return
	 */
	int updateQNoteService(String note,String qName);
	/**
	 * 修改群昵称
	 * @param qNickname
	 * @param qName
	 * @param qAccount
	 * @return
	 */
	int updateQNicknameService(String qNickname,String qName,String qAccount);
	/**
	 * 退出群聊
	 * @param qAccount
	 * @param qName
	 * @return
	 */
	int deleteQService(String qAccount,String qName);
	/**
	 * 查询指定昵称的好友
	 * @param account
	 * @return
	 */
	Friends  selAllFriendsByNicknameService(String name,String account);
	/**
	 * 查询群聊中的所有用户
	 * @param qname
	 * @return
	 */
	List<QChat> selAllQChatByQNameService(String qname);
	
	/**
	 * 查询群聊中指定昵称的用户
	 * @param qname
	 * @param nickname
	 * @return
	 */
	QChat selQChatUserInfoByNicknameService(String qname,String nickname);
	/**
	 * 修改登录标志
	 * @param account
	 * @param flag
	 * @return
	 */
	int  updatUserOnlineFlagService(String account,String flag);
	
	/**
	 * 存储离线消息
	 * @param msg
	 * @return
	 */
	int insertMsgService(MsgCache msg);
	/**
	 * 删除用户的所有离线消息
	 * @param rAccount
	 * @return
	 */
	int deleteMsgService(String rAccount);
	/**
	 * 查询用户的所有离线消息
	 * @param rAccount
	 * @return
	 */
	List<MsgCache> selectMsgService(String rAccount);
}	

