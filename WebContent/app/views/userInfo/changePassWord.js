var changePassWordApp = angular.module("changePassWordApp",[]);
changePassWordApp.controller("changePassWordCtrl",["$scope","$rootScope","$state","$stateParams",function($scope,$rootScope,$state,$stateParams) {
	
	$scope.changePassWord = function() {
		if(!$scope.checkPassWord()) {
			alert("两次密码输入不一致");
			return;
		}
		
		//与后台通信,进行密码更改操作
		alert("修改成功");
	}
	
	//方法,用来检测两次输入的新密码是否相同
	$scope.checkPassWord = function() {
		if(($scope.newPassWord == $scope.againNewPassWord) && $scope.newPassWord != "" && $scope.againNewPassWord != "") {
			//相同,返回true
			return true;
		}
		
		return false;
	}
}]);