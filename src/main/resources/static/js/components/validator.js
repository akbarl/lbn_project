function isAbleToCalculate(netPrice, paymentList) {
	if(netPrice > 0 && paymentList.length > 0 && paymentList[0].percent > 0) {
		return true;
	} else {
		return false;
	}
}

function checkPaymentList(paymentList) {
	var total = 0;
	if(paymentList.length > 0) {
		for(var i = 0 ; i < paymentList.length ; i ++) {
			total += paymentList[i].percent;
		}
		
		if(total == 100) {
			return true;
		} else {
			return false;
		}
	}
}

function getFriendlyNameOfOrderStatus(orderStatusId) {
	var PREFIX = "ORDER_STATUS_";
	switch(orderStatusId) {
		case 1:
			return PREFIX + "OPEN"
			break;
		default:
			return PREFIX + "UNKNOWN";
	}
}