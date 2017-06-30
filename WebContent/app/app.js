define(['angularAMD','angularUiRouter','angular','ngLoad','jquery','bootstrap','angularResource','dialog','angularFileUpload','ngImgCrop'],function (angularAMD,moment){

    var app = angular.module('app',['ui.router','ngResource','dialogApp']);

    app.run(function($rootScope,$state,$stateParams){
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;
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
                                     '../lib/bootstrapfileinput/fileinput.js',
                                     '../app/directives/fileupload/fileupload.js',
                                     '../app/directives/foot/foot.js']
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
                                     '../app/service/user/userService.js']
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
                                     '../app/service/user/userService.js']
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
                                     '../app/service/user/userService.js']
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
                                     '../app/service/user/userService.js']
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
                                     '../app/service/user/userService.js']
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
        /*管理系统*/
        .state('adminMain',angularAMD.route({
            url : '/adminMain',
            templateUrl:'../app/views/adminMain/adminMain.html'
        }))
        /*管理员登录*/
        .state('adminMain.index',angularAMD.route({
            url : '/index/:adminId',
            views : {
                'left@adminMain' : angularAMD.route({
                    templateUrl : '../app/views/adminMain/adminLeft/adminLeft.html',
                    controller : 'adminLeftCtrl',
                    controllerUrl : ['../app/views/adminMain/adminLeft/adminLeft.js']
                }),
                'right@adminMain' : angularAMD.route({
                    templateUrl : '../app/views/adminMain/adminRight/adminRight.html',
                    controller : 'adminRightCtrl',
                    controllerUrl : ['../app/views/adminMain/adminRight/adminRight.js']
                })
            }
        }))
         .state('fileupload',angularAMD.route({
            url : '/fileupload',
            views : {
                '' : angularAMD.route({
                    templateUrl : '/GSHARING/app/directives/fileupload/fileupload.html',
                    controller : '',
                    controllerUrl : ['/GSHARING/lib/bootstrapfileinput/fileinput.js',
                                     '/GSHARING/app/directives/fileupload/fileupload.js']
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