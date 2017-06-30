var footApp = angular.module("footApp",[]);
footApp.directive("foot",function(){
	return{
		scope:{
			
		}, 
		restrict:"EAMC",
		controller:["$scope","$rootScope","$state","$stateParams",function($scope,$rootScope,$state,$stateParams) {
			$scope.userId = $stateParams.userId;
			
			$scope.goAbout = function() {
				$state.go("aboutUs",{userId:$scope.userId});
			}
			
			$scope.goQuestion = function() {
				$state.go("question",{userId:$scope.userId});
			}
		}],
		templateUrl:'../app/directives/foot/foot.html'
	}
})