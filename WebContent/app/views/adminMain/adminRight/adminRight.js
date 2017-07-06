var adminRightApp = angular.module("adminRightApp",[]);
adminRightApp.controller("adminRightCtrl",["$state","$scope","$rootScope","$stateParams","AdminService",function($state,$scope,$rootScope,$stateParams,AdminService) {
	$scope.adminId = $stateParams.adminId;
	$scope.admin = {};
	$scope.goMain = function() {
		$state.go("adminMain.index.main",{adminId:$scope.adminId});
	}
	
	$scope.findAdminById = function() {
		AdminService.findAdminById($scope.adminId,sucesscb,errorcb);
		function sucesscb(data) {
			$scope.admin = data;
		}
		function errorcb(error) {
			$rootScope.alertWarn("查询管理员信息失败!");
		}
	}
	
	$scope.quit = function() {
		$state.go("adminLogin");
	}
	
	$scope.findAdminById();
}])