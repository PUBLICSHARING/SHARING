var chooseApp = angular.module("chooseApp",[]);
chooseApp.controller("chooseCtrl",["$scope","$state","$rootScope","$stateParams",function($scope,$state,$rootScope,$stateParams) {
	$scope.userId = $stateParams.userId;
	
}])
