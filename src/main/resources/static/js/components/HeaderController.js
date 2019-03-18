app.controller('HeaderController', function($rootScope, $scope, $localStorage, $mdSidenav, $log) {
	$scope.username = $localStorage.currentUser.username;
	
	$scope.toggleLeft = buildToggler('left');
	
	function buildToggler(navID) {
		return function() {
			$mdSidenav(navID)
			.toggle()
			.then(function () {
				$log.info("toggle " + navID + " is done");
			});
		};
	};
});