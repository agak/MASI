'use strict';

angular.module('dataservices', [])
        .factory('DataFactory', ['$http', '$rootScope',
            function ($http, $rootScope) {

                var urlBase = 'http://localhost:8080';
                var dataFactory = {};

                dataFactory.getAllJobOffers = function () {
                    return $http.get(urlBase + '/jobOffers/getAll');
                };

                dataFactory.login = function (credentials) {
                    console.dir(credentials);
                    
       var headers = credentials ? {
                    authorization : "Basic "
                            + btoa(credentials.username + ":"
                                    + credentials.password)
                } : {};
		
		return $http.get(urlBase + '/account/login', {
			headers : headers
		}).success(function(data) {
                    console.log("po logowaniu")
                    console.dir(data)
                });
                    
                    
                  //  return $http.post(urlBase + '/account/login', account);
                };
              
                return dataFactory;
            }]);
