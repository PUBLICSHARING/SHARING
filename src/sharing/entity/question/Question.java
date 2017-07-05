package sharing.entity.question;

import java.io.Serializable;
import java.util.Date;

import sharing.entity.user.User;

public class Question implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String remark;
	
	private Date submitDate;
	
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
