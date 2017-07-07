angular.module("publishingDynamicsModule",[])
.controller("publishingDynamicsCtrl",["$scope","$rootScope","$state","$stateParams","DynamicsService","UserService",function($scope,$rootScope,$state,$stateParams,DynamicsService,UserService) {
	$rootScope.dynamic = {};
	$scope.fileCount = 0;
	$scope.userId = $stateParams.userId;
	
	
	$scope.publishDynamic = function(){
		if($rootScope.dynamic.content==""){
			alert("没有发布内容，无法发布动态");
			return;
		}
		else{
			var user = {};
			user.id = $scope.userId;
			$rootScope.dynamic.user = user;
			return user.id + "," + $rootScope.dynamic.content + "," + $scope.fileCount++;
		}
	}
	
	$("#input-images").fileinput({
		uploadUrl:"../base/servlet/fileUploadServlet/FileUploadServlet",
		allowedFileExtensions: ["jpg", "png", "gif","xlsx","doc"],
		resizePreference: 'height',
		maxFileCount: 10,
		language: 'zh',
		dropZoneEnabled:false,
		overwriteInitial: false,
		resizeImage: true,
		uploadAsync:true,
		uploadExtraData: function(previewId, index) {
			return {"dynamic":$scope.publishDynamic()};
		}
	
	}).on('filepreupload', function(event, data, previewId, index) {     //上传中
		var form = data.form, files = data.files, extra = data.extra,
		response = data.response, reader = data.reader;
	}).on("fileuploaded", function (event, data, previewId, index) {    //一个文件上传成功的回掉函数
		$rootScope.alertWarn("上传成功！");
		$state.go('main.publishingDynamics',{"userId":$scope.userId},{
		    reload:true
		});
	}).on('fileerror', function(event, data, msg) {  //一个文件上传失败
		$rootScope.alertWarn("上传失败！");
	})
	
	
	$scope.init=function(){
		$scope.like = {};
		$scope.comment = {};
		$scope.personalDynamics = [];
		//权限判断
		var currentUserid = window.localStorage.getItem("UID");
		if(currentUserid == $scope.userId){
			$scope.isIillegal = true;
		}
		else{
			$scope.isIillegal = false;
		}
		
		//查询用户个人信息
		UserService.findUserById($scope.userId,suc,ero);
		function suc(data){
			$scope.currentPageOwner = data;
		}
		
		function ero(error){
			$rootScope.alertWarn("查询用户信息出错！亲联系管理员");
		}
		
		//查询个人动态
		DynamicsService.findAllDynamicsByUserId($scope.userId,1,5,sucess,error);
		function sucess(data){
				$scope.personalDynamics = data.dynamics;
			
			$scope.dynamicNums = $scope.personalDynamics.length;
		}
		
		function error(error){
			$rootScope.alertWarn("查询出错");
		}
	}
	
	$scope.hanImgCode = function (imgCode){
		return "data:image/jpeg;base64,"+imgCode;
	}
	
	$scope.goHome = function(){
		$state.go("main.index",{userId:$scope.userId});
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
}]);