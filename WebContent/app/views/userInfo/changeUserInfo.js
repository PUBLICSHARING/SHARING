var changeUserInfo = angular.module("changeUserInfo",[]);
changeUserInfo.controller("changeUserInfoCtrl",["$scope","$rootScope","$state","$stateParams","UserService",function($scope,$rootScope,$state,$stateParams,UserService) {
	$scope.init = function() {
		$scope.user = {};
		$scope.findUser();
	}
	
	//方法,根据传递过来的用户ID展示用户信息
	$scope.findUser = function() {
		$scope.user.id = 26;
		UserService.findUserById($scope.user.id, sucesscb, errorcb);
		
		function sucesscb(data) {
			$scope.user = data;
		}
		
		function errorcb(error) {
			
		}
	}
	
	//方法,用于更新用户信息
	$scope.submitInfo = function() {
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