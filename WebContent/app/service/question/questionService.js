define(['app'],function(app) {
	app.factory('QuestionService',function($resource) {
		var questionService = $resource('../QuestionAction/:action', {});
		questionService.addQuestion = function(question,sucesscb,errorcb) {
			questionService.save({action:"addQuestion"},{question:question},sucesscb,errorcb);
		}
		return questionService;
	})
})