var alApp = angular.module("adminLoginApp",[]);
alApp.controller("alCtrl",["$state","$scope","$rootScope","$stateParams","$timeout","AdminService",function($state,$scope,$rootScope,$stateParams,$timeout,AdminService) {
	$scope.admin = {};
	
	$scope.judgeAdmin = function() {
		$rootScope.alertRefresh();
		AdminService.judgeAdmin($scope.admin,sucesscb,errorcb);
		function sucesscb(data) {
			if(data == "null") {
				$rootScope.hideRefresh();
				$rootScope.alertWarn("用户或密码错误，请检查");
			}
			else{
				$rootScope.hideRefresh();
				$state.go("adminMain.index.main",{adminId:data});
			}
		}
		function errorcb(error) {
			$rootScope.hideRefresh();
			$rootScope.alertWarn("登陆失败");
		}
	}
	
	$('.carousel').carousel({
		interval: 2000
	});
}])