var myApp = angular.module("myApp", []);

myApp.controller("mainController", function($scope, $log){
    var request = new XMLHttpRequest();
    
    request.onreadystatechange = function(){
        $scope.$apply(function(){
            if (request.readyState == 4 && request.status == 200){
                var responseText = JSON.parse(request.responseText);
                $scope.items = responseText;
                $log.info(responseText);
            }
        });
    };
    
    request.open("GET", "http://api.geonames.org/citiesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&lang=de&username=demo", true);
    request.send();
});