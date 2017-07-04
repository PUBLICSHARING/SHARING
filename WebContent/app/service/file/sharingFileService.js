define(['app'],function(app) {
	app.factory('SharingFileService',function($resource) {
		var sharingFileService = $resource('../SharingFileAction/:action', {});
		sharingFileService.addSharingFile = function(sharingFile,sucesscb,errorcb) {
			sharingFileService.save({action:"addSharingFile"},{sharingFile:sharingFile},sucesscb,errorcb);
		},
		sharingFileService.findAllFilesTotal = function(sucesscb,errorcb) {
			sharingFileService.get({action:"findAllFilesTotal"},sucesscb,errorcb);
		},
		sharingFileService.findFilesByLimit = function(currentPage,pageSize,sucesscb,errorcb) {
			sharingFileService.get({action:"findFilesByLimit",currentPage:currentPage,pageSize:pageSize},sucesscb,errorcb);
		},
		sharingFileService.findFilesAndTotalByLimit = function(currentPage,pageSize,sucesscb,errorcb) {
			sharingFileService.get({action:"findFilesAndTotalByLimit",currentPage:currentPage,pageSize:pageSize},sucesscb,errorcb);
		}
		return sharingFileService;
	})
})