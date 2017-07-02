var changeUserInfo = angular.module("changeUserInfo",[]);
changeUserInfo.controller("changeUserInfoCtrl",["$scope","$rootScope","$state","$stateParams","UserService",function($scope,$rootScope,$state,$stateParams,UserService) {
	$scope.user = {};
	
	//方法,用于更新用户信息
	$scope.submitInfo = function() {
		$scope.user.id = 1;
		UserService.updateUser($scope.user, successCB, errorCB);
		
		function successCB(data) {
			alert("更新成功");
		}
		
		function errorCB(error) {
			alert("更新失败");
		}
	}  
}]);