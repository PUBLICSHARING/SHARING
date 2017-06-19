package base.sqlSession;
import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryUtils {
	/**配置文件**/
	private static final String resource = "mybatis-config.xml";
	/**单列**/
	private static SqlSessionFactoryUtils sqlSessionFactoryUtils = new SqlSessionFactoryUtils();
	/**SqlSessionFactory**/
	private static SqlSessionFactory sqlSessionFactory;
	
	private SqlSessionFactoryUtils() {
	}
	
	/**
	 * 获取SqlSessionFactory对象
	 */
	public synchronized static SqlSessionFactory getSqlSessionFactory() {
		if(sqlSessionFactory == null) {
			InputStream iStream = null;
			try {
				iStream = Resources.getResourceAsStream(resource);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return new SqlSessionFactoryBuilder().build(iStream);
		}
		else{
			return sqlSessionFactory;
		}
	}
}
