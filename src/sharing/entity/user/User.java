package sharing.entity.user;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import sharing.entity.comment.Comment;
import sharing.entity.dynamic.Dynamic;
import sharing.entity.file.SharingFile;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private String password;
	
	private String sex;
	
	private Integer age;
	
	private String email;
	
	private String phone;
	
	private String profile;
	
	private String headImg;
	
	private List<SharingFile> images;
	
	private List<Dynamic> dynamics;
	
	private List<Comment> comments;
	
	public static final String PATH = "D:/angular/workspace/GSHARING/src/sharing/image";
	
	private String isStop;
	
	public String getIsStop() {
		return isStop;
	}

	public void setIsStop(String isStop) {
		this.isStop = isStop;
	}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<SharingFile> getImages() {
		return images;
	}

	public void setImages(List<SharingFile> images) {
		this.images = images;
	}

	public List<Dynamic> getDynamics() {
		return dynamics;
	}

	public void setDynamics(List<Dynamic> dynamics) {
		this.dynamics = dynamics;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
}
