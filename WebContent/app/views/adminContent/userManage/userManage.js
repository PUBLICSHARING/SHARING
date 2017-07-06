var userMApp = angular.module("userMApp",[]);
userMApp.controller("userMCtrl",["$state","$scope","$rootScope","$stateParams","UserService",function($state,$scope,$rootScope,$stateParams,UserService) {
	$scope.adminId = $stateParams.adminId;
	$scope.users = [];
	$scope.currentPage = 1;
	$scope.pageSize = 12;
	$scope.total = 0;
	$scope.hasDataMore = true;
	$scope.allPage = 0;
	$scope.isSearch = false;
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
	
	$scope.goDisplay = function(userId) {
		$state.go("adminMain.index.userDisplay",{adminId:$scope.adminId,userId:userId});
	}
	
	/*上一页*/
	$scope.prePage = function() {
		if($scope.currentPage != 1) {
			$scope.currentPage--;
			if(!$scope.isSearch){
				$scope.findUsersAndTotalByLimit();
			}
			else{
				$scope.searchUsersAndTotalByLimit();
			}
		}
	}
	/*下一页*/
	$scope.nextPage = function() {
		if($scope.currentPage != $scope.allPage) {
			$scope.currentPage++;
			if(!$scope.isSearch){
				$scope.findUsersAndTotalByLimit();
			}
			else{
				$scope.searchUsersAndTotalByLimit();
			}
		}
	}
	/*首页*/
	$scope.firstPage = function() {
		if($scope.currentPage != 1) {
			$scope.currentPage = 1;
			if(!$scope.isSearch){
				$scope.findUsersAndTotalByLimit();
			}
			else{
				$scope.searchUsersAndTotalByLimit();
			}
		}
	}
	/*尾页*/
	$scope.lastPage = function() {
		if($scope.currentPage != $scope.allPage) {
			$scope.currentPage = $scope.allPage;
			if(!$scope.isSearch){
				$scope.findUsersAndTotalByLimit();
			}
			else{
				$scope.searchUsersAndTotalByLimit();
			}
		}
	}
	/*跳转*/
	$scope.goPageIndex = 1;
	$scope.goPage = function() {
		if($scope.goPageIndex < 0 || $scope.goPageIndex === $scope.currentPage||$scope.goPageIndex > $scope.totalPage){
		}
		else{
			$scope.currentPage = $scope.goPageIndex;
			if(!$scope.isSearch){
				$scope.findUsersAndTotalByLimit();
			}
			else{
				$scope.searchUsersAndTotalByLimit();
			}
		}
	}
	
	/*冻结用户*/
	$scope.stopUser = function(index,userId) {
		$rootScope.alertRefresh();
		UserService.stopUser(userId,sucesscb,errorcb);
		function sucesscb(data) {
			$scope.users[index].isStop='是';
			$rootScope.hideRefresh();
		}
		function errorcb(error) {
			$rootScope.hideRefresh();
			$rootScope.alertWarn("冻结失败！");
		}
	}
	
	/*解冻用户*/
	$scope.startUser = function(index,userId) {
		$rootScope.alertRefresh();
		UserService.startUser(userId,sucesscb,errorcb);
		function sucesscb(data) {
			$scope.users[index].isStop='否';
			$rootScope.hideRefresh();
		}
		function errorcb(error) {
			$rootScope.hideRefresh();
			$rootScope.alertWarn("解冻失败！");
		}
	}
	
	$scope.searchInfo = null;
	$scope.searchUser = function() {
		if($scope.searchInfo === null || $scope.searchInfo===""){
			$scope.findUsersAndTotalByLimit();
		}
		else{
			$scope.searchUsersAndTotalByLimit();
		}
	}
	
	$scope.searchUsersAndTotalByLimit = function() {
		if(!$scope.isSearch) {
			$scope.currentPage = 1;
			$scope.pageSize = 12;
			$scope.total = 0;
			$scope.hasDataMore = true;
			$scope.allPage = 0;
			$scope.isSearch = true;
		}
		UserService.searchUsersAndTotalByLimit($scope.searchInfo,$scope.currentPage,$scope.pageSize,sucesscb,errorcb);
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
			$rootScope.alertWarn("搜索失败！");
		}
	}
	
	$scope.findUsersAndTotalByLimit();
}])