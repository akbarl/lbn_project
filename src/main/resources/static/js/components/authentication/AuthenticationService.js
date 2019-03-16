app.factory('AuthenticationService', AuthenticationService);

function AuthenticationService($localStorage, $http, $location, Oauth2Service) {
	var service = {};
	
	service.processLogin = processLogin;
	service.processLogout = processLogout;
	
	return service;
	
	function processLogin(username, password, rememberMe, callback) {
		Oauth2Service.getToken(username, password).then(function(data) {
			if (data.success !== undefined && !data.success) {
				response = { success: false, message: 'Username or password is incorrect' };
			} else {
				response = { success: true, data: data};
			}
			callback(response);
		});
	}
	
	function processLogout() {
		delete $localStorage.currentUser;
		$http.defaults.headers.common.Authorization = '';
		console.log('Logout successfully');
	}
}