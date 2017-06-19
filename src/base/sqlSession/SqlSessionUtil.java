package base.sqlSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionManager;

public class SqlSessionUtil {
	
	/**单例模式**/
	private static SqlSessionUtil sqlSessionUtil = new SqlSessionUtil();
	
	private SqlSessionUtil() {
	}
	
	/**
	 * 获取SqlSession对象
	 */
	public static SqlSession getSqlSession() {
		SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
		return SqlSessionManager.newInstance(sqlSessionFactory);
	}
}
