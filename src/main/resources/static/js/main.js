var app = angular.module('myAngularApp', []);
app.controller("myAngularController", function ($scope, $http) {

    $scope.arrayBooks = [];

    $http.get('http://localhost:8080/getAllBooksForAngular').success(function (data) {

        $scope.arrayBooks = data;
    });

});