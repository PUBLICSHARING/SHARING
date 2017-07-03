var changePassWordApp = angular.module("changePassWordApp",[]);
changePassWordApp.controller("changePassWordCtrl",["$scope","$rootScope","$state","$stateParams","UserService",function($scope,$rootScope,$state,$stateParams,UserService) {
	
	$scope.changePassWord = function() {
		if(!$scope.checkPassWord()) {
			alert("两次密码输入不一致");
			return;
		}
		
		UserService.updatePassWord($scope.user.id, $scope.originalPassWord, $scope.newPassWord, sucesscb, errorcb);
		
		function sucesscb(data) {
			alert("修改成功");
		}
		
		function errorcb(error) {
			alert("修改失败,请联系维护人员");
		}
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