package sharing.service.user.impl;


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

}
