var loginInApp = angular.module("loginInApp",[]);
loginInApp.controller("loginInCtrl",["$scope","$rootScope","$state","$stateParams","UserService",function($scope,$rootScope,$state,$stateParams,UserService) {
	$scope.user = {};
	
	$scope.goHome = function() {
		$state.go("main.index",{});
	}
	
	$scope.goRegister = function() {
		$state.go("register");
	}
	
	/*判断用户名与密码是否匹配*/
	$scope.judgeLoginUser = function() {
		$rootScope.alertRefresh();
		UserService.judgeLoginUser($scope.user,sucesscb,errorcb);
		function sucesscb(data) {
			if(data == "null") {
				$rootScope.hideRefresh();
				$rootScope.alertWarn("用户或密码错误，请检查");
			}
			else{
				$rootScope.hideRefresh();
				$state.go("main.index",{userId:data});
			}
		}
		function errorcb(error) {
			$rootScope.hideRefresh();
			$rootScope.alertWarn("登陆失败");
		}
	}
}])