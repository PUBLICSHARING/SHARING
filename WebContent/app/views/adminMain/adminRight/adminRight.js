var adminRightApp = angular.module("adminRightApp",[]);
adminRightApp.controller("adminRightCtrl",["$state","$scope","$rootScope","$stateParams",function($state,$scope,$rootScope,$stateParams) {
	$scope.adminId = $stateParams.adminId;
	
	$scope.goMain = function() {
		$state.go("adminMain.index.main",{adminId:$scope.adminId});
	}
}])