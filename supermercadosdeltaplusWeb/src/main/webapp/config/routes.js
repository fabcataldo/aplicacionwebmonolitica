angular.module('moduloPrincipal').config(
		function($routeProvider, $locationProvider, $httpProvider) {
			$httpProvider.defaults.withCredentials = true;

			$httpProvider.interceptors.push('APIInterceptor');
			
			$locationProvider.hashPrefix('!');
			$routeProvider.when('/', {
				templateUrl : 'views/main.html',
				controller : 'mainController'
			}).when('/usuarios', {
				templateUrl : 'views/usuarios.html',
				controller : 'usuariosController'
			}).when('/roles', {
				templateUrl : 'views/roles.html',
				controller : 'rolesController'
			}).when('/privilegios', {
				templateUrl : 'views/privilegios.html',
				controller : 'privilegiosController'
			}).when('/cambiarcontrasenia', {
				templateUrl : 'views/changePasswordForm.html',
				controller : 'ChangePasswordFormController',
			}).otherwise({
				redirectTo : '/'
			});
		});

