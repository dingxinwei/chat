package com.wx.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/**
 * dao�㹤����
 * @author Administrator
 *
 */
public class DbTools {
	//mybatis���󹤳�
	private static SqlSessionFactory sqlSessionFactory;
	
	/**
	 * ��ʼ�� ����mybatis���󹤳�
	 */
	static{
		String resource = "myBatisConfig.xml";
		InputStream inputStream=null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory  = new SqlSessionFactoryBuilder().build(inputStream);   
		} catch (IOException e) {	
			e.printStackTrace();
		}
	}
	/**
	 * ����sqlsession����
	 * @return 
	 */
	public static SqlSession createSession(){
		return sqlSessionFactory.openSession(true);
	}
	/**
	 * �ر�����
	 * @param session
	 */
	public static void closeSession(SqlSession session){
		session.close();
	}
}
