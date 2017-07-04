var userInfoApp = angular.module("userInfoApp",[]);
userInfoApp.controller("userInfoCtrl",["$scope","$rootScope","$state","$stateParams",function($scope,$rootScope,$state,$stateParams) {
	
	$scope.init = function() {
		$scope.userId = $stateParams.userId;
		$state.go("userInfo.changeUserInfo",{userId:$scope.userId});
	}
	
	$scope.go = function(state) {
		if(state == "changeInfo") {
			$state.go("userInfo.changeUserInfo", {userId:$scope.userId});
		} else if(state == "changePassWord") {
			$state.go("userInfo.changePassWord", {userId:$scope.userId});
		} else if(state == "changeComment") {
			$state.go("userInfo.changeComment");
		}
	}
}]);