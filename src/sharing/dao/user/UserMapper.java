package sharing.dao.user;

import base.sqlSession.SqlSessionUtil;
import sharing.entity.user.User;

public interface UserMapper {
	
	public User findUserById(Long userId) throws Exception;
}
