var changeHeaderImgApp = angular.module("changeHeaderImgApp",['ngImgCrop']);
changeHeaderImgApp.controller("changeHeaderImgCtrl",["$scope","$rootScope","$state","$stateParams","UserService",function($scope,$rootScope,$state,$stateParams,UserService) {
	$scope.init = function() {
		$scope.user = {};
		$scope.user.id = $stateParams.userId;
		$scope.myImage = "";
		$scope.myCroppedImage = "";
		
		UserService.findUserHeadImg($scope.user.id, sucesscb, errorcb);
		
		function sucesscb(data) {
			var b64 = data.replace('','data:image/png;base64,');
			$scope.myCroppedImage = b64;
		}
		
		function errorcb(error) {
			$rootScope.alertWarn("查询头像信息失败");
		}
	}

	var handleFileSelect=function(evt) {
		var file=evt.currentTarget.files[0];
		var reader = new FileReader();
		
		reader.onload = function (evt) {
			$scope.$apply(function($scope){
				$scope.myImage=evt.target.result;
			});
		};
		
		reader.readAsDataURL(file);
	};
	
	$scope.saveHeadImg = function() {
		UserService.updateHeadImg($scope.user.id, $scope.myCroppedImage, sucesscb, errorcb);
		
		function sucesscb(data) {
			alert("保存成功");
		}
		
		function errorcb(error) {
			$rootScope.alertWarn("保存失败");
		}
	}
	
	angular.element(document.querySelector('#fileInput')).on('change',handleFileSelect);
}]);