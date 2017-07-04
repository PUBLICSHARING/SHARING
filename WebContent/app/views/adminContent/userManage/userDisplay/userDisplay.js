var userDApp = angular.module("userDApp",[]);
userDApp.controller("userDCtrl",["$state","$scope","$rootScope","$stateParams","UserService",function($state,$scope,$rootScope,$stateParams,UserService) {
	$scope.adminId = $stateParams.adminId;
	$scope.userId = $stateParams.userId;
	$scope.userInfo = {};
	/*指定页*/
	$scope.findUserInfoAndCountOfOthers = function() {
		$rootScope.alertRefresh();
		UserService.findUserInfoAndCountOfOthers($scope.userId,sucesscb,errorcb);
		function sucesscb(data) {
			$scope.userInfo = data;
			$rootScope.hideRefresh();
		}
		function errorcb(error) {
			$rootScope.hideRefresh();
			$rootScope.alertWarn("查询失败！");
		}
	}
	
	$scope.findUserInfoAndCountOfOthers();
}])