app.factory('UserService', UserService);

function UserService($http) {
	var service = {};
	service.getCurrentUser = getCurrentUser;
	service.getAllUser = getAllUser;
	return service;
	
	function getCurrentUser() {
		return $http.get("/user/current");
	}
	
	function getAllUser() {
		return $http.get("/user");
	}
}