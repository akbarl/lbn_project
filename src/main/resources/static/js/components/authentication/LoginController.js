app.controller('LoginController', LoginController);

function LoginController($rootScope, $scope, $location, $localStorage, $http, AuthenticationService, UserService) {
	
	$rootScope.isLoginPage = true;
	console.log($scope.isLoginPage);
	$scope.handleLogin = function() {
		AuthenticationService.processLogin($scope.username, $scope.password, $scope.rememberMe, function(response) {
			if(response.success) {
				$localStorage.currentUser = { username: $scope.username, token: response.data.access_token };
				$http.defaults.headers.common.Authorization = 'Bearer ' + response.data.access_token;
				$rootScope.isLoginPage = false;
				UserService.getCurrentUser().then(function(response) {
					$localStorage.currentUser.userId = response.data.id;
					console.log($localStorage.currentUser.userId);
				});
				$location.path('/home');
			} else {
				//Print error message here
				$scope.error = true;
				$scope.message = response.message;
			}
		});
	}
}