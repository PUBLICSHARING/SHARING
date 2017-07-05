define(['angularAMD','angularUiRouter','angular','ngLoad','jquery','bootstrap','angularResource','dialog','angularFileUpload','ngImgCrop'],function (angularAMD,moment){

    var app = angular.module('app',['ui.router','ngResource','dialogApp']);

    app.run(function($rootScope,$state,$stateParams){
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;
    });
    
    /*自适应高度*/
    app.directive('autoHeight',function ($window,$rootScope) {
        return {
            restrict : 'A',
            scope : {},
            link : function($scope, element, attrs) {
                $scope.winowHeight = $window.innerHeight;
                element.css('height',$scope.winowHeight + 'px');
              //添加浏览器窗口高度变化
				window.onresize = function() {
					$scope.winowHeight = $window.innerHeight;
	                element.css('height',$scope.winowHeight + 'px');
					$scope.$apply(function() {
					})
				}
            }
        };
    });

    /**
     * 路由
     */
    app.config(function($stateProvider,$rootScopeProvider,$urlRouterProvider,$controllerProvider){

        $urlRouterProvider.otherwise('/main/index/');
        $stateProvider
        .state('main',angularAMD.route({
            url : '/main',
            templateUrl:"../app/index.html"
        }))
        /*主页路由*/
        .state('main.index',angularAMD.route({
            url : '/index/:userId',
            views : {
                '' : angularAMD.route({
                    templateUrl : '../app/views/index/index.html',
                    controller : 'indexCtrl',
                    controllerUrl : ['../app/views/index/index.js',
                                     '../app/directives/title/title.js',
                                     '../app/service/user/userService.js',
                                     '../app/directives/foot/foot.js',
                                     '../app/service/question/questionService.js',
                                     '../app/service/dynamic/dynamicsService.js',
                                     '../app/service/accusation/accusationService.js']
                })
            }
        }))
        
        /*登录*/
        .state('loginIn',angularAMD.route({
            url : '/loginIn',
            views : {
                '' : angularAMD.route({
                    templateUrl : '../app/views/loginIn/loginIn.html',
                    controller : 'loginInCtrl',
                    controllerUrl : ['../app/views/loginIn/loginIn.js',
                                     '../app/directives/foot/foot.js',
                                     '../app/service/user/userService.js',
                                     '../app/service/question/questionService.js']
                })
            }
        }))
        
        /*注册*/
        .state('register',angularAMD.route({
            url : '/register',
            views : {
                '' : angularAMD.route({
                    templateUrl : '../app/views/register/register.html',
                    controller : 'registerCtrl',
                    controllerUrl : ['../app/views/register/register.js',
                                     '../app/directives/foot/foot.js',
                                     '../app/service/user/userService.js',
                                     '../app/service/question/questionService.js']
                })
            }
        }))
        /*选择兴趣*/
        .state('chooseInterests',angularAMD.route({
            url : '/chooseInterests/:userId',
            views : {
                '' : angularAMD.route({
                    templateUrl : '../app/views/chooseInterests/chooseInterests.html',
                    controller : 'chooseCtrl',
                    controllerUrl : ['../app/views/chooseInterests/chooseInterests.js',
                                     '../app/directives/foot/foot.js',
                                     '../app/service/user/userService.js',
                                     '../app/service/question/questionService.js']
                })
            }
        }))
        /*关于我们*/
        .state('aboutUs',angularAMD.route({
            url : '/aboutUs/:userId',
            views : {
                '' : angularAMD.route({
                    templateUrl : '../app/views/aboutUs/aboutUs.html',
                    controller : 'aboutCtrl',
                    controllerUrl : ['../app/views/aboutUs/aboutUs.js',
                                     '../app/directives/foot/foot.js',
                                     '../app/service/user/userService.js',
                                     '../app/service/question/questionService.js']
                })
            }
        }))
        /*问题反馈*/
        .state('question',angularAMD.route({
            url : '/question/:userId',
            views : {
                '' : angularAMD.route({
                    templateUrl : '../app/views/question/question.html',
                    controller : 'questionCtrl',
                    controllerUrl : ['../app/views/question/question.js',
                                     '../app/directives/foot/foot.js',
                                     '../app/service/user/userService.js',
                                     '../app/service/question/questionService.js']
                })
            }
        }))
        /*管理员登录*/
        .state('adminLogin',angularAMD.route({
            url : '/adminLogin',
            views : {
                '' : angularAMD.route({
                    templateUrl : '../app/views/adminLogin/adminLogin.html',
                    controller : 'alCtrl',
                    controllerUrl : ['../app/views/adminLogin/adminLogin.js',
                                     '../app/service/admin/adminService.js']
                })
            }
        }))
        /*管理-入口*/
        .state('adminMain',angularAMD.route({
            url : '/adminMain',
            templateUrl:'../app/views/adminMain/adminMain.html'
        }))
        /*管理=左块、右块的加载*/
        .state('adminMain.index',angularAMD.route({
            url : '/index/:adminId',
            views : {
                'left@adminMain' : angularAMD.route({
                    templateUrl : '../app/views/adminMain/adminLeft/adminLeft.html',
                    controller : 'adminLeftCtrl',
                    controllerUrl : ['../app/views/adminMain/adminLeft/adminLeft.js',
                                     '../app/service/question/questionService.js']
                }),
                'right@adminMain' : angularAMD.route({
                    templateUrl : '../app/views/adminMain/adminRight/adminRight.html',
                    controller : 'adminRightCtrl',
                    controllerUrl : ['../app/views/adminMain/adminRight/adminRight.js']
                })
            }
        }))
        /*管理-主页*/
        .state('adminMain.index.main',angularAMD.route({
            url : '/main/:adminId',
            views : {
                '' : angularAMD.route({
                    templateUrl : '../app/views/adminContent/main/main.html',
                    controller : 'mainCtrl',
                    controllerUrl : ['../app/views/adminContent/main/main.js']
                })
            }
        }))
        /*管理-用户管理*/
        .state('adminMain.index.userManage',angularAMD.route({
            url : '/userManage/:adminId',
            views : {
                '' : angularAMD.route({
                    templateUrl : '../app/views/adminContent/userManage/userManage.html',
                    controller : 'userMCtrl',
                    controllerUrl : ['../app/views/adminContent/userManage/userManage.js',
                                     '../app/service/user/userService.js']
                })
            }
        }))
        /*管理-用户查看*/
        .state('adminMain.index.userDisplay',angularAMD.route({
            url : '/userDisplay/:adminId/:userId',
            views : {
                '' : angularAMD.route({
                    templateUrl : '../app/views/adminContent/userManage/userDisplay/userDisplay.html',
                    controller : 'userDCtrl',
                    controllerUrl : ['../app/views/adminContent/userManage/userDisplay/userDisplay.js',
                                     '../app/service/user/userService.js']
                })
            }
        }))
        /*管理-动态管理*/
        .state('adminMain.index.dynamicManage',angularAMD.route({
            url : '/dynamicManage/:adminId',
            views : {
                '' : angularAMD.route({
                    templateUrl : '../app/views/adminContent/dynamicManage/dynamicManage.html',
                    controller : 'dynamicMCtrl',
                    controllerUrl : ['../app/views/adminContent/dynamicManage/dynamicManage.js',
                                     '../app/service/dynamic/dynamicsService.js']
                })
            }
        }))
        /*管理-文件管理*/
        .state('adminMain.index.fileManage',angularAMD.route({
            url : '/fileManage/:adminId',
            views : {
                '' : angularAMD.route({
                    templateUrl : '../app/views/adminContent/fileManage/fileManage.html',
                    controller : 'fileMCtrl',
                    controllerUrl : ['../app/views/adminContent/fileManage/fileManage.js',
                                     '../app/service/file/sharingFileService.js']
                })
            }
        }))
        /*管理-反馈管理*/
        .state('adminMain.index.questionManage',angularAMD.route({
            url : '/questionManage/:adminId',
            views : {
                '' : angularAMD.route({
                    templateUrl : '../app/views/adminContent/questionManage/questionManage.html',
                    controller : 'questionMCtrl',
                    controllerUrl : ['../app/views/adminContent/questionManage/questionManage.js',
                                     '../app/service/question/questionService.js']
                })
            }
        }))
        /*管理-举报管理*/
        .state('adminMain.index.accusationManage',angularAMD.route({
            url : '/accusationManage/:adminId',
            views : {
                '' : angularAMD.route({
                    templateUrl : '../app/views/adminContent/accusationManage/accusationManage.html',
                    controller : 'accusationMCtrl',
                    controllerUrl : ['../app/views/adminContent/accusationManage/accusationManage.js',
                                     '../app/service/accusation/accusationService.js']
                })
            }
        }))
         .state('fileupload',angularAMD.route({
            url : '/fileupload',
            views : {
                '' : angularAMD.route({
                    templateUrl : '../app/directives/fileupload/fileupload.html',
                    controller : '',
                    controllerUrl : ['../lib/bootstrapfileinput/fileinput.js',
                                     '../app/directives/fileupload/fileupload.js']
                })
            }
        }))
        	//发布动态
		.state('publishingDynamics',angularAMD.route({
			url : '/publishingDynamics',
			views : {
				'' : angularAMD.route({
					templateUrl : '../app/views/publishingDynamics/publishingDynamics.html',
					controller : 'publishingDynamicsCtrl',
					controllerUrl : ['../lib/bootstrapfileinput/fileinput.js',
					                 '../app/directives/fileupload/fileupload.js',
					                 '../app/service/dynamic/dynamicsService.js',
					                 '../app/views/publishingDynamics/publishingDynamics.js']
				})
			}
		}))
		
		  /*用户中心*/
        .state('userInfo',angularAMD.route({
            url : '/userInfo/:userId',
            views : {
                '' : angularAMD.route({
                    templateUrl : '../app/views/userInfo/userInfo.html',
                    controller : 'userInfoCtrl',
                    controllerUrl : ['../app/views/userInfo/userInfo.js',
                                     '../app/directives/title/title.js',
                                     '../app/service/user/userService.js',
                                     '../lib/bootstrap/js/bootstrap.min.js',
                                     '../lib/bootstrapfileinput/fileinput.js',
                                     '../app/directives/fileupload/fileupload.js',
                                     '../app/service/user/userService.js']
                })
            }
        }))
        
        .state('userInfo.changeUserInfo',angularAMD.route({
            url : '/information/:userId',
            views : {
                '' : angularAMD.route({
                    templateUrl : '../app/views/userInfo/changeUserInfo.html',
                    controller : 'changeUserInfoCtrl',
                    controllerUrl : ['../app/views/userInfo/changeUserInfo.js',
                                     '../app/directives/title/title.js',
                                     '../lib/bootstrap/js/bootstrap.min.js',
                                     '../app/service/user/userService.js',
                                     '../lib/ng-img-crop/ng-img-crop.js']
                })
            }
        }))
        
         .state('userInfo.changePassWord',angularAMD.route({
            url : '/changePd/:userId',
            views : {
                '' : angularAMD.route({
                    templateUrl : '/SHARING/app/views/userInfo/changePassWord.html',
                    controller : 'changePassWordCtrl',
                    controllerUrl : ['../app/views/userInfo/changePassWord.js',
                                     '../app/directives/title/title.js',
                                     '../app/service/user/userService.js',
                                     '../lib/bootstrap/js/bootstrap.min.js',
                                     '../lib/bootstrapfileinput/fileinput.js',
                                     '../app/directives/fileupload/fileupload.js',
                                     '../app/service/user/userService.js']
                })
            }
        }))
        
        .state('userInfo.changeHeaderImg',angularAMD.route({
            url : '/headerImg/:userId',
            views : {
                '' : angularAMD.route({
                    templateUrl : '../app/views/userInfo/changeHeaderImg.html',
                    controller : 'changeHeaderImgCtrl',
                    controllerUrl : ['../app/views/userInfo/changeHeaderImg.js',
                                     '../app/directives/title/title.js',
                                     '../lib/bootstrap/js/bootstrap.min.js',
                                     '../app/service/user/userService.js',
                                     '../lib/ng-img-crop/ng-img-crop.js']
                })
            }
        }))
    });
    
    app.run(function($rootScope){
		$rootScope.getController = function(element,controllerName){
			return angular.element($("#"+element)[0]).controller(controllerName);
		}
		$rootScope.app= {};
		$rootScope.app.treeId = 1;
	});
    
    app.config(function($provide) {
	    $provide.decorator('$state', function($delegate, $stateParams) {
	        $delegate.forceReload = function() {
	            return $delegate.go($delegate.$current.name, $stateParams, {
	                reload: true,
	                inherit: false,
	                notify: true
	            });
	        };
	        return $delegate;
	    });
	});

    return angularAMD.bootstrap(app);
})