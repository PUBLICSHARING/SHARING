define(['app'],function(app) {
	app.factory('DynamicsService',function($resource) {
		var dynamicService = $resource('../DynamicAction/:action', {});
		dynamicService.addDynamic = function(dynamic,sucesscb,errorcb) {
			dynamicService.save({action:"addDynamic"},{dynamic:dynamic},sucesscb,errorcb);
		},
		dynamicService.findAllDynamicsTotal = function(sucesscb,errorcb) {
			dynamicService.get({action:"findAllDynamicsTotal"},{dynamic:dynamic},sucesscb,errorcb);
		},
		dynamicService.findDynamicsByLimit = function(currentPage,pageSize,sucesscb,errorcb) {
			dynamicService.get({action:"findDynamicsByLimit",currentPage:currentPage,pageSize:pageSize},sucesscb,errorcb);
		},
		dynamicService.findDynamicsAndTotalByLimit = function(currentPage,pageSize,sucesscb,errorcb) {
			dynamicService.get({action:"findDynamicsAndTotalByLimit",currentPage:currentPage,pageSize:pageSize},sucesscb,errorcb);
		},
		
		dynamicService.findAllDynamicsByUserId = function(userId,sucesscb,errorcb){
			dynamicService.get({action:"findAllDynamicsByUserId",id:userId},sucesscb,errorcb);
		},
		
		dynamicService.findNewestDynamics = function(sucesscb,errorcb){
			dynamicService.get({action:"findNewestDynamics"},sucesscb,errorcb);
		},
		dynamicService.updateDynamic = function(dynamic,sucesscb,errorcb) {
			dynamicService.save({action:"updateDynamic"},{dynamic:dynamic},sucesscb,errorcb);
		}
		
		return dynamicService;
	})
})