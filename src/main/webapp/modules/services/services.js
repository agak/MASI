'use strict';

angular.module('dataservices', [])
        .factory('DataFactory', ['$http', '$rootScope',
            function ($http, $rootScope) {

                var urlBase = 'http://localhost:8080';
                var dataFactory = {};

                dataFactory.getAllJobOffers = function () {
                    return $http.get(urlBase + '/jobOffers/getAll');
                };
                
                dataFactory.addJobOffer = function (jobOffer) {
                    console.log("serwis");
                    return $http.post(urlBase + '/jobOffers/add',jobOffer);
                };

              
                return dataFactory;
            }]);
