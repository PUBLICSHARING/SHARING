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
		UserService.judgeLoginUser($scope.user,sucesscb,errorcb);
		function sucesscb(data) {
			if(data == "false") {
				alert("用户密码错误，请检查")
			}
			else{
				alert("登陆成功");
			}
		}
		function errorcb(error) {
			alert("出错了");
		}
	}
}])