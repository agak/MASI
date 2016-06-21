'use strict';

mainControllers.controller('HeaderCtrl', ['$scope', '$rootScope', 'DataFactory', '$http', '$state', '$uibModal',
    function ($scope, $rootScope, DataFactory, $http, $state, $uibModal) {

        $scope.loginModal = function () {
            console.log("open ");  
		$uibModal.open({
			templateUrl : 'modules/header/login.html',
			backdrop : true,
			controller : function($scope,  $uibModalInstance) {
                    console.log("open modal");        
                            
                            
                    $scope.cancel = function () {
                        $uibModalInstance.dismiss('cancel');
                    };

                    $scope.performLogin = function () {
//                         var loginAccount = {
//                                login: $scope.account.login,
//                                password: $scope.account.password
//                            };
//                        
//                         console.dir(loginAccount);
                        DataFactory.login($scope.account)
                                .success(function (data, status, headers, config) {
                                    console.log(data);
                                }).error(function (data, status, headers, config) {
                            console.log(data);
                        });
                    };

			}
		});


        };

    }]);