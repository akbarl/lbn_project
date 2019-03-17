app.controller("DetailOrderController", DetailOrderController);

function DetailOrderController($rootScope, $scope, $stateParams, $localStorage, $mdDialog, $state, OrderService) {
	$rootScope.title = "ORDER_PAGE";
	OrderService.get({id: $stateParams.id}).$promise.then(function(response){
		$scope.order = response;
		
		$scope.totalPaid = 0;
		
		$scope.order.paymentList.forEach(function(payment){
			if (payment.paid) {
				$scope.totalPaid += payment.percent * $scope.order.netPrice / 100;
			}
		});
	});
	
	$scope.editOrder = function(id) {
		OrderService.get({id: id}).$promise.then(function(response){
			$mdDialog.show({
				clickOutsideToClose: true,
				locals: {order: response},
				controller: 'EditOrderController',
				controllerAs: 'ctrl',
				focusOnOpen: false,
				targetEvent: event,
				templateUrl: "template/order/edit.view.html"
			}).then($scope.reload);
		});
	};
	
	
	$scope.reload = function() {
		$state.reload();
	};
	
	$scope.editPayment = function(id) {
		$mdDialog.show({
			clickOutsideToClose: true,
			locals: {orderId: id},
			controller: 'EditPaymentController',
			controllerAs: 'ctrl',
			focusOnOpen: false,
			targetEvent: event,
			templateUrl: "template/payment/edit.view.html"
		}).then($scope.reload);
	};

}