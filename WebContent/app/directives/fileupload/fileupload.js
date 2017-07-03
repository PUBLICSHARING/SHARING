var listfileuploadApp = angular.module("listfileuploadApp",[]);

listfileuploadApp.directive("listfileupload",["$rootScope",function($rootScope){
	return{
		restrict:"AEMC",
		replace:true,
		template:"<input id='input-images' alt = 'd' type='file' multiple class='file-loading' accept='/*'>",
		controller:function(){
			var obj = $rootScope.dynamic;
			$("#input-images").fileinput({
				uploadUrl:"/GSHARING/base/servlet/fileUploadServlet/FileUploadServlet?dynamic=" + JSON.stringify($rootScope.dynamic),
			    allowedFileExtensions: ["jpg", "png", "gif","xlsx","doc"],
			    resizePreference: 'height',
			    maxFileCount: 10,
			    language: 'zh',
			    overwriteInitial: false,
			    resizeImage: true,
			    uploadAsync:false
			}).on('filepreupload', function(event, data, previewId, index) {     //上传中
	            var form = data.form, files = data.files, extra = data.extra,
	            response = data.response, reader = data.reader;
	        }).on("fileuploaded", function (event, data, previewId, index) {    //一个文件上传成功的回掉函数
	        	$rootScope.alertWarn("上传成功！");

	        }).on('fileerror', function(event, data, msg) {  //一个文件上传失败
	        	$rootScope.alertWarn("上传失败！");
	        })
		}
	}
}]);