package sharing.service.user.impl;

import com.sun.swing.internal.plaf.metal.resources.metal_zh_TW;

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

}
