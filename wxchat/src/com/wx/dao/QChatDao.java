package com.wx.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wx.pojo.QChat;
/**
 *�����û���Ⱥ�ĵĲ���
 */
public interface QChatDao {
	/**
	 * ����Ⱥ��
	 * @param qname Ⱥ��
	 * @param qaccount ��Ա�˺�
	 * @param qflag ��� 0Ⱥ�� 1Ⱥ��Ա
	 * @return
	 */
	int createQChatDao(@Param("qname")String qName,@Param("qaccount")String qAccount,@Param("qflag")Integer qFlag);
	/**
	 * ��ѯ�û������Ⱥ��
	 * @param account
	 * @return
	 */
	List<QChat> selAllQByAccountDao(String account);
	/**
	 * ����Ⱥ��
	 * @param qName Ⱥ��
	 * @return
	 */
	int insertQChatDao(@Param("qname")String qName,@Param("qAccount")String qAccount,@Param("qflag")Integer qFlag);
	/**
	 * �޸�Ⱥ��
	 * @param oldQname
	 * @param newQname
	 * @return
	 */
	int updataQNameDao(@Param("oldQname")String oldQname,@Param("newQname")String newQname);
	/**
	 *  �޸�Ⱥ����
	 * @param note
	 * @param Qname
	 * @return
	 */
	int updateQNoteDao(@Param("note")String note,@Param("Qname")String qName);
	/**
	 * �޸�Ⱥ�ǳ�
	 * @param map
	 * @return
	 */
	int updateQNicknameDao(Map<String,String> map);
	/**
	 * �˳�Ⱥ��
	 */
	int deleteQDao(@Param("qAccount")String qAccount,@Param("qName")String qName);
	/**
	 * ��ѯȺ���е������û�
	 * @param qname
	 * @return
	 */
	List<QChat> selAllQChatByQNameDao(String qName);
	/**
	 * ��ѯȺ���е�ָ���ǳƵ��û�
	 * @param qName
	 * @param nickname
	 * @return
	 */
	QChat selQChatUserInfoByNicknameDao(@Param("qName")String qName,@Param("nickname")String nickname);

}
