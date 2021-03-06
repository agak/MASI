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
                    return $http.post(urlBase + '/jobOffers/add', jobOffer);
                };

                dataFactory.getAllSkills = function () {
                    return $http.get(urlBase + '/jobOffers/getAllSkills');
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

                dataFactory.getLogUser = function (login, role) {
                    return $http.get(urlBase + '/account/getLogUser?login=' + login + "&role=" + role);
                };

                dataFactory.addCv = function (curriculumVitae, login) {
                    return $http.post(urlBase + '/user/addCv?login=' + login, curriculumVitae);
                };

                dataFactory.editUser = function (userAccount) {
                    return $http.post(urlBase + '/user/editUser', userAccount);
                };

                dataFactory.getCv = function (login) {
                    return $http.get(urlBase + '/user/getCv?login=' + login);
                };

                dataFactory.newDocument = function (formData) {

                    return         $http.post('/user/uploadFile', formData, {
                        transformRequest: function (data, headersGetterFunction) {
                            return data;
                        },
                        headers: {'Content-Type': undefined}
                    }).success(function (data, status) {
                        // alert("Success ... " + status);
                    }).error(function (data, status) {
                        // alert("Error ... " + status);
                    });
                };

                dataFactory.jobApply = function (login, jobOfferId) {
                    return $http.get(urlBase + '/jobOffers/jobApply?login=' + login + "&jobOfferId=" + jobOfferId);
                };

                return dataFactory;
            }]);
