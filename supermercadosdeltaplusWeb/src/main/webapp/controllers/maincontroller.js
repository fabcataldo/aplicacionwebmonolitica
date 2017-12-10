angular.module('moduloPrincipal').controller('mainController',
		[ '$scope', '$rootScope', 'coreService', MainController ]);

function MainController($scope, $rootScope, coreService) {
	$rootScope.titulo = "";
	$rootScope.recoverUserInfo();
	$rootScope.option='main';
}
