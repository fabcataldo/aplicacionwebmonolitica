angular.module('moduloPrincipal')
.service('APIInterceptor', function($rootScope) {
    var service = this;

    service.responseError = function(response) {
       if(response.status==401) {
    	   $rootScope.openLoginForm();
       }
       return response;
    };
})