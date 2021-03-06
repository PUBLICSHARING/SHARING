require.config({
    paths : {
        "angular" : "../lib/angular/angular.min",
        "angularAMD" : "../lib/angularAMD/angularAMD",
        "ngLoad" : "../lib/angularAMD/ngload.min",
        "angularUiRouter" : "../lib/uiRouter/0.2.15/angular-ui-router.min",
        "jquery" : "../lib/jquery/2.1.4/jquery-2.1.4.min",
        "bootstrap" : "../lib/bootstrap/js/bootstrap.min",
        "angularResource" : "../lib/angularResource/angular-resource",
        "dialog" : "../app/directives/dialog/dialog",
        "angularFileUpload":"../lib/angular-file-upload/2.2.0/angular-file-upload.min",
        "ngImgCrop" : "../lib/ng-img-crop/ng-img-crop",
    },
        
    shim : {
        "angular" : {
            experts : "angular",
        },
        "angularUiRouter" : {
            deps : ["angular"],
            experts : "angularUiRouter"
        },
        "angularAMD" : {
            deps : ["angular"],
            experts : "angularAMD"
        },
        "ngLoad" : {
            deps : ["angularAMD"],
            experts : "ngLoad"
        },
        "jquery" : {
        	experts : "jquery"
        },
        "bootstrap" : {
        	deps : ["jquery"],
        	experts : "bootstrap"
        },
        "angularResource" : {
        	deps : ["angular"],
        	experts : "angularResource"
        },
        "dialog" :{
        	deps: ["angular","jquery"],
        	experts : "dialog"
        },
        "angularFileUpload":{
        	deps:["angular"],
        	experts:"angularFileUpload"
        },
        "ngImgCrop":{
        	deps:["angular"],
        	experts:"ngImgCrop"
        }
    },
    deps : ['app']
});
