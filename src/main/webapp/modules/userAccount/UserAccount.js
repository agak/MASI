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
                        $scope.userAccount.birthDate = new Date($scope.userAccount.birthDate);
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
                            $scope.education.startDate = new Date($scope.education.startDate);
                            $scope.education.endDate = new Date($scope.education.endDate);
                            $scope.job.startDate = new Date($scope.job.startDate);
                            $scope.job.endDate = new Date($scope.job.endDate);
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
            if (!angular.isObject($scope.curriculumVitae.skills)) {
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
$rootScope.correctlySavedCV=false;

        $scope.saveUserDetails = function () {
            DataFactory.editUser($scope.userAccount)
                    .success(function (data, status, headers, config) {
                    }).error(function (data, status, headers, config) {
                console.log(data);
            });

            if (!angular.isObject($scope.curriculumVitae)) {
                $scope.curriculumVitae = {};
            }
            if (!angular.isObject($scope.curriculumVitae.lifeEvents)) {
                $scope.curriculumVitae.lifeEvents = [];
            }

            $scope.education.type = "EDUKACJA";
            $scope.job.type = "PRACA";
            $scope.curriculumVitae.lifeEvents.push($scope.education);
            $scope.curriculumVitae.lifeEvents.push($scope.job);

            DataFactory.addCv($scope.curriculumVitae, $rootScope.loginAccount.name)
                    .success(function (data, status, headers, config) {
                        $rootScope.correctlySavedCV=true;
                    }).error(function (data, status, headers, config) {
                console.log(data);
            });
        };


        $scope.getLogUser();
        $scope.getAllSkills();
        $scope.getCv();

    }]);