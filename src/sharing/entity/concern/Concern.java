package sharing.entity.concern;

import java.io.Serializable;

/*关注实体*/
public class Concern implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long userId;
	
	private Long concernUserId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getConcernUserId() {
		return concernUserId;
	}

	public void setConcernUserId(Long concernUserId) {
		this.concernUserId = concernUserId;
	}
	
}
