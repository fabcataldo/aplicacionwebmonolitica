angular.module('moduloPrincipal')
.controller('ChangePasswordFormController', ['$rootScope', '$scope', '$log', '$uibModal', 'usuariosService', 'URL_API_BASE', '$http', '$routeParams', 'recoverPasswordService', 'coreService', ChangePasswordFormController]);

function ChangePasswordFormController($rootScope, $scope, $log, $uibModal, usuariosService, URL_API_BASE, $http, $routeParams, recoverPasswordService, coreService) {
	$scope.userbd={};
	$scope.modifieduser={};
	$scope.bandera=0;
	
	if($routeParams.token!=undefined){
		var tokenValue = $routeParams.token;
		recoverPasswordService.loginTemporarly(tokenValue).then(
				function(resp){ 
						coreService.pingAuth().then(
								function(resp){   
									  if(resp.status===200 && resp.data.code==0) {
										  $rootScope.user.name=resp.data.username;
										  $rootScope.authenticated=true;
										  $rootScope.regularCall=true;
										  $rootScope.loginOpen = true;
										  console.log($rootScope.user);
									  } else {
										  $log.log(respErr);
									  }
									});
				},
				function(respErr){
					$log.log(respErr);
				}
		);
	}
	
	
	usuariosService.list().then(
			function(respuesta){			
				if(respuesta.status===200){
					for(i=0;i<respuesta.data.length;i++){						
						if(respuesta.data[i].username==$rootScope.user.name){
							$scope.userbd=respuesta.data[i];
							break;
						}
					}
					$scope.modifieduser=$scope.userbd;					
					$scope.bandera=1;
				}
				console.log($scope.modifieduser);
				$scope.modifieduser.password="";				
			},
			function(respuestaerronea){
				$scope.userbd={};
			}
	);
	


	$scope.changePassword = function () {	
		if($scope.bandera==1){
			usuariosService.edit($scope.modifieduser).then(
				function(resp){ 
					if(resp.status===200) {
						$rootScope.authenticated=false;
						$rootScope.regularCall=false;
						$rootScope.loginOpen = false;
						$rootScope.logout();
						$rootScope.openLoginForm();
					}
				},
				function(respErr){
					if(resp.status==500)
						$log.log(respErr);
				}
			);
		}	
	}
}