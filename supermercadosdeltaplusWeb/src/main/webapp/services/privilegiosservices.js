angular.module('moduloPrincipal').factory('privilegiosService',['$http','URL_API_BASE',function($http, URL_API_BASE) {
   return {
	   list: function() {
		   return $http.get(URL_API_BASE+"privilegios");
	   },
	   add: function(e) {
		   return $http.post(URL_API_BASE+"privilegios",e);
	   },
	   edit: function(e) {
		   return $http.put(URL_API_BASE+"privilegios",e);
	   },
	   remove: function(id) {
		   return $http.delete(URL_API_BASE+"privilegios?id="+id);
	   } 
   }
}]);
