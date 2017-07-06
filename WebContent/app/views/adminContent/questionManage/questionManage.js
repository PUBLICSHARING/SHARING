var questionMApp = angular.module("questionMApp",[]);
questionMApp.controller("questionMCtrl",["$state","$scope","$rootScope","$stateParams","QuestionService",function($state,$scope,$rootScope,$stateParams,QuestionService) {
	$scope.adminId = $stateParams.adminId;
	$scope.questions = [];
	$scope.currentPage = 1;
	$scope.pageSize = 12;
	$scope.total = 0;
	$scope.hasDataMore = true;
	$scope.allPage = 0;
	/*指定页*/
	$scope.findTotalAndQuestionByLimit = function() {
		$rootScope.alertRefresh();
		QuestionService.findTotalAndQuestionByLimit($scope.currentPage,$scope.pageSize,sucesscb,errorcb);
		function sucesscb(data) {
			$scope.total = data.total;
			$scope.questions = data.questions;
			if(($scope.currentPage * $scope.pageSize) >= $scope.total) {
				$scope.hasDataMore = false;
			}
			if($scope.total % $scope.pageSize !== 0) {
				$scope.allPage = ($scope.total-$scope.total % $scope.pageSize) / $scope.pageSize + 1;
			}
			else{
				$scope.allPage = $scope.total / $scope.pageSize;
			}
			$rootScope.hideRefresh();
		}
		function errorcb(error) {
			$rootScope.hideRefresh();
			$rootScope.alertWarn("查询失败！");
		}
	}
	/*上一页*/
	$scope.prePage = function() {
		if($scope.currentPage != 1) {
			$scope.currentPage--;
			$scope.findTotalAndQuestionByLimit();
		}
	}
	/*下一页*/
	$scope.nextPage = function() {
		if($scope.currentPage != $scope.allPage) {
			$scope.currentPage++;
			$scope.findTotalAndQuestionByLimit();
		}
	}
	/*首页*/
	$scope.firstPage = function() {
		if($scope.currentPage != 1) {
			$scope.currentPage = 1;
			$scope.findTotalAndQuestionByLimit();
		}
	}
	/*尾页*/
	$scope.lastPage = function() {
		if($scope.currentPage != $scope.allPage) {
			$scope.currentPage = $scope.allPage;
			$scope.findTotalAndQuestionByLimit();
		}
	}
	/*跳转*/
	$scope.goPageIndex = 1;
	$scope.goPage = function() {
		if($scope.goPageIndex < 0 || $scope.goPageIndex === $scope.currentPage||$scope.goPageIndex > $scope.totalPage){
		}
		else{
			$scope.currentPage = $scope.goPageIndex;
			$scope.findTotalAndQuestionByLimit();
		}
	}
	
	$scope.markReaded = function(index,questionId) {
		QuestionService.markReaded(questionId,sucesscb,errorcb);
		function sucesscb(data) {
			$scope.questions[index].isRead = '是';
			$state.go("adminMain.index.questionManage",{adminId:$scope.adminId});
		}
		function errorcb(error) {
			$rootScope.alert("标记失败！");
		}
	}
	
	$scope.deleteQuestionById = function(questionId) {
		QuestionService.deleteQuestionById(questionId,sucesscb,errorcb);
		function sucesscb(data) {
			$scope.findTotalAndQuestionByLimit();
		}
		function errorcb(error) {
			$rootScope.alertWarn("删除失败！");
		}
	}
	
	$scope.findTotalAndQuestionByLimit();
	
}])