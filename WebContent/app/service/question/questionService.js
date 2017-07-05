define(['app'],function(app) {
	app.factory('QuestionService',function($resource) {
		var questionService = $resource('../QuestionAction/:action', {});
		questionService.addQuestion = function(question,sucesscb,errorcb) {
			questionService.save({action:"addQuestion"},{question:question},sucesscb,errorcb);
		},
		questionService.findQuestionsTotal = function(sucesscb,errorcb) {
			questionService.get({action:"findQuestionsTotal"},sucesscb,errorcb);
		},
		questionService.findQuestionsByLimit = function(currentPage,pageSize,sucesscb,errorcb) {
			questionService.get({action:"findQuestionsByLimit",currentPage:currentPage,pageSize:pageSize},sucesscb,errorcb);
		},
		questionService.findTotalAndQuestionByLimit = function(currentPage,pageSize,sucesscb,errorcb) {
			questionService.get({action:"findTotalAndQuestionByLimit",currentPage:currentPage,pageSize:pageSize},sucesscb,errorcb);
		},
		questionService.markReaded = function(questionId,sucesscb,errorcb) {
			questionService.query({action:"markReaded",questionId:questionId},sucesscb,errorcb);
		},
		questionService.findQuestionInfoById = function(questionId,sucesscb,errorcb) {
			questionService.get({action:"findQuestionInfoById",questionId:questionId},sucesscb,errorcb);
		},
		questionService.findNotReadCountOfQuestion = function(sucesscb,errorcb) {
			questionService.get({action:"findNotReadCountOfQuestion"},sucesscb,errorcb);
		},
		questionService.deleteQuestionById = function(questionId,sucesscb,errorcb) {
			questionService.query({action:"deleteQuestionById",questionId:questionId},sucesscb,errorcb);
		}
		return questionService;
	})
})