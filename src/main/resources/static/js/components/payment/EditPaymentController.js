app.controller("EditPaymentController", EditPaymentController);

function EditPaymentController($rootScope, $scope, orderId, OrderService, CustomerService, UserService, PaymentService, $mdDialog) {
	$scope.query = {
		order: 'orderId',
		limit: 5,
		page: 1,
		filter: orderId
	};
	
	$scope.paymentStatus = [{
		name: "Đã thanh toán",
		value: true
	}, {
		name: "Chưa thanh toán",
		value: false
	}];
	
	PaymentService.query($scope.query).$promise.then(function(response) {
		console.log(response);
		$scope.paymentList = response;
	});
}