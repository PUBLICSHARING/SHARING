package sharing.action.admin;

import sharing.entity.admin.Admin;
import sharing.service.admin.AdminService;
import sharing.service.admin.impl.AdminServiceBean;

public class AdminAction {
	private AdminService adminService = new AdminServiceBean();
	
	public Long judgeAdmin(Admin admin) throws Exception {
		try{
		return this.adminService.judgeAdmin(admin);	
		}
		catch(Exception e) {
			throw e;
		}
	}
}
