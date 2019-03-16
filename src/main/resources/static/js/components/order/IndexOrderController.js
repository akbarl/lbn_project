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
}