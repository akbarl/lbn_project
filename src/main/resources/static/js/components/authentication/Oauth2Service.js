app.factory('Oauth2Service', Oauth2Service);

function Oauth2Service($http, $httpParamSerializer, $localStorage) {
	
	var service = {};
	
	service.getToken = getToken;
	
	return service;
	
	function getToken(username, password) {
		var body = {
        	grant_type:"password", 
        	username: username, 
        	password: password
    	};
    	var encoded = btoa("client-lbl-web-ui:secret-lbl-web-ui");
    	
    	var config = {
    		headers: {
                "Authorization": "Basic " + encoded,
                "Content-type": "application/x-www-form-urlencoded; charset=utf-8"
            }
    	};

    	return $http.post('/oauth/token', $httpParamSerializer(body), config).then(handleSuccess, handleError('Error getting token'));
    	
	}
	
	function handleSuccess(res) {
        return res.data;
    }

    function handleError(error) {
        return function () {
            return { success: false, message: error };
        };
    }
}