var loginInApp = angular.module("loginInApp",[]);
loginInApp.controller("loginInCtrl",["$scope","$rootScope","$state","$stateParams","UserService",function($scope,$rootScope,$state,$stateParams,UserService) {
	$scope.user = {};
	
	$scope.goHome = function() {
		$state.go("main.index",{});
	}
	
	$scope.goRegister = function() {
		$state.go("register");
	}
	
	$scope.judgeLoginUser = function() {
		$rootScope.alertRefresh();
		UserService.judgeLoginUser($scope.user,sucesscb,errorcb);
		function sucesscb(data) {
			if(data == "false") {
				$rootScope.hideRefresh();
				$rootScope.alertWarn("用户密码错误，请检查");
			}
			else{
				$rootScope.hideRefresh();
				$rootScope.alertDisappear("登陆成功",1000);
			}
		}
		function errorcb(error) {
			$rootScope.alertWarn("登陆失败");
		}
	}
}])