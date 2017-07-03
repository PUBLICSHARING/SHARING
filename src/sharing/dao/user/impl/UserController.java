package sharing.dao.user.impl;
import java.util.HashMap;
import java.util.Map;

import base.method.Param;
import base.sqlSession.SqlSessionUtil;
import sharing.dao.user.UserMapper;
import sharing.entity.user.User;

public class UserController implements UserMapper{

	@Override
	public User findUserById(Long userId) throws Exception{
		return SqlSessionUtil.getSqlSession().selectOne("sharing.entity.user.findUserById", userId);
	}

	@Override
	public boolean checkUserName(String name) throws Exception {
		User user = SqlSessionUtil.getSqlSession().selectOne("sharing.entity.user.findUserByName", name);
		if(user != null) {
			return false;
		}
		else{
			return true;
		}
	}

	@Override
	public Long addUser(User user) throws Exception {
		SqlSessionUtil.getSqlSession().insert("sharing.entity.user.addUser", user);
		return user.getId();
	}

	@Override
	public Long judgeLoginUser(User user) throws Exception {
		User result = SqlSessionUtil.getSqlSession().selectOne("sharing.entity.user.judgeLoginUser", user);
		if(result == null) {
			return null;
		}
		else{
			return result.getId();
		}
	}

	@Override
	public Map<String, Object> findUserInfoTitleNeedById(Long userId) throws Exception {
		return SqlSessionUtil.getSqlSession().selectMap("sharing.entity.user.findUserInfoTitleNeedById", userId, "id");
	}
	
	@Override
	public User updateUser(User user) throws Exception {
		SqlSessionUtil.getSqlSession().update("sharing.entity.user.updateUser", user);
		return user;
	}
	
	@Override
	public Long updatePassWord(@Param("userId")Long userId, @Param("originalPassWord")String originalPassWord, @Param("newPassWord")String newPassWord) throws Exception {
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		params.put("newPassWord", newPassWord);
		SqlSessionUtil.getSqlSession().update("sharing.entity.user.updatePassWord",params);
		//在配置文件中配置更新语句
		return userId;
	}

}
