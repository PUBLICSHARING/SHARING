var userMApp = angular.module("userMApp",[]);
userMApp.controller("userMCtrl",["$state","$scope","$rootScope","$stateParams",function($state,$scope,$rootScope,$stateParams) {
	$scope.adminId = $stateParams.adminId;
}])