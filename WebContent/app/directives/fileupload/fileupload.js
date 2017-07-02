var listfileuploadApp = angular.module("listfileuploadApp",[]);

listfileuploadApp.directive("listfileupload",["$rootScope",function($rootScope){
	return{
		restrict:"AEMC",
		replace:true,
		template:"<input id='input-images' alt = 'd' type='file' multiple class='file-loading' accept='/*'>",
		controller:function(){
			$("#input-images").fileinput({
				uploadUrl:"/GSHARING/base/servlet/fileUploadServlet/FileUploadServlet",
			    allowedFileExtensions: ["jpg", "png", "gif","xlsx","doc"],
			    resizePreference: 'height',
			    maxFileCount: 10,
			    language: 'zh',
			    overwriteInitial: false,
			    resizeImage: true,
			}).on('filepreupload', function(event, data, previewId, index) {     //上传中
	            var form = data.form, files = data.files, extra = data.extra,
	            response = data.response, reader = data.reader;
	            console.log('文件正在上传');
	        }).on("fileuploaded", function (event, data, previewId, index) {    //一个文件上传成功的回掉函数
	        	$rootScope.alertWarn("上传成功！");

	        }).on('fileerror', function(event, data, msg) {  //一个文件上传失败
	        	$rootScope.alertWarn("上传失败！");
	            console.log('文件上传失败！'+data.id);
	        })
		}
	}
}]);