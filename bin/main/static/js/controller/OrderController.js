app.controller('OrderController', OrderController);

function OrderController($rootScope, $scope, $location, $localStorage, $http, $mdDialog, OrderService, CustomerService, UserService) {
	
	  $rootScope.title = "ORDER_PAGE";
	  $scope.selected = [];
	  $scope.limitOptions = [5, 10, 15];
	  $scope.paymentSchedules = [1, 2, 3];
	  $scope.maxPaymentList = 3;
	  $scope.paymentList = [{"percent" : "50"}];
	  
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
	
	$scope.getAllOrder = function () {
		OrderService.query($scope.query).$promise.then(function(response){
			$scope.orders = response;
			console.log($scope.orders)
		});
	}
	
	$scope.getAllOrder();
	
	$scope.getAllUser = function () {
		UserService.getAllUser().then(function(response) {
			console.log(response.data);
			$scope.assigneeList = response.data;
		});
	}
	
	$scope.getAllCustomer = function() {
		CustomerService.query($scope.query).$promise.then(function(response){
			$scope.customers = response;
			console.log($scope.customers)
		});
	}
	
	
	$scope.addItem = function (event) {
	    $mdDialog.show({
	      clickOutsideToClose: true,
	      controller: 'OrderController',
	      controllerAs: 'ctrl',
	      focusOnOpen: false,
	      targetEvent: event,
	      templateUrl: "template/order/add.view.html"
	    }).then($scope.getAllOrder);
	};
	
	$scope.addOrder = function() {
		$scope.order.createdBy = $localStorage.currentUser.userId;
		$scope.order.status = 1;
		$scope.order.paymentList = $scope.paymentList;
		console.log($scope.order);
		if($scope.order.form.$valid) {
			OrderService.save($scope.order, success)
		}
	}
	
	function success(customer) {
		$mdDialog.hide(customer);
	}
	
	$scope.addPayment = function() {
		if($scope.paymentList.length < 3) {
			$scope.paymentList.push({"phase": ""});
		}
	}
	
	$scope.removePayment = function() {
		
	}
	
	$scope.cancel = $mdDialog.cancel;
	
	$scope.delete = function(event) {
		console.log($scope.selected);
		$scope.selected.forEach(deleteOrder);
	}
	
	function deleteOrder(order, index) {
		OrderService.remove({id: $scope.selected[index].id}).$promise.then(function(response) {
			$scope.selected.splice(0, 1);
		}).then($scope.getAllOrder);
	}
	
	$scope.getStatus = function(statusId) {
		var PREFIX = "ORDER_STATUS_";
		switch(statusId) {
			case 1:
				return PREFIX + "OPEN"
				break;
			default:
				return PREFIX + "UNKNOWN";
		}
	}
	
	$scope.refresh = function() {
		$scope.getAllOrder();
	}
}