var changePassWordApp = angular.module("changePassWordApp",[]);
changePassWordApp.controller("changePassWordCtrl",["$scope","$rootScope","$state","$stateParams","UserService",function($scope,$rootScope,$state,$stateParams,UserService) {
	
	$scope.init = function() {
		$scope.userId = $stateParams.userId;
	}
	
	$scope.changePassWord = function() {
		if(!$scope.checkPassWord()) {
			$rootScope.alertWarn("两次密码输入不一致");
			return;
		}
		
		UserService.updatePassWord($scope.userId, $scope.originalPassWord, $scope.newPassWord, sucesscb, errorcb);
		
		function sucesscb(data) {
			if(data == "null" || data == null) {
				$rootScope.alertWarn("原始密码错误,请检查");
			} else {
				$rootScope.alertDisappear("修改成功", 1000);
				setTimeout($state.go("userInfo.changeUserInfo"), 1500);
			}
		}
		
		function errorcb(error) {
			$rootScope.alertWarn("修改失败,请联系维护人员");
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