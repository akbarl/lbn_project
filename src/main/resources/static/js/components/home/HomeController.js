app.controller('HomeController', function($rootScope, $scope, $localStorage, AuthenticationService, UserService) {
	
	$rootScope.title = "DASHBOARD_PAGE";
	
	//$rootScope.username = $localStorage.currentUser.username;
	$scope.handleLogout = function() {
		AuthenticationService.processLogout();
	}
});