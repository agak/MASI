'use strict';

mainControllers.controller('HeaderCtrl', ['$scope', '$rootScope', 'DataFactory', '$http', '$state', '$uibModal',
    function ($scope, $rootScope, DataFactory, $http, $state, $uibModal) {

        // $scope.loginAccount = {};
        // $scope.loginAccount.state = false;
        $rootScope.loginAccount = {};
        $rootScope.loginAccount.state = false;

        $scope.loginModal = function () {
            $uibModal.open({
                templateUrl: 'modules/header/login.html',
                //backdrop: true,
                controller: PopupCtrl,
                resolve: {
                    loginAccount: function () {
                        return $scope.loginAccount;
                    }
                }
            }).result.then(function (result) {
                //  $scope.loginAccount = result;
                $rootScope.loginAccount = result;
            });
        };


        var PopupCtrl = function ($scope, $modalInstance, $uibModalInstance, loginAccount) {
            $scope.loginAccount = loginAccount;

            $scope.cancel = function () {
                $uibModalInstance.dismiss('cancel');
            };

            $scope.performLogin = function () {
                DataFactory.login($scope.account)
                        .success(function (data, status, headers, config) {
                            if (angular.isObject(data)) {
                                $scope.loginAccount.state = true;
                                $scope.loginAccount.name = data.name;
                                $scope.loginAccount.role = data.authorities[0].authority;
                                $state.go('main.jobOffers');
                                $modalInstance.close($scope.loginAccount);
                            } else {
                                console.log("Error signing in");
                            }
                        }).error(function (data, status, headers, config) {
                    if (status === 401) {
                        $scope.loginAccount.msg = "Bląd logowania";
                    }
                });
            };
        }




    }]);