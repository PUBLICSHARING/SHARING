var messageCenterApp = angular.module("messageCenterApp",[]);
messageCenterApp.controller("messageCenterCtrl",["$scope","$rootScope","$state","$stateParams","DynamicsService","AccusationService","CommentService", "UserService","LikeService","DisLikeService","NotifycationService", function($scope,$rootScope,$state,$stateParams,DynamicsService,AccusationService,CommentService, UserService, LikeService, DisLikeService,NotifycationService) {
	$scope.userId = $stateParams.userId;
	
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
		$scope.like = {};
		$scope.disLike = {};
		$scope.comment = {};
		$scope.newestDynamics = [];
		$scope.loginUserId = window.localStorage.getItem("UID");
		DynamicsService.findNewestDynamics(success,error);
		function success(data){
			for(var pro in data){
				$scope.newestDynamics.push(data[pro]);
			}
		}

		function error(error){
			$rootScope.alertWarn("查询出错");
		}
		
		//查询有未读消息的用户动态
		NotifycationService.findUserNotifycationByLimit($scope.userId,1,2,suc1,ero1);
		function suc1(data){
			$scope.messages = data;
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

	$scope.openParentConment = function(){
		$("#ddd").slideUp(20);
		$("#ddd").slideDown(200);
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

	$scope.saveComment = function(outerIndex,dynamicId){
		//评论用户
		$scope.comment.fromUser = {id:window.localStorage.getItem("UID")};
		//被评动态
		$scope.comment.dynamic = {id:dynamicId};
		
		$scope.comment.content = $scope.newestDynamics[outerIndex].newContent;
		
		CommentService.addComment($scope.comment,success,error);

		function success(data){
			$state.go('main.index',{"userId":$stateParams.userId},{ reload:true});
		}

		function error(error){

		}
	}
	/*举报*/
	$scope.accusation = {};
	$scope.type = null;
	$scope.id = null;
	$scope.edId = null;
	$scope.clickAccusation = function(type,id,edId) {
		$scope.type = type;
		$scope.id = id;
		$scope.edId = edId;
		$('#accusationDialog').modal({backdrop:'static', keyboard: false});
		$('#accusationDialog').modal('show');
	}

	$scope.submmitAccusation = function(){
		$scope.accusation.accusationUser = {id:$scope.userId};
		if($scope.type==="dynamic"){
			$scope.accusation.accusationedDynamic = {id:$scope.id};
		}
		else if($scope.type==="user"){
			$scope.accusation.accusationedUser = {id:$scope.id};
		}
		else if($scope.type==="comment"){
			$scope.accusation.accusationedComment = {id:$scope.id};
		}
		$scope.addAccusation();
	}

	$scope.addAccusation = function() {
		AccusationService.addAccusation($scope.accusation,sucesscb,errorcb);
		function sucesscb(data) {
			$('#accusationDialog').modal('hide');
			$rootScope.alertDisappear("举报成功，我们会尽快处理！",1000);
			$scope.addNotifycation('accusation',$scope.edId,data.id);
			$scope.accusation = {};
			$scope.type = null;
			$scope.id = null;
			$scope.edId = null;
		}
		function errorcb(error) {
			$scope.accusation = {};
			$scope.type = null;
			$scope.id = null;
			$rootScope.alertWarn("举报失败！");
		}
	}
	
	/*赞操作*/
	$scope.clickLike = function(dynamic) {
		//首先判断用户有没有踩过这条动态,如果踩过删除踩的记录,然后执行增加赞的记录
		DisLikeService.findDisLikeByUserIdAndDynamicId($stateParams.userId, dynamic.id, sucessDis, errorDis);
		function sucessDis(data) {
			if(data == "false") {	//如果存在踩的记录,删除该条记录
				DisLikeService.deleteDisLikeByDynamicIdAndUserId(dynamic.id, $stateParams.userId, su, er);
				
				function su(data) {
					dynamic.dislikeNum--;
					
					DynamicsService.updateDynamic(dynamic,sucesscb, errorcb);

					function sucesscb(data) {
						
					}

					function errorcb(error) {

					}
				}
				
				function er(error) {
					
				}
			}
			
			LikeService.findLikeByUserIdAndDynamicId($stateParams.userId, dynamic.id, sucessCallBack, errorCallBack);

			function sucessCallBack(data) {
				if(data == "true") {
					$scope.like.dynamic = {id:dynamic.id};
					$scope.like.user = {id:$stateParams.userId};

					LikeService.addLike($scope.like,sucess,error);

					function sucess(data) {
						$scope.addNotifycation("like", dynamic.user.id, data);
						dynamic.likeNum++;
						//更新信息
						DynamicsService.updateDynamic(dynamic,sucesscb, errorcb);

						function sucesscb(data) {
							
						}

						function errorcb(error) {

						}
					}
					function error(error) {
						alert("点赞失败");
					}
				} else {
					alert("你已经为该条动态点过赞了");
				}
			}

			function errorCallBack(error) {

			}
		}
		
		function errorDis(error) {
			
		}
		
	}
	
	/*踩操作*/
	$scope.clickDisLike = function(dynamic) {
		//首先判断有无赞的记录,如果有,删除赞的记录,进行赞
		LikeService.findLikeByUserIdAndDynamicId($stateParams.userId, dynamic.id, sucesslike, errorlike);
		
		function sucesslike(data) {
			if(data == "false") { //存在赞的记录,删除这条记录
				LikeService.deleteLikeByDynamicIdAndUserId(dynamic.id, $stateParams.userId, suc, err);
				
				function suc(data) {
					dynamic.likeNum--;
					
					DynamicsService.updateDynamic(dynamic,sucesscb, errorcb);

					function sucesscb(data) {
						
					}

					function errorcb(error) {

					}
				}
				
				function err(error) {
					
				}				
			} 
			
			DisLikeService.findDisLikeByUserIdAndDynamicId($stateParams.userId, dynamic.id, sucessCallBack, errorCallBack);

			function sucessCallBack(data) {
				if(data == "true") {
					$scope.disLike.dynamic = {id:dynamic.id};
					$scope.disLike.user = {id:$stateParams.userId};

					DisLikeService.addDisLike($scope.disLike,sucess,error);

					function sucess(data) {
						dynamic.dislikeNum++;
						//更新信息
						DynamicsService.updateDynamic(dynamic,sucesscb, errorcb);

						function sucesscb(data) {
						}

						function errorcb(error) {

						}
					}
					function error(error) {
						alert("踩失败");
					}
				} else {
					alert("你已经踩过该条动态了")
				}
			}

			function errorCallBack(error) {

			}
		}
		
		function errorlike(error) {
			
		}
	}
	/*消息*/
	$scope.notifycation = {};
	$scope.addNotifycation = function(type,userId,aimId) {
		$scope.notifycation.noticeUser = {id:userId};
		if(type==='comment') {
			$scope.notifycation.noticeFromComment = {id:aimId};
		}
		else if(type==='accusation') {
			$scope.notifycation.noticeFromAccusation = {id:aimId};
		}
		else if(type==='like') {
			$scope.notifycation.noticeFromLike = {id:aimId};
		}
		else if(type==='dynamic') {
			$scope.notifycation.noticeFromDynamic = {id:aimId};
		}
		else{
			$rootScope.alertWarn("添加消息传入类型错误");
			return;
		}
		NotifycationService.addNotifycation($scope.notifycation,sucesscb,errorcb);
		function sucesscb(data) {
			
		}
		function errorcb(error) {
			$rootScope.alertWarn("添加消息失败！");
		}
	}
}])