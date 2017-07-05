package sharing.service.user.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import sharing.dao.user.UserMapper;
import sharing.dao.user.impl.UserController;
import sharing.entity.user.User;
import sharing.service.user.UserService;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

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
			user.setHeadImg("D:/myWorkSpace/groupGit/SHARING/WebContent/app/directives/title/image/default.jpg");
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
			String userName = user.getName();
			User newUser = findUserByName(userName);
			if(newUser != null && newUser.getId() != user.getId()) { //如果存在该用户,且该用户不是自己
				return null;
			} else {	//如果用户名不存在冲突,执行更新操作
				return this.userMapper.updateUser(user);	
			}
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
			return this.userMapper.findUserBaseInfoAndImages(userId);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("findUserBaseInfoAndImages",e);
		}
	}

	@Override
	public int stopUser(Long userId) throws Exception {
		try{
			return this.userMapper.stopUser(userId);
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public int startUser(Long userId) throws Exception {
		try{
			return this.userMapper.startUser(userId);
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public Long updateHeadImg(Long userId, String imgCode) throws Exception {
		try {
			String[] splitString = imgCode.split(",");	//分割传递过来的base64字符串
			/*获取文件的名称*/
			String path = getPath(User.REAL_PATH);	//获取上传的头像新的路径名+文件名
			
			/*将图片解码并写入文件*/
			if(GenerateImage(splitString[1], path)) {	//成功将图片保存在指定目录后的操作,将书籍信息和保存的文件路径写入到数据库
				User user = findUserById(userId);	//根据用户Id查询出对应用户
				user.setHeadImg(path);	//设置头像路径
				updateUser(user);
				return user.getId();
			} else {
				return null;
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("updateHeadImg", e);
		}
	}
	
	@Override
	public String findUserHeadImg(Long userId) throws Exception {
		try {
			User user = findUserById(userId); //根据用户Id查询用哪用户信息
			String path = user.getHeadImg();
			if(!path.equals("") && path != "" && path != null) {
				File file = new File(path);
				return getImageString(file);
			}else {
				return null;
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("findUserHeadImg", e);
		}
	}
	
	@Override
	public User findUserByName(String userName) throws Exception {
		try {
			return this.userMapper.findUserByName(userName);
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("findUserByName", e);
		}
	}
	
	//对字节数组字符串进行Base64解码并生成图片
	public  boolean GenerateImage(String imgStr, String imgFilePath) {
		// 图像数据为空  
		if(imgStr == null) {
			return false; 
		} 
		BASE64Decoder decoder = new BASE64Decoder();  
		try {  
			// Base64解码  
			byte[] bytes = decoder.decodeBuffer(imgStr);  
			for (int i = 0; i < bytes.length; ++i) {  
				if (bytes[i] < 0) {		// 调整异常数据  
					bytes[i] += 256;  
				}  
			}
				
			OutputStream out = new FileOutputStream(imgFilePath);  
			out.write(bytes);  
			out.flush();  
			out.close();  
		return true;  
		} catch (Exception e) {  
			return false;  
		}  
	}
	
	/*获取当前目录的路径,并且基于时间给图片创建一个名称，返回该图片的完整路径+名称*/
	public  String getPath(String realPath){
		return realPath + "/" + getNo() + ".png";
	}
	
	/*一个方法,读取指定图片文件,将其转化为base64编码字符串*/
	public String getImageString(File file) {
		byte[] data = null;
		try{
			InputStream	input = new FileInputStream(file);
			data = new byte[input.available()];
			input.read(data);
			input.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		BASE64Encoder encoder = new BASE64Encoder();
		
		return encoder.encode(data);
	}
	
	/*获取一个记录编号*格式:12位时间+业务编号+随机数*/
	public  String getNo() {
		//返回的code String code;
		//系统当前时间 12位
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmmss");
		String nowDate = sdf.format(new java.util.Date());
		
		//随机数
		String iRandom = Math.round(Math.random() * 900) + 100 + "";
		
		//整合一个code
		return nowDate + iRandom;
	}

}
