var titleApp = angular.module("titleApp",[]);
titleApp.directive("title",function() {
	return{
		scope:{
			
		},
		restrict:'EMAC',
		controller:['$scope','$rootScope','$state','$stateParams','UserService',function($scope,$rootScope,$state,$stateParams,UserService){
			$scope.userId = $stateParams.userId;
			/*注册、登录跳转*/
			$scope.go = function(state) {
				$state.go(state);
			}
			/*主页*/
			$scope.goHome = function() {
				$state.go("main.index",{userId:$scope.userId});
			}
			
			/*加载用户信息*/
			$scope.findUserInfoTitleNeedById = function() {
				
				UserService.findUserInfoTitleNeedById($scope.userId,sucesscb,errorcb);
				function sucesscb(data) {
					 $scope.userInfo = data[""+ $scope.userId +""];
				}
				function errorcb(error) {
					$rootScope.alertWarn("查询错误");
				}
			}
			
			/*退出*/
			$scope.exit = function() {
				$scope.userId = null;
			}
			
			/*跳转用户中心*/
			$scope.manageUserInfo = function() {
				$state.go("userInfo",{userId:$scope.userId});
			}
			
			$scope.findUserInfoTitleNeedById();
		}],
		templateUrl:'../app/directives/title/title.html'
	}
})