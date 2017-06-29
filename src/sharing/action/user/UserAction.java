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
	
	public boolean checkUserName(String name) throws Exception{
		try{
			return userService.checkUserName(name);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Long addUser(User user) throws Exception {
		try{
			return userService.addUser(user);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public boolean judgeLoginUser(User user) throws Exception {
		try{
			return userService.judgeLoginUser(user);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}