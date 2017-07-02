var mainApp = angular.module("mainApp",[]);
mainApp.controller("mainCtrl",["$state","$scope","$rootScope","$stateParams",function($state,$scope,$rootScope,$stateParams) {
	$scope.adminId = $stateParams.adminId;
}])