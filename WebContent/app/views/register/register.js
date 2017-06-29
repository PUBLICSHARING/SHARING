var registerApp = angular.module("registerApp",[]);
registerApp.controller("registerCtrl",["$scope","$rootScope","$state","$stateParams","UserService",function($scope,$rootScope,$state,$stateParams,UserService) {
	$scope.user = {};
	$scope.userId = null;
	$scope.checkTheUserName = function(){
		if($scope.user.name) {
			UserService.checkUserName($scope.user.name,sucesscb,errorcb);
			function sucesscb(data) {
				$scope.isTrue = data;
			}
			function errorcb(error) {
				$rootScope.alertWarn("查询失败！");
			}
		}
	}
	
	$scope.isChecked = false;
	$scope.select = function() {
		$scope.isChecked = !$scope.isChecked;
	}
	
	$scope.register = function() {
		UserService.addUser($scope.user,sucesscb,errorcb);
		function sucesscb(data) {
			$scope.userId = data;
			$state.go("loginIn");
			$rootScope.alertDisappear("注册成功",1000);
		}
		function errorcb(error) {
			$rootScope.alertWarn("注册失败");
		}
	}
	
	/*注册、登录跳转*/
	$scope.go = function(state) {
		$state.go(state);
	}
	/*主页*/
	$scope.goHome = function() {
		$state.go("main.index");
	}
	
}])

