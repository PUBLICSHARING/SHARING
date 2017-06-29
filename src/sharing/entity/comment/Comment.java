package sharing.entity.comment;

import java.io.Serializable;
import java.util.Date;
import sharing.entity.dynamic.Dynamic;
import sharing.entity.user.User;

public class Comment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String content;
	
	private Date commentTime;
	
	private Dynamic dynamic;
	
	private User user;
	
	private Comment fatherComment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public Dynamic getDynamic() {
		return dynamic;
	}

	public void setDynamic(Dynamic dynamic) {
		this.dynamic = dynamic;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Comment getFatherComment() {
		return fatherComment;
	}

	public void setFatherComment(Comment fatherComment) {
		this.fatherComment = fatherComment;
	}
}
