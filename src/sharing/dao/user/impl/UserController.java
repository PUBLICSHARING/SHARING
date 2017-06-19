package sharing.dao.user.impl;

import base.sqlSession.SqlSessionUtil;
import sharing.dao.user.UserMapper;
import sharing.entity.user.User;

public class UserController implements UserMapper{

	@Override
	public User findUserById(Long userId) throws Exception{
		return SqlSessionUtil.getSqlSession().selectOne("sharing.entity.user.findUserById", userId);
	}

	
}
