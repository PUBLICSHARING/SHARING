var alApp = angular.module("adminLoginApp",[]);
alApp.controller("alCtrl",["$state","$scope","$rootScope","$stateParams","$timeout",function($state,$scope,$rootScope,$stateParams,$timeout) {
	$scope.admin = {};
	
	$scope.judgeAdmin = function() {
		$rootScope.alertRefresh();
		UserService.judgeAdmin($scope.admin,sucesscb,errorcb);
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
	
	$scope.index = 1;
	
	$scope.startImgChange = function() {
		$timeout(function() {
			if($scope.index!=3) {
				$scope.index++;
			}
			else{
				$scope.index=1;
			}
		},1000)
	}
	$scope.imgLeft = function() {
		if($scope.index!=1) {
			$scope.index--;
		}
		else{
			$scope.index=3;
		}
	}
	$scope.imgRight = function() {
		if($scope.index!=3) {
			$scope.index++;
		}
		else{
			$scope.index=1;
		}
	}
	$scope.goImg = function(count) {
		$scope.index=count;
	}
	$scope.startImgChange();
}])