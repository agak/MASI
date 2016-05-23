'use strict';

mainControllers.controller('MenuCtrl', ['$scope', '$rootScope', '$state',
function($scope, $rootScope, $state) {
    	$scope.changeState = function(state) {
		$state.go(state);
	};
    
    
    }]);