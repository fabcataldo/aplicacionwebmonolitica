angular.module('moduloPrincipal').factory('rolesService',['$http','URL_API_BASE',function($http, URL_API_BASE) {
   return {
	   list: function() {
		   return $http.get(URL_API_BASE+"roles");
	   },
	   add: function(e) {
		   return $http.post(URL_API_BASE+"roles",e);
	   },
	   edit: function(e) {
		   return $http.put(URL_API_BASE+"roles",e);
	   },
	   remove: function(id) {
		   return $http.delete(URL_API_BASE+"roles/"+id);
	   } 
   }
}]);
