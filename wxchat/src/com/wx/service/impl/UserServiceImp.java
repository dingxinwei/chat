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
	//dao层对象
	AccessDao mapper = new AccessDao();
	//日志对象
	Logger log =  Logger.getLogger(UserServlet.class);
	/**
	 * 用户登录
	 */
	@Override
	public User checkLoginService(String account,String pwd) {
		User user = mapper.checkLoginDao(account,pwd);
		if(null!=user){//登录成功打印日志
			log.debug(account+"登录成功");
		}
		return user;
	}
	/**
	 * 用户注册
	 */
	@Override
	public int regService(User u) {
		int index = mapper.regDao(u);
		if(index>0){//注册成功打印日志
			log.debug(u.getAccount()+"注册成功");
		}
		return index;
	}
	/**
	 * 修改用户信息
	 */
	@Override
	public void updateUserInfoService(Object value, Integer account, String name) {
		
		int index = mapper.updateUserInfoDao(value,account,name);
		if(index > 0){
			log.debug(account+"修改了"+name);
		}
		
	}
	/**
	 * 查找用户
	 */
	@Override
	public User selUserService(String account) {
		User u = mapper.selUserDao(account);
		return u;
	}
	/**
	 * 添加好友
	 */
	@Override
	public void addFriendService(String account, String fAccount) {
		int index = mapper.addFriendDao(account,fAccount);
		
	}
	/**
	 * 查找所有好友
	 */
	@Override
	public Friends selAllFriendsService(String account) {
		Friends friends = mapper.selAllFriendsDao(account);
		return friends;
	}
	/**
	 * 修改好友备注
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
	 * 删除好友
	 */
	@Override
	public int deleteFriendService(String account, String faccount) {
		int index = mapper.deleteFriendDao(account, faccount);
		if(index >0){
			log.debug(account + "删除好友" + faccount + "成功");
		}else{
			log.debug(account + "删除好友" + faccount + "失败");
		}
		return index;
	}
	@Override
	public int createQChatService(String qname, String qaccount, Integer qflag) {
		int index = mapper.createQChatDao(qname, qaccount, qflag);
		if(index > 0){
			log.debug(qaccount + "创建群聊");
		}
		return index;
	}
	/**
	 * 查询用户加入的所有群聊
	 */
	@Override
	public List<QChat> selAllQByAccountService(String account) {
		List<QChat> qList = mapper.selAllQByAccountDao(account);
		return qList;
	}
	/**
	 * 加入群聊
	 */
	@Override
	public int insertQChatService(String qName, String qAccount, Integer qflag) {
		int index = mapper.insertQChatDao(qName, qAccount, qflag);
		return index;
	}
	/**
	 * 修改群名
	 */
	@Override
	public int updateQNameService(String oldQname, String newQname) {
		int index = mapper.updataQNameDao(oldQname, newQname);
		return index;
	}
	/**
	 * 修改群公告
	 */
	@Override
	public int updateQNoteService(String note, String qName) {
		int index = mapper.updateQNoteDao(note, qName);
		return index;
	}
	/**
	 * 修改群昵称
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
	 * 退出群聊
	 */
	@Override
	public int deleteQService(String qAccount, String qName) {
		
		return mapper.deleteQDao(qAccount, qName);
	}
	/**
	 * 查询指定昵称好友
	 */
	@Override
	public Friends selAllFriendsByNicknameService(String name, String account) {
		if(name.contains("%")){//过滤特殊字符
			name = name.replaceAll("%", "");
		}else if(name.contains("_")){
			name = name.replaceAll("_", "");
		}
		name = "%" + name + "%";
		return mapper.selAllFriendsByNicknameDao(name, account);
	}
	
	
	/**
	 * 修改密码
	 */
	@Override
	public int updataUserPwdService(String account, String pwd) {
		int index = mapper.updataUserPwdDao(account, pwd);
		if(index > 0){
			log.debug(account + "修改了密码");
		}
		return index;
	}
	/**
	 * 查询用户密码
	 */
	@Override
	public String selUserPwdService(String account) {
		
		return mapper.selUserPwdDao(account);
	}
	/**
	 * 查询群聊中的所有用户
	 */
	@Override
	public List<QChat> selAllQChatByQNameService(String qname) {
		
		return mapper.selAllQChatByQNameDao(qname);
	}
	/**
	 * 查询群聊中指定昵称的用户
	 */
	@Override
	public QChat selQChatUserInfoByNicknameService(String qname, String nickname) {
		if(nickname.contains("%")){//过滤特殊字符
			nickname = nickname.replaceAll("%", "");
		}else if(nickname.contains("_")){
			nickname = nickname.replaceAll("_", "");
		}
		nickname = "%" + nickname + "%";
		return mapper.selQChatUserInfoByNicknameDao(qname, nickname);
	}
	/**
	 * 修改登录标志
	 */
	@Override
	public int updatUserOnlineFlagService(String account, String flag) {
		
		return mapper.updatUserOnlineFlagDao(account, flag);
	}
	/**
	 * 存储离线消息
	 */
	@Override
	public int insertMsgService(MsgCache msg) {
		
		return mapper.insertMsgDao(msg);
	}
	/**
	 * 删除离线消息
	 */
	@Override
	public int deleteMsgService(String rAccount) {
		return mapper.deleteMsgDao(rAccount);
	}
	/**
	 * 查询离线消息
	 */
	@Override
	public List<MsgCache> selectMsgService(String rAccount) {
		
		return mapper.selectMsgDao(rAccount);
	}

}
