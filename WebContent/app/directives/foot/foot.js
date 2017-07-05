var footApp = angular.module("footApp",[]);
footApp.directive("foot",function(){
	return{
		scope:{
			
		}, 
		restrict:"EAMC",
		controller:["$scope","$rootScope","$state","$stateParams",function($scope,$rootScope,$state,$stateParams) {
			$scope.userId = $stateParams.userId;
			$scope.question = {};
			$scope.goAbout = function() {
				$state.go("aboutUs",{userId:$scope.userId});
			}
			
			$scope.goQuestion = function() {
				$state.go("question",{userId:$scope.userId});
			}
			
			$scope.openDialog = function() {
				$('#questionDialog').modal({backdrop:'static', keyboard: false});
				$('#questionDialog').modal('show');
			}
			
			$scope.submmitQuestion = function() {
				$scope.question.user = {id:$scope.userId};
				QuestionService.addQuestion($scope.question,sucesscb,errorcb);
				function sucesscb(data) {
					$rootScope.alertDisappear("反馈成功，我们会尽快处理");
				}
				function errorcb(error) {
					$rootScope.alertWarn("反馈失败！");
				}
			}
			
		}],
		templateUrl:'../app/directives/foot/foot.html'
	}
})