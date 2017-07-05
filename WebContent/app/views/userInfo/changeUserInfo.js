var changeUserInfo = angular.module("changeUserInfo",[]);
changeUserInfo.controller("changeUserInfoCtrl",["$scope","$rootScope","$state","$stateParams","UserService",function($scope,$rootScope,$state,$stateParams,UserService) {
	$scope.init = function() {
		$scope.user = {};
		
		$scope.user.id = $stateParams.userId;
		$scope.findUser();
	}
	
	//方法,根据传递过来的用户ID展示用户信息
	$scope.findUser = function() {
		UserService.findUserById($scope.user.id, sucesscb, errorcb);
		
		function sucesscb(data) {
			if(data != null && data != "null") {	//在查询用户信息时,如果不存在,则返回null
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
		//在这里应该注意一个逻辑判断,因为在登录时利用的是用户的用户名登录,所以用户名不允许重复.
		UserService.updateUser($scope.user, sucesscb, errorcb); //这里调用的updateUser只是更新有限的几个字段,如果发现某字段没有更新,需要去确定配置文件中的SQL语句
		
		function sucesscb(data) {
			if(data == "null") {
				$rootScope.alertDisappear("姓名已经存在,请修改", 1000);
			} else {
				$rootScope.alertDisappear("修改成功", 1000);
			}
		}
		
		function errorcb(error) {
			$rootScope.alertDisappear("修改信息失败", 1000);
		}
	}  
}]);