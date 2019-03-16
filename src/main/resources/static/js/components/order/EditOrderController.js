app.controller("EditOrderController", EditOrderController);

function EditOrderController($rootScope, $scope, order, OrderService, CustomerService, UserService, $mdDialog) {
	$scope.order = order;
	$scope.helpers = $rootScope.helpers;
	$scope.cancel = $mdDialog.cancel;
	$scope.query = {
		order: 'name',
		limit: 5,
		page: 1,
		filter: ''
	};
	
	$scope.addPayment = function() {
		if($scope.order.paymentList.length < 3) {
			$scope.order.paymentList.push({"percent": 0});
		}
	}
	
	$scope.updateOrder = function() {
		if($scope.order.form.$valid && $scope.helpers.checkPaymentList($scope.order.paymentList)) {
			OrderService.save($scope.order, success)
		}
	}
	
	function success(customer) {
		$mdDialog.hide(customer);
	}
	
	$scope.getAllUser = function () {
		UserService.getAllUser().then(function(response) {
			console.log(response.data);
			$scope.assigneeList = response.data;
		});
	};
	
	$scope.getAllCustomer = function() {
		CustomerService.query($scope.query).$promise.then(function(response){
			$scope.customers = response;
			console.log($scope.customers)
		});
	};
}