package sharing.action.user;

import sharing.entity.user.User;
import sharing.service.user.UserService;
import sharing.service.user.impl.UserServiceBean;

public class UserAction {

	private UserService userService = new UserServiceBean();
	
	public User findUserById(Long userId) throws Exception{
		try{
			return userService.findUserById(userId);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
