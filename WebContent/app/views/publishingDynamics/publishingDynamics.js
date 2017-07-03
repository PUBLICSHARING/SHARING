angular.module("publishingDynamicsModule",[])
.controller("publishingDynamicsCtrl",["$scope","$rootScope","$state","$stateParams","DynamicsService",function($scope,$rootScope,$state,$stateParams,PublishingDynamicsService) {
	$rootScope.dynamic = {};
	$scope.fileCount = 0;
	$scope.publishDynamic = function(){
		if($rootScope.dynamic.content==""){
			alert("没有发布内容，无法发布动态");
			return;
		}
		else{
			var user = {};
			user.id = 32;//window.localStorage.getItem("UID");
			$rootScope.dynamic.user = user;
			return user.id + "," + $rootScope.dynamic.content + "," + $scope.fileCount++;
		}
	}
	
	$("#input-images").fileinput({
		uploadUrl:"/GSHARING/base/servlet/fileUploadServlet/FileUploadServlet",
		allowedFileExtensions: ["jpg", "png", "gif","xlsx","doc"],
		resizePreference: 'height',
		maxFileCount: 10,
		language: 'zh',
		dropZoneEnabled:false,
		overwriteInitial: false,
		resizeImage: true,
		//uploadAsync:true,
		uploadExtraData: function(previewId, index) {
			return {"dynamic":$scope.publishDynamic()};
		}
	
	}).on('filepreupload', function(event, data, previewId, index) {     //上传中
		var form = data.form, files = data.files, extra = data.extra,
		response = data.response, reader = data.reader;
	}).on("fileuploaded", function (event, data, previewId, index) {    //一个文件上传成功的回掉函数
		$rootScope.alertWarn("上传成功！");
		$state.go('publishingDynamics',null,{
		    reload:true
		});
	}).on('fileerror', function(event, data, msg) {  //一个文件上传失败
		$rootScope.alertWarn("上传失败！");
	})
}]);