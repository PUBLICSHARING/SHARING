var adminLeftApp = angular.module("adminLeftApp",[]);
adminLeftApp.controller("adminLeftCtrl",["$state","$scope","$rootScope","$stateParams","QuestionService",function($state,$scope,$rootScope,$stateParams,QuestionService) {
	$scope.adminId = $stateParams.adminId;
	
	$scope.goAdminMain = function() {
		$state.go("adminMain.index.main",{adminId:$scope.adminId});
	}
	
	$scope.goUserManage = function() {
		$state.go("adminMain.index.userManage",{adminId : $scope.adminId});
	}
	
	$scope.goDynamicManage = function() {
		$state.go("adminMain.index.dynamicManage",{adminId : $scope.adminId});
	}
	
	$scope.goFileManage = function() {
		$state.go("adminMain.index.fileManage",{adminId:$scope.adminId});
	}
	
	$scope.goQuestionManage = function() {
		$state.go("adminMain.index.questionManage",{adminId:$scope.adminId});
	}
	
	$scope.findNotReadCountOfQuestion = function() {
		QuestionService.findNotReadCountOfQuestion(sucesscb,errorcb);
		function sucesscb(data) {
			$scope.notReadQuestionNum = data;
		}
		function errorcb(error) {
			$rootScope.alertWarn("查询未查看反馈数量失败！");
		}
	}
	
	$scope.goAccusationManage = function() {
		$state.go("adminMain.index.accusationManage",{adminId:$scope.adminId});
	}
	
	$scope.findNotReadCountOfQuestion();
}])