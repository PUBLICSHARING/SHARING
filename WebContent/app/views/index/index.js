var indexApp = angular.module("indexApp",[]);
indexApp.controller("indexCtrl",["$scope","$rootScope","$state","$stateParams","DynamicsService",function($scope,$rootScope,$state,$stateParams,DynamicsService) {
	$scope.dialog = function() {
		$rootScope.alertDisappear("注册成功",1000);
	}
	
	$scope.enterFavor = function($event) {
		if($($event.target).context.currentSrc == "http://localhost:8080/SHARING/app/image/index/like.png") {
			$($event.target).attr("src", "/SHARING/app/image/index/likeTwo.png");
		} else if($($event.target).context.currentSrc == "http://localhost:8080/SHARING/app/image/index/likeTwo.png") {
			$($event.target).attr("src", "/SHARING/app/image/index/like.png");
		}
	}
	
	$scope.saveComment = function($event) {
		$scope.comment = $event.target.parentElement.firstElementChild.value;
		$rootScope.alertDisappear($scope.comment, 1000);
	}
	
	$scope.init = function(){
		$scope.newestDynamics = [];
		DynamicsService.findNewestDynamics(success,error);
		function success(data){
			for(var pro in data){
				$scope.newestDynamics.push(data[pro]);
			}
		}
		
		function error(error){
			
		}
	}
}])