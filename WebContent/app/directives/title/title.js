var titleApp = angular.module("titleApp",[]);
titleApp.directive("title",function() {
	return{
		scope:{

		},
		restrict:'EMAC',
		controller:['$scope','$rootScope','$state','$stateParams','UserService',function($scope,$rootScope,$state,$stateParams,UserService){

			$scope.init = function() {
				$scope.show = false;
				$scope.userId = $stateParams.userId;
				/*加载用户信息*/

				UserService.findUserInfoTitleNeedById($scope.userId,sucesscb,errorcb);
				function sucesscb(data) {
					$scope.userInfo = data[""+ $scope.userId +""];
				}
				function errorcb(error) {
					$rootScope.alertWarn("查询错误");
				}

				/*加载用户头像*/
				UserService.findUserHeadImg($scope.userId, sucess, error);

				function sucess(data) {
					if(data != null) {
						var b64 = data.replace('','data:image/png;base64,');
						$scope.myCroppedImage = b64;
					} else {
						alert("获取头像信息失败");
					}
				}

				function error(error) {
					$scope.myCroppedImage = "";
				}
			}
			/*注册、登录跳转*/
			$scope.go = function(state) {
				$state.go(state);
			}
			/*主页*/
			$scope.goHome = function() {
				$state.go("main.index",{userId:$scope.userId});
			}


			/*退出*/
			$scope.exit = function() {
				$scope.userId = null;
			}

			/*跳转用户中心*/
			$scope.manageUserInfo = function() {
				$state.go("userInfo.changeUserInfo",{userId:$scope.userId});
			}
			
			$scope.showList = function() {
				$scope.show = !$scope.show;
			}

		}],
		templateUrl:'../app/directives/title/title.html'
	}
})