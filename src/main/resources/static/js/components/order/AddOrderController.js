app.controller("AddOrderController", AddOrderController);

function AddOrderController($scope, UserService, CustomerService, OrderService, $mdDialog, $localStorage) {
	$scope.cancel = $mdDialog.cancel;
	$scope.paymentList = [{"percent" : 50}];
	
	$scope.query = {
		order: 'name',
		limit: 5,
		page: 1,
		filter: ''
	};
	
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
	
	$scope.addOrder = function() {
		if($scope.order.form.$valid && $scope.checkPaymentList($scope.paymentList)) {
			$scope.order.createdBy = $localStorage.currentUser.userId;
			$scope.order.status = 1;
			$scope.order.paymentList = $scope.paymentList;
			OrderService.save($scope.order, success)
		}
	}
	
	$scope.isAbleToCalculate = isAbleToCalculate;
	
	$scope.checkPaymentList = checkPaymentList;
	
	$scope.addPayment = function() {
		if($scope.paymentList.length < 3) {
			$scope.paymentList.push({"percent": 0});
		}
	}
	
	function success(customer) {
		$mdDialog.hide(customer);
	}
}