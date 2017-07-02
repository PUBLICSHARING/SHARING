var changeUserInfo = angular.module("changeUserInfo",[]);
changeUserInfo.controller("changeUserInfoCtrl",["$scope","$rootScope","$state","$stateParams",function($scope,$rootScope,$state,$stateParams) {

	$scope.submitInfo = function() {
		alert("提交成功!");
	}
}]);