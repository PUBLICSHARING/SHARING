var dialogApp = angular.module("dialogApp",[]);
dialogApp.directive("myDialog",function() {
	return{
		scope:{
		},
		restrict:"AEMC",
		controller:['$scope','$rootScope','$state','$stateParams','$timeout',function($scope,$rootScope,$state,$stateParams,$timeout) {
			$scope.dialogSate = false;
			$scope.dialogContent = null;
			$rootScope.alertWarn = function(content) {
				$('#Dialog').modal({backdrop:'static', keyboard: false});
				$scope.dialogSate = true;
				$scope.dialogContent = content;
				$('#Dialog').modal('show');
			}
			$rootScope.hideWarn = function(){
				$scope.dialogSate = false;
				$scope.dialogContent = null;
				$('#Dialog').modal('hide');
			}
			$rootScope.alertRefresh = function() {
				$('#refreshDialog').modal({backdrop:'static', keyboard: false});
				$('#refreshDialog').modal('show');
			}
			$rootScope.hideRefresh = function(){
				$('#refreshDialog').modal('hide');
			}
			$rootScope.alertDisappear = function(content,time) {
				$('#disappearDialog').modal({backdrop:'static', keyboard: false});
				$scope.disappearContent = content;
				$('#disappearDialog').modal('show');
				$timeout(function() {
					$('#disappearDialog').modal('hide');
		    	},time);
			}
		}],
		templateUrl:function(){
			return "../app/directives/dialog/warnDialog.html";
		}
	}
})