'use strict';

mainControllers.controller('JobOffersCtrl', ['$scope', '$rootScope', 'DataFactory', '$http', '$state',
    function ($scope, $rootScope, DataFactory, $http, $state) {
         //$rootScope.correctlyAddedJobOffer=false;

        $scope.getAllJobOffers = function () {
            DataFactory.getAllJobOffers()
                    .success(function (data, status, headers, config) {
                        $scope.jobOffersAll = data;
                console.dir(data);
                    }).error(function (data, status, headers, config) {
                console.log(data);
            });
        };

        $scope.addJobOffer = function () {
            console.dir($scope.jobOffer);
            DataFactory.addJobOffer($scope.jobOffer)
                    .success(function (data, status, headers, config) {
                        $rootScope.correctlyAddedJobOffer=true;
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
        $scope.jobOffer={};
        $scope.jobOffer.skills = [];

        $scope.skillSelect = function () {
            $scope.jobOffer.skills.push({
                idSkill: $scope.skillSelected,
                name: $scope.allSkills[$scope.skillSelected - 1].name,
                category: $scope.allSkills[$scope.skillSelected - 1].category
            } );
        };

        $scope.getAllJobOffers();
        $scope.getAllSkills();

    }]);