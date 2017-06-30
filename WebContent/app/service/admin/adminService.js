define(['app'],function(app) {
	app.factory('AdminService',function($resource) {
		var adminService = $resource('../AdminAction/:action', {});
		adminService.judgeAdmin = function(admin,sucesscb,errorcb) {
			adminService.save({action:"judgeAdmin"},{admin:admin},sucesscb,errorcb);
		}
		return adminService;
	})
})