var loginInApp = angular.module("loginInApp",[]);
loginInApp.controller("loginInCtrl",["$scope","$rootScope","$state","$stateParams",function($scope,$rootScope,$state,$stateParams) {
	$scope.goHome = function() {
		$state.go("main.index",{});
	}
	
	$scope.goRegister = function() {
		$state.go("register");
	}
}])