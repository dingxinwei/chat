package com.wx.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wx.pojo.QChat;
import com.wx.pojo.User;
/**
 * dao��ӿ� �����û�������Ĳ���
 * @author dxw
 *
 */
public interface UserDao {
	/**
	 * 
	 * @param account �˺�
	 * @param pwd ����
	 * @return ����һ�������ݿ��в鵽���û�����
	 */
	User checkLoginDao(@Param("account")String account,@Param("pwd")String pwd);
	/**
	 * 
	 * @param u ��װ�û�ע����Ϣ�Ķ���
	 * @return ����Ӱ�������
	 */
	int regDao(User u);
	/**
	 * �޸��û���Ϣ
	 * @param value ֵ
	 * @param account �˺�
	 * @param name ����
	 * @return ����Ӱ�������
	 */
	int updateUserInfoDao(@Param("value")Object value,@Param("account")Integer account,@Param("name")String name);
	
	/**
	 * �޸��û�����
	 * @param account
	 * @param pwd
	 * @return
	 */
	int updataUserPwdDao(@Param("account")String account,@Param("pwd")String pwd);
	/**
	 * ��ѯ�û�����
	 * @param account
	 * @return
	 */
	String selUserPwdDao(String account);
	/**
	 * �޸ĵ�¼��־
	 * 
	 */
	int updatUserOnlineFlagDao(@Param("account")String account,@Param("flag")String onlineFlag);
}
