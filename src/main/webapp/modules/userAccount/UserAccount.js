'use strict';

mainControllers.controller('UserAccountCtrl', ['$scope', '$rootScope', 'DataFactory', '$state',
    function ($scope, $rootScope, DataFactory, $state) {
        if (!$rootScope.loginAccount.role) {
            $state.go('main');
        }

        $scope.getLogUser = function () {
            DataFactory.getLogUser($rootScope.loginAccount.name, $rootScope.loginAccount.role)
                    .success(function (data, status, headers, config) {
                        $scope.userAccount = data;
                    }).error(function (data, status, headers, config) {
                console.log(data);
            });
        };

        $scope.getCv = function () {
            DataFactory.getCv($rootScope.loginAccount.name)
                    .success(function (data, status, headers, config) {
                        $scope.curriculumVitae = data;
                        if (angular.isObject(data.lifeEvents)) {
                            $scope.education = data.lifeEvents[0];
                            $scope.job = data.lifeEvents[1];
                        }
                    }).error(function (data, status, headers, config) {
                console.log(data);
            });
        };

        $scope.getAllSkills = function () {
            DataFactory.getAllSkills()
                    .success(function (data, status, headers, config) {
                        $scope.allSkills = data;
                    }).error(function (data, status, headers, config) {
                console.log(data);
            });
        };

      //  $scope.curriculumVitae = {};
       // $scope.curriculumVitae.skills = [];

        $scope.skillSelect = function () {
            if (!angular.isObject( $scope.curriculumVitae.skills)) {
                   $scope.curriculumVitae = {};
        $scope.curriculumVitae.skills = [];
            }

            $scope.curriculumVitae.skills.push({
                idSkill: $scope.skillSelected,
                name: $scope.allSkills[$scope.skillSelected - 1].name,
                category: $scope.allSkills[$scope.skillSelected - 1].category
            });
        };

        //$scope.curriculumVitae.lifeEvents = [];


        $scope.saveUserDetails = function () {
            DataFactory.editUser($scope.userAccount)
                    .success(function (data, status, headers, config) {
                        console.log(data);
                    }).error(function (data, status, headers, config) {
                console.log(data);
            });

            if (!angular.isObject( $scope.curriculumVitae.lifeEvents)) {
$scope.curriculumVitae.lifeEvents = [];
            }

            $scope.education.type = "EDUKACJA";
            $scope.job.type = "PRACA";
            $scope.curriculumVitae.lifeEvents.push($scope.education);
            $scope.curriculumVitae.lifeEvents.push($scope.job);

            DataFactory.addCv($scope.curriculumVitae, $rootScope.loginAccount.name)
                    .success(function (data, status, headers, config) {
                        console.log(data);
                    }).error(function (data, status, headers, config) {
                console.log(data);
            });
        };


        $scope.getLogUser();
        $scope.getAllSkills();
        $scope.getCv();

    }]);