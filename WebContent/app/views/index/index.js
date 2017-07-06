var indexApp = angular.module("indexApp",[]);
indexApp.controller("indexCtrl",["$scope","$rootScope","$state","$stateParams","DynamicsService","AccusationService","CommentService", "UserService","LikeService", function($scope,$rootScope,$state,$stateParams,DynamicsService,AccusationService,CommentService, UserService, LikeService) {
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
		$scope.comment = {};
		$scope.newestDynamics = [];
		DynamicsService.findNewestDynamics(success,error);
		function success(data){
			for(var pro in data){
				$scope.newestDynamics.push(data[pro]);
			}
		}

		function error(error){
			$rootScope.alertWarn("查询出错");
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

	$scope.openParentConment = function(index,id,type){
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
	$scope.clickAccusation = function(type,id) {
		$scope.type = type;
		$scope.id = id;
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
			$scope.accusation = {};
			$scope.type = null;
			$scope.id = null;
			$('#accusationDialog').modal('hide');
			$rootScope.alertDisappear("举报成功，我们会尽快处理！",1000);
		}
		function errorcb(error) {
			$scope.accusation = {};
			$scope.type = null;
			$scope.id = null;
			$rootScope.alertWarn("举报失败！");
		}
	}

	$scope.clickLike = function(dynamic) {
		LikeService.findLikeByUserIdAndDynamicId($stateParams.userId, dynamic.id, sucessCallBack, errorCallBack);

		function sucessCallBack(data) {
			if(data == "true") {
				$scope.like.dynamic = {id:dynamic.id};
				$scope.like.user = {id:$stateParams.userId};

				LikeService.addLike($scope.like,sucess,error);

				function sucess(data) {
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
				alert("你已经为该条动态点过赞了")
			}
		}

		function errorCallBack(error) {

		}
	}
}])