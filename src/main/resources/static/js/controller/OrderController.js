app.controller('OrderController', OrderController);

function OrderController($rootScope, $scope, $location, $localStorage, $http, $mdDialog, OrderService, CustomerService, UserService) {
	
	  $rootScope.title = "ORDER_PAGE";
	  $scope.selected = [];
	  $scope.limitOptions = [5, 10, 15];
	  $scope.paymentSchedules = [1, 2, 3];
	  $scope.maxPaymentList = 3;
	  if($scope.paymentList == undefined) {
		  $scope.paymentList = [{"percent" : 50}];
	  }
	  
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
	
	$scope.getAllOrder = function () {
		OrderService.query($scope.query).$promise.then(function(response){
			$scope.orders = response;
			console.log($scope.orders);
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
		if($scope.order.form.$valid && $scope.checkPaymentList()) {
			$scope.order.createdBy = $localStorage.currentUser.userId;
			$scope.order.status = 1;
			$scope.order.paymentList = $scope.paymentList;
			OrderService.save($scope.order, success)
		}
	}
	
	function success(customer) {
		$mdDialog.hide(customer);
	}
	
	$scope.addPayment = function() {
		if($scope.paymentList.length < 3) {
			$scope.paymentList.push({"percent": 0});
		}
	}
	
	$scope.isAbleToCalculate = function() {
		if($scope.order.netPrice > 0 && $scope.paymentList.length > 0 && $scope.paymentList[0].percent > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	$scope.checkPaymentList = function() {
		var total = 0;
		if($scope.paymentList.length > 0) {
			for(var i = 0 ; i < $scope.paymentList.length ; i ++) {
				total += $scope.paymentList[i].percent;
			}
			
			if(total == 100) {
				return true;
			} else {
				return false;
			}
		}
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
	
	$scope.viewOrder = function() {
		
	}
	
	$scope.editOrder = function(id) {
		OrderService.get({id: id}).$promise.then(function(response){
			$scope.order = response;
			$scope.paymentList = response.paymentList;
			$mdDialog.show({
			      clickOutsideToClose: true,
			      controller: 'OrderController',
			      controllerAs: 'ctrl',
			      focusOnOpen: false,
			      targetEvent: event,
			      templateUrl: "template/order/edit.view.html",
			      scope: $scope,
			      preserveScope: true
			}).then($scope.getAllOrder);
		});
	}
	
	$scope.updateOrder = function() {
		if($scope.order.form.$valid && $scope.checkPaymentList()) {
			OrderService.save($scope.order, success)
		}
	}
}