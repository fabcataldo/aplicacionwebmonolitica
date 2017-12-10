angular.module('moduloPrincipal').config(
		function($routeProvider, $locationProvider, $httpProvider) {
			$httpProvider.defaults.withCredentials = true;

			$httpProvider.interceptors.push('APIInterceptor');
			
			$locationProvider.hashPrefix('!');
			$routeProvider.when('/', {
				templateUrl : 'views/main.html',
				controller : 'mainController'
			}).when('/usuarios', {
				templateUrl : 'views/users.html',
				controller : 'usuariosController'
			}).when('/roles', {
				templateUrl : 'views/roles.html',
				controller : 'rolesController'
			}).when('/privilegios', {
				templateUrl : 'views/privilegios.html',
				controller : 'privilegiosController'
			}).otherwise({
				redirectTo : '/'
			});
		});
