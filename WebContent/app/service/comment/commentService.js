define(['app'],function(app) {
	app.factory('CommentService',function($resource) {
		var commentService = $resource('../CommentAction/:action', {});
		commentService.addComment = function(comment,sucesscb,errorcb) {
			commentService.save({action:"addComment"},{comment:comment},sucesscb,errorcb);
		}
		return commentService;
	})
})