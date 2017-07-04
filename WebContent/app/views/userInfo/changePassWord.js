var changePassWordApp = angular.module("changePassWordApp",[]);
changePassWordApp.controller("changePassWordCtrl",["$scope","$rootScope","$state","$stateParams","UserService",function($scope,$rootScope,$state,$stateParams,UserService) {
	
	$scope.init = function() {
		$scope.userId = 26;
	}
	
	$scope.changePassWord = function() {
		if(!$scope.checkPassWord()) {
			$rootScope.alertDisappear("两次输入的新密码不一致", 1000);
			return;
		}
		
		UserService.updatePassWord($scope.userId, $scope.originalPassWord, $scope.newPassWord, sucesscb, errorcb);
		
		function sucesscb(data) {
			if(data == null) {
				$rootScope.alertDisappear("原始密码错误", 1000);
			} else {
				$rootScope.alertDisappear("修改成功", 1000);
				setTimeout($state.go("userInfo.changeUserInfo"), 1500);
			}
		}
		
		function errorcb(error) {
			alert("修改失败,请联系维护人员");
		}
	}
	
	//方法,用来检测两次输入的新密码是否相同
	$scope.checkPassWord = function() {
		if(($scope.newPassWord == $scope.againNewPassWord)) {
			//相同,返回true
			return true;
		}
		
		return false;
	}
}]);