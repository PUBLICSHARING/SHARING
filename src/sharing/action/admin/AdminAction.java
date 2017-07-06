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
			e.printStackTrace();
			throw e;
		}
	}
	
	public Admin findAdminById (Long adminId) throws Exception {
		try{
			return this.adminService.findAdminById(adminId);	
			}
			catch(Exception e) {
				e.printStackTrace();
				throw e;
			}
	}
}
