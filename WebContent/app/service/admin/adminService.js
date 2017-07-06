define(['app'],function(app) {
	app.factory('AdminService',function($resource) {
		var adminService = $resource('../AdminAction/:action', {});
		adminService.judgeAdmin = function(admin,sucesscb,errorcb) {
			adminService.save({action:"judgeAdmin"},{admin:admin},sucesscb,errorcb);
		},
		adminService.findAdminById = function(adminId,sucesscb,errorcb) {
			adminService.get({action:"findAdminById",adminId:adminId},sucesscb,errorcb);
		}
		return adminService;
	})
})