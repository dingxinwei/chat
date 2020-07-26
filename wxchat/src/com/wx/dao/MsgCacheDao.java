package com.wx.dao;

import java.util.List;

import com.wx.pojo.MsgCache;

public interface MsgCacheDao {
	/**
	 * ���߻�����Ϣ
	 * @param msg
	 * @return
	 */
	int insertMsgDao(MsgCache msg);
	/**
	 * ɾ��ָ���û��Ļ�����Ϣ
	 * @param rAccount
	 * @return
	 */
	int deleteMsgDao(String rAccount);
	/**
	 * ��ѯָ���û������л�����Ϣ
	 * @param rAccount
	 * @return
	 */
	List<MsgCache> selectAllMsgDao(String rAccount);
}
