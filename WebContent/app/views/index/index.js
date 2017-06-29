var indexApp = angular.module("indexApp",[]);
indexApp.controller("indexCtrl",["$scope","$rootScope","$state","$stateParams",function($scope,$rootScope,$state,$stateParams) {
	$scope.dialog = function() {
		$rootScope.alertDisappear("注册成功",1000);
	}
	
	$scope.enterFavor = function($event) {
		$($event.target).attr("src", "/SHARING/app/image/index/likeTwo.png");
	}
}])