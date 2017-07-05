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
		},
		userService.stopUser = function(userId, sucesscb, errorcb) {
			userService.query({action:"stopUser", userId:userId},sucesscb, errorcb);
		},
		userService.startUser = function(userId, sucesscb, errorcb) {
			userService.query({action:"startUser", userId:userId},sucesscb, errorcb);
		},
		userService.updateHeadImg = function(userId, imgCode, sucesscb, errorcb) {
			userService.save({action:"updateHeadImg"}, {userId:userId, imgCode:imgCode}, sucesscb, errorcb);
		},
		userService.findUserHeadImg = function(userId, sucesscb, errorcb) {
			userService.save({action:"findUserHeadImg"}, {userId:userId}, sucesscb, errorcb);
		},
		userService.searchUsersAndTotalByLimit = function(searchInfo,currentPage,pageSize,sucesscb, errorcb) {
			userService.get({action:"searchUsersAndTotalByLimit",searchInfo:searchInfo,currentPage:currentPage,pageSize:pageSize}, sucesscb, errorcb);
		}
		return userService;
	})
})