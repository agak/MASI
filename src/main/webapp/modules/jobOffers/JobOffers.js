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

            $scope.getAllJobOffers();

    }]);