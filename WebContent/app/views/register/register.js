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
				alert("出错了");
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
		}
		function errorcb(error) {
			alert("出错了");
		}
	}
	
}])