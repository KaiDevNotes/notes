var myApp = angular.module("myApp", []);

myApp.controller("mainController", function($scope, $http, $log){
    $http.get("http://api.geonames.org/citiesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&lang=de&username=demo")
        .success(function(response){
            $log.info(response);
            $scope.items = response;
        })
        .error(function(data, status){
            $log.error(status + " ---> " + data);
        });
});