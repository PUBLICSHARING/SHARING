var titleApp = angular.module("titleApp",[]);
titleApp.directive("title",function() {
	return{
		scope:{
			
		},
		restrict:'EMAC',
		controller:['$scope','$rootScope','$state','$stateParams',function($scope,$rootScope,$state,$stateParams){
			$scope.userId = $stateParams.userId;
			/*注册、登录跳转*/
			$scope.go = function(state) {
				$state.go(state);
			}
			/*主页*/
			$scope.goHome = function() {
				$state.go("main.index",{userId:$scope.userId});
			}
		}],
		templateUrl:'../app/directives/title/title.html'
	}
})