var changeUserInfo = angular.module("changeUserInfo",[]);
changeUserInfo.controller("changeUserInfoCtrl",["$scope","$rootScope","$state","$stateParams","UserService",function($scope,$rootScope,$state,$stateParams,UserService) {
	$scope.init = function() {
		$scope.user = {};
		
		$scope.user.id = $stateParams.userId;
		$scope.findUser();
	}
	
	//方法,根据传递过来的用户ID展示用户信息
	$scope.findUser = function() {
		$scope.user.id = 26;
		UserService.findUserById($scope.user.id, sucesscb, errorcb);
		
		function sucesscb(data) {
			if(data != null) {
				$scope.user = data;
			} else {
				$rootScope.alertDisappear("获取用户信息失败", 1000);
			}
			
		}
		
		function errorcb(error) {
			$rootScope.alertDisappear("获取用户信息失败", 1000);
		}
	}
	
	//方法,用于更新用户信息
	$scope.submitInfo = function() {
		UserService.updateUser($scope.user, sucesscb, errorcb);
		
		function sucesscb(data) {
			$rootScope.alertDisappear("修改成功", 1000);
		}
		
		function errorcb(error) {
			$rootScope.alertDisappear("修改信息失败", 1000);
		}
	}  
}]);