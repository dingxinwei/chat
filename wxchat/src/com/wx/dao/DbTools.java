package com.wx.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/**
 * dao层工具类
 * @author Administrator
 *
 */
public class DbTools {
	//mybatis对象工厂
	private static SqlSessionFactory sqlSessionFactory;
	
	/**
	 * 初始化 构建mybatis对象工厂
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
	 * 创建sqlsession对象
	 * @return 
	 */
	public static SqlSession createSession(){
		return sqlSessionFactory.openSession(true);
	}
	/**
	 * 关闭连接
	 * @param session
	 */
	public static void closeSession(SqlSession session){
		session.close();
	}
}
