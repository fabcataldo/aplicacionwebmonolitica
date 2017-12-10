angular.module('moduloPrincipal', ['ui.bootstrap','ngRoute','angularUtils.directives.dirPagination']);
angular.module('moduloPrincipal').constant('URL_BASE', '/supermercadosdeltaplus/');
angular.module('moduloPrincipal').constant('URL_API_BASE','http://localhost:8080/supermercadosdeltaplus/api/v1/');

angular.module('moduloPrincipal').filter('highlight',function($sce) {
			return function(text, phrase) {
				if (phrase && text) {
					text += '';
					text = text.replace(new RegExp('(' + phrase + ')', 'gi'),
							'<span style="background: yellow">$1</span>');
				}

				return $sce.trustAsHtml(text);
			}
		});
		
angular.module('moduloPrincipal').filter('limitString', function() {
	return function(string, limit) {
		if (string && string.length > limit && string.length > limit + 3)
			string = string.substring(0, limit - 3) + '...';
		return string;
	}
});

angular.module('moduloPrincipal').run(['$rootScope','$location','$interval','$log','$uibModal','$http',
			'coreService',
			function($rootScope, $location, $interval, $log, $uibModal, $http,
					coreService) {
				$rootScope.relocate = function(loc) {
					setTimeout(function() { $location.path(loc); },0);
					//$location.path(loc);
				}

				$rootScope.regularCall = true;
				$rootScope.authenticated = false;
				$rootScope.user = {
					name : "",
					password : ""
				};
				$rootScope.loginOpen = false;

				$rootScope.openLoginForm = function(size) {
					if (!$rootScope.loginOpen) {
						$rootScope.loginOpen = true;
						$uibModal.open({
							animation : true,
							backdrop : 'static',
							keyboard : false,
							templateUrl : 'views/loginForm.html',
							controller : 'LoginFormController',
							size : size,
							resolve : {
								user : function() {
									return $rootScope.user
								}
							}
						});
					}
				};
				$rootScope.logout=function() {
					console.log("logout");
					coreService.logout().then(function(){
						$rootScope.authenticated = false;
						coreService.pingAuth();
					});
				}
				$rootScope.recoverUserInfo = function() {
					coreService.pingAuth().then(function(resp) {
						if (resp.status === 200 && resp.data.code == 0) {
							$rootScope.user.name = resp.data.username;
							$rootScope.authenticated = true;
						} else {
							$rootScope.authenticated = false;
							$rootScope.openLoginForm();
						}
						$rootScope.regularCall = true;
					});
				}

				$interval(function() {
					if (!$rootScope.user.name) {
						$rootScope.recoverUserInfo();
					}
				}, 200);
			} ]);