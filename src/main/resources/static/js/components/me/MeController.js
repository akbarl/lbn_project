app.controller('MeController', MeController);

function MeController($rootScope, $scope, $location, $localStorage, $http, AuthenticationService, UserService) {
	
	$rootScope.title = "PERSONAL_PAGE";
	getCurrentUser();
	function getCurrentUser() {
		UserService.getCurrentUser().then(function(response){
			console.log(response.data);
			var data = response.data;
			$scope.firstname = data.firstname;
			$scope.lastname = data.lastname;
			$scope.email = data.email;
			$scope.phone = data.phone;
			$scope.address = data.address;
		});
	}
}