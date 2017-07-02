define(['app'],function(app) {
	app.factory('PublishingDynamicsService',function($resource) {
		var dynamicService = $resource('../DynamicAction/:action', {});
		dynamicService.addDynamic = function(dynamic,sucesscb,errorcb) {
			dynamicService.save({action:"addDynamic"},{dynamic:dynamic},sucesscb,errorcb);
		}
		return dynamicService;
	})
})