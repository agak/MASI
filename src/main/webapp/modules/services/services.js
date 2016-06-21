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
                    return $http.post(urlBase + '/jobOffers/add', jobOffer);
                };

                dataFactory.login = function (credentials) {
                    var headers = credentials ? {
                        authorization: "Basic "
                                + btoa(credentials.username + ":"
                                        + credentials.password)
                    } : {};

                    return $http.get(urlBase + '/account/login', {
                        headers: headers
                    });
                };

                return dataFactory;
            }]);
