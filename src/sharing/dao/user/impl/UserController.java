package sharing.dao.user.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import base.method.Param;
import base.sqlSession.SqlSessionUtil;
import sharing.dao.comment.CommentMapper;
import sharing.dao.comment.impl.CommentController;
import sharing.dao.dynamic.DynamicMapper;
import sharing.dao.dynamic.impl.DynamicController;
import sharing.dao.user.UserMapper;
import sharing.entity.user.User;

public class UserController implements UserMapper{

	private DynamicMapper dynamicMapper = new DynamicController();
	
	private CommentMapper commentMapper = new CommentController();
	
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

	@Override
	public List<User> findUsersByLimit(Long currentPage, Long pageSize) throws Exception {
		Map<String, Object> params = new HashMap<String,Object>();
		Long startIndex = Long.valueOf((currentPage.longValue() - 1L)* pageSize.longValue());
		params.put("startIndex", startIndex);
		params.put("pageSize", pageSize);
		return SqlSessionUtil.getSqlSession().selectList("sharing.entity.user.findUsersByLimit",params);
	}

	@Override
	public Long findAllUsersTotal() throws Exception {
		return SqlSessionUtil.getSqlSession().selectOne("sharing.entity.user.findAllUsersTotal");
	}

	@Override
	public Map<String, Object> findUserInfoAndCountOfOthers(Long userId) throws Exception {
		Map<String, Object> result = new HashMap<String,Object>();
		result.put("user", this.findUserBaseInfoAndImages(userId));
		result.put("commentCount", this.commentMapper.findCountOfCommentsByUserId(userId));
		result.put("dynamicCount", this.dynamicMapper.findCountOfDynamicsByUserId(userId));
		return result;
	}

	@Override
	public User findUserBaseInfoAndImages(Long userId) throws Exception {
		return SqlSessionUtil.getSqlSession().selectOne("sharing.entity.user.findUserBaseInfoAndImages",userId);
	}

	@Override
	public int stopUser(Long userId) throws Exception {
		return SqlSessionUtil.getSqlSession().update("sharing.entity.user.stopUser", userId);
	}

	@Override
	public int startUser(Long userId) throws Exception {
		return SqlSessionUtil.getSqlSession().update("sharing.entity.user.startUser", userId);
	}
	
	@Override
	public User findUserByName(String userName) throws Exception {
		return SqlSessionUtil.getSqlSession().selectOne("sharing.entity.user.findUserByName", userName);
	}
}
