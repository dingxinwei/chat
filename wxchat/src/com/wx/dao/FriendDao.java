package com.wx.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wx.pojo.Friends;
import com.wx.pojo.QChat;
import com.wx.pojo.User;
/**
 * �����û��Ժ��ѵĲ���
 * @author dxw
 *
 */
public interface FriendDao {
	/**
	 * �����û�
	 * @param account �˺�
	 * @return �û�����
	 */
	User selUserDao(String account);
	/**
	 * ��Ӻ���
	 * @param account �Լ����˺�
	 * @param fAccount Ҫ��ӵ��˺�
	 * @return Ӱ�������
	 */
	int addFriendDao(@Param("account")String account, @Param("fAccount")String fAccount);
	/**
	 * ���Һ���
	 * @param account �û��˺�
	 * @return ����һ�����Ѷ���
	 */
	Friends selAllFriendsDao(String account);
	/**
	 * �޸ĺ��ѱ�ע
	 * @param map
	 */
	void updateFriendNoteDao(Map<String, String> map);
	/**
	 * ɾ������
	 * @param account
	 * @param fAccount
	 * @return
	 */
	int deleteFriendDao(@Param("account")String account, @Param("faccount")String faccount);
	/**
	 * ��ѯ���г�Ա���˺�
	 * @param qName Ⱥ��
	 * @return
	 */
	List<QChat> selAllQAccountByQName(String qName);
	/**
	 * ͨ���ǳƲ��Һ���
	 * 
	 */
	Friends selAllFriendsByNicknameDao(@Param("name")String name,@Param("account")String account);
}
