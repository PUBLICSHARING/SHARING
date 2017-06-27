var footApp = angular.module("footApp",[]);
footApp.directive("foot",function(){
	return{
		scope:{
			
		},
		restrict:"EAMC",
		controller:["$scope","$rootScope","$state","$stateParams",function($scope,$rootScope,$state,$stateParams) {
			
		}],
		templateUrl:'../app/directives/foot/foot.html'
	}
})