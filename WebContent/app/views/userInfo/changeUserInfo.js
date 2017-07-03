var changeUserInfo = angular.module("changeUserInfo",[]);
changeUserInfo.controller("changeUserInfoCtrl",["$scope","$rootScope","$state","$stateParams","UserService",function($scope,$rootScope,$state,$stateParams,UserService) {
	$scope.user = {};
	
	//方法,根据传递过来的用户ID展示用户信息
	$scope.findUser = function() {
		UserService
	}
	
	//方法,用于更新用户信息
	$scope.submitInfo = function() {
		$scope.user.id = 1;
		$scope.user.password = "123456";
		$scope.user.age = 20;
		UserService.updateUser($scope.user, sucesscb, errorcb);
		
		function sucesscb(data) {
			alert(data.name);
			alert("更新成功");
		}
		
		function errorcb(error) {
			alert("更新失败");
		}
	}  
}]);