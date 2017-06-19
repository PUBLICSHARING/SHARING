define(['angularAMD','angularUiRouter','angular','ngLoad','mainTitle','jquery','bootstrap','angularResource','comFoot'],function (angularAMD,moment){

    var app = angular.module('app',['ui.router','mainTitleApp','ngResource','comFootApp']);

    app.run(function($rootScope,$state,$stateParams){
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;
    });

    /**
     * 路由
     */
    app.config(function($stateProvider,$rootScopeProvider,$urlRouterProvider,$controllerProvider){

        $urlRouterProvider.otherwise('/index/');
        $stateProvider
        .state('index',angularAMD.route({
            url : '/index'
        }))
//        .state('main.index',angularAMD.route({
//            url : '/index/:userId',
//            views : {
//                'content@main' : angularAMD.route({
//                    templateUrl : 'views/main/content/content.html',
//                    controller : 'contentController',
//                    controllerUrl : 'views/main/content/content',
//                })
//            }
//        }))
    });
    
    /**
     * 用户服务（前后台交互）
     */
    app.factory('UserService',function($resource) {
		var userService = $resource('../UserAction/:action', {});
		userService.getUserByName = function(name,sucesscb,errorcb) {
			userService.get({action:"getUserByName",name:name},sucesscb,errorcb);
		},
		userService.addUser = function(user,sucesscb,errorcb) {
			userService.save({action:"addUser"},{user:user},sucesscb,errorcb);
		},
		userService.judgePassword = function(name,password,sucesscb,errorcb) {
			userService.get({action:"judgePassword",name:name,password:password},sucesscb,errorcb);
		},
		userService.findUserById = function(userId,sucesscb,errorcb){
			userService.get({action:"findUserById",userId:userId},sucesscb,errorcb);
		},
		userService.saveQuestionnaire = function(questionnaire,sucesscb,errorcb){
			userService.save({action:"saveQuestionnaire"},{questionnaire:questionnaire},sucesscb,errorcb);
		}
		return userService;
	})
    
    /**
     * 问卷服务（前后台交互）
     */
    app.factory("QuestionnaireService",function($resource) {
    	var questionnaireService = $resource('../QuestionnaireAction/:action',{});
    	questionnaireService.saveQuestionnaire = function(questionnaire,sucesscb,errorcb){
    		questionnaireService.save({action:"saveQuestionnaire"},{questionnaire:questionnaire},sucesscb,errorcb);
		},
		questionnaireService.findQuestionnairesByUserId = function(userId,sucesscb,errorcb){
    		questionnaireService.get({action:"findQuestionnairesByUserId",userId:userId},sucesscb,errorcb);
		},
		questionnaireService.findQuestionnairesByLimit = function(currentPage,pageSize,sucesscb,errorcb) {
			questionnaireService.get({action:"findQuestionnairesByLimit",currentPage:currentPage,pageSize:pageSize},sucesscb,errorcb);
		},
		questionnaireService.findQuestionnaireDetailByQuestionnaireId = function(questionnaireId,sucesscb,errorcb) {
			questionnaireService.get({action:"findQuestionnaireDetailByQuestionnaireId",questionnaireId:questionnaireId},sucesscb,errorcb);
		},
		questionnaireService.deleteQuestionnaire = function(questionnaireId,sucesscb,errorcb) {
			questionnaireService.save({action:"deleteQuestionnaire"},{questionnaireId:questionnaireId},sucesscb,errorcb);
		},
		questionnaireService.updateQuestionnaire = function(questionnaire,sucesscb,errorcb) {
			questionnaireService.save({action:"updateQuestionnaire"},{questionnaire:questionnaire},sucesscb,errorcb);
		}
    	
    	return questionnaireService;
    })
    
    app.factory("FillInQuestionnaireService",function($resource) {
    	var fillInQuestionnaireService = $resource('../FillInQuestionnaireAction/:action',{});
    	fillInQuestionnaireService.fillInQuestionnaire = function(fillInQuestionnaire,sucesscb,errorcb){
    		fillInQuestionnaireService.save({action:"fillInQuestionnaire"},{fillInQuestionnaire:fillInQuestionnaire},sucesscb,errorcb);
    	},
    	fillInQuestionnaireService.findFillInQuestionnaires = function(questionnaireId,sucesscb,errorcb){
    		fillInQuestionnaireService.get({action:"findFillInQuestionnaires",questionnaireId:questionnaireId},sucesscb,errorcb);
    	},
    	fillInQuestionnaireService.findFillInQuestionnaireDetail = function(fillInQuestionnaireId,sucesscb,errorcb){
    		fillInQuestionnaireService.get({action:"findFillInQuestionnaireDetail",fillInQuestionnaireId:fillInQuestionnaireId},sucesscb,errorcb);
    	}
    	return fillInQuestionnaireService;
    })
    
    app.run(function($rootScope){
		$rootScope.getController = function(element,controllerName)
		{
			return angular.element($("#"+element)[0]).controller(controllerName);
		}
		$rootScope.app= {};
		$rootScope.app.treeId = 1;
	});
    
    app.config(function($provide) {
	    $provide.decorator('$state', function($delegate, $stateParams) {
	        $delegate.forceReload = function() {
	            return $delegate.go($delegate.$current.name, $stateParams, {
	                reload: true,
	                inherit: false,
	                notify: true
	            });
	        };
	        return $delegate;
	    });
	});

    return angularAMD.bootstrap(app);
})