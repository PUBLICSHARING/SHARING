package base.entity;

import java.util.Date;
import base.entity.Column;;

public class BaseEntity {
	
	@Column(label = "操作日期")
	private Date operateDate;

	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
	
}
