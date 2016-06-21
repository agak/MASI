'use strict';

mainControllers.controller('HeaderCtrl', ['$scope', '$rootScope', 'DataFactory', '$http', '$state', '$uibModal',
    function ($scope, $rootScope, DataFactory, $http, $state, $uibModal) {

        $scope.loginAccount = {};
        $scope.loginAccount.state = false;




        $scope.loginModal = function () {
            console.log("open ");
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
                $scope.loginAccount = result;
                console.log("res")
                console.dir(result)
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
                                console.dir(data)
                                $scope.loginAccount.state = true;
                                $scope.loginAccount.name = data.name;
                                $scope.loginAccount.role = data.authorities[0].authority;
                                $state.go('main.jobOffers');
                                $modalInstance.close($scope.loginAccount);
                            } else {
                                console.log("Error signing in");
                            }
                        }).error(function (data, status, headers, config) {
                    console.log(data);
                });
            };
        }




    }]);