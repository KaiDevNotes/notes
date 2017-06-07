var myApp = angular.module("myApp", []);

myApp.controller("mainController", function($scope, $log, $filter){
    $scope.message = "Hello world !";
    
    $log.info($scope);
    $log.warn("Warning");
    $log.error("Error");
    
});