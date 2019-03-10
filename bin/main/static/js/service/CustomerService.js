app.factory('CustomerService', CustomerService);

function CustomerService($resource) {
	/*var service = {};
	service.getAllCustomer = getAllCustomer;
	service.getUserById = getUserById;
	
	return service;
	
	function getAllCustomer() {
		return $http.get("/customer/all");
	}
	
	function getUserById(id) {
		return $http.get("/customer/" + id);
	}*/
	return $resource('/customer/:id');
}