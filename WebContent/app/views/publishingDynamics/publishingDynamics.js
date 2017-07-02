angular.module("publishingDynamicsModule",[])
.controller("publishingDynamicsCtrl",["$scope","$rootScope","$state","$stateParams","PublishingDynamicsService",function($scope,$rootScope,$state,$stateParams,PublishingDynamicsService) {
	$scope.dynamic = {};
	
	$scope.publishDynamic = function(){
		if($scope.dynamic.content==""){
			alert("没有发布内容，无法发布动态");
			return;
		}
		else{
			var user = {};
			user.id = window.localStorage.getItem("UID");
			$scope.dynamic.user = user;
			PublishingDynamicsService.addDynamic($scope.dynamic,success,error);
			
			function success(data){
				alert("成功");
			}
			
			function error(error){
				alert("失败");
			}
		}
	}
}]);