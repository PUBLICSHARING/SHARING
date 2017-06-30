package sharing.entity.dynamic;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import sharing.entity.comment.Comment;
import sharing.entity.file.SharingFile;
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
	
	private List<Comment> comments;
	
	private List<SharingFile> images;

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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<SharingFile> getImages() {
		return images;
	}

	public void setImages(List<SharingFile> images) {
		this.images = images;
	}
	
}
