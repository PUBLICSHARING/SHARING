var dynamicMApp = angular.module("dynamicMApp",[]);
dynamicMApp.controller("dynamicMCtrl",["$state","$scope","$rootScope","$stateParams","DynamicsService",function($state,$scope,$rootScope,$stateParams,DynamicsService) {
	$scope.adminId = $stateParams.adminId;
	$scope.dynamics = [];
	$scope.currentPage = 1;
	$scope.pageSize = 12;
	$scope.total = 0;
	$scope.hasDataMore = true;
	$scope.allPage = 0;
	/*指定页*/
	$scope.findDynamicsAndTotalByLimit = function() {
		$rootScope.alertRefresh();
		DynamicsService.findDynamicsAndTotalByLimit($scope.currentPage,$scope.pageSize,sucesscb,errorcb);
		function sucesscb(data) {
			$scope.total = data.total;
			$scope.dynamics = data.dynamics;
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
			$scope.findDynamicsAndTotalByLimit();
		}
	}
	/*下一页*/
	$scope.nextPage = function() {
		if($scope.currentPage != $scope.allPage) {
			$scope.currentPage++;
			$scope.findDynamicsAndTotalByLimit();
		}
	}
	/*首页*/
	$scope.firstPage = function() {
		if($scope.currentPage != 1) {
			$scope.currentPage = 1;
			$scope.findDynamicsAndTotalByLimit();
		}
	}
	/*尾页*/
	$scope.lastPage = function() {
		if($scope.currentPage != $scope.allPage) {
			$scope.currentPage = $scope.allPage;
			$scope.findDynamicsAndTotalByLimit();
		}
	}
	/*跳转*/
	$scope.goPageIndex = 1;
	$scope.goPage = function() {
		if($scope.goPageIndex < 0 || $scope.goPageIndex === $scope.currentPage||$scope.goPageIndex > $scope.totalPage){
		}
		else{
			$scope.currentPage = $scope.goPageIndex;
			$scope.findDynamicsAndTotalByLimit();
		}
	}
	
	$scope.findDynamicsAndTotalByLimit();
	
}])