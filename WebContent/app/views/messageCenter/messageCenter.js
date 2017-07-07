var messageCenterApp = angular.module("messageCenterApp",[]);
messageCenterApp.controller("messageCenterCtrl",["$scope","$rootScope","$state","$stateParams","DynamicsService","AccusationService","CommentService", "UserService","LikeService","DisLikeService","NotifycationService", function($scope,$rootScope,$state,$stateParams,DynamicsService,AccusationService,CommentService, UserService, LikeService, DisLikeService,NotifycationService) {
	$scope.userId = $stateParams.userId;
	
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
		$scope.comment = {};
		$scope.newestDynamics = [];
/*		$scope.loginUserId = window.localStorage.getItem("UID");
		DynamicsService.findNewestDynamics(success,error);
		function success(data){
			for(var pro in data){
				$scope.newestDynamics.push(data[pro]);
			}
		}

		function error(error){
			$rootScope.alertWarn("查询出错");
		}*/
		
		//查询有未读消息的用户动态
		NotifycationService.findUserNotifycationByLimit($scope.userId,1,10,suc1,ero1);
		function suc1(data){
			$scope.messages = data;
/*			for(var i = 0; i < data.length; i ++){
				
			}*/
		}
		
		function ero1(error){
			alert("查询消息出错");
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

	$scope.openParentConment = function(index){
		$("#" + index).slideUp(20);
		$("#" + index).slideDown(200);
		$scope.content = "";
		$scope.comment.fatherComment = null;
		$scope.comment.toUser = null;
		$scope.contentType = "parent";
	}
	
	$scope.openChildConment = function(index,parentConmentId,toUser){
		$("#" + index).slideUp(20);
		$("#" + index).slideDown(200);
		//父亲评论
		$scope.comment.fatherComment = {id:parentConmentId};
		//被评论用户
		$scope.comment.toUser = toUser;
		
		$scope.contentType = "child";
		
		console.log($scope.comment.fatherComment.id + "#####" + $scope.comment.toUser.id);
	}

	//取消，至空
	$scope.cancelConment = function(index){
		$("#" + index).slideUp(200);
		$scope.content = "";
		$scope.comment.fatherComment = null;
		$scope.comment.toUser = null;
	}
	
	//点击进入页面的。
	$scope.myLand = function(userId){
		$state.go("main.publishingDynamics",{userId:userId});
	}

	$scope.saveComment = function(dynamicId,toUserId,content,fatherCommentId,Ownid,curentInputId){
		//评论用户
		$scope.comment.fromUser = {id:$scope.userId};
		
		$scope.comment.toUser = {id:toUserId};
		//被评动态
		$scope.comment.dynamic = {id:dynamicId};
		
		$scope.comment.content = content;
		
		//设置父评论
		if(fatherCommentId == null || fatherCommentId == undefined){
			$scope.comment.fatherComment = {id:Ownid};
		}
		else{
			$scope.comment.fatherComment = {id:fatherCommentId};
		}
		
		CommentService.addComment($scope.comment,success,error);

		function success(data){
			$rootScope.alertDisappear("回复成功",1000);
			$("#" + curentInputId).slideUp(200);
		}

		function error(error){

		}
	}

}])