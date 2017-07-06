define(['app'],function(app) {
	app.factory('AccusationService',function($resource) {
		var accusationService = $resource('../AccusationAction/:action', {});
		accusationService.addAccusation = function(accusation,sucesscb,errorcb) {
			accusationService.save({action:"addAccusation"},{accusation:accusation},sucesscb,errorcb);
		},
		accusationService.deleteAccusation = function(accusationId,sucesscb,errorcb) {
			accusationService.query({action:"deleteAccusation",accusationId:accusationId},sucesscb,errorcb);
		},
		accusationService.findAccusationByLimit = function(currentPage,pageSize,sucesscb,errorcb) {
			accusationService.get({action:"findAccusationByLimit",currentPage:currentPage,pageSize:pageSize},sucesscb,errorcb);
		},
		accusationService.findTotalOfAllAccusation = function(sucesscb,errorcb) {
			accusationService.get({action:"findTotalOfAllAccusation"},sucesscb,errorcb);
		},
		accusationService.findTotalAndAccusationByLimit = function(currentPage,pageSize,sucesscb,errorcb) {
			accusationService.get({action:"findTotalAndAccusationByLimit",currentPage:currentPage,pageSize:pageSize},sucesscb,errorcb);
		},
		accusationService.findCountOfNotReaded = function(sucesscb,errorcb) {
			accusationService.get({action:"findCountOfNotReaded"},sucesscb,errorcb);
		},
		accusationService.markReaded = function(accusationId,sucesscb,errorcb) {
			accusationService.query({action:"markReaded",accusationId:accusationId},sucesscb,errorcb);
		}
		return accusationService;
	})
})