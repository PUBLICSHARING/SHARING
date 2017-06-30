var adminLeftApp = angular.module("adminLeftApp",[]);
adminLeftApp.controller("adminLeftCtrl",["$state","$scope","$rootScope","$stateParams",function($state,$scope,$rootScope,$stateParams) {
	$scope.adminId = $stateParams.adminId;
}])