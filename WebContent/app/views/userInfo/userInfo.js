var userInfoApp = angular.module("userInfoApp",[]);
userInfoApp.controller("userInfoCtrl",["$scope","$rootScope","$state","$stateParams",function($scope,$rootScope,$state,$stateParams) {
	$scope.go = function(state) {
		if(state == "changeInfo") {
			$state.go("userInfo.changeUserInfo");
		} else if(state == "changePassWord") {
			$state.go("userInfo.changePassWord");
		} else if(state == "changeComment") {
			$state.go("userInfo.changeComment");
		}
	}
}]);