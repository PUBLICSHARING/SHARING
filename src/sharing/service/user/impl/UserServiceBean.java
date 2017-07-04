package sharing.service.user.impl;

import java.util.List;
import java.util.Map;

import sharing.dao.user.UserMapper;
import sharing.dao.user.impl.UserController;
import sharing.entity.user.User;
import sharing.service.user.UserService;

public class UserServiceBean implements UserService{

	private UserMapper userMapper = new UserController();
	
	@Override
	public User findUserById(Long userId) throws Exception {
		try{
			return userMapper.findUserById(userId);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("findUserById",e);
		}
	}

	@Override
	public boolean checkUserName(String name) throws Exception {
		try{
			return userMapper.checkUserName(name);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("checkUserName",e);
		}
	}

	@Override
	public Long addUser(User user) throws Exception {
		try{
			return this.userMapper.addUser(user);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("addUser",e);
		}
	}

	@Override
	public Long judgeLoginUser(User user) throws Exception {
		try{
			return this.userMapper.judgeLoginUser(user);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("judgeLoginUser",e);
		}
	}

	@Override
	public Map<String, Object> findUserInfoTitleNeedById(Long userId) throws Exception {
		try{
			return this.userMapper.findUserInfoTitleNeedById(userId);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("findUserInfoTitleNeedById",e);
		}
	}
	
	@Override
	public User updateUser(User user) throws Exception {
		try {
			return this.userMapper.updateUser(user);
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("updateUser", e);
		}
	}
	
	@Override
	public Long updatePassWord(Long userId, String originalPassWord, String newPassWord) throws Exception {
		try {
			//根据用户ID,查询所对应用户,查看密码是否输入正确
			User user = findUserById(userId);
			
			if(!user.getPassword().equals(originalPassWord)) {	//密码输入有误
				return null;
			}
			
			//如果原始密码正确,则执行密码的更新操作
			return this.userMapper.updatePassWord(userId, originalPassWord, newPassWord);
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("updatePassWord", e);
		}
	}

	@Override
	public List<User> findUsersByLimit(Long currentPage, Long pageSize) throws Exception {
		try{
			return this.userMapper.findUsersByLimit(currentPage,pageSize);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("findUsersByLimit",e);
		}
	}

	@Override
	public Long findAllUsersTotal() throws Exception {
		try{
			return userMapper.findAllUsersTotal();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("findAllUsersTotal",e);
		}
	}

	@Override
	public Map<String, Object> findUserInfoAndCountOfOthers(Long userId) throws Exception {
		try{
			return userMapper.findUserInfoAndCountOfOthers(userId);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("findUserInfoAndCountOfOthers",e);
		}
	}

	@Override
	public User findUserBaseInfoAndImages(Long userId) throws Exception {
		try{
			return userMapper.findUserBaseInfoAndImages(userId);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("findUserBaseInfoAndImages",e);
		}
	}

}
