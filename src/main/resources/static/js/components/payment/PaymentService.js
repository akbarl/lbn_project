app.factory('PaymentService', PaymentService);

function PaymentService($resource) {
	return $resource('/payment/:id');
}