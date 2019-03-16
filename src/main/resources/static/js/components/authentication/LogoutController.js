app.controller('LogoutController', LogoutController);

function LogoutController($scope, $location, AuthenticationService) {
	AuthenticationService.processLogout();
	$location.path('/login');
}