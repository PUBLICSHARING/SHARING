package sharing.entity.dynamic;

import java.io.Serializable;
import java.util.Date;

import sharing.entity.user.User;

/*动态*/
public class Dynamic implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String content;
	
	private Date publishTime;
	
	private User user;
	
	private Long likeNum;
	
	private Long dislikeNum;

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

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(Long likeNum) {
		this.likeNum = likeNum;
	}

	public Long getDislikeNum() {
		return dislikeNum;
	}

	public void setDislikeNum(Long dislikeNum) {
		this.dislikeNum = dislikeNum;
	}
}
