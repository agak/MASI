'use strict';
angular.module('jobbox', ['jobbox.mainControllers', 'ngRoute', 'ui.router', 'dataservices', 'ui.bootstrap'])
        .config(['$stateProvider', '$urlRouterProvider', '$httpProvider',
            function ($stateProvider, $urlRouterProvider, $httpProvider) {
                $stateProvider.state('main', {
                    url: '/',
                    views: {
                        'main': {
                            controller: 'MainCtrl'
                        },
                         'header': {
                            templateUrl: 'modules/header/header.html',
                            controller: 'HeaderCtrl'
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
                            templateUrl: 'modules/jobOffers/addJobOffer.html',
                            controller: 'JobOffersCtrl'
                        },
                         'header': {
                            templateUrl: 'modules/header/header.html',
                            controller: 'HeaderCtrl'
                        }
                    }
                    }).state('main.addJobOffer', {
                    url: 'addJobOffer.html',
                    cache: false,
                    views: {
                        'menu': {
                            templateUrl: 'modules/menu/menu.html',
                            controller: 'MenuCtrl'
                        },
                        'list': {
                            templateUrl: 'modules/jobOffers/addJobOffer.html',
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
                $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
                $urlRouterProvider.otherwise('/');
               //  $urlRouterProvider.otherwise('/jobOffers.html');
            }])
        .controller('MainCtrl', ['$state',
            function ($state) {
            }]);

var mainControllers = angular.module('jobbox.mainControllers', []);