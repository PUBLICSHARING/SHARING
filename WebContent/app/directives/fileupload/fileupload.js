var listfileuploadApp = angular.module("listfileuploadApp",[]);

listfileuploadApp.directive("listfileupload",function(){
	return{
		restrict:"AEMC",
		replace:true,
		template:"<input id='input-images' alt = 'd' type='file' multiple class='file-loading' accept='/*'>",
		controller:function(){
			$("#input-images").fileinput({
				uploadUrl:"/GSHARING/base/servlet/fileUploadServlet/FileUploadServlet",
			    allowedFileExtensions: ["jpg", "png", "gif"],
			    resizePreference: 'height',
			    maxFileCount: 10,
			    language: 'zh',
			    overwriteInitial: false,
			    resizeImage: true,
			}).on('filepreupload', function(event, data, previewId, index) {     //上传中
	            var form = data.form, files = data.files, extra = data.extra,
	            response = data.response, reader = data.reader;
	            console.log('文件正在上传');
	        }).on("fileuploaded", function (event, data, previewId, index) {    //一个文件上传成功
	        	alert("文件上传成功");
	        	//$("#dd").html("<img src= 'data:image/png;base64,"+ data.response + "' />");

	        }).on('fileerror', function(event, data, msg) {  //一个文件上传失败
	        	alert("文件上传失败");
	            console.log('文件上传失败！'+data.id);
	        })
		}
	}
});