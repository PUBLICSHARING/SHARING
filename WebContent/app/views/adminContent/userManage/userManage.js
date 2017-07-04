var userMApp = angular.module("userMApp",[]);
userMApp.controller("userMCtrl",["$state","$scope","$rootScope","$stateParams","UserService",function($state,$scope,$rootScope,$stateParams,UserService) {
	$scope.adminId = $stateParams.adminId;
	$scope.users = [];
	$scope.currentPage = 1;
	$scope.pageSize = 12;
	$scope.total = 0;
	$scope.hasDataMore = true;
	$scope.allPage = 0;
	/*指定页*/
	$scope.findUsersAndTotalByLimit = function() {
		$rootScope.alertRefresh();
		UserService.findUsersAndTotalByLimit($scope.currentPage,$scope.pageSize,sucesscb,errorcb);
		function sucesscb(data) {
			$scope.total = data.total;
			$scope.users = data.users;
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
	
	$scope.goDisplay = function(userId) {
		$state.go("adminMain.index.userDisplay",{adminId:$scope.adminId,userId:userId});
	}
	
	/*上一页*/
	$scope.prePage = function() {
		if($scope.currentPage != 1) {
			$scope.currentPage--;
			$scope.findUsersAndTotalByLimit();
		}
	}
	/*下一页*/
	$scope.nextPage = function() {
		if($scope.currentPage != $scope.allPage) {
			$scope.currentPage++;
			$scope.findUsersAndTotalByLimit();
		}
	}
	/*首页*/
	$scope.firstPage = function() {
		if($scope.currentPage != 1) {
			$scope.currentPage = 1;
			$scope.findUsersAndTotalByLimit();
		}
	}
	/*尾页*/
	$scope.lastPage = function() {
		if($scope.currentPage != $scope.allPage) {
			$scope.currentPage = $scope.allPage;
			$scope.findUsersAndTotalByLimit();
		}
	}
	/*跳转*/
	$scope.goPageIndex = 1;
	$scope.goPage = function() {
		if($scope.goPageIndex < 0 || $scope.goPageIndex === $scope.currentPage||$scope.goPageIndex > $scope.totalPage){
		}
		else{
			$scope.currentPage = $scope.goPageIndex;
			$scope.findUsersAndTotalByLimit();
		}
	}
	
	$scope.findUsersAndTotalByLimit();
}])