package sharing.entity.disLike;

import java.io.Serializable;
import java.util.Date;

import sharing.entity.dynamic.Dynamic;
import sharing.entity.user.User;

public class DisLike implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private User user;

	private Dynamic dynamic;
	
	private Date clickTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Dynamic getDynamic() {
		return dynamic;
	}

	public void setDynamic(Dynamic dynamic) {
		this.dynamic = dynamic;
	}

	public Date getClickTime() {
		return clickTime;
	}

	public void setClickTime(Date clickTime) {
		this.clickTime = clickTime;
	}
}
