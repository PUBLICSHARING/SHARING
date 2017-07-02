var changePassWordApp = angular.module("changePassWordApp",[]);
changePassWordApp.controller("changePassWordCtrl",["$scope","$rootScope","$state","$stateParams",function($scope,$rootScope,$state,$stateParams) {
	$scope.show = function() {
		alert("show");
	}
}]);