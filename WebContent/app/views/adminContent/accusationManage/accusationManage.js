var accusationMApp = angular.module("accusationMApp",[]);
accusationMApp.controller("accusationMCtrl",["$state","$scope","$rootScope","$stateParams","AccusationService",function($state,$scope,$rootScope,$stateParams,AccusationService) {
	$scope.adminId = $stateParams.adminId;
	$scope.accusations = [];
	$scope.currentPage = 1;
	$scope.pageSize = 12;
	$scope.total = 0;
	$scope.hasDataMore = true;
	$scope.allPage = 0;
	/*指定页*/
	$scope.findTotalAndAccusationByLimit = function() {
		$rootScope.alertRefresh();
		AccusationService.findTotalAndAccusationByLimit($scope.currentPage,$scope.pageSize,sucesscb,errorcb);
		function sucesscb(data) {
			$scope.total = data.total;
			$scope.accusations = data.accusations;
			if(($scope.currentPage * $scope.pageSize) >= $scope.total) {
				$scope.hasDataMore = false;
			}
			if($scope.total % $scope.pageSize !== 0) {
				$scope.allPage = ($scope.total-$scope.total % $scope.pageSize) / $scope.pageSize + 1;
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
			$scope.findTotalAndAccusationByLimit();
		}
	}
	/*下一页*/
	$scope.nextPage = function() {
		if($scope.currentPage != $scope.allPage) {
			$scope.currentPage++;
			$scope.findTotalAndAccusationByLimit();
		}
	}
	/*首页*/
	$scope.firstPage = function() {
		if($scope.currentPage != 1) {
			$scope.currentPage = 1;
			$scope.findTotalAndAccusationByLimit();
		}
	}
	/*尾页*/
	$scope.lastPage = function() {
		if($scope.currentPage != $scope.allPage) {
			$scope.currentPage = $scope.allPage;
			$scope.findTotalAndAccusationByLimit();
		}
	}
	/*跳转*/
	$scope.goPageIndex = 1;
	$scope.goPage = function() {
		if($scope.goPageIndex < 0 || $scope.goPageIndex === $scope.currentPage||$scope.goPageIndex > $scope.totalPage){
		}
		else{
			$scope.currentPage = $scope.goPageIndex;
			$scope.findTotalAndAccusationByLimit();
		}
	}
	
	
	$scope.deleteAccusation = function(accusationId) {
		AccusationService.deleteAccusation(accusationId,sucesscb,errorcb);
		function sucesscb(data) {
			$scope.findTotalAndAccusationByLimit();
		}
		function errorcb(error) {
			$rootScope.alertWarn("删除失败！");
		}
	}
	
	$scope.findTotalAndAccusationByLimit();
	
}])