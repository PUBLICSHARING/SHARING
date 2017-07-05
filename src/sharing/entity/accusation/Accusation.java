package sharing.entity.accusation;

import java.io.Serializable;
import java.util.Date;

import sharing.entity.comment.Comment;
import sharing.entity.dynamic.Dynamic;
import sharing.entity.user.User;

public class Accusation implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private User accusationUser;
	
	private User accusationedUser;
	
	private Dynamic accusationedDynamic;
	
	private Comment accusationedComment;
	
	private String accusationedRemark;
	
	private Date accusationedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getAccusationUser() {
		return accusationUser;
	}

	public void setAccusationUser(User accusationUser) {
		this.accusationUser = accusationUser;
	}

	public User getAccusationedUser() {
		return accusationedUser;
	}

	public void setAccusationedUser(User accusationedUser) {
		this.accusationedUser = accusationedUser;
	}

	public Dynamic getAccusationedDynamic() {
		return accusationedDynamic;
	}

	public void setAccusationedDynamic(Dynamic accusationedDynamic) {
		this.accusationedDynamic = accusationedDynamic;
	}

	public Comment getAccusationedComment() {
		return accusationedComment;
	}

	public void setAccusationedComment(Comment accusationedComment) {
		this.accusationedComment = accusationedComment;
	}

	public String getAccusationedRemark() {
		return accusationedRemark;
	}

	public void setAccusationedRemark(String accusationedRemark) {
		this.accusationedRemark = accusationedRemark;
	}

	public Date getAccusationedDate() {
		return accusationedDate;
	}

	public void setAccusationedDate(Date accusationedDate) {
		this.accusationedDate = accusationedDate;
	}
	
}
