'use strict';
angular.module('jobbox', ['jobbox.mainControllers', 'ngRoute', 'ui.router', 'dataservices'])
        .config(['$stateProvider', '$urlRouterProvider', '$httpProvider',
            function ($stateProvider, $urlRouterProvider, $httpProvider) {
                $stateProvider.state('main', {
                    url: '/',
                    views: {
                        'main': {
                            controller: 'MainCtrl'
                        }
                    }
                }).state('main.jobOffers', {
                    url: 'jobOffers.html',
                    cache: false,
                    views: {
                        'menu': {
                            templateUrl: 'modules/menu/menu.html',
                            controller: 'MenuCtrl'
                        },
                        'list': {
                            templateUrl: 'modules/jobOffers/jobOffers.html',
                            controller: 'JobOffersCtrl'
                        }
                    }
                }).state('main.userAccount', {
                    url: 'userAccount.html',
                    cache: false,
                    views: {
                        'menu': {
                            templateUrl: 'modules/menu/menu.html',
                            controller: 'MenuCtrl'
                        },
                        'list': {
                            templateUrl: 'modules/userAccount/userAccount.html',
                            controller: 'UserAccountCtrl'
                        }
                    }
                });
//$httpProvider.defaults.useXDomain = true;
//$httpProvider.defaults.withCredentials = true;
//delete $httpProvider.defaults.headers.common["X-Requested-With"];
//$httpProvider.defaults.headers.common["Accept"] = "application/json";
//$httpProvider.defaults.headers.common["Content-Type"] = "application/json";
                $urlRouterProvider.otherwise('/jobOffers.html');
            }])
        .controller('MainCtrl', ['$state',
            function ($state) {
            }]);

var mainControllers = angular.module('jobbox.mainControllers', []);