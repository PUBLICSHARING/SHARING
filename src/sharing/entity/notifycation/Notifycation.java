package sharing.entity.notifycation;

import java.io.Serializable;
import java.util.Date;

import sharing.entity.accusation.Accusation;
import sharing.entity.comment.Comment;
import sharing.entity.dynamic.Dynamic;
import sharing.entity.like.Like;
import sharing.entity.user.User;

public class Notifycation implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private User noticeUser;
	
	private Comment noticeFromComment;
	
	private Accusation noticeFromAccusation;
	
	private Like noticeFromLike;
	
	private Dynamic noticeFromDynamic;
	
	private String noticeContent;
	
	private String isRead;
	
	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getNoticeUser() {
		return noticeUser;
	}

	public void setNoticeUser(User noticeUser) {
		this.noticeUser = noticeUser;
	}

	public Comment getNoticeFromComment() {
		return noticeFromComment;
	}

	public void setNoticeFromComment(Comment noticeFromComment) {
		this.noticeFromComment = noticeFromComment;
	}

	public Accusation getNoticeFromAccusation() {
		return noticeFromAccusation;
	}

	public void setNoticeFromAccusation(Accusation noticeFromAccusation) {
		this.noticeFromAccusation = noticeFromAccusation;
	}

	public Like getNoticeFromLike() {
		return noticeFromLike;
	}

	public void setNoticeFromLike(Like noticeFromLike) {
		this.noticeFromLike = noticeFromLike;
	}

	public Dynamic getNoticeFromDynamic() {
		return noticeFromDynamic;
	}

	public void setNoticeFromDynamic(Dynamic noticeFromDynamic) {
		this.noticeFromDynamic = noticeFromDynamic;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	
}
