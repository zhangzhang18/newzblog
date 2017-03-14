package com.zblog.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisHelperForTask {

	private static String environmentId = null;

	private static MybatisHelperForTask instance = null;

	/**
	 * 获取 对象实例
	 * 
	 * @return 对象实例
	 */
	public static synchronized MybatisHelperForTask getInstance(String environmentid) {
		if (MybatisHelperForTask.environmentId != null && !environmentid.equals(MybatisHelperForTask.environmentId)) {
			MybatisHelperForTask.instance = new MybatisHelperForTask(environmentid);
		} else if (MybatisHelperForTask.environmentId == null) {
			MybatisHelperForTask.instance = new MybatisHelperForTask(environmentid);
		}
		return MybatisHelperForTask.instance;
	}

	private SqlSessionFactory sessionFactory = null;

	private MybatisHelperForTask(String environmentid) {
		MybatisHelperForTask.environmentId = environmentid;
		Reader reader;
		try {
//			String url = Path.getCurrentPath();
			reader = Resources.getResourceAsReader("resource/configuration-" + MybatisHelperForTask.environmentId + ".xml");
			this.sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @return the sessionFactory
	 */
	public SqlSessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	/**
	 * @param sessionFactory
	 *            the sessionFactory to set
	 */
	public void setSessionFactory(SqlSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
