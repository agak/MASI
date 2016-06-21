'use strict';

mainControllers.controller('JobOffersCtrl', ['$scope', '$rootScope', 'DataFactory', '$http', '$state', 
    function ($scope, $rootScope, DataFactory, $http, $state) {

        $scope.getAllJobOffers = function () {
            DataFactory.getAllJobOffers()
                    .success(function (data, status, headers, config) {
                        $scope.jobOffersAll = data;
                    }).error(function (data, status, headers, config) {
                console.log(data);
            });
        };
        
        $scope.addJobOffer = function () {
            console.log("cokolwiek test");
             console.dir($scope.jobOffer);
            DataFactory.addJobOffer($scope.jobOffer)
                    .success(function (data, status, headers, config) {
                        $state.go('main.jobOffers');
                    }).error(function (data, status, headers, config) {
                console.log(data);
            });
        };
        
                $scope.getAllSkills = function () {
            DataFactory.getAllSkills()
                    .success(function (data, status, headers, config) {
                        $scope.allSkills = data;
                    }).error(function (data, status, headers, config) {
                console.log(data);
            });
        };
        
                        $scope.skillSelected = function () {
        };

            $scope.getAllJobOffers();
            $scope.getAllSkills();

    }]);