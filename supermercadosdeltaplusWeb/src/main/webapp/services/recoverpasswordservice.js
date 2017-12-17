angular.module('moduloPrincipal')
.factory('recoverPasswordService',['$http','URL_BASE',function($http, URL_BASE) {
   return {
	   sendMailToResetPasswordService: function(email) {
		   return $http.get(URL_BASE+"/sendmailtoresetpassword?email="+email);
	   }, 
	   loginTemporarly: function(token){
		   return $http.get(URL_BASE+"login?token="+token);
	   }
   }
}]);
