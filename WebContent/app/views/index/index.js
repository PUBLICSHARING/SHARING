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
	
	
	$scope.formatDate = function(longTime){
		var date = new Date(longTime);
		var year = date.getFullYear();
	    var month = date.getMonth()+1;    //js从0开始取 
	    var date1 = date.getDate(); 
	    var hour = date.getHours(); 
	    var minutes = date.getMinutes(); 
	    var second = date.getSeconds();
	    
	    return "" + year + "年" + month + "月    " + hour + "时" + minutes + "分" + second + "秒";
	}
	
	$scope.conment = function(index){
		$("#" + index).slideDown(200);
	}
	
	$scope.cancelConment = function(index){
		$("#" + index).slideUp(200);
	}
}])