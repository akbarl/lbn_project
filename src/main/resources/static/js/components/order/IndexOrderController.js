app.controller("IndexOrderController", IndexOrderController);

function IndexOrderController($rootScope, $scope, $location, $localStorage, $http, $mdDialog, OrderService, CustomerService, UserService) {
	$rootScope.title = "ORDER_PAGE";
	$scope.selected = [];
	$scope.limitOptions = [5, 10, 15];
	
	$scope.options = {
		rowSelection: true,
		multiSelect: true,
		autoSelect: false,
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
	
	$scope.init = function() {
		$scope.getAllOrder();
	}
	
	$scope.getAllOrder = function () {
		OrderService.query($scope.query).$promise.then(function(response){
			$scope.orders = response;
			console.log($scope.orders);
		});
	};

	$scope.addItem = function (event) {
		$mdDialog.show({
			clickOutsideToClose: true,
			controller: 'AddOrderController',
			controllerAs: 'ctrl',
			focusOnOpen: false,
			targetEvent: event,
			templateUrl: "template/order/add.view.html"
		}).then($scope.getAllOrder);
	};
	
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
			}).then($scope.getAllOrder);
		});
	}
	
	$scope.delete = function(event) {
		console.log($scope.selected);
		$scope.selected.forEach(deleteOrder);
	}
	
	function deleteOrder(order, index) {
		OrderService.remove({id: $scope.selected[index].id}).$promise.then(function(response) {
			$scope.selected.splice(0, 1);
		}).then($scope.getAllOrder);
	}
}