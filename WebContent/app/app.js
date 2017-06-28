define(['angularAMD','angularUiRouter','angular','ngLoad','jquery','bootstrap','angularResource','dialog'],function (angularAMD,moment){

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
                                     '../app/directives/title/title.js']
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