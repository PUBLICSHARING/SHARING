var adminLeftApp = angular.module("adminLeftApp",[]);
adminLeftApp.controller("adminLeftCtrl",["$state","$scope","$rootScope","$stateParams",function($state,$scope,$rootScope,$stateParams) {
	$scope.adminId = $stateParams.adminId;
	
	$scope.goAdminMain = function() {
		$state.go("adminMain.index.main",{adminId:$scope.adminId});
	}
	
	$scope.goUserManage = function() {
		$state.go("adminMain.index.userManage",{adminId : $scope.adminId});
	}
}])