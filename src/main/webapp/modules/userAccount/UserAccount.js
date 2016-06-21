'use strict';

mainControllers.controller('UserAccountCtrl', ['$scope', '$rootScope', 'DataFactory', 
    function ($scope, $rootScope, DataFactory) {
        if (!$rootScope.loginAccount.role) {
            $state.go('main');
        }


    }]);