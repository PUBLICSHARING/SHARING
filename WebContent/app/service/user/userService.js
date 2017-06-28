define(['app'],function(app) {
	app.factory('UserService',function($resource) {
		var userService = $resource('../UserAction/:action', {});
		userService.checkUserName = function(name,sucesscb,errorcb) {
			userService.get({action:"checkUserName",name:name},sucesscb,errorcb);
		},
		userService.addUser = function(user,sucesscb,errorcb) {
			userService.save({action:"addUser"},{user:user},sucesscb,errorcb);
		},
		userService.judgeLoginUser = function(user,sucesscb,errorcb) {
			userService.save({action:"judgeLoginUser"},{user:user},sucesscb,errorcb);
		}
		return userService;
	})
})