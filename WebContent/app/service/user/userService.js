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
		},
		userService.findUserInfoTitleNeedById = function(userId,sucesscb,errorcb) {
			userService.query({action:"findUserInfoTitleNeedById",userId:userId},sucesscb,errorcb);
		},
		userService.updateUser = function(user, sucesscb, errorcb) {
			userService.save({action:"updateUser"},{user:user},sucesscb, errorcb);
		},
		userService.updatePassWord = function(userId, originalPassWord, newPassWord, sucesscb, errorcb) {
			userService.get({action:"updatePassWord", userId:userId, originalPassWord:originalPassWord, newPassWord:newPassWord},sucesscb, errorcb);
		},
		userService.findUsersByLimit = function(currentPage,pageSize,sucesscb, errorcb) {
			userService.get({action:"findUsersByLimit",currentPage:currentPage,pageSize:pageSize},sucesscb, errorcb);
		},
		userService.findUsersAndTotalByLimit = function(currentPage,pageSize,sucesscb, errorcb) {
			userService.get({action:"findUsersAndTotalByLimit",currentPage:currentPage,pageSize:pageSize},sucesscb, errorcb);
		},
		userService.findUserById = function(userId, sucesscb, errorcb) {
			userService.get({action:"findUserById", userId:userId},sucesscb, errorcb);
		},
		userService.findUserInfoAndCountOfOthers = function(userId, sucesscb, errorcb) {
			userService.get({action:"findUserInfoAndCountOfOthers", userId:userId},sucesscb, errorcb);
		},
		userService.findUserBaseInfoAndImages = function(userId, sucesscb, errorcb) {
			userService.get({action:"findUserBaseInfoAndImages", userId:userId},sucesscb, errorcb);
		}
		return userService;
	})
})