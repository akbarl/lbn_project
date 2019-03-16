app.controller('CustomerController', CustomerController);

function CustomerController($rootScope, $scope, $location, $localStorage, $http, $mdDialog, AuthenticationService, CustomerService) {
	
	$rootScope.title = "CUSTOMER_PAGE";
	  $scope.selected = [];
	  $scope.limitOptions = [5, 10, 15];
	  
	  $scope.options = {
	    rowSelection: true,
	    multiSelect: true,
	    autoSelect: true,
	    decapitate: false,
	    largeEditDialog: false,
	    boundaryLinks: false,
	    limitSelect: true,
	    pageSelect: true
	  };
	  
	  $scope.query = {
	    order: 'name',
	    limit: 5,
	    page: 1,
	    filter: ''
	  };
	
	$scope.getAllCustomer = function () {
		CustomerService.query($scope.query).$promise.then(function(response){
			$scope.customers = response;
		});
	}
	
	$scope.getAllCustomer();
	
	$scope.addCustomer = function () {
		if($scope.customer.form.$valid) {
			console.log("add customer");
			CustomerService.save($scope.customer, success);
		}
	};
	
	function success(customer) {
		$mdDialog.hide(customer);
	}
	
	$scope.refresh = function() {
		$scope.getAllCustomer();
	}
	
	$scope.cancel = $mdDialog.cancel;
	
	$scope.addItem = function (event) {
	    $mdDialog.show({
	      clickOutsideToClose: true,
	      controller: 'CustomerController',
	      controllerAs: 'ctrl',
	      focusOnOpen: false,
	      targetEvent: event,
	      templateUrl: "template/customer/add.view.html"
	    }).then($scope.getAllCustomer);
	};
	
	$scope.delete = function(event) {
		console.log($scope.selected);
		//console.log($scope.selected[0])
		//CustomerService.remove({id: $scope.selected[0].id}).$promise.then(function);
		$scope.selected.forEach(deleteCustomer);
	}
	function deleteCustomer(customer, index) {
		CustomerService.remove({id: $scope.selected[index].id}).$promise.then(function(response) {
			$scope.selected.splice(0, 1);
		}).then($scope.getAllCustomer);
	}
}