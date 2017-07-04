var changeHeaderImgApp = angular.module("changeHeaderImgApp",['ngImgCrop']);
changeHeaderImgApp.controller("changeHeaderImgCtrl",["$scope","$rootScope","$state","$stateParams","UserService",function($scope,$rootScope,$state,$stateParams,UserService) {
	$scope.init = function() {
		$scope.user = {};
		$scope.user.id = $stateParams.userId;
		$scope.myImage = "";
		$scope.myCroppedImage = "";
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
	
	angular.element(document.querySelector('#fileInput')).on('change',handleFileSelect);
}]);