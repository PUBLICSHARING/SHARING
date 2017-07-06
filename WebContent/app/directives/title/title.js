var titleApp = angular.module("titleApp",[]);
titleApp.directive("title",function() {
	return{
		scope:{

		},
		restrict:'EMAC',
		controller:['$scope','$rootScope','$state','$stateParams','UserService','NotifycationService',function($scope,$rootScope,$state,$stateParams,UserService,NotifycationService){
			$scope.userId = $stateParams.userId;
			$scope.countOfNotifycationNotRead = null;
			
			$scope.findCountOfNotifycationNotRead = function() {
				NotifycationService.findCountOfNotifycationNotRead($scope.userId,sucesscb,errorcb);
				function sucesscb(data) {
					$scope.countOfNotifycationNotRead = data;
				}
				function errorcb(error) {
					$rootScope.alertWarn("查询消息个数失败!");
				}
			}
			$scope.init = function() {
				$scope.show = false;
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
						//把用户头像放入缓存，免得再次请求后台
						window.localStorage.setItem($scope.userId, b64);
					} else {
						alert("获取头像信息失败");
					}
				}

				function error(error) {
					$scope.myCroppedImage = "";
				}
				
				//查询用户未读消息个数
				NotifycationService.findCountOfNotifycationNotRead($scope.userId,suc1,ero1);
				function suc1(data){
					$scope.countOfNotifycationNotRead = data;
				}
				
				function ero1(error){
					$rootScope.alertWarn("查询未读消息出错！");
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
			
			$scope.myLand = function(){
				$state.go("main.publishingDynamics",{userId:$scope.userId});
			}


			/*退出*/
			$scope.exit = function() {
				$scope.userId = null;
				$state.go("loginIn");
			}

			/*跳转用户中心*/
			$scope.manageUserInfo = function() {
				$state.go("userInfo.changeUserInfo",{userId:$scope.userId});
			}
			
			$scope.showList = function() {
				$scope.show = !$scope.show;
			}
			
			$scope.findCountOfNotifycationNotRead();
			
			//进入消息中心
			$scope.messageCenter = function(){
				$state.go("main.messageCenter",{userId:$scope.userId});
			}

		}],
		templateUrl:'../app/directives/title/title.html'
	}
})