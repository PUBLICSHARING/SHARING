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
			if(data == "用户名或密码错误") {
				$rootScope.hideRefresh();
				$rootScope.alertWarn("用户或密码错误，请检查");
			} else if(data == "冻结") {
				$rootScope.hideRefresh();
				$rootScope.alertWarn("很抱歉,您的账号可能由于不当言论已被冻结,请联系管理员");
			}
			else{
				//暂时性利用缓存
				window.localStorage.setItem("UID", data);
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