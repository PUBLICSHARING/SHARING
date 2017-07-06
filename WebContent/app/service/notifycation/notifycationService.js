define(['app'],function(app) {
	app.factory('NotifycationService',function($resource) {
		var notifycationService = $resource('../NotifycationAction/:action', {});
		notifycationService.addNotifycation = function(notifycation,sucesscb,errorcb) {
			notifycationService.save({action:"addNotifycation"},{notifycation:notifycation},sucesscb,errorcb);
		},
		notifycationService.findCountOfNotifycationNotRead = function(userId,sucesscb,errorcb) {
			notifycationService.query({action:"findCountOfNotifycationNotRead",userId:userId},sucesscb,errorcb);
		},
		notifycationService.findUserNotifycationByLimit = function(userId,currentPage,pageSize,sucesscb,errorcb) {
			notifycationService.get({action:"findUserNotifycationByLimit",userId:userId,currentPage:currentPage,pageSize:pageSize},sucesscb,errorcb);
		},
		notifycationService.findTotalOfUserNotifycation = function(userId,sucesscb,errorcb) {
			notifycationService.query({action:"findTotalOfUserNotifycation",userId:userId},sucesscb,errorcb);
		},
		notifycationService.findTotalAndUserNotifycationByLimit = function(userId,currentPage,pageSize,sucesscb,errorcb) {
			notifycationService.get({action:"findTotalAndUserNotifycationByLimit",userId:userId,currentPage:currentPage,pageSize:pageSize},sucesscb,errorcb);
		},
		notifycationService.markReaded = function(notifycationId,sucesscb,errorcb) {
			notifycationService.query({action:"markReaded",notifycationId:notifycationId},sucesscb,errorcb);
		},
		notifycationService.findTotalOfAllNotifycation = function(sucesscb,errorcb) {
			notifycationService.query({action:"findTotalOfAllNotifycation"},sucesscb,errorcb);
		},
		notifycationService.findAllNotifycationByLimit = function(currentPage,pageSize,sucesscb,errorcb) {
			notifycationService.get({action:"findAllNotifycationByLimit",currentPage:currentPage,pageSize:pageSize},sucesscb,errorcb);
		},
		notifycationService.findTotalAndAllNotifycationByLimit = function(currentPage,pageSize,sucesscb,errorcb) {
			notifycationService.query({action:"findTotalAndAllNotifycationByLimit",currentPage:currentPage,pageSize:pageSize},sucesscb,errorcb);
		}
		
		return notifycationService;
	})
})