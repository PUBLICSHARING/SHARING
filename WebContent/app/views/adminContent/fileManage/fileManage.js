var fileMApp = angular.module("fileMApp",[]);
fileMApp.controller("fileMCtrl",["$state","$scope","$rootScope","$stateParams","SharingFileService",function($state,$scope,$rootScope,$stateParams,SharingFileService) {
	$scope.adminId = $stateParams.adminId;
	$scope.files = [];
	$scope.currentPage = 1;
	$scope.pageSize = 11;
	$scope.total = 0;
	$scope.hasDataMore = true;
	$scope.allPage = 0;
	/*指定页*/
	$scope.findFilesAndTotalByLimit = function() {
		$rootScope.alertRefresh();
		SharingFileService.findFilesAndTotalByLimit($scope.currentPage,$scope.pageSize,sucesscb,errorcb);
		function sucesscb(data) {
			$scope.total = data.total;
			$scope.files = data.files;
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
			$scope.findFilesAndTotalByLimit();
		}
	}
	/*下一页*/
	$scope.nextPage = function() {
		if($scope.currentPage != $scope.allPage) {
			$scope.currentPage++;
			$scope.findFilesAndTotalByLimit();
		}
	}
	/*首页*/
	$scope.firstPage = function() {
		if($scope.currentPage != 1) {
			$scope.currentPage = 1;
			$scope.findFilesAndTotalByLimit();
		}
	}
	/*尾页*/
	$scope.lastPage = function() {
		if($scope.currentPage != $scope.allPage) {
			$scope.currentPage = $scope.allPage;
			$scope.findFilesAndTotalByLimit();
		}
	}
	/*跳转*/
	$scope.goPageIndex = 1;
	$scope.goPage = function() {
		if($scope.goPageIndex < 0 || $scope.goPageIndex === $scope.currentPage||$scope.goPageIndex > $scope.totalPage){
		}
		else{
			$scope.currentPage = $scope.goPageIndex;
			$scope.findFilesAndTotalByLimit();
		}
	}
	
	$scope.findFilesAndTotalByLimit();
	
}])