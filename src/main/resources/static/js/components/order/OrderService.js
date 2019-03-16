app.factory('OrderService', OrderService);

function OrderService($resource) {
	return $resource('/order/:id');
}