var myApp = angular.module("myApp", ["ngRoute"]);

myApp.config(function($routeProvider){
    $routeProvider
    
        .when("/", {
            templateUrl : "pages/main.html",
            controller : "mainController"
        })
        
        .when("/second", {   
            templateUrl : "pages/second.html",
            controller : "secondController"
        })
        
        // you can use any URI parameter name instead of "uriParam" 
        .when("/second/:uriParam", {   
            templateUrl : "pages/second.html",
            controller : "secondController"
        })
        
        .when("/sum/:operand1/:operand2", {   
            templateUrl : "pages/operation-result.html",
            controller : "sumController"
        })
        
        .when("/mult/:operand1/:operand2", {   
            templateUrl : "pages/operation-result.html",
            controller : "multController"
        });
});

myApp.controller("mainController", function($scope){
    $scope.name = "mainController";
});

// To get params values from URI we should use $routeParams from "ngRoute" module
myApp.controller("secondController", function($scope, $routeParams){
    $scope.name = "secondController";
    $scope.uriParamValue = $routeParams.uriParam || 1; // if $routeParams.uriParam is undefined --> use default value = 1
});

myApp.controller("sumController", function($scope, $routeParams){
    $scope.getResult = function(){
        var op1 = parseInt($routeParams.operand1);
        var op2 = parseInt($routeParams.operand2);
        return op1 + op2;
    };
});

myApp.controller("multController", function($scope, $routeParams){
    $scope.getResult = function(){
        var op1 = parseInt($routeParams.operand1);
        var op2 = parseInt($routeParams.operand2);
        return op1 * op2;
    };
});