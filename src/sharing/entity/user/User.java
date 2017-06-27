package sharing.entity.user;

import java.io.Serializable;

import org.apache.commons.beanutils.BeanUtils;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;
	
	private String password;
	
	private String sex;
	
	private Integer age;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}
}
